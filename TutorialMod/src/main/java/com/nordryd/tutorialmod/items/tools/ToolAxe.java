package com.nordryd.tutorialmod.items.tools;

import com.nordryd.tutorialmod.Main;
import com.nordryd.tutorialmod.init.ModCreativeTabs;
import com.nordryd.tutorialmod.init.ModItems;
import com.nordryd.tutorialmod.util.IHasModel;

import net.minecraft.item.ItemAxe;

/**
 * Class to create new <i>axe</i> tools.<br/>
 * 
 * @author Nordryd
 */
public class ToolAxe extends ItemAxe implements IHasModel
{
	public ToolAxe(String name, ToolMaterial material) {
		this(name, material, material.getAttackDamage() + 2.0F, 1.0F);
	}

	public ToolAxe(String name, ToolMaterial material, float attackDamage, float attackSpeed) {
		super(material, attackDamage, attackSpeed);
		// Sets the item's name with "item" prepended.
		setUnlocalizedName(name);

		// Sets name in the registry
		setRegistryName(name);

		// Sets item to go into the MATERIALS tab of creative mode
		setCreativeTab(ModCreativeTabs.TOOLS);

		// Tell minecraft that this [object] is an item
		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
