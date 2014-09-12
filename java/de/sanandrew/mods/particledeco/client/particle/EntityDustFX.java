/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.particledeco.client.particle;

import de.sanandrew.core.manpack.mod.client.particle.EntityParticle;
import net.minecraft.world.World;

public class EntityDustFX
    extends EntityParticle
{
    public EntityDustFX(World world, double x, double y, double z, double flyHeight, double motX, double motY, double motZ) {
        super(world, x, y, z);
        this.motionX = motX;
        this.motionY = motY;
        this.motionZ = motZ;
        this.setParticleTextureIndex(7);
        this.particleMaxAge = 12 * 5 - 6;

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

//        this.particleScale += 0.05F;

        this.moveEntity(this.motionX, this.motionY, this.motionZ);

//        this.motionX *= 0.96D;
//        this.motionY *= 0.96D;
//        this.motionZ *= 0.96D;

        if (this.onGround)
        {
            this.motionX *= 0.7D;
            this.motionZ *= 0.7D;
        }
    }
}
