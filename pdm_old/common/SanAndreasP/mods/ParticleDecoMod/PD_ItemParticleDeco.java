package SanAndreasP.mods.ParticleDecoMod;
import net.minecraft.src.*;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

//import net.minecraft.src.forge.*;

public class PD_ItemParticleDeco extends Item {
	public PD_ItemParticleDeco(int i) {
		super(i);
        setHasSubtypes(true);
        setMaxDamage(0);
        setTabToDisplayOn(CreativeTabs.tabDeco);
	}
		
	public boolean tryPlaceIntoWorld(ItemStack itemstack, EntityPlayer par2EntityPlayer, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		
		if(!world.getEntitiesWithinAABB(PD_EntityParticleDeco.class, AxisAlignedBB.getBoundingBox(par4, par5+1D, par6, par4+1D, par5+2D, par6+1D)).isEmpty())
			return true;
		
		if(world.isRemote) return true;
		
        PD_EntityParticleDeco epd = new PD_EntityParticleDeco(world, par4, par5+1, par6, itemstack.getItemDamage());
        world.spawnEntityInWorld(epd);
        itemstack.stackSize--;
		return true;
	};
	
	@Override
	public int getIconFromDamage(int i) {
		return i;
	}
	
	@Override
	public String getItemNameIS(ItemStack itemstack) {
		for(int i = 0; i < 8; i++) {
			if(itemstack.getItemDamage() == i) {
				return ("partdeco"+i);
			}
		}
		return "";
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int l1 = 0; l1 < 8; l1++)
        {
        	ItemStack is = new ItemStack(this, 1, l1);
        	if(!((is.getItemNameandInformation().get(0)).equals("")))
        		par3List.add(is);
        }
	}

}
