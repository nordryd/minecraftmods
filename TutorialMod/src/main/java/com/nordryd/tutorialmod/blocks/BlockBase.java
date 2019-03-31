package com.nordryd.tutorialmod.blocks;

import com.nordryd.tutorialmod.Main;
import com.nordryd.tutorialmod.init.ModBlocks;
import com.nordryd.tutorialmod.init.ModCreativeTabs;
import com.nordryd.tutorialmod.init.ModItems;
import com.nordryd.tutorialmod.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

/**
 * Class for creating new mod blocks.
 * 
 * @author Nordryd
 */
public class BlockBase extends Block implements IHasModel
{
	public BlockBase(String name, Material material) {

		super(material);

		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(ModCreativeTabs.BLOCKS);

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName())); // Blocks must be both an ITEM and a BLOCK
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
