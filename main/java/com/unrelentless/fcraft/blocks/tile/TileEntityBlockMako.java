package com.unrelentless.fcraft.blocks.tile;

import net.minecraft.tileentity.TileEntity;

public class TileEntityBlockMako extends TileEntity {

	float scale = 1.0F;
	float[] colours = {1.0F, 1.0F ,1.0F};
	
	public float getScale(){
		return this.scale;
	}
	
	public void setScale(float scale){
		this.scale = scale;
	}
	
	public float[] getColour(){
		return this.colours;
	}
	
	public void setColour(float[] colour){
		for(int i=0;i<colour.length;i++){
			this.colours[i] = colour[i];
		}
	}
}
