package com.unrelentless.fcraft.gui;

import com.unrelentless.fcraft.FantasyCraft;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSocket extends GuiScreen {
	private int xSize = 256;
	private int ySize = 229;
	
	public GuiSocket(EntityPlayer player)
	{
		this.doesGuiPauseGame();
	}

	@Override
	public void drawScreen(int x, int y, float f)
	{
		drawDefaultBackground();
		ResourceLocation textureLoc = new ResourceLocation(FantasyCraft.MODID+":" + "textures/gui/Socket_Sword.png");
		this.mc.renderEngine.bindTexture(textureLoc);
		this.drawTexturedModalRect(0, 0, 0, 0, xSize, ySize);
	}
}
