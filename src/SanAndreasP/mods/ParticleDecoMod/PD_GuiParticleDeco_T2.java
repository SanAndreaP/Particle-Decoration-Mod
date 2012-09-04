// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package SanAndreasP.mods.ParticleDecoMod;
import net.minecraft.src.*;

import java.util.*;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

//import cpw.mods.fml.common.network.FMLNetworkHandler;
//import cpw.mods.fml.common.network.NetworkModHandler;
// Referenced classes of package net.minecraft.src:
//            GuiContainer, EntityPlayer, AchievementList, PlayerController, 
//            GuiContainerCreative, EntityPlayerSP, FontRenderer, RenderEngine, 
//            RenderHelper, RenderManager, GuiButton, GuiAchievements, 
//            GuiStats, PotionEffect, Potion, StatCollector

public class PD_GuiParticleDeco_T2 extends GuiContainer
{

    private float xSize_lo;
    private float ySize_lo;
    private PD_EntityParticleDeco pd;
    private EntityPlayer player;

    public PD_GuiParticleDeco_T2(EntityPlayer entityplayer, PD_EntityParticleDeco ptd)
    {
    	super(new Container(){
    		public boolean canInteractWith(EntityPlayer entityplayer){
    			return true;
    		}
    	});
        allowUserInput = true;
        player = entityplayer;
        pd = ptd;
    }


    public void initGui()
    {
        super.initGui();
//        if(!mc.thePlayer.func_40118_aO().isEmpty())
//        {
//            guiLeft = 160 + (width - xSize - 200) / 2;
//        }
    }

