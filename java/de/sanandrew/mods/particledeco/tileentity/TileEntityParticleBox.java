/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.particledeco.tileentity;

import de.sanandrew.core.manpack.mod.client.particle.EntityParticle;
import de.sanandrew.core.manpack.mod.client.particle.SAPEffectRenderer;
import de.sanandrew.mods.particledeco.client.particle.EntityDustFX;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityParticleBox
    extends TileEntity
{
    private int ticksExisted;
    private float[] colors = new float[] {1.0F, 1.0F, 1.0F};

    public TileEntityParticleBox() {
        colors[0] = 0.0F;
        colors[2] = 0.0F;
//        colors[1] = 0.0F;
    }

    public boolean canUpdate() {
        return true;
    }

    @Override
    public void updateEntity() {
        this.ticksExisted++;

        if( this.worldObj.isRemote && this.ticksExisted % 2 == 0 ) {
            ForgeDirection dir = ForgeDirection.getOrientation(this.getBlockMetadata());

            float motionX = 0.075F * dir.offsetX;
            float motionY = 0.075F * dir.offsetY;
            float motionZ = 0.075F * dir.offsetZ;

            EntityParticle particle = new EntityDustFX(this.worldObj, this.xCoord + 0.5F, this.yCoord + 0.5F, this.zCoord + 0.5F, 10.0F, motionX, motionY, motionZ);
            particle.setParticleColor(this.colors[0], this.colors[1], this.colors[2]);
            particle.setBrightness(0xF0);
            SAPEffectRenderer.INSTANCE.addEffect(particle);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setFloat("particleRed", this.colors[0]);
        nbt.setFloat("particleGreen", this.colors[1]);
        nbt.setFloat("particleBlue", this.colors[2]);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.colors[0] = nbt.getFloat("particleRed");
        this.colors[1] = nbt.getFloat("particleGreen");
        this.colors[2] = nbt.getFloat("particleBlue");
    }
}
