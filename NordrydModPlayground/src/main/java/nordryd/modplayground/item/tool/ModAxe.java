package nordryd.modplayground.item.tool;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemAxe;
import net.minecraft.util.ResourceLocation;
import nordryd.modplayground.init.ModItems;
import nordryd.modplayground.util.Reference;

public class ModAxe extends ItemAxe	
{
	public ModAxe(String registryName, IItemTier tier, float attackDamage, float attackSpeedIn, Properties properties) {
		super(tier, attackDamage, attackSpeedIn, properties);
		this.setRegistryName(new ResourceLocation(Reference.MOD_ID, registryName));
		
		ModItems.ITEMS.add(this);
	}
}