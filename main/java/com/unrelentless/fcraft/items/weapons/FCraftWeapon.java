package com.unrelentless.fcraft.items.weapons;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemSword;

public class FCraftWeapon extends ItemSword{

	public static ItemSword swordBuster;
	
	public FCraftWeapon(ToolMaterial material) {
		super(material);
	}
	
	public static void init(){
		swordBuster = new SwordBuster();
		
		GameRegistry.registerItem(FCraftWeapon.swordBuster, swordBuster.getUnlocalizedName());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon){

		//registers the block
		itemIcon = icon.registerIcon("fcraft:bustersword");
	}
}
