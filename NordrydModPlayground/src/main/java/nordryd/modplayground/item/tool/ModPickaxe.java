package nordryd.modplayground.item.tool;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.ResourceLocation;
import nordryd.modplayground.init.ModItems;
import nordryd.modplayground.util.Reference;

public class ModPickaxe extends ItemPickaxe
{
	public ModPickaxe(String registryName, IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties properties) {
		super(tier, attackDamageIn, attackSpeedIn, properties);
		this.setRegistryName(new ResourceLocation(Reference.MOD_ID, registryName));
		
		ModItems.ITEMS.add(this);
	}
}
