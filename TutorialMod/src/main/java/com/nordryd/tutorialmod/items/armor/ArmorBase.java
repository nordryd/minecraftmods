package com.nordryd.tutorialmod.items.armor;

import com.nordryd.tutorialmod.Main;
import com.nordryd.tutorialmod.init.ModCreativeTabs;
import com.nordryd.tutorialmod.init.ModItems;
import com.nordryd.tutorialmod.util.IHasModel;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

/**
 * Base class for creating armor.
 * 
 * @author Nordryd
 */
public class ArmorBase extends ItemArmor implements IHasModel
{
	public ArmorBase(String name, ArmorMaterial material, int renderIndex, EntityEquipmentSlot equipmentSlot) {
		super(material, renderIndex, equipmentSlot);

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
