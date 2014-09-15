/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.particledeco.tileentity;

import de.sanandrew.core.manpack.mod.client.particle.EntityParticle;
import de.sanandrew.core.manpack.mod.client.particle.SAPEffectRenderer;
import de.sanandrew.core.manpack.util.SAPUtils;
import de.sanandrew.mods.particledeco.client.particle.EntityDustFX;
import de.sanandrew.mods.particledeco.util.ParticleBoxData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityParticleBox
    extends TileEntity
{
    private int ticksExisted;
    public ParticleBoxData particleData = new ParticleBoxData();
    public int prevColor = this.particleData.particleColor;
    public float[] particleColorSplit = SAPUtils.getRgbaFromColorInt(this.particleData.particleColor).getColorFloatArray();

    public TileEntityParticleBox() {
    }

    public boolean canUpdate() {
        return true;
    }

    @Override
    public void updateEntity() {
        this.ticksExisted++;

        if( this.worldObj.isRemote && this.ticksExisted % 2 == 0 ) {
            if( this.prevColor != this.particleData.particleColor ) {
                this.particleColorSplit = SAPUtils.getRgbaFromColorInt(this.particleData.particleColor).getColorFloatArray();
                this.prevColor = this.particleData.particleColor;
            }

            ForgeDirection dir = ForgeDirection.getOrientation(this.getBlockMetadata());

            float motionX = 0.075F * dir.offsetX;
            float motionY = 0.075F * dir.offsetY;
            float motionZ = 0.075F * dir.offsetZ;

            EntityParticle particle = new EntityDustFX(this.worldObj, this.xCoord + 0.5F, this.yCoord + 0.5F, this.zCoord + 0.5F, this.particleData.particleHeight,
                                                       this.particleData.particleSpeed, motionX, motionY, motionZ);
            particle.setParticleColorRNG(this.particleColorSplit[0], this.particleColorSplit[1], this.particleColorSplit[2]);
            particle.setBrightness(0xF0);
            SAPEffectRenderer.INSTANCE.addEffect(particle);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        this.particleData.writeDataToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.particleData = ParticleBoxData.getDataFromNbt(nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.particleData = ParticleBoxData.getDataFromNbt(pkt.func_148857_g());
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.particleData.writeDataToNBT(nbt);

        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbt);
    }
}
