package nordryd.modplayground.init;

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
	public static final List<Block> BLOCKS = new ArrayList<>();

	public static final Block differentBlock = new ModBlock("different_block",
			Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 5.0f).lightValue(5).sound(SoundType.SLIME),
			new Item.Properties().group(ModItemGroups.MAIN));
	public static final Block different_ore = new ModBlock("different_ore",
			Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 3.0f).lightValue(3).sound(SoundType.SLIME),
			new Item.Properties().group(ModItemGroups.MAIN));
	public static final Block different_ore_nether = new ModBlock("different_ore_nether",
			Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 3.0f).lightValue(3).sound(SoundType.SLIME),
			new Item.Properties().group(ModItemGroups.MAIN));
}
