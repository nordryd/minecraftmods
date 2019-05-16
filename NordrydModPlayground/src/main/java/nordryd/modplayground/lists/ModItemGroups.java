package nordryd.modplayground.lists;

import net.minecraft.item.Item;
import nordryd.modplayground.itemGroups.ModItemGroup;

public class ModItemGroups
{
	public static final ModItemGroup playgroundItems = new ModItemGroup("playgroundItems", ModItems.start_item);
	public static final ModItemGroup playgroundBlocks = new ModItemGroup("playgroundBlocks", Item.BLOCK_TO_ITEM.get(ModBlocks.start_block));
}
