// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package SanAndreasP.mods.ParticleDecoMod;
import net.minecraft.src.*;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

// Referenced classes of package net.minecraft.src:
//            Gui, RenderEngine

public class PD_GuiButtonS extends GuiButton
{
    protected int type;

    public PD_GuiButtonS(int i, int j, int k, int l)
    {
        this(i, j, k, 19, 9, "");
        type = l;
    }

    public PD_GuiButtonS(int i, int j, int k, int l, int i1, String s)
    {
        super(i, j, k, l, i1, s);
    }

    public void drawButton(Minecraft minecraft, int i, int j)
    {
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, minecraft.renderEngine.getTexture("/SanAndreasP/mods/ParticleDecoMod/images/buttonspd.png"));
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        boolean flag = i >= xPosition && j >= yPosition && i < xPosition + width && j < yPosition + height;
        int k = getHoverState(flag);
        mouseDragged(minecraft, i, j);
        drawTexturedModalRect(xPosition, yPosition, 57+k*19, type*9, width, height);
    }
}
