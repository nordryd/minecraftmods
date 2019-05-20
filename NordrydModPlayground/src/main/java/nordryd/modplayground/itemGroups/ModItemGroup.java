package nordryd.modplayground.itemGroups;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import nordryd.modplayground.init.ModItems;

/**
 * <p>Item as icon: Item itself</p>
 * 
 * <p>Block as icon: Item.BLOCK_TO_ITEM.get(the block)</p>
 * 
 * <p>BLOCK_TO_ITEM maps Items to their Block equivalents.</p>
 * @author Nordryd
 */
public class ModItemGroup extends ItemGroup
{
	public ModItemGroup(String name) {
		super(name);
	}
	
	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.different_item);
	}
}
