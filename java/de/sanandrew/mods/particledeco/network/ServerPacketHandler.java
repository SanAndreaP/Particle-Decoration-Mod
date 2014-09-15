/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.particledeco.network;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import de.sanandrew.mods.particledeco.util.PDM_Main;
import net.minecraft.network.NetHandlerPlayServer;

import java.io.IOException;

public class ServerPacketHandler
{
    @SubscribeEvent
    public void onServerPacket(FMLNetworkEvent.ServerCustomPacketEvent event) throws IOException {
        NetHandlerPlayServer netHandlerPlayServer = (NetHandlerPlayServer)event.handler;

        if( event.packet.channel().equals(PDM_Main.MOD_CHANNEL) ) {
            PacketProcessor.processPacket(event.packet.payload(), event.packet.getTarget(), netHandlerPlayServer);
        }
    }
}
