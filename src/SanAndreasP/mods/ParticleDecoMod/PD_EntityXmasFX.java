// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package SanAndreasP.mods.ParticleDecoMod;
import net.minecraft.src.*;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;


// Referenced classes of package net.minecraft.src:
//            EntityFX, World, Tessellator

public class PD_EntityXmasFX extends PD_EntityParticlesFX
{

    public PD_EntityXmasFX(World world, double d, double d1, double d2, double d3, double d4, double d5, boolean b, boolean b1, int i, int j)
    {
        super(world, d, d1, d2, d3, d4, d5, 0, 0, 0, b, b1, i, j);
    }

    public void initParticle(World world, double d, double d1, double d2, double d3, double d4, double d5, float f, float f1, float f2, float f3)
    {
        if(d3 < 0.001D)
        	motionX *= 0.10000000149011612D;
        else 
            motionX = d3 * 0.20000000298023224D + (double)((float)(Math.random() * 2D - 1.0D) * 0.02F);

        if(d4 < 0.001D)
            motionY *= 0.10000000149011612D;
        else
            motionY = d4 * 0.20000000298023224D + (double)((float)(Math.random() * 2D - 1.0D) * 0.02F);
        
        if(d5 < 0.001D)
            motionZ *= 0.10000000149011612D;
        else
            motionZ = d5 * 0.20000000298023224D + (double)((float)(Math.random() * 2D - 1.0D) * 0.02F);
        
        float f4 = (float)Math.random()* 0.4F + 0.6F;
        float f5 = ((float)(Math.random() * 0.20000000298023224D) + 0.8F);
        particleRed = particleGreen = particleBlue = 1.0F;
        particleScale *= 0.75F;
        particleScale *= f;
        reddustParticleScale = particleScale;
        particleMaxAge = (int)(8D / (Math.random() * 0.80000000000000004D + 0.20000000000000001D));
        particleMaxAge *= f;
        setParticleTextureIndex(world.rand.nextInt(2));
    }

    public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        Tessellator tessellator1 = new Tessellator();
        tessellator1.startDrawingQuads();
        tessellator1.setBrightness(getBrightnessForRender(f));
        
        float f6 = (((float)particleAge + f) / (float)particleMaxAge) * 32F;
        if(f6 < 0.0F)
        {
            f6 = 0.0F;
        }
        if(f6 > 1.0F)
        {
            f6 = 1.0F;
        }
        particleScale = reddustParticleScale * f6;
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, ModLoader.getMinecraftInstance().renderEngine.getTexture("/SanAndreasP/mods/ParticleDecoMod/images/PDparticles.png"));
        float f0 = (float)(getParticleTextureIndex() % 16) / 16F;
        float f7 = f0 + 0.0624375F;
        float f8 = (float)(getParticleTextureIndex() / 16) / 16F;
        float f9 = f8 + 0.0624375F;
        float f10 = 0.1F * particleScale;
        float f11 = (float)((prevPosX + (posX - prevPosX) * (double)f) - interpPosX);
        float f12 = (float)((prevPosY + (posY - prevPosY) * (double)f) - interpPosY);
        float f13 = (float)((prevPosZ + (posZ - prevPosZ) * (double)f) - interpPosZ);
        float f14 = 1.0F;
        tessellator1.setColorOpaque_F(particleRed * f14, particleGreen * f14, particleBlue * f14);
        tessellator1.addVertexWithUV(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5 * f10, f7, f9);
        tessellator1.addVertexWithUV((f11 - f1 * f10) + f4 * f10, f12 + f2 * f10, (f13 - f3 * f10) + f5 * f10, f7, f8);
        tessellator1.addVertexWithUV(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5 * f10, f0, f8);
        tessellator1.addVertexWithUV((f11 + f1 * f10) - f4 * f10, f12 - f2 * f10, (f13 + f3 * f10) - f5 * f10, f0, f9);
        
        tessellator1.draw();
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, ModLoader.getMinecraftInstance().renderEngine.getTexture("/particles.png"));
    }
    
    public void onUpdate()
    {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        if(particleAge++ >= particleMaxAge)
        {
            setDead();
        }
        moveParticle();
    }

    float reddustParticleScale;
}
