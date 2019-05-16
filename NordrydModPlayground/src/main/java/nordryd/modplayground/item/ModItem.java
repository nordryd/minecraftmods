package nordryd.modplayground.item;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import nordryd.modplayground.lists.ModItems;
import nordryd.modplayground.util.Reference;

public class ModItem extends Item
{
	public ModItem(String name, Properties properties) {
		super(properties);
		this.setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
		
		ModItems.MOD_ITEMS.add(this);
	}
}
