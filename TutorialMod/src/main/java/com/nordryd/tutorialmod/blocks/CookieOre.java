package com.nordryd.tutorialmod.blocks;

import java.util.Random;

import com.nordryd.tutorialmod.init.ModItems;
import com.nordryd.tutorialmod.util.variables.HarvestLevels;
import com.nordryd.tutorialmod.util.variables.Tools;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

/**
 * Class for <b>Oreo Ore</b>, and its specific properties.<br/>
 * <b>NOTE</b>: These properties are all <i>optional</i>.
 * 
 * @author Nordryd
 */
public class CookieOre extends Ore
{
	public CookieOre(String name, Material material, int dimensionId, int chance, int minVeinSize, int maxVeinSize, int minY, int maxY) {
		super(name, material, dimensionId, chance, minVeinSize, maxVeinSize, minY, maxY);

		// Block sound when PLAYER RUNS OVER IT
		setSoundType(SoundType.STONE);

		// Set hardness of the block WITH RESPECT TO PICKAXES
		// http://minecraftmodcustomstuff.wikia.com/wiki/Hardness
		// Dirt = 0.5F, Stone = 1.5F, Diamond Ore = 3F, Iron Block = 5F, Obsidian = 50F
		setHardness(5.0F);

		// Set strength against explosives
		// https://minecraft.gamepedia.com/Explosion
		setResistance(15.0F);

		// Set the type of tool, as well as the tier of tool required to harvest the
		// block
		// 0 = Wood+, 1=Stone+, 2=Iron+, 3=Diamond
		setHarvestLevel(Tools.PICKAXE, HarvestLevels.RUBY);

		// Set the intensity of light emitted from a block.
		// https://minecraft.gamepedia.com/Light
		// Redstone Torch = 7, Torch = 14
		setLightLevel(2.5F);

		// Set if light can pass through the block or not.
		// setLightOpacity(1);

		// Toggle if block is unbreakable.
		// setBlockUnbreakable();
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModItems.OREO_COOKIE;
	}

	@Override
	public int quantityDropped(Random random) {
		return random.nextInt(7) + 1;
	}
}
