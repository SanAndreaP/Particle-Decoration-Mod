/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP, SilverChiren and CliffracerX
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.particledeco.client.gui;

import de.sanandrew.core.manpack.mod.client.gui.GuiColorPickerCtrl;
import de.sanandrew.core.manpack.util.javatuples.Pair;
import de.sanandrew.mods.particledeco.network.PacketProcessor;
import de.sanandrew.mods.particledeco.tileentity.TileEntityParticleBox;
import de.sanandrew.mods.particledeco.util.PDM_Main;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.ResourceLocation;

public class GuiParticleBox
    extends GuiScreen
{
    private final TileEntityParticleBox box;
    private int xSize = 256;
    private int ySize = 256;
    private int guiLeft;
    private int guiTop;

    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(PDM_Main.MOD_ID, "textures/gui/particlebox_color.png");

    private GuiColorPickerCtrl colorPicker;

    public GuiParticleBox(TileEntityParticleBox particleBox) {
        this.box = particleBox;
    }

    @Override
    public void initGui() {
        super.initGui();

        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

        this.colorPicker = new GuiColorPickerCtrl(this.guiLeft + 11, this.guiTop + 28);

        this.colorPicker.setHsbFromRgb(this.box.particleData.particleColor);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partTicks) {
        this.drawDefaultBackground();

        this.mc.renderEngine.bindTexture(GUI_TEXTURE);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        this.colorPicker.drawControl();

        super.drawScreen(mouseX, mouseY, partTicks);
    }

    @Override
    protected void mouseClicked(int x, int y, int key) {
        this.colorPicker.mouseClicked(x, y, key);

        this.box.particleData.particleColor = this.colorPicker.getOutputColor();

        super.mouseClicked(x, y, key);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();

        PacketProcessor.sendToServer(PacketProcessor.PKG_PARTICLEBOX_SETTINGS, Pair.with(new ChunkCoordinates(this.box.xCoord, this.box.yCoord, this.box.zCoord),
                                                                                         this.box.particleData));
    }
}
