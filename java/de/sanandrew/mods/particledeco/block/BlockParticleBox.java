/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP, SilverChiren and CliffracerX
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.particledeco.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.sanandrew.mods.particledeco.client.util.ClientProxy;
import de.sanandrew.mods.particledeco.util.PDM_Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockParticleBox
    extends Block
{
    @SideOnly(Side.CLIENT)
    public IIcon[] icons;

    public BlockParticleBox() {
        super(Material.rock);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
        ForgeDirection dir = ForgeDirection.getOrientation(blockAccess.getBlockMetadata(x, y, z) & 7);
        float[] boxOff = getBoxOffset(dir);

        this.setBlockBounds(boxOff[0], boxOff[1], boxOff[2], boxOff[3], boxOff[4], boxOff[5]);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        ForgeDirection dir = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z) & 7);
        float[] boxOff = getBoxOffset(dir);

        return AxisAlignedBB.getBoundingBox(x + boxOff[0], y + boxOff[1], z + boxOff[2], x + boxOff[3], y + boxOff[4], z + boxOff[5]);
    }

    public static float[] getBoxOffset(ForgeDirection dir) {
        return new float[] {
            0.25F - (0.25F * dir.offsetX),
            0.25F - (0.25F * dir.offsetY),
            0.25F - (0.25F * dir.offsetZ),
            0.75F - (0.25F * dir.offsetX),
            0.75F - (0.25F * dir.offsetY),
            0.75F - (0.25F * dir.offsetZ),
        };
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        ForgeDirection fSide = ForgeDirection.getOrientation(side);
        ForgeDirection dir = ForgeDirection.getOrientation(meta & 7);

        if( dir == ForgeDirection.UP ) {
            if( fSide == ForgeDirection.UP ) {
                return this.icons[1];
            } else {
                return this.icons[3];
            }
        } else if( dir == ForgeDirection.DOWN ) {
            if( fSide == ForgeDirection.DOWN ) {
                return this.icons[1];
            } else {
                return this.icons[2];
            }
        } else if( dir == ForgeDirection.NORTH ) {
            if( fSide == ForgeDirection.NORTH ) {
                return this.icons[1];
            } else if( fSide == ForgeDirection.EAST || fSide == ForgeDirection.WEST ) {
                return this.icons[5];
            } else {
                return this.icons[3];
            }
        } else if( dir == ForgeDirection.EAST ) {
            if( fSide == ForgeDirection.EAST ) {
                return this.icons[1];
            } else {
                return this.icons[4];
            }
        } else if( dir == ForgeDirection.SOUTH ) {
            if( fSide == ForgeDirection.SOUTH ) {
                return this.icons[1];
            } else if( fSide == ForgeDirection.EAST || fSide == ForgeDirection.WEST ) {
                return this.icons[4];
            } else {
                return this.icons[2];
            }
        } else if( dir == ForgeDirection.WEST ) {
            if( fSide == ForgeDirection.WEST ) {
                return this.icons[1];
            } else {
                return this.icons[5];
            }
        }

        return this.icons[0];
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float offX, float offY, float offZ, int meta) {
        return side & 7;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderType() {
        return ClientProxy.particleBoxRenderId;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        return this.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.icons = new IIcon[6];
        this.icons[0] = iconRegister.registerIcon(PDM_Main.MOD_ID + ":pd_box_bottom");
        this.icons[1] = iconRegister.registerIcon(PDM_Main.MOD_ID + ":pd_box_top");
        this.icons[2] = iconRegister.registerIcon(PDM_Main.MOD_ID + ":pd_box_up");
        this.icons[3] = iconRegister.registerIcon(PDM_Main.MOD_ID + ":pd_box_down");
        this.icons[4] = iconRegister.registerIcon(PDM_Main.MOD_ID + ":pd_box_left");
        this.icons[5] = iconRegister.registerIcon(PDM_Main.MOD_ID + ":pd_box_right");

        this.blockIcon = this.icons[0];
    }

    @Override
    public void setBlockBoundsForItemRender() {
        float[] boxOff = getBoxOffset(ForgeDirection.UP);

        this.setBlockBounds(boxOff[0], boxOff[1], boxOff[2], boxOff[3], boxOff[4], boxOff[5]);
    }
}
