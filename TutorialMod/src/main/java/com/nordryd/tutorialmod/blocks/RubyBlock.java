package com.nordryd.tutorialmod.blocks;

import com.nordryd.tutorialmod.util.variables.HarvestLevels;
import com.nordryd.tutorialmod.util.variables.Tools;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * Class for <b>Block of Ruby</b>, and its specific properties.<br/>
 * <b>NOTE</b>: These properties are all <i>optional</i>.
 * 
 * @author Nordryd
 */
public class RubyBlock extends BlockBase
{
	public RubyBlock(String name, Material material) {
		super(name, material);

		// Block sound when PLAYER RUNS OVER IT
		setSoundType(SoundType.METAL);

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
		setHarvestLevel(Tools.PICKAXE, HarvestLevels.DIAMOND);

		// Set the intensity of light emitted from a block.
		// https://minecraft.gamepedia.com/Light
		// Redstone Torch = 7, Torch = 14
		// setLightLevel(2.5F);

		// Set if light can pass through the block or not.
		// setLightOpacity(1);

		// Toggle if block is unbreakable.
		// setBlockUnbreakable();
	}
}
