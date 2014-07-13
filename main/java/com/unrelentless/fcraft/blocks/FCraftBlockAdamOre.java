package com.unrelentless.fcraft.blocks;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import com.unrelentless.fcraft.FantasyCraft;
import com.unrelentless.fcraft.blocks.tile.TileEntityBlockAdamOre;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FCraftBlockAdamOre extends BlockContainer {

	int ticks;
	Minecraft mc;
	Random rand = new Random();

	public FCraftBlockAdamOre(){
		super(Material.rock);

		this.setHardness(50.0F);
		this.setResistance(50.0F);
		this.setBlockName("ore.adamore");
		this.setCreativeTab(FantasyCraft.fcraftTabBlocks);
		this.setTickRandomly(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon)
	{
		//registers the block texture to block
		blockIcon = icon.registerIcon(String.format("%s:%s", FantasyCraft.MODID, ":Ore3"));	
	}

	//Makes sure that only the name and not tile.name is sent.
	protected String getUnwrappedUnlocalizedName(String name){
		return name.substring(name.indexOf(".") + 1);
	}


	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {

		return new TileEntityBlockAdamOre();
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9)
	{
		if(!world.isRemote){
			TileEntityBlockAdamOre tile = (TileEntityBlockAdamOre) world.getTileEntity(x, y, z);
			tile.setActive();
			player.addChatMessage(new ChatComponentText("Activated."));
		}
		return true;
	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
		TileEntityBlockAdamOre tile = (TileEntityBlockAdamOre) world.getTileEntity(x, y, z);
		int[] coords = {x, y, z};

		if(tile != null){
			int[] stoneCoords = searchStone(world, tile, coords);
			if(!tile.isInert()){
				world.setBlock(x+stoneCoords[0], y+stoneCoords[1], z+stoneCoords[2], FCraftBlock.blockAdamOre);
				TileEntityBlockAdamOre tileBred = (TileEntityBlockAdamOre) world.getTileEntity(x+stoneCoords[0], y+stoneCoords[1], z+stoneCoords[2]);
				tileBred.setInert();
				ticks = 0;
			}
		}
		ticks++;
	}



	private int[] searchStone(World world, TileEntityBlockAdamOre te, int[] coords){
		int[] stoneCoords = {0, 0, 0};
		outerloop:
			for(int i=-1;i<=1;i++){
				for(int j=-1;j<=1;j++){
					for(int k=-1;i<=1;k++){
						if(world.getBlock(coords[0]+i, coords[1]+j, coords[2]+k) == Blocks.stone){
							stoneCoords[0] = i;
							stoneCoords[1] = j;
							stoneCoords[2] = k;
							break outerloop;
						}
					}
				}
			}
		return stoneCoords;
	}

}