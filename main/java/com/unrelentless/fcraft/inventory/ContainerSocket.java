package com.unrelentless.fcraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.unrelentless.fcraft.items.FCraftItemMateria;

public class ContainerSocket extends Container
{
	Boolean firstAccess = true;

	/** Avoid magic numbers! This will greatly reduce the chance of you making errors in 'transferStackInSlot' method */
	private static int INV_START, INV_END, HOTBAR_START, HOTBAR_END;

	public int numberOfSlots;

	public ContainerSocket(EntityPlayer player, InventoryPlayer inventoryPlayer, InventorySocket inventoryCustom) {

		numberOfSlots = player.getCurrentEquippedItem().stackTagCompound.getInteger("CurrentSockets");

		INV_START = numberOfSlots*2;
		INV_END = INV_START+26;
		HOTBAR_START = INV_END+1;
		HOTBAR_END = HOTBAR_START+8;
		int i;

		//Add Custom weapon slot = 0
		//addSlotToContainer(new SlotCustom(inventoryCustom, 0, -32, -24));

		// Add CUSTOM slots = 1-5 depending on number of sockets available.
		for(i=0;i<numberOfSlots;i++){
			addSlotToContainer(new SlotMateria(inventoryCustom, i, 1, 0+(i*21)));
		}

		//Add support materia slots
		for(i=0;i<numberOfSlots;i++){
			addSlotToContainer(new SlotSupport(inventoryCustom, i+numberOfSlots, 191, 0+(i*21)));
		}


		// Add ACTION BAR - just copied/pasted from vanilla classes
		for (i = 0; i < 9; ++i) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 48 + i * 18, 167));
		}
		
		// Add vanilla PLAYER INVENTORY - just copied/pasted from vanilla classes
		for (i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 48 + j * 18, 109 + i * 18));
			}
		}

		materiaPut(player);
	}

	public void materiaPut(EntityPlayer player){

		for(int i=0; i<numberOfSlots;i++){	
			int materiaID = player.getCurrentEquippedItem().stackTagCompound.getInteger("SocketContents"+i);
			int materiaMeta = player.getCurrentEquippedItem().stackTagCompound.getInteger("SocketContentsMeta"+i);

			if(materiaID != 0){
				ItemStack materia = new ItemStack(Item.getItemById(materiaID), 1, materiaMeta);
				((Slot)this.inventorySlots.get(i)).putStack(materia);
			}else{
				((Slot)this.inventorySlots.get(i)).putStack((ItemStack)null);
			}
		}
	}

	public void updateMateriaInWeapon(EntityPlayer player){
		for(int i=0; i<numberOfSlots;i++){
			ItemStack item = ((Slot) this.inventorySlots.get(i)).getStack();

			if(item != null && ((Slot) this.inventorySlots.get(i)).getHasStack()){
				int materiaID = Item.getIdFromItem(((Slot) this.inventorySlots.get(i)).getStack().getItem());
				int materiaMeta = ((Slot) this.inventorySlots.get(i)).getStack().getItemDamage();

				String toolTip = (String) new ItemStack(Item.getItemById(materiaID), 1, materiaMeta).getTooltip(player, true).get(1);

				player.getCurrentEquippedItem().stackTagCompound.setInteger("SocketContents"+(i), materiaID);		
				player.getCurrentEquippedItem().stackTagCompound.setInteger("SocketContentsMeta"+(i), materiaMeta);
				player.getCurrentEquippedItem().stackTagCompound.setString("SocketContentsString"+(i), toolTip);
			}else{
				player.getCurrentEquippedItem().stackTagCompound.setInteger("SocketContents"+(i), 0);		
				player.getCurrentEquippedItem().stackTagCompound.setInteger("SocketContentsMeta"+(i), 0);
				player.getCurrentEquippedItem().stackTagCompound.setString("SocketContentsString"+(i), "--");
			}
		}
	}


	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer) {
		updateMateriaInWeapon(par1EntityPlayer);
	}

	/**
	 * This should always return true, since custom inventory can be accessed from anywhere
	 */
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	 * Basically the same as every other container I make, since I define the same constant indices for all of them
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int par2) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			// Either armor slot or custom item slot was clicked
			if (par2 < INV_START) {
				// try to place in player inventory / action bar
				if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END + 1, true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
			//If materia is clicked in either hotbar or inv.
			}else{ 
				if (itemstack1.getItem() instanceof FCraftItemMateria) {
					// try to place in player inventory / action bar
					if (!this.mergeItemStack(itemstack1, 0, numberOfSlots, true)) {
						return null;
					}
					slot.onSlotChange(itemstack1, itemstack);
					//If Slot Hotbar is clicked
				}else if (par2 >= HOTBAR_START && par2 < HOTBAR_END+1) {
					// try to place in player inventory / action bar
					if (!this.mergeItemStack(itemstack1, 0, INV_START, true)) {
						return null;
					}
					slot.onSlotChange(itemstack1, itemstack);
				}else if (par2 >= INV_START && par2 < HOTBAR_START) {
					// try to place in player inventory / action bar
					if (!this.mergeItemStack(itemstack1, 0, INV_START, true)) {
						return null;
					}
					slot.onSlotChange(itemstack1, itemstack);
				}
			}
			
			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}
}