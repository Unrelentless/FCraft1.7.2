package com.unrelentless.fcraft;


import java.io.File;
import java.io.IOException;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

import com.unrelentless.fcraft.blocks.FCraftBlock;
import com.unrelentless.fcraft.blocks.tile.FCraftTileEntity;
import com.unrelentless.fcraft.creativetabs.FCraftCreativeTab;
import com.unrelentless.fcraft.entity.FCraftEntity;
import com.unrelentless.fcraft.events.ExtendedPropertiesHandler;
import com.unrelentless.fcraft.events.ZodiacEventHandler;
import com.unrelentless.fcraft.handlers.ConfigHandler;
import com.unrelentless.fcraft.handlers.KeybindHandler;
import com.unrelentless.fcraft.packets.PacketPipeline;
import com.unrelentless.fcraft.proxy.CommonProxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = FantasyCraft.MODID, version = FantasyCraft.VERSION)
public class FantasyCraft
{
	public static final String MODID = "fcraft";
	public static final String VERSION = "0.1.5";

	//Keybinds
	public static KeyBinding scan, scanPoke;

	//says where the client and server 'proxy' code is loaded
	@SidedProxy(clientSide = "com.unrelentless.fcraft.proxy.ClientProxy", serverSide = "com.unrelentless.fcraft.proxy.CommonProxy")
	public static CommonProxy proxy;

	//Set Creative Tabs
	public static CreativeTabs fcraftTabBlocks = new FCraftCreativeTab(CreativeTabs.getNextID(), MODID);
	//Set Packet Handling
	public static final PacketPipeline packetPipeline = new PacketPipeline();

	@Instance(MODID)
	public static FantasyCraft instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) throws IOException{

		ConfigHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + FantasyCraft.MODID + File.separator + FantasyCraft.MODID + ".cfg"));	
		FCraftTileEntity.init();
		FCraftBlock.init();
		FCraftEntity.init();

	}
	@EventHandler
	public void init(FMLInitializationEvent event)
	{        
		proxy.registerRenderers();
		packetPipeline.initialise();
		//keybinding
		/*		scan = new KeyBinding("key.scanBlock", Keyboard.KEY_F, "key.categories.xxxmod");
		scanPoke = new KeyBinding("key.scanPoke", Keyboard.KEY_G, "key.categories.xxxmod");
		ClientRegistry.registerKeyBinding(scan);
		ClientRegistry.registerKeyBinding(scanPoke);*/

		//event registration
		FMLCommonHandler.instance().bus().register(new KeybindHandler());
		MinecraftForge.EVENT_BUS.register(new ZodiacEventHandler());
		MinecraftForge.EVENT_BUS.register(new ExtendedPropertiesHandler());

	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		packetPipeline.postInitialise();
	}
}
