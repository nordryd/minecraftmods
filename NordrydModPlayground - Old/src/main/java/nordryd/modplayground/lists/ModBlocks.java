package nordryd.modplayground.lists;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import nordryd.modplayground.block.ModBlock;

/**
 * <p>
 * Don't forget the 3 JSON files.
 * </p>
 * 
 * <p>
 * assets.nordrydmodplayground.blockstates <br/>
 * assets.nordrydmodplayground.models.block <br/>
 * assets.nordrydmodplayground.models.item
 * </p>
 * 
 * @author Nordryd
 */
public class ModBlocks
{
	public static final List<Block> MOD_BLOCKS = new ArrayList<>();

	public static Block start_block = new ModBlock("start_block",
			Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 5.0f).lightValue(10).sound(SoundType.SLIME),
			new Item.Properties().group(ModItemGroups.playgroundBlocks));
}
