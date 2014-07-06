package com.unrelentless.fcraft.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class FCraftBlock {

	
	public static Block blockMako;
	
	public static void init(){

		blockMako = new FCraftBlockMako(Material.rock);
		
		GameRegistry.registerBlock(blockMako, blockMako.getUnlocalizedName());
		
	}	
}
