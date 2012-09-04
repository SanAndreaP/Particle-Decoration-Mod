// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package SanAndreasP.mods.ParticleDecoMod;
import net.minecraft.src.*;


// Referenced classes of package net.minecraft.src:
//            Slot, Item, ItemStack, ContainerBrewingStand, 
//            IInventory

class PD_SlotParticleDeco extends Slot
{

    final PD_ContainerParticleDeco field_40442_a; /* synthetic field */
    private int SType;

    public PD_SlotParticleDeco(PD_ContainerParticleDeco containerparticledeco, IInventory iinventory, int i, int j, int k, int l)
    {
        super(iinventory, i, j, k);
        field_40442_a = containerparticledeco;
        SType = l;
    }

    public boolean isItemValid(ItemStack itemstack)
    {
        if(itemstack != null)
        {
            return ((itemstack.itemID == Item.lightStoneDust.shiftedIndex && SType == 0) || (itemstack.itemID == Item.dyePowder.shiftedIndex && SType == 1));
        } else
        {
            return false;
        }
    }

    public int getSlotStackLimit()
    {
        return 1;
    }
}
