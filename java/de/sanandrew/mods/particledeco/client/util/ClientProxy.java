/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.particledeco.client.util;

import cpw.mods.fml.client.registry.RenderingRegistry;
import de.sanandrew.mods.claysoldiers.util.RegistryEntities;
import de.sanandrew.mods.particledeco.client.render.block.RenderParticleBox;
import de.sanandrew.mods.particledeco.util.CommonProxy;

public class ClientProxy
    extends CommonProxy
{
    public static int particleBoxRenderId;

    @Override
    public void modInit() {
        super.modInit();

        RegistryEntities.registerRenderers();

        particleBoxRenderId = RenderingRegistry.getNextAvailableRenderId();

        RenderingRegistry.registerBlockHandler(particleBoxRenderId, new RenderParticleBox());
    }
}
