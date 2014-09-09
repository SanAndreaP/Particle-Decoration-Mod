/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.particledeco.client.util;

import de.sanandrew.mods.particledeco.util.PDM_Main;
import net.minecraft.util.ResourceLocation;

public enum Textures
{
    PARTICLEDECO_BOX("textures/entity/pd_base.png");

    public final ResourceLocation res;

    private Textures(String path) {
        this.res = new ResourceLocation(PDM_Main.MOD_ID, path);
    }
}
