package com.nordryd.tutorialmod.init;

import java.util.ArrayList;
import java.util.List;

import com.nordryd.tutorialmod.items.Food;
import com.nordryd.tutorialmod.items.ItemBase;
import com.nordryd.tutorialmod.items.armor.ArmorBase;
import com.nordryd.tutorialmod.items.tools.ToolAxe;
import com.nordryd.tutorialmod.items.tools.ToolHoe;
import com.nordryd.tutorialmod.items.tools.ToolPickaxe;
import com.nordryd.tutorialmod.items.tools.ToolSpade;
import com.nordryd.tutorialmod.items.tools.ToolSword;
import com.nordryd.tutorialmod.util.Reference;
import com.nordryd.tutorialmod.util.variables.HarvestLevels;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Contains all of the items, tools, and {@link ToolMaterial}s for the Tutorial
 * Mod mod.<br/>
 * Adding a new item:
 * <ol>
 * <li>Add the line:<br/>
 * {@code public static final Item [ITEM_NAME] = new ItemBase("[item_name_lowercase]");}</li>
 * <li>Add item to <b>en_us.lang</b>:
 * {@code item.[item_name_lowercase].name=[Item Name]}</li>
 * <li>Retrieve a texture for the item. Place it in<br/>
 * <b>src/main/resources/assets.[mod_id].textures.items</b></li>
 * <li>Create a new <i>JSON</i> file: <b>[item_name_lowercase].json</b>.<br/>
 * Change the line {@code "layer0": "tm:items/[item_name_lowercase]"}</li>
 * </ol>
 * 
 * Adding a new tool:
 * <ol>
 * <li>Make a new material using:<br/>
 * {@code public static final ToolMaterial MATERIAL_[NAME] = EnumHelper.addToolMaterial(name, toolTier, maxUses, efficiency, damage, enchantablility);}</li>
 * <li>Add the line:<br/>
 * {@code public static final Item[ToolType] [TOOL_NAME] = new Tool[ToolType]([tool_name, ToolMaterial]);}<br/>
 * <b>NOTE</b>: If the Tool[ToolType] class has not been created, it will need
 * to be. Use the other ones as templates.</li>
 * <li>Add item to <b>en_us.lang</b>:
 * {@code item.[tool_name_lowercase].name=[Item Name]}</li>
 * <li>Retrieve a texture for the item. Place it in<br/>
 * <b>src/main/resources/assets.[mod_id].textures.items</b></li>
 * <li>Create a new <i>JSON</i> file: <b>[tool_name_lowercase].json</b>.<br/>
 * {@code "parent": "item/handheld"}<br/>
 * {@code "layer0": "tm:items/[item_name_lowercase]"}</li>
 * </ol>
 * 
 * <p>
 * <a
 * href=https://docs.google.com/spreadsheets/d/1vvsVxfxhrOZJjGOqtkHOcK87bLBEdVCHZS9hOvMB_D0/edit#gid=0>Armor
 * Spreadsheet</a>
 * </p>
 * <p>
 * Minecraft items for the <i>JSON</i> files are located in <b>Referenced Libraries &gt forgeSrc &gt assets &gt minecraft &gt models &gt block/item</b>.
 * </p>
 * @author Nordryd
 */
public class ModItems
{
	public static final List<Item> ITEMS = new ArrayList<>();

	// Materials
	// Armor Spreadsheet
	// https://docs.google.com/spreadsheets/d/1vvsVxfxhrOZJjGOqtkHOcK87bLBEdVCHZS9hOvMB_D0/edit#gid=0
	// Reduction amounts: {feet, legs, chest, helm}
	public static final ToolMaterial MATERIAL_RUBY = EnumHelper.addToolMaterial("material_ruby", HarvestLevels.RUBY, 250, 8.0F, 6.0F, 10);
	public static final ArmorMaterial ARMOR_MATERIAL_RUBY = EnumHelper.addArmorMaterial("armor_material_ruby", (Reference.MOD_ID + ":ruby"), 14,
			new int[] { 5, (5 * 3), (5 * 5), (5 * 2) }, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F);

	public static final ToolMaterial MATERIAL_OREO = EnumHelper.addToolMaterial("material_oreo", HarvestLevels.OREO, 1337, 10.0F, 8.0F, 10);
	public static final ArmorMaterial ARMOR_MATERIAL_OREO = EnumHelper.addArmorMaterial("armor_material_oreo", (Reference.MOD_ID + ":oreo"), 18,
			new int[] { 69, (69 + 69 + 69), (69 + 69 + 69 + 69 + 69), (69 + 69) }, 25, SoundEvents.BLOCK_GRAVEL_BREAK, 5.0F);

