package com.unrelentless.fcraft.blocks.tile;

import net.minecraft.nbt.NBTTagCompound;
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
	
	public void writeToNBT(NBTTagCompound par1)
	{
		super.writeToNBT(par1);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1)
	{
		super.readFromNBT(par1);
	}
}
