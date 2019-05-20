package nordryd.modplayground.item.tool;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import nordryd.modplayground.init.ModItems;
import nordryd.modplayground.util.Reference;

public class ModSword extends ItemSword	
{
	public ModSword(String registryName, IItemTier tier, int attackDamage, float attackSpeedIn, Properties properties) {
		super(tier, attackDamage, attackSpeedIn, properties);
		this.setRegistryName(new ResourceLocation(Reference.MOD_ID, registryName));
		
		ModItems.ITEMS.add(this);
	}
}