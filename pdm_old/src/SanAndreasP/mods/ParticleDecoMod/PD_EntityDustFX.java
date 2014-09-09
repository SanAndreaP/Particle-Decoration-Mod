// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package SanAndreasP.mods.ParticleDecoMod;
import net.minecraft.src.*;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;


// Referenced classes of package net.minecraft.src:
//            EntityFX, World, Tessellator

public class PD_EntityDustFX extends PD_EntityParticlesFX
{

    public PD_EntityDustFX(World world, double d, double d1, double d2, double d3, double d4, double d5, float f, float f1, float f2, boolean b, boolean b1, int i, int j) {
		super(world, d, d1, d2, d3, d4, d5, f, f1, f2, b, b1, i, j);
		// TODO Auto-generated constructor stub
	}

	public void initParticle(World world, double d, double d1, double d2, double d3, double d4, double d5, float f, float f1, float f2, float f3) {
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
        particleRed = particleGreen = particleBlue = f5 * f1 * f4;
        particleGreen = f5 * f2 * f4;
        particleBlue = f5 * f3 * f4;
        particleScale *= 0.75F;
        particleScale *= f;
        reddustParticleScale = particleScale;
//        particleMaxAge = (int)(8D / (Math.random() * 0.80000000000000004D + 0.20000000000000001D));
        particleMaxAge = 40;
        particleMaxAge *= f;
    }

    public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
    {
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
        super.renderParticle(tessellator, f, f1, f2, f3, f4, f5);
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

        setParticleTextureIndex(7 - (particleAge * 8) / particleMaxAge);
        moveParticle();
    }

    float reddustParticleScale;
}
