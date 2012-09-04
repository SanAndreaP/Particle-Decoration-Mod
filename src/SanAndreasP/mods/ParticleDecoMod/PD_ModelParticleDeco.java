// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package SanAndreasP.mods.ParticleDecoMod;
import net.minecraft.src.*;


// Referenced classes of package net.minecraft.src:
//            ModelBase, ModelRenderer, Entity

public class PD_ModelParticleDeco extends ModelBase
{

    public PD_ModelParticleDeco()
    {
        Body = new ModelRenderer(this, 0, 0);
        Body.addBox(-4F, -4F, -4F, 8, 8, 8);
        Body.setRotationPoint(0F, 20F, 0F);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5);
        Body.render(f5);
    }
    
    public void setLivingAnimations(EntityLiving entityliving, float f, float f1, float f2) {
    	if(entityliving instanceof PD_EntityParticleDeco) {
    		PD_EntityParticleDeco entity = (PD_EntityParticleDeco)entityliving;
    		switch(entity.getDirection()) {
	    		case 0:
	    			Body.rotateAngleX = 0F;
	    			Body.rotateAngleZ = 0F;
	    			break;
	    		case 1:
	    			Body.rotateAngleX = 0F;
	    			Body.rotateAngleZ = 1.570796F;
	    			break;
	    		case 2:
	    			Body.rotateAngleX = 0F;
	    			Body.rotateAngleZ = -1.570796F;
	    			break;
	    		case 3:
	    			Body.rotateAngleX = 1.570796F;
	    			Body.rotateAngleZ = 0F;
	    			break;
	    		case 4:
	    			Body.rotateAngleX = -1.570796F;
	    			Body.rotateAngleZ = 0F;
	    			break;
    		}
    	}
    }

    ModelRenderer Body;
}
