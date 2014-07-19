package com.unrelentless.fcraft.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FCraftCreativeTabWeapons extends CreativeTabs{

	public FCraftCreativeTabWeapons(int tabID, String label) {
		super(tabID, label);
	}

    @Override
    @SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel()
    {
        return "Fantasy Craft Weapons";
    }
    
	@Override
	public Item getTabIconItem() {
		// TODO Auto-generated method stub
		return Items.fireworks;
	}

}
