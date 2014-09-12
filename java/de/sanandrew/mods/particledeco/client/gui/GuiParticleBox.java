/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP, SilverChiren and CliffracerX
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.particledeco.client.gui;

import de.sanandrew.core.manpack.mod.client.gui.GuiColorPickerCtrl;
import net.minecraft.client.gui.GuiScreen;

public class GuiParticleBox
    extends GuiScreen
{
    private GuiColorPickerCtrl colorPicker;

    @Override
    public void initGui() {
        super.initGui();

        this.colorPicker = new GuiColorPickerCtrl(30, 30);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partTicks) {
        this.drawDefaultBackground();

        this.colorPicker.drawControl();

        super.drawScreen(mouseX, mouseY, partTicks);
    }

    @Override
    protected void mouseClicked(int x, int y, int key) {
        colorPicker.mouseClicked(x, y, key);

        super.mouseClicked(x, y, key);
    }
}