	// Items
	public static final Item RUBY = new ItemBase("ruby");
	public static final Item CREME = new ItemBase("creme");
	
	public static final Item VANILLA_BEAN = new ItemBase("vanilla_bean");
	public static final Item MINT_LEAF = new ItemBase("mint_leaf");
	

	// Food
	public static final Item OREO_COOKIE = new Food("oreo_cookie", 1, 0.5, false);
	public static final Item SUGAR_CONE = new Food("sugar_cone", 1, 0.0, false);
	public static final Item OREO = new Food("oreo", 6, 10.0, false);
	public static final Item MINT_OREO = new Food("mint_oreo", 6, 10.5, false);
	public static final Item STRAWBERRY = new Food("strawberry", 1, 0.5, false);
	public static final Item CHOCOLATE = new Food("chocolate", 1, 0.5, false);
	
	public static final Item VANILLA_ICE_CREAM = new Food("vanilla_ice_cream", 2, 1.0, false);
	public static final Item MINT_ICE_CREAM = new Food("mint_ice_cream", 2, 1.0, false);
	public static final Item STRAWBERRY_ICE_CREAM = new Food("strawberry_ice_cream", 2, 1.0, false);
	public static final Item CHOCOLATE_ICE_CREAM = new Food("chocolate_ice_cream", 2, 1.0, false);
	
	public static final Item VANILLA_ICE_CREAM_CONE = new Food("vanilla_ice_cream_cone", 4, 6.0, false);
	public static final Item MINT_ICE_CREAM_CONE = new Food("mint_ice_cream_cone", 4, 6.0, false);
	public static final Item STRAWBERRY_ICE_CREAM_CONE = new Food("strawberry_ice_cream_cone", 4, 6.0, false);
	public static final Item CHOCOLATE_ICE_CREAM_CONE = new Food("chocolate_ice_cream_cone", 4, 6.0, false);

	// Tools
	public static final ItemSword RUBY_SWORD = new ToolSword("ruby_sword", MATERIAL_RUBY);
	public static final ItemSpade RUBY_SHOVEL = new ToolSpade("ruby_shovel", MATERIAL_RUBY);
	public static final ItemPickaxe RUBY_PICKAXE = new ToolPickaxe("ruby_pickaxe", MATERIAL_RUBY);
	public static final ItemAxe RUBY_AXE = new ToolAxe("ruby_axe", MATERIAL_RUBY);
	public static final ItemHoe RUBY_HOE = new ToolHoe("ruby_hoe", MATERIAL_RUBY);
	
	public static final ItemSword OREO_SWORD = new ToolSword("oreo_sword", MATERIAL_OREO);
	public static final ItemSpade OREO_SHOVEL = new ToolSpade("oreo_shovel", MATERIAL_OREO);
	public static final ItemPickaxe OREO_PICKAXE = new ToolPickaxe("oreo_pickaxe", MATERIAL_OREO);
	public static final ItemAxe OREO_AXE = new ToolAxe("oreo_axe", MATERIAL_OREO);
	public static final ItemHoe OREO_HOE = new ToolHoe("oreo_hoe", MATERIAL_OREO);

	// Armor (Legs have a render index of 2 for some reason)
	public static final Item RUBY_HELMET = new ArmorBase("ruby_helmet", ARMOR_MATERIAL_RUBY, 1, EntityEquipmentSlot.HEAD);
	public static final Item RUBY_CHESTPLATE = new ArmorBase("ruby_chestplate", ARMOR_MATERIAL_RUBY, 1, EntityEquipmentSlot.CHEST);
	public static final Item RUBY_LEGGINGS = new ArmorBase("ruby_leggings", ARMOR_MATERIAL_RUBY, 2, EntityEquipmentSlot.LEGS);
	public static final Item RUBY_BOOTS = new ArmorBase("ruby_boots", ARMOR_MATERIAL_RUBY, 1, EntityEquipmentSlot.FEET);
	
	public static final Item OREO_HELMET = new ArmorBase("oreo_helmet", ARMOR_MATERIAL_OREO, 1, EntityEquipmentSlot.HEAD);
	public static final Item OREO_CHESTPLATE = new ArmorBase("oreo_chestplate", ARMOR_MATERIAL_OREO, 1, EntityEquipmentSlot.CHEST);
	public static final Item OREO_LEGGINGS = new ArmorBase("oreo_leggings", ARMOR_MATERIAL_OREO, 2, EntityEquipmentSlot.LEGS);
	public static final Item OREO_BOOTS = new ArmorBase("oreo_boots", ARMOR_MATERIAL_OREO, 1, EntityEquipmentSlot.FEET);
}
