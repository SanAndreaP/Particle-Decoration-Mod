/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP, SilverChiren and CliffracerX
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.particledeco.network.packet;

import de.sanandrew.core.manpack.util.javatuples.Pair;
import de.sanandrew.core.manpack.util.javatuples.Tuple;
import de.sanandrew.mods.particledeco.network.IPacket;
import de.sanandrew.mods.particledeco.tileentity.TileEntityParticleBox;
import de.sanandrew.mods.particledeco.util.ParticleBoxData;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;

import java.io.IOException;

public class PacketParticleBoxSettings
        implements IPacket
{
    @Override
    public void process(ByteBufInputStream stream, ByteBuf rawData, INetHandler handler) throws IOException {
        if( handler instanceof NetHandlerPlayServer ) {
            TileEntity te = ((NetHandlerPlayServer) handler).playerEntity.worldObj.getTileEntity(stream.readInt(), stream.readInt(), stream.readInt());
            if( te instanceof TileEntityParticleBox ) {
                ((TileEntityParticleBox) te).particleData = ParticleBoxData.getDataFromByteBuf(stream);
            }
        }
    }

    @Override
    public void writeData(ByteBufOutputStream stream, Tuple dataTuple) throws IOException {
        @SuppressWarnings("unchecked")
        Pair<ChunkCoordinates, ParticleBoxData> data = (Pair) dataTuple;

        stream.writeInt(data.getValue0().posX);
        stream.writeInt(data.getValue0().posY);
        stream.writeInt(data.getValue0().posZ);

        data.getValue1().writeDataToByteBuf(stream);
    }
}
