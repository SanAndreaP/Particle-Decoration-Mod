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

public class PD_GuiParticleDeco_T3 extends GuiContainer
{

    private float xSize_lo;
    private float ySize_lo;
    private PD_EntityParticleDeco pd;
    private EntityPlayer player;

    public PD_GuiParticleDeco_T3(EntityPlayer entityplayer, PD_EntityParticleDeco ptd)
    {
    	super(new Container() {
    		public boolean canInteractWith(EntityPlayer entityplayer)
    		{
    			return true;
    		}
    	});
        allowUserInput = true;
        player = entityplayer;
        pd = ptd;
    }

    protected void actionPerformed(GuiButton guibutton)
    {
    	mc.displayGuiScreen(this);
    	switch(guibutton.id) {
    		case 0: //FALL-THROUGH;
    		case 1: 
    			player.openGui(PD_ModRegistry.instance, guibutton.id, player.worldObj, pd.entityId, 0, 0);
    			return;
    		
    		case 3: pd.setRSActive(true); break;
    		case 4: pd.setRSActive(false); break;
    		case 5: pd.setActive(true); break;
    		case 6: pd.setActive(false); break;
    		case 7: pd.setCamouflaged(true); break;
    		case 8: pd.setCamouflaged(false); break;
    		case 9: pd.setGlowItself(true); break;
    		case 10: pd.setGlowItself(false); break;
    		case 11: pd.setParticleNC(true); break;
    		case 12: pd.setParticleNC(false); break;
    		case 13: pd.setIsFireFXSetFire(true); break;
    		case 14: pd.setIsFireFXSetFire(false); break;
    	}
    	
    	byte[] entityIDByte = getEntityIdAsByteArray();
    	
    	Packet250CustomPayload packet = new Packet250CustomPayload("ParticleDeco", new byte[] {
    			(byte)2, entityIDByte[0], entityIDByte[1], entityIDByte[2], entityIDByte[3], (byte)player.dimension,
    			(byte)(pd.isActive() ? 1 : 0), (byte)(pd.isRSActive() ? 1 : 0), (byte)(pd.hasParticleNC() ? 1 : 0),
    			(byte)(pd.isCamouflaged() ? 1 : 0), (byte)(pd.isGlowItself() ? 1 : 0), (byte)(pd.isFireFXSetFire() ? 1 : 0)
    	});
    	
    	PD_ModRegistry.proxy.sendToServer(packet);
    }

    private byte[] getEntityIdAsByteArray() {
    	return new byte[] {
    			(byte)(pd.entityId & 255),
    			(byte)(((pd.entityId) >> 8) & 255),
    			(byte)(((pd.entityId) >> 16) & 255),
    			(byte)(((pd.entityId) >> 24) & 255),
    	};
    }

    protected void drawGuiContainerForegroundLayer()
    {
       	GuiButton tab1 = new PD_GuiButtonTab(0, guiLeft-19, 4+guiTop, 0);
       	GuiButton tab2 = new PD_GuiButtonTab(1, guiLeft-19, 4+guiTop+19, 1);
       	GuiButton tab3 = new PD_GuiButtonTab(2, guiLeft-19, 4+guiTop+38, 2);
       	
       	drawRect(-19, 4+37+20, 0, 4+38+20, 0xFF000000);
       	
       	tab3.enabled = false;

   		controlList.add(tab1);
   		controlList.add(tab2);
   		controlList.add(tab3);
   		
   		GuiButton on_I = new PD_GuiButtonS(3, guiLeft+125, guiTop+17, 7);
   		GuiButton off_I = new PD_GuiButtonS(4, guiLeft+144, guiTop+17, 8); 
   		GuiButton on_II = new PD_GuiButtonS(5, guiLeft+125, guiTop+17+15, 7);
   		GuiButton off_II = new PD_GuiButtonS(6, guiLeft+144, guiTop+17+15, 8); 
   		GuiButton on_III = new PD_GuiButtonS(7, guiLeft+125, guiTop+17+30, 7);
   		GuiButton off_III = new PD_GuiButtonS(8, guiLeft+144, guiTop+17+30, 8); 
   		GuiButton on_IV = new PD_GuiButtonS(9, guiLeft+125, guiTop+17+45, 7);
   		GuiButton off_IV = new PD_GuiButtonS(10, guiLeft+144, guiTop+17+45, 8);
   		GuiButton on_V = new PD_GuiButtonS(11, guiLeft+125, guiTop+17+77, 7);
   		GuiButton off_V = new PD_GuiButtonS(12, guiLeft+144, guiTop+17+77, 8);
   		GuiButton on_VI = new PD_GuiButtonS(13, guiLeft+125, guiTop+17+95, 7);
   		GuiButton off_VI = new PD_GuiButtonS(14, guiLeft+144, guiTop+17+95, 8);
       	
       	
   		on_I.enabled = !pd.isRSActive();
   		off_I.enabled = pd.isRSActive();
   		on_II.enabled = !pd.isRSActive() && !pd.isActive();
   		off_II.enabled = !pd.isRSActive() && pd.isActive();
   		on_III.enabled = !pd.isCamouflaged();
   		off_III.enabled = pd.isCamouflaged();
   		on_IV.enabled = pd.isGlow() && !pd.isGlowItself();
   		off_IV.enabled = pd.isGlow() && pd.isGlowItself();
   		on_V.enabled = !pd.hasParticleNC();
   		off_V.enabled = pd.hasParticleNC();
   		on_VI.enabled = !pd.isFireFXSetFire();
   		off_VI.enabled = pd.isFireFXSetFire();
   		

   		controlList.add(on_I);
   		controlList.add(off_I);
   		controlList.add(on_II);
   		controlList.add(off_II);
   		controlList.add(on_III);
   		controlList.add(off_III);
   		controlList.add(on_IV);
   		controlList.add(off_IV);
   		controlList.add(on_V);
   		controlList.add(off_V);
   		if(pd.getType() == 3) {
   			controlList.add(on_VI);
   			controlList.add(off_VI);
   		}
    }

    public void drawScreen(int i, int j, float f)
    {
//    	mc.displayGuiScreen(this);
        super.drawScreen(i, j, f);
        xSize_lo = i;
        ySize_lo = j;
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        int k = mc.renderEngine.getTexture("/SanAndreasP/mods/ParticleDecoMod/images/guipd_T3.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(k);
        int l = guiLeft;
        int i1 = guiTop;
        drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
        if(pd.getType() != 3) drawTexturedModalRect(l+20, i1+110, 10, 125, 106, 15);
        GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        GL11.glEnable(2903 /*GL_COLOR_MATERIAL*/);
        GL11.glPushMatrix();
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
    }
}
