package nordryd.modplayground.item;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import nordryd.modplayground.init.ModItems;
import nordryd.modplayground.util.Reference;

public class ModItem extends Item
{
	public ModItem(String registryName, Item.Properties itemProperties) {
		super(itemProperties);
		this.setRegistryName(new ResourceLocation(Reference.MOD_ID, registryName));
		
		ModItems.ITEMS.add(this);
	}
}
