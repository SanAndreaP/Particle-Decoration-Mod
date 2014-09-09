/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.particledeco.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelParticleBox
    extends ModelBase
{
    public ModelRenderer box;

    public ModelParticleBox() {
        this.box = new ModelRenderer(this, 0, 0);
        this.box.addBox(-4.0F, 0.0F, -4.0F, 8, 8, 8);
        this.box.setRotationPoint(0.0F, 16.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float rotFloat, float rotYaw, float rotPitch, float partTicks) {
        this.box.render(partTicks);
    }
}
