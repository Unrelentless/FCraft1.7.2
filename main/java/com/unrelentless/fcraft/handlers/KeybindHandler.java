package com.unrelentless.fcraft.handlers;

import com.unrelentless.fcraft.FantasyCraft;
import com.unrelentless.fcraft.packets.OpenGuiPacket;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;


public class KeybindHandler {
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {

		if(FantasyCraft.socketMateria.isPressed() &&  FMLClientHandler.instance().getClient().inGameHasFocus) {
			FantasyCraft.packetPipeline.sendToServer(new OpenGuiPacket(0));
		}
	}
}
