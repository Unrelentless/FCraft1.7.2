package com.unrelentless.fcraft;


import java.io.File;
import java.io.IOException;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;

import com.unrelentless.fcraft.blocks.FCraftBlock;
import com.unrelentless.fcraft.creativetabs.FCraftCreativeTab;
import com.unrelentless.fcraft.handlers.ConfigHandler;
import com.unrelentless.fcraft.handlers.KeybindHandler;
import com.unrelentless.fcraft.proxy.CommonProxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = FantasyCraft.MODID, version = FantasyCraft.VERSION)
public class FantasyCraft
{
	public static final String MODID = "fcraft";
	public static final String VERSION = "0.0.2";

	//Keybinds
	public static KeyBinding scan, scanPoke;

	//says where the client and server 'proxy' code is loaded
	@SidedProxy(clientSide = "com.unrelentless.fcraft.proxy.ClientProxy", serverSide = "com.unrelentless.fcraft.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static CreativeTabs fcraftTabBlocks = new FCraftCreativeTab(CreativeTabs.getNextID(), MODID);
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) throws IOException{

		ConfigHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + FantasyCraft.MODID + File.separator + FantasyCraft.MODID + ".cfg"));
		FCraftBlock.init();
	}
	@EventHandler
	public void init(FMLInitializationEvent event)
	{        
		proxy.registerRenderers();

		//keybinding
/*		scan = new KeyBinding("key.scanBlock", Keyboard.KEY_F, "key.categories.xxxmod");
		scanPoke = new KeyBinding("key.scanPoke", Keyboard.KEY_G, "key.categories.xxxmod");
		ClientRegistry.registerKeyBinding(scan);
		ClientRegistry.registerKeyBinding(scanPoke);*/

		//event registration
		FMLCommonHandler.instance().bus().register(new KeybindHandler());

	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
	}
}
