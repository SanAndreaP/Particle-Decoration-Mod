/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.particledeco.client.particle;

import de.sanandrew.core.manpack.mod.client.particle.EntityParticle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.List;

public class EntityDustFX
    extends EntityParticle
{
    public EntityDustFX(World world, double x, double y, double z, double flyHeight, double speed, double motX, double motY, double motZ) {
        super(world, x, y, z);
        this.motionX = motX * speed;
        this.motionY = motY * speed;
        this.motionZ = motZ * speed;

        this.setParticleTextureIndex(7);

        this.particleMaxAge = (int) ((12.6D * flyHeight - 6.0D) / speed);

        float rndColorMulti = 0.6F + this.rand.nextFloat() * 0.4F;
        this.particleRed *= rndColorMulti;
        this.particleGreen *= rndColorMulti;
        this.particleBlue *= rndColorMulti;

        this.noClip = true;
    }

    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if( this.particleAge++ >= this.particleMaxAge ) {
            this.setDead();
        }

        this.moveEntity(this.motionX, this.motionY, this.motionZ);

        if (this.onGround)
        {
            this.motionX *= 0.7D;
            this.motionZ *= 0.7D;
        }

        //TODO: test for "matter particles"
        List<Entity> eL = this.worldObj.getEntitiesWithinAABB(Entity.class, this.boundingBox);
        for( Entity e : eL ) {
            e.motionY = 0.1D;
        }
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer p_70100_1_) {
        super.onCollideWithPlayer(p_70100_1_);
        p_70100_1_.motionY = 0.01D;
    }
}
