package com.unrelentless.fcraft.blocks;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;

public class FCraftBlock {

	public static Block blockMako;
	public static Block blockOre;
	
	public static void init(){

		blockMako = new FCraftBlockMako();
		blockOre = new FCraftBlockOre();
		
		GameRegistry.registerBlock(blockMako, blockMako.getUnlocalizedName());
		GameRegistry.registerBlock(blockOre, FCraftBlockOreItem.class, blockOre.getUnlocalizedName().substring(5));
	}	
}
