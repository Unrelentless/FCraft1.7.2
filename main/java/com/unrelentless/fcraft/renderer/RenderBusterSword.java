package com.unrelentless.fcraft.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.unrelentless.fcraft.FantasyCraft;
import com.unrelentless.fcraft.model.ModelBusterSword;


public class RenderBusterSword implements IItemRenderer {

	protected ModelBusterSword busterModel;
	private ResourceLocation texture = new ResourceLocation(FantasyCraft.MODID + ":textures/items/BusterSword.png");

	public RenderBusterSword()
	{
		busterModel = new ModelBusterSword();
	}
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type){
		case EQUIPPED: return true;
		case EQUIPPED_FIRST_PERSON: return true;
		case INVENTORY: return true;
		case ENTITY: return true;
		default: return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		switch (type) {
		case ENTITY: {
			return (helper == ItemRendererHelper.ENTITY_BOBBING ||
					helper == ItemRendererHelper.ENTITY_ROTATION ||
					helper == ItemRendererHelper.BLOCK_3D);
		}
		case INVENTORY: {
			return (helper == ItemRendererHelper.INVENTORY_BLOCK);
		}
		default: {
			return false;
		}
		}
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		ResourceLocation location = texture;
		Minecraft.getMinecraft().renderEngine.bindTexture(location);

		switch(type){
		case EQUIPPED:{
		
			GL11.glPushMatrix();

			GL11.glScalef(2.0F, 2.0F, 2.0F);
			GL11.glRotatef(-90F, 1, 0, 0);
			GL11.glRotatef(-90F, 0, 0, 1);
			GL11.glRotatef(-40F, -1, 0, 0);
			GL11.glTranslatef(0, 0.3F, -0.3F);
			
			busterModel.render();
			GL11.glPopMatrix();
			break;
		}
		case EQUIPPED_FIRST_PERSON:{			
			GL11.glPushMatrix();

			GL11.glScalef(5.0F, 5.0F, 5.0F);
			GL11.glRotatef(90F, 1, 1, 0);
			GL11.glRotatef(90F, 0, 1, 0);
			//GL11.glTranslatef(2F, 0, 0F);
			busterModel.render();
			GL11.glPopMatrix();
			break;
		}
		case INVENTORY:{
			busterModel.render();
		}
		case ENTITY:{
			GL11.glPushMatrix();
			GL11.glRotatef(90F, 1, 0, 0);	
			GL11.glTranslatef(0.4F, -0.55F, 0);
			busterModel.render();
			GL11.glPopMatrix();
		}
		default: break;
		}

	}
}
