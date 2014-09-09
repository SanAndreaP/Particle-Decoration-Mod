/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.particledeco.util;

import cpw.mods.fml.common.registry.GameRegistry;
import de.sanandrew.core.manpack.util.SAPUtils;
import de.sanandrew.mods.particledeco.block.BlockParticleBox;
import de.sanandrew.mods.particledeco.tileentity.TileEntityParticleBox;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public final class RegistryBlocks
{
    public static Block particleBox;

    public static void initialize() {
        particleBox = new BlockParticleBox();

        particleBox.setCreativeTab(CreativeTabs.tabDecorations);
        particleBox.setBlockName(PDM_Main.MOD_ID + ":particlebox");

        GameRegistry.registerTileEntity(TileEntityParticleBox.class, "tileParticleBox");

        SAPUtils.registerBlocks(particleBox);
    }
}
