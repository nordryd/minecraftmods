package com.nordryd.tutorialmod.init;

import java.util.ArrayList;
import java.util.List;

import com.nordryd.tutorialmod.blocks.CookieOre;
import com.nordryd.tutorialmod.blocks.Ore;
import com.nordryd.tutorialmod.blocks.OreoBlock;
import com.nordryd.tutorialmod.blocks.RubyBlock;
import com.nordryd.tutorialmod.blocks.RubyOre;
import com.nordryd.tutorialmod.blocks.variant.IceCreamBlocks;
import com.nordryd.tutorialmod.blocks.variant.Leaves;
import com.nordryd.tutorialmod.blocks.variant.Logs;
import com.nordryd.tutorialmod.blocks.variant.Planks;
import com.nordryd.tutorialmod.blocks.variant.Saplings;
import com.nordryd.tutorialmod.util.variables.Dimensions;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Contains all of the blocks and ores for the Tutorial Mod mod.<br/>
 * Adding a new block:
 * <ol>
 * <li>Add the line:<br/>
 * {@code public static final Block [BLOCK_NAME] = new BlockBase("[block_name_lowercase]", Material.[Material]);}</li>
 * <li>Add block to <b>en_us.lang</b>:
 * {@code tile.[block_name_lowercase].name=[Block Name]}</li>
 * <li>Retrieve a texture for the block. Place it in<br/>
 * <b>src/main/resources/assets.[mod_id].textures.blocks</b></li>
 * <li>Create new <i>JSON</i> files. One in
 * <b>src/main/resources/assets.[mod_id].blockstates</b>,<br/>
 * <b>src/main/resources/assets.[mod_id].models.blocks</b>, and <br/>
 * <b>src/main/resources/assets.[mod_id].models.items</b>. All called
 * <b>[block_name_lowercase].json</b>. Copy and paste from other <i>JSON</i>
 * files.
 * </ol>
 * 
 * @author Nordryd
 */
public class ModBlocks
{
	public static final List<Block> BLOCKS = new ArrayList<>();
	public static final List<Ore> ORES = new ArrayList<>();

	// Generic Blocks
	public static final Block RUBY_BLOCK = new RubyBlock("ruby_block", Material.IRON);
	public static final Block OREO_BLOCK = new OreoBlock("oreo_block", Material.ROCK);

	// Ores
	public static final Ore RUBY_ORE = new RubyOre("ruby_ore", Material.ROCK, Dimensions.OVERWORLD, 50, 1, 6, 0, 100);
	public static final Ore COOKIE_ORE = new CookieOre("cookie_ore", Material.ROCK, Dimensions.OVERWORLD, 75, 1, 6, 0, 100);

	// Blocks with Variants based on Ice Cream Flavors
	public static final Block PLANKS = new Planks("planks");
	public static final Block LOGS = new Logs("logs");
	public static final Block LEAVES = new Leaves("leaves");
	public static final Block SAPLINGS = new Saplings("sapling");
	public static final Block ICE_CREAM_BLOCKS = new IceCreamBlocks("blocks");
}
