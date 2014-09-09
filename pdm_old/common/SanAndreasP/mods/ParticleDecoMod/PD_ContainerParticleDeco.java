// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package SanAndreasP.mods.ParticleDecoMod;
import net.minecraft.src.*;

import java.util.List;

// Referenced classes of package net.minecraft.src:
//            Container, SlotBrewingStandPotion, InventoryPlayer, SlotBrewingStandIngredient, 
//            Slot, ICrafting, TileEntityBrewingStand, ItemStack, 
//            EntityPlayer

public class PD_ContainerParticleDeco extends Container
{

    private PD_EntityParticleDeco pdentity;
//    private int field_40242_b;

    public PD_ContainerParticleDeco(InventoryPlayer inventoryplayer, PD_EntityParticleDeco ptd)
    {
//        field_40242_b = 0;
        pdentity = ptd;
        if(ptd.getDataWatcher().getWatchableObjectShort(10) != 3)
        	addSlotToContainer(new PD_SlotParticleDeco(this, ptd.inventory, 0, 47, 17, 0));
        if(ptd.canBeColorized())
        	addSlotToContainer(new PD_SlotParticleDeco(this, ptd.inventory, 1, 47, 52, 1));
        
        for(int i = 0; i < 3; i++)
        {
            for(int k = 0; k < 9; k++)
            {
            	addSlotToContainer(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }

        }

        for(int j = 0; j < 9; j++)
        {
        	addSlotToContainer(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }

    }

//    public void updateCraftingResults()
//    {
//        super.updateCraftingResults();
//        for(int i = 0; i < crafters.size(); i++)
//        {
//            ICrafting icrafting = (ICrafting)crafters.get(i);
//            if(field_40242_b != field_40243_a.func_40053_g())
//            {
//                icrafting.updateCraftingInventoryInfo(this, 0, field_40243_a.func_40053_g());
//            }
//        }
//
//        field_40242_b = field_40243_a.func_40053_g();
//    }

//    public void updateProgressBar(int i, int j)
//    {
//        if(i == 0)
//        {
//            field_40243_a.func_40049_b(j);
//        }
//    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return true;
    }

    public ItemStack transferStackInSlot(int i)
    {
//        ItemStack itemstack = null;
//        Slot slot = (Slot)inventorySlots.get(i);
//        if(slot != null && slot.getHasStack())
//        {
//            ItemStack itemstack1 = slot.getStack();
//            itemstack = itemstack1.copy();
//            if(i >= 0 && i <= 2 || i == 3)
//            {
//                if(!mergeItemStack(itemstack1, 4, 40, true))
//                {
//                    return null;
//                }
//            } else
//            if(i >= 4 && i < 31)
//            {
//                if(!mergeItemStack(itemstack1, 31, 40, false))
//                {
//                    return null;
//                }
//            } else
//            if(i >= 31 && i < 40)
//            {
//                if(!mergeItemStack(itemstack1, 4, 31, false))
//                {
//                    return null;
//                }
//            } else
//            if(!mergeItemStack(itemstack1, 4, 40, false))
//            {
//                return null;
//            }
//            if(itemstack1.stackSize == 0)
//            {
//                slot.putStack(null);
//            } else
//            {
//                slot.onSlotChanged();
//            }
//            if(itemstack1.stackSize != itemstack.stackSize)
//            {
//                slot.onPickupFromSlot(itemstack1);
//            } else
//            {
//                return null;
//            }
//        }
        return null;
    }
}
