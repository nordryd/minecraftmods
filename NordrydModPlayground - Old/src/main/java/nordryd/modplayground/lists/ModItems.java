package nordryd.modplayground.lists;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import nordryd.modplayground.item.ModItem;

/**
 * <p>
 * Don't forget the JSON files.
 * </p>
 * 
 * <p>
 * assets.nordrydmodplayground.models.item
 * </p>
 * 
 * @author Nordryd
 */
public class ModItems
{
	public static final List<Item> MOD_ITEMS = new ArrayList<>();
	public static final List<Item> MOD_BLOCKS_AS_ITEMS = new ArrayList<>();

	public static ModItem start_item = new ModItem("start_item", new Item.Properties().group(ModItemGroups.playgroundItems));
}
