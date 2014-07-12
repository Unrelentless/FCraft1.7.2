package com.unrelentless.fcraft.events;

import java.util.ArrayList;
import java.util.List;

import com.sun.media.jfxmedia.events.PlayerEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.player.PlayerEvent.HarvestCheck;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ZodiacEventHandler {

	Minecraft mc;

	int maxZodiac;
	int totalMined;
	boolean reached = false;
	List<Integer> individualZodiac = new ArrayList<Integer>();

	@SubscribeEvent
	public void onPlayerCreationEvent(EntityConstructing event) {

		maxZodiac = FMLClientHandler.instance().getServer().getCurrentPlayerCount()*1;

		if(event.entity instanceof EntityPlayer){
			DataWatcher dw = event.entity.getDataWatcher();
			dw.addObject(20, Integer.valueOf(1));
		}
	}

	@SubscribeEvent
	public void onHarvestEvent(BreakEvent event) {
		if (event.getPlayer() != null) {
			EntityPlayer player = (EntityPlayer) event.getPlayer();
			DataWatcher dw = event.getPlayer().getDataWatcher(); 
			event.getPlayer().getDataWatcher().updateObject(20, dw.getWatchableObjectInt(20)+1);
			player.addChatMessage(new ChatComponentText(""+player.getDataWatcher().getWatchableObjectInt(20)));
		}

		totalMined = 0;

		for(int i=0;i<event.getPlayer().worldObj.getLoadedEntityList().size();i++){
			if (event.getPlayer().worldObj.loadedEntityList.get(i) instanceof EntityPlayer){
				totalMined += event.getPlayer().getDataWatcher().getWatchableObjectInt(20);
			}
		}
		
		if(totalMined >= maxZodiac){
			for(int j=0;j<event.getPlayer().worldObj.getLoadedEntityList().size();j++){
				if (event.getPlayer().worldObj.loadedEntityList.get(j) instanceof EntityPlayer){
					EntityPlayer player = (EntityPlayer) event.getPlayer().worldObj.loadedEntityList.get(j);

					player.getDataWatcher().updateObject(20, 0);
				}
			}
		}
	}
}