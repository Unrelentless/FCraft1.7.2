package com.unrelentless.fcraft.blocks.tile;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBlockAdamOre extends TileEntity {
	Random rand = new Random();
	
	int growthStage = 0;
	boolean inert = true;
			
	public float getStage(){
		return this.growthStage;
	}
	
	public void setStage(int stage){
		this.growthStage = stage;
	}
	
	public boolean isInert(){
		return this.inert;
	}
	
	public void setInert(){
		this.inert = true;
	}
	
	public void setActive(){
		this.inert = false;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		tagCompound.setBoolean("inert", this.inert);
		
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		tagCompound.getBoolean("inert");
	}
}
