package com.unrelentless.fcraft.events;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

import com.unrelentless.fcraft.extendedprops.FCraftExtendedPlayer;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ZodiacEventHandler {

	Minecraft mc;

	int maxZodiac;
	int totalMined;
	boolean reached = false;
	List<Integer> individualZodiac = new ArrayList<Integer>();

	@SubscribeEvent
	public void onHarvestEvent(BreakEvent event) {
		maxZodiac = FMLClientHandler.instance().getServer().getCurrentPlayerCount()*10;
		if (event.getPlayer() != null) {
			EntityPlayer player = (EntityPlayer) event.getPlayer();
			FCraftExtendedPlayer props = FCraftExtendedPlayer.get(player);
			props.setZodiac(props.getZodiac()+1);
			player.addChatMessage(new ChatComponentText(""+props.getZodiac()));
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
					FCraftExtendedPlayer props = FCraftExtendedPlayer.get(player);
					props.setZodiac(0);
					
					
				}
			}
		}
	}
}