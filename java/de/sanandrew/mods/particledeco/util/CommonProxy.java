/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.particledeco.util;

import de.sanandrew.mods.particledeco.network.ServerPacketHandler;

public class CommonProxy
{
    public void modInit() {
        PDM_Main.channel.register(new ServerPacketHandler());
    }
}
