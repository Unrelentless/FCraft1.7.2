package com.unrelentless.fcraft.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.unrelentless.fcraft.FantasyCraft;
import com.unrelentless.fcraft.blocks.tile.TileEntityBlockMako;

public class RenderTileBlockMako extends TileEntitySpecialRenderer  {

	private IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation(FantasyCraft.MODID+":models/Crystal.obj"));
    private ResourceLocation texture = new ResourceLocation(FantasyCraft.MODID + ":textures/blocks/CrystalTest.png");
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y,
			double z, float tick) {
		TileEntityBlockMako tile = (TileEntityBlockMako) tileEntity;
		float[] colours = tile.getColour();
        GL11.glPushMatrix();
    	GL11.glColor3f(colours[0],colours[1],colours[2]);
    	GL11.glEnable(3042);
    	GL11.glBlendFunc(770,771);
    	GL11.glTranslated(x, y, z);
    	GL11.glRotatef(180F, 0, 1, 0);
    	GL11.glTranslatef(-0.2F, 0, -0.4F);
    	GL11.glScalef(tile.getScale(), tile.getScale(), tile.getScale());
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        model.renderAll();
    	GL11.glDisable(3042);
    	GL11.glPopMatrix();
	}
}
