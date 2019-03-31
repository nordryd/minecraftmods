package com.nordryd.tutorialmod.init;

import com.nordryd.tutorialmod.blocks.variant.IceCreamBlocks;
import com.nordryd.tutorialmod.util.handlers.EnumHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * All creative tabs for this mod.
 * 
 * @author Nordryd
 */
public class ModCreativeTabs
{
	public static final CreativeTabs ITEMS = new CreativeTabs("tab_tutorial_items") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItems.CHOCOLATE);
		}
	};

	public static final CreativeTabs BLOCKS = new CreativeTabs("tab_tutorial_blocks") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModBlocks.ICE_CREAM_BLOCKS.getDefaultState()
					.withProperty(IceCreamBlocks.VARIANT, EnumHandler.Flavors.VANILLA).getBlock());
		}
	};

	public static final CreativeTabs TOOLS = new CreativeTabs("tab_tutorial_tools") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItems.OREO_PICKAXE);
		}
	};

	public static final CreativeTabs COMBAT = new CreativeTabs("tab_tutorial_combat") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItems.OREO_SWORD);
		}
	};

	public static final CreativeTabs FOOD = new CreativeTabs("tab_tutorial_food") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItems.OREO);
		}
	};
	
	public static final CreativeTabs DECOR = new CreativeTabs("tab_tutorial_decor") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModBlocks.SAPLINGS.getDefaultState()
					.withProperty(IceCreamBlocks.VARIANT, EnumHandler.Flavors.VANILLA).getBlock());
		}
	};
}
