package com.nordryd.tutorialmod.items.tools;

import com.nordryd.tutorialmod.Main;
import com.nordryd.tutorialmod.init.ModCreativeTabs;
import com.nordryd.tutorialmod.init.ModItems;
import com.nordryd.tutorialmod.util.IHasModel;

import net.minecraft.item.ItemSword;

/**
 * Class to create new <i>sword</i> weapons.<br/>
 * The damage will be based on the material's <i>harvest level</i>.
 * 
 * @author Nordryd
 */
public class ToolSword extends ItemSword implements IHasModel
{
	public ToolSword(String name, ToolMaterial material) {
		super(material);

		// Sets the item's name with "item" prepended.
		setUnlocalizedName(name);

		// Sets name in the registry
		setRegistryName(name);

		// Sets item to go into the MATERIALS tab of creative mode
		setCreativeTab(ModCreativeTabs.COMBAT);

		// Tell minecraft that this [object] is an item
		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
