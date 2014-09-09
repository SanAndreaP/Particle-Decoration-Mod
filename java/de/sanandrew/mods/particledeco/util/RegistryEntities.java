/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.particledeco.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public final class RegistryEntities
{
    @SideOnly(Side.CLIENT)
    public static void registerRenderers() {
//        RenderingRegistry.registerEntityRenderingHandler(EntityParticleBox.class, new RenderParticleBox());
    }

    public static void registerEntities(Object mod) {
        int entityId = 0;

//        EntityRegistry.registerModEntity(EntityParticleBox.class, "particleBox", entityId, mod, 64, 20, false);
    }
}
