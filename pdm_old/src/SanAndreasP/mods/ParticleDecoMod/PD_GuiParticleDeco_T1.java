// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package SanAndreasP.mods.ParticleDecoMod;
import net.minecraft.src.*;

import java.util.*;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

// Referenced classes of package net.minecraft.src:
//            GuiContainer, EntityPlayer, AchievementList, PlayerController, 
//            GuiContainerCreative, EntityPlayerSP, FontRenderer, RenderEngine, 
//            RenderHelper, RenderManager, GuiButton, GuiAchievements, 
//            GuiStats, PotionEffect, Potion, StatCollector

public class PD_GuiParticleDeco_T1 extends GuiContainer
{

    private float xSize_lo;
    private float ySize_lo;
    private PD_EntityParticleDeco pd;
    private EntityPlayer player;

    public PD_GuiParticleDeco_T1(EntityPlayer entityplayer, PD_EntityParticleDeco ptd)
    {
        super(PD_ModRegistry.currItemContainer.containsKey(ptd.entityId) ? PD_ModRegistry.currItemContainer.get(ptd.entityId) : new PD_ContainerParticleDeco(entityplayer.inventory, ptd));
		PD_ModRegistry.currItemContainer.clear();
        player = entityplayer;
        allowUserInput = true;
        pd = ptd;
    }


    public void initGui()
    {
        super.initGui();
    }

    protected void actionPerformed(GuiButton guibutton)
    {
    	this.mc.displayGuiScreen(this);
		PD_ModRegistry.currItemContainer.put(pd.entityId, (PD_ContainerParticleDeco) this.inventorySlots);
		player.openGui(PD_ModRegistry.instance, guibutton.id, player.worldObj, pd.entityId, 0, 0);
    }

    protected void drawGuiContainerForegroundLayer()
    {
       	GuiButton tab1 = new PD_GuiButtonTab(0, guiLeft-19, 4+guiTop, 0);
       	GuiButton tab2 = new PD_GuiButtonTab(1, guiLeft-19, 4+guiTop+19, 1);
       	GuiButton tab3 = new PD_GuiButtonTab(2, guiLeft-19, 4+guiTop+38, 2);
       	
       	drawRect(-19, 4+37+20, 0, 4+38+20, 0xFF000000);
       	
       	tab1.enabled = false;

   		controlList.add(tab1);
   		controlList.add(tab2);
   		controlList.add(tab3);
       	
    }

    public void drawScreen(int i, int j, float f)
    {
        super.drawScreen(i, j, f);
        xSize_lo = i;
        ySize_lo = j;
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        int k = mc.renderEngine.getTexture("/SanAndreasP/mods/ParticleDecoMod/images/guipd_T1.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(k);
        int l = guiLeft;
        int i1 = guiTop;
        drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
       	if(pd.getDataWatcher().getWatchableObjectShort(10) == 3) {
            drawTexturedModalRect(l+46, i1+16, 0, 166, 84, 18);
       	}
       	if(!pd.canBeColorized()) {
            drawTexturedModalRect(l+46, i1+51, 0, 166, 84, 18);
       	}
        GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        GL11.glEnable(2903 /*GL_COLOR_MATERIAL*/);
        GL11.glPushMatrix();
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
    }
}
