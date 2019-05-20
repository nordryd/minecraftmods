package nordryd.modplayground.item.enums;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import nordryd.modplayground.init.ModItems;
import nordryd.modplayground.util.Reference;

/**
 * <p>Go to <b><i>forge-1.13.2-25.0.209</b></i>.net.minecraft.util.<i><b>SoundEvent.class</b></i> for all the sounds possible when you put on armor.</p>
 * @author Nordryd
 */
public enum ModArmorMaterial implements IArmorMaterial
{
	DIFFERENT("different", 400, 25, ModItems.different_item, "block.slime_block.break", 0.0f, 8, 9, 10, 7);
	
	private static final int NUMBER_OF_DAMAGE_REDUCTION_AMOUNTS_NEEDED = 4;
	private static final int[] MAX_DAMAGE_ARRAY = {13, 15, 16, 11};
	
	private String name, equipSound;
	private int durability, enchantability;
	private int[] damageReductionAmounts;
	private float toughness;
	private Item repairItem;
	
	private ModArmorMaterial(String name, int durability, int enchantability, Item repairItem, String equipSound, float toughness, int... damageReductionAmounts){
		if(damageReductionAmounts.length != NUMBER_OF_DAMAGE_REDUCTION_AMOUNTS_NEEDED) {
			throw new IllegalArgumentException("Must have 4 damage reduction amounts!");
		}
		
		this.name = name;
		this.durability = durability;
		this.enchantability = enchantability;
		this.repairItem = repairItem;
		this.equipSound = equipSound;
		this.toughness = toughness;
		this.damageReductionAmounts = damageReductionAmounts;
	}
	
	@Override
	public int getDamageReductionAmount(EntityEquipmentSlot slot) {
		return damageReductionAmounts[slot.getIndex()];
	}
	
	@Override
	public int getDurability(EntityEquipmentSlot slot) {
		return MAX_DAMAGE_ARRAY[slot.getIndex()] * durability;
	}
	
	@Override
	public int getEnchantability() {
		return enchantability;
	}
	
	@Override
	public String getName() {
		return Reference.MOD_ID + ":" + name;
	}
	
	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(this.repairItem);
	}
	
	@Override
	public SoundEvent getSoundEvent() {
		return new SoundEvent(new ResourceLocation(equipSound));
	}
	
	@Override
	public float getToughness() {
		return toughness;
	}
}
