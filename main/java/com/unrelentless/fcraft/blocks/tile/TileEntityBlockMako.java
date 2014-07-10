package com.unrelentless.fcraft.blocks.tile;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBlockMako extends TileEntity {
	Random rand = new Random();
	
	float scale = 1.0F;
	float[] colours = {1.0F, 1.0F ,1.0F};
	int numOfCrystals = 0;
	int stage = rand.nextInt(4);
	float[] angles = {(float)rand.nextInt(1080), (float)rand.nextInt(1080), (float)rand.nextInt(1080), (float)rand.nextInt(1080)};
	
	public float getScale(){
		return this.scale;
	}
	
	public void setScale(float scale){
		this.scale = scale;
	}
	
	public float getStage(){
		return this.stage;
	}
	
	public void setStage(int stage){
		this.stage = stage;
	}
	
	public float[] getAngles(){
		return this.angles;
	}
	
	public void setAngles(float[] angles){
		this.angles = angles;
	}
	
	public float getNumberOfCrystals(){
		return this.numOfCrystals;
	}
	
	public void setNumberOfCrystals(int number){
		this.numOfCrystals = number;
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
