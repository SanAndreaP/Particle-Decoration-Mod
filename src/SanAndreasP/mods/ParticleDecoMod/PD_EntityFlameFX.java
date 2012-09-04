// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package SanAndreasP.mods.ParticleDecoMod;
import net.minecraft.src.*;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            EntityFX, World, Tessellator

public class PD_EntityFlameFX extends PD_EntityParticlesFX
{

	private boolean canEntitiesSetOnFire = false;
	
    public PD_EntityFlameFX(World world, double d, double d1, double d2, double d3, double d4, double d5, boolean b, boolean b1, int i, int j)
    {
        super(world, d, d1, d2, d3, d4, d5, 0, 0, 0, true, b, i, j);
        canEntitiesSetOnFire = b1;
    }
    
    @Override
    public void initParticle(World world, double d, double d1, double d2, double d3, double d4, double d5, float f, float f1, float f2, float f3) {
        particleRed = 1.0F;
        particleGreen = 1.0F;
        particleBlue = 1.0F;
        setParticleTextureIndex(48);
        setSize(0.02F, 0.02F);
        particleScale = particleScale * (rand.nextFloat() * 0.6F + 0.2F);
        motionX = d3 * 0.20000000298023224D + (double)((float)(Math.random() * 2D - 1.0D) * 0.02F);
        motionY = d4 * 0.20000000298023224D + (double)((float)(Math.random() * 2D - 1.0D) * 0.02F);
        motionZ = d5 * 0.20000000298023224D + (double)((float)(Math.random() * 2D - 1.0D) * 0.02F);
        particleMaxAge = (int)(8D / (Math.random() * 0.80000000000000004D + 0.20000000000000001D));
    }

    public void onUpdate()
    {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

        moveParticle();
        
        if(particleMaxAge-- <= 0)
        {
            setDead();
        }
        
        EntityLiving entity = (EntityLiving)getCollidedEntity();
        if(entity != null & canEntitiesSetOnFire && !entity.isImmuneToFire() && entity instanceof EntityLiving) {
        	entity.attackEntityFrom(DamageSource.inFire, 3);
        	entity.setFire(5);
        	
        	byte[] eID = getEntityIdAsByteArray(entity);
        	Packet250CustomPayload packet = new Packet250CustomPayload("ParticleDeco", new byte[] {3, eID[0], eID[1], eID[2], eID[3]});
        	PD_ModRegistry.proxy.sendToServer(packet);
        }
    }
    
    private byte[] getEntityIdAsByteArray(Entity e) {
    	return new byte[] {
    			(byte)(e.entityId & 255),
    			(byte)(((e.entityId) >> 8) & 255),
    			(byte)(((e.entityId) >> 16) & 255),
    			(byte)(((e.entityId) >> 24) & 255),
    	};
    }
    
    public Entity getCollidedEntity() {
		List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.contract(0.28000000298023224D, 0.0D, 0.28000000298023224D));
        if(list != null && list.size() > 0)
        {
            for(int i = 0; i < list.size(); i++)
            {
                Entity entity = (Entity)list.get(i);
                if(entity != null && !(entity instanceof PD_EntityParticleDeco) && entity instanceof EntityLiving) {
                	return entity;
                }
            }
        }
        return null;
    }
}
