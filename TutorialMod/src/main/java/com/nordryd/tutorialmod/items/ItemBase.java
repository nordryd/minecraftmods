package com.nordryd.tutorialmod.items;

import com.nordryd.tutorialmod.Main;
import com.nordryd.tutorialmod.init.ModCreativeTabs;
import com.nordryd.tutorialmod.init.ModItems;
import com.nordryd.tutorialmod.util.IHasModel;

import net.minecraft.item.Item;

/**
 * Class for creating new mod blocks.
 * 
 * @author Nordryd
 */
public class ItemBase extends Item implements IHasModel
{
	public ItemBase(String name) {

		// Sets the item's name with "item" prepended.
		setUnlocalizedName(name);

		// Sets name in the registry
		setRegistryName(name);

		// Sets item to go into the MATERIALS tab of creative mode
		setCreativeTab(ModCreativeTabs.ITEMS);

		// Tell minecraft that this [object] is an item
		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}