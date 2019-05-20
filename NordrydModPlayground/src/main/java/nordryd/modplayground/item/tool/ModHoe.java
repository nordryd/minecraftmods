package nordryd.modplayground.item.tool;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemHoe;
import net.minecraft.util.ResourceLocation;
import nordryd.modplayground.init.ModItems;
import nordryd.modplayground.util.Reference;

public class ModHoe extends ItemHoe	
{
	public ModHoe(String registryName, IItemTier tier, float attackSpeedIn, Properties properties) {
		super(tier, attackSpeedIn, properties);
		this.setRegistryName(new ResourceLocation(Reference.MOD_ID, registryName));
		
		ModItems.ITEMS.add(this);
	}
}