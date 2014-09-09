/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.particledeco.tileentity;

import de.sanandrew.core.manpack.util.client.SAPClientUtils;
import de.sanandrew.mods.particledeco.client.particle.EntityDustFX;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.tileentity.TileEntity;

public class TileEntityParticleBox
    extends TileEntity
{
    private int ticksExisted;
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void updateEntity() {
        this.ticksExisted++;

        if( this.worldObj.isRemote && this.ticksExisted % 2 == 0 ) {
            EntityFX particle = new EntityDustFX(this.worldObj, this.xCoord + 0.5F, this.yCoord + 0.5F, this.zCoord + 0.5F, 10.0F, 0.0F, 0.075F, 0.0F);
            SAPClientUtils.addEffectWithNoLimit(Minecraft.getMinecraft().effectRenderer, particle);
        }
    }
}
