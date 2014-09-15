/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP, SilverChiren and CliffracerX
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.particledeco.util;

import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import net.minecraft.nbt.NBTTagCompound;

import java.io.IOException;

public final class ParticleBoxData
{
    public int particleColor = 0xFFFFFF;
    public float particleHeight = 5.0F;
    public float particleSpeed = 0.15F;
    public EnumParticleType particleType = EnumParticleType.DUST;
    public EnumParticleSpread particleSpread = EnumParticleSpread.STRAIGHT_UP;

    public ParticleBoxData() {
    }

    public void writeDataToByteBuf(ByteBufOutputStream stream) throws IOException {
        stream.writeInt(this.particleColor);
        stream.writeFloat(this.particleHeight);
        stream.writeFloat(this.particleSpeed);
        stream.writeInt(this.particleType.ordinal());
        stream.writeInt(this.particleSpread.ordinal());
    }

    public void writeDataToNBT(NBTTagCompound nbt) {
        NBTTagCompound dataTag = new NBTTagCompound();
        dataTag.setInteger("color", this.particleColor);
        dataTag.setFloat("height", this.particleHeight);
        dataTag.setFloat("speed", this.particleSpeed);
        dataTag.setInteger("type", this.particleType.ordinal());
        dataTag.setInteger("spread", this.particleSpread.ordinal());

        nbt.setTag("particleBoxData", dataTag);
    }

    public static ParticleBoxData getNewData(int color, float height, float speed, EnumParticleType type, EnumParticleSpread spread) {
        ParticleBoxData data = new ParticleBoxData();

        data.particleColor = color;
        data.particleHeight = height;
        data.particleSpeed = speed;
        data.particleType = type;
        data.particleSpread = spread;

        return data;
    }

    public static ParticleBoxData getDataFromByteBuf(ByteBufInputStream stream) throws IOException {
        return getNewData(stream.readInt(), stream.readFloat(), stream.readFloat(), EnumParticleType.values[stream.readInt()],
                          EnumParticleSpread.values[stream.readInt()]);
    }

    public static ParticleBoxData getDataFromNbt(NBTTagCompound nbt) {
        if( !nbt.hasKey("particleBoxData") ) {
            return new ParticleBoxData();
        }

        NBTTagCompound dataTag = nbt.getCompoundTag("particleBoxData");

        return getNewData(dataTag.getInteger("color"), dataTag.getFloat("height"), dataTag.getFloat("speed"), EnumParticleType.values[dataTag.getInteger("type")],
                          EnumParticleSpread.values[dataTag.getInteger("spread")]
        );
    }

    public static enum EnumParticleType
    {
        DUST;

        public static EnumParticleType[] values = values();
    }

    public static enum EnumParticleSpread
    {
        STRAIGHT_UP;

        public static EnumParticleSpread[] values = values();
    }
}
