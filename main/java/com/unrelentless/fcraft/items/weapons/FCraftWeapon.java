package com.unrelentless.fcraft.items.weapons;

import net.minecraft.item.ItemSword;
import cpw.mods.fml.common.registry.GameRegistry;

public class FCraftWeapon extends ItemSword{

	public static ItemSword swordBuster;
	
	public FCraftWeapon(ToolMaterial material) {
		super(material);
	}
	
	public static void init(){
		swordBuster = new SwordBuster();
		
		GameRegistry.registerItem(FCraftWeapon.swordBuster, swordBuster.getUnlocalizedName());
	}
}
