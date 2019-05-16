package nordryd.modplayground.item.tool;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemPickaxe;

public class ModItemPickaxe extends ItemPickaxe
{
	public ModItemPickaxe(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties properties) {
		super(tier, attackDamageIn, attackSpeedIn, properties);
	}
}
