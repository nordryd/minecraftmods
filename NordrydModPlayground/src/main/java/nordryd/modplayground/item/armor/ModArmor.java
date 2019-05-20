package nordryd.modplayground.item.armor;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;
import nordryd.modplayground.init.ModItems;
import nordryd.modplayground.util.Reference;

public class ModArmor extends ItemArmor
{
	public ModArmor(String registryName, IArmorMaterial material, EntityEquipmentSlot equipmentSlot, Properties properties) {
		super(material, equipmentSlot, properties);
		this.setRegistryName(new ResourceLocation(Reference.MOD_ID, registryName));
		
		ModItems.ITEMS.add(this);
	}
}