    protected void actionPerformed(GuiButton guibutton)
    {
    	mc.displayGuiScreen(this);
    	switch(guibutton.id) {
    		case 0: // FALL-TROUGH
    		case 2: 
    			player.openGui(PD_ModRegistry.instance, guibutton.id, player.worldObj, pd.entityId, 0, 0);
    			return;
    		
    		case 3: pd.setParticleHeight(pd.getParticleHeight()+1); break;
    		case 4: pd.setParticleHeight(pd.getParticleHeight()-1); break;
    		case 5: pd.setParticleHeight(pd.getParticleHeight()+10); break;
    		case 6: pd.setParticleHeight(pd.getParticleHeight()-10); break;
    		
    		case 7: pd.setParticleHeight(10); break;
    		case 8: pd.setParticleHeight(250); break;
    		case 9: pd.setParticleHeight(100); break;
    		
    		case 10: pd.setDirection(0); break;
    		case 11: pd.setDirection(1); break;
    		case 12: pd.setDirection(2); break;
    		case 13: pd.setDirection(4); break;
    		case 14: pd.setDirection(3); break;
    	}
    	
    	byte[] entityIDByte = getEntityIdAsByteArray();
    	
    	Packet250CustomPayload packet = new Packet250CustomPayload("ParticleDeco", new byte[] {
    			(byte)1, entityIDByte[0], entityIDByte[1], entityIDByte[2], entityIDByte[3], (byte)player.dimension,
    			(byte)pd.getParticleHeight(), (byte)pd.getDirection()
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
    	String s = Integer.toString(pd.getParticleHeight()) + "%";
       	fontRenderer.drawString(s, 89 - fontRenderer.getStringWidth(s) / 2, 23, 0x000000);
       	fontRenderer.drawString(s, 91 - fontRenderer.getStringWidth(s) / 2, 23, 0x000000);
       	fontRenderer.drawString(s, 90 - fontRenderer.getStringWidth(s) / 2, 24, 0x000000);
       	fontRenderer.drawString(s, 90 - fontRenderer.getStringWidth(s) / 2, 22, 0x000000);
       	fontRenderer.drawString(s, 90 - fontRenderer.getStringWidth(s) / 2, 23, 0xFFFFFF);

       	GuiButton tab1 = new PD_GuiButtonTab(0, guiLeft-19, 4+guiTop, 0);
       	GuiButton tab2 = new PD_GuiButtonTab(1, guiLeft-19, 4+guiTop+19, 1);
       	GuiButton tab3 = new PD_GuiButtonTab(2, guiLeft-19, 4+guiTop+38, 2);
       	
       	drawRect(-19, 4+37+20, 0, 4+38+20, 0xFF000000);
       	
       	tab2.enabled = false;

   		controlList.add(tab1);
   		controlList.add(tab2);
   		controlList.add(tab3);
   		
   		GuiButton plusI  = new PD_GuiButtonS(3, guiLeft+104, guiTop+18, 0);
   		GuiButton minusI = new PD_GuiButtonS(4, guiLeft+56, guiTop+18, 1);
   		GuiButton plusX  = new PD_GuiButtonS(5, guiLeft+104, guiTop+27, 2);
   		GuiButton minusX = new PD_GuiButtonS(6, guiLeft+56, guiTop+27, 3);
   		GuiButton min    = new PD_GuiButtonS(7, guiLeft+56, guiTop+38, 4);
   		GuiButton max    = new PD_GuiButtonS(8, guiLeft+104, guiTop+38, 5);
   		GuiButton res    = new PD_GuiButtonS(9, guiLeft+80, guiTop+38, 6);
       	
       	
       	if(pd.getParticleHeight()-10 >= 10)
       		minusX.enabled = true;
       	else
       		minusX.enabled = false;
       	
       	if(pd.getParticleHeight() > 10) {
       		minusI.enabled = true;
       		min.enabled = true;
       	} else {
       		minusI.enabled = false;
   			min.enabled = false;
       	}
       	
       	if(pd.getParticleHeight()+10 <= 250)
       		plusX.enabled = true;
       	else
       		plusX.enabled = false;
       	
       	if(pd.getParticleHeight() < 250) {
       		plusI.enabled = true;
       		max.enabled = true;
       	} else {
       		plusI.enabled = false;
       		max.enabled = false;
       	}
       	
       	if(pd.getParticleHeight() != 100)
       		res.enabled = true;
       	else
       		res.enabled = false;
       	
       	GuiButton up = new PD_GuiButtonS(10, guiLeft+80, guiTop+74, 9);
       	GuiButton lft = new PD_GuiButtonS(11, guiLeft+61, guiTop+74, 13);
       	GuiButton rgt = new PD_GuiButtonS(12, guiLeft+99, guiTop+74, 12);
       	GuiButton bck = new PD_GuiButtonS(13, guiLeft+42, guiTop+74, 10);
       	GuiButton fnt = new PD_GuiButtonS(14, guiLeft+118, guiTop+74, 11);
       	
       	switch(pd.getDirection()) {
       		case 0: up.enabled = false; lft.enabled = rgt.enabled = bck.enabled = fnt.enabled = true; break;
       		case 1: lft.enabled = false; up.enabled = rgt.enabled = bck.enabled = fnt.enabled = true; break;
       		case 2: rgt.enabled = false; up.enabled = lft.enabled = bck.enabled = fnt.enabled = true; break;
       		case 3: fnt.enabled = false; up.enabled = rgt.enabled = bck.enabled = lft.enabled = true; break;
       		case 4: bck.enabled = false; up.enabled = rgt.enabled = lft.enabled = fnt.enabled = true; break;
       	}

       	controlList.add(minusI); // -
       	controlList.add(minusX); // -10
       	controlList.add(plusI); // +
       	controlList.add(plusX); // +10
       	
       	controlList.add(min); // =10
       	controlList.add(res); // =100
       	controlList.add(max); // =250
       	
       	controlList.add(up); // up
       	controlList.add(lft); // up
       	controlList.add(rgt); // up
       	controlList.add(bck); // up
       	controlList.add(fnt); // up
    }

    public void drawScreen(int i, int j, float f)
    {
        super.drawScreen(i, j, f);
        xSize_lo = i;
        ySize_lo = j;
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        int k = mc.renderEngine.getTexture("/SanAndreasP/mods/ParticleDecoMod/images/guipd_T2.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(k);
        int l = guiLeft;
        int i1 = guiTop;
        drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
        GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        GL11.glEnable(2903 /*GL_COLOR_MATERIAL*/);
        GL11.glPushMatrix();
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
    }
}
