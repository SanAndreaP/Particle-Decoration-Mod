package SanAndreasP.mods.ParticleDecoMod;
import net.minecraft.src.*;

public class PD_Inventory implements IInventory {
	public PD_EntityParticleDeco pd;
	
	PD_Inventory(PD_EntityParticleDeco pde) {
		pd = pde;
	}
	

//	@Override
	public int getSizeInventory() {
		return 2;
	}

//	@Override
	public ItemStack getStackInSlot(int i) {
            switch(i) {
	            case 0: return pd.glowstone;
	            case 1: return pd.particleDye;
	            default: return pd.glowstone;
            }
	}

//	@Override
	public ItemStack decrStackSize(int i, int j) {
			ItemStack itemstack = pd.glowstone;
			if(i == 1) {
	            itemstack = pd.particleDye;
	            pd.particleDye = null;
			} else {
				pd.glowstone = null;
			}
            return itemstack;
	}

//	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
        switch(i) {
	        case 0: pd.glowstone = itemstack; break;
	        case 1: pd.particleDye = itemstack; break;
	        default: pd.glowstone = itemstack;
        }		
	}

	@Override
	public String getInvName() {
		return "Particle Deco";
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public void onInventoryChanged() {
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

}
