package com.unrelentless.fcraft.proxy;

import com.unrelentless.fcraft.blocks.tile.TileEntityBlockMako;
import com.unrelentless.fcraft.entity.FCraftEntityOrich;
import com.unrelentless.fcraft.renderer.RenderEntityOrichalcum;
import com.unrelentless.fcraft.renderer.RenderTileBlockMako;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	@Override
    public void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockMako.class, new RenderTileBlockMako());
		RenderingRegistry.registerEntityRenderingHandler(FCraftEntityOrich.class, new RenderEntityOrichalcum());

    }
}

