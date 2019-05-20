package nordryd.modplayground.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import nordryd.modplayground.item.ModItem;
import nordryd.modplayground.item.armor.ModArmor;
import nordryd.modplayground.item.enums.ModArmorMaterial;
import nordryd.modplayground.item.enums.ModToolMaterial;
import nordryd.modplayground.item.tool.ModAxe;
import nordryd.modplayground.item.tool.ModHoe;
import nordryd.modplayground.item.tool.ModPickaxe;
import nordryd.modplayground.item.tool.ModShovel;
import nordryd.modplayground.item.tool.ModSword;

/**
 * <p>
 * Don't forget the JSON files.
 * </p>
 * 
 * <p>
 * assets.nordrydmodplayground.models.item
 * </p>
 * 
 * @author Nordryd
 */
public class ModItems
{
	public static final List<Item> ITEMS = new ArrayList<>();
	
	public static final Item different_item = new ModItem("different_item", new Item.Properties().group(ModItemGroups.MAIN));

	// Tools
	public static final Item different_sword = new ModSword("different_sword", ModToolMaterial.DIFFERENT, 0, 6.0f,
			new Item.Properties().group(ModItemGroups.MAIN));
	public static final Item different_axe = new ModAxe("different_axe", ModToolMaterial.DIFFERENT, -1.0f, 6.0f,
			new Item.Properties().group(ModItemGroups.MAIN));
	public static final Item different_pickaxe = new ModPickaxe("different_pickaxe", ModToolMaterial.DIFFERENT, -2, 6.0f,
			new Item.Properties().group(ModItemGroups.MAIN));
	public static final Item different_hoe = new ModHoe("different_hoe", ModToolMaterial.DIFFERENT, 6.0f,
			new Item.Properties().group(ModItemGroups.MAIN));
	public static final Item different_shovel = new ModShovel("different_shovel", ModToolMaterial.DIFFERENT, -3.0f, 6.0f,
			new Item.Properties().group(ModItemGroups.MAIN));
	
	// Armor
	public static final Item different_helmet = new ModArmor("different_helmet", ModArmorMaterial.DIFFERENT, EntityEquipmentSlot.HEAD,
			new Item.Properties().group(ModItemGroups.MAIN));
	public static final Item different_chestpiece = new ModArmor("different_chestpiece", ModArmorMaterial.DIFFERENT, EntityEquipmentSlot.CHEST,
			new Item.Properties().group(ModItemGroups.MAIN));
	public static final Item different_leggings = new ModArmor("different_leggings", ModArmorMaterial.DIFFERENT, EntityEquipmentSlot.LEGS,
			new Item.Properties().group(ModItemGroups.MAIN));
	public static final Item different_boots = new ModArmor("different_boots", ModArmorMaterial.DIFFERENT, EntityEquipmentSlot.FEET,
			new Item.Properties().group(ModItemGroups.MAIN));
}
