package com.nordryd.tutorialmod.blocks;

import com.google.common.base.Preconditions;
import com.nordryd.tutorialmod.init.ModBlocks;

import net.minecraft.block.material.Material;

/**
 * Special {@link BlockBase} for <i>ores</i>.
 * 
 * @author Nordryd
 */
public class Ore extends BlockBase
{
	private final int dimensionId, chance, minVeinSize, maxVeinSize, minY, maxY;

	/**
	 * Constructor for OreBase
	 * 
	 * @param name
	 *            Filename of this ore.
	 * @param material
	 *            Material of this ore.
	 * @param dimensionId
	 *            Dimension this ore spawns in.
	 * @param chance
	 *            The chance this ore has to spawn as an integer percentage within
	 *            (0, 100].
	 * @param minVeinSize
	 *            The minimum vein size of this ore.
	 * @param maxVeinSize
	 *            The maximum vein size of this ore.
	 * @param minY
	 *            The minimum height this ore may spawn at.
	 * @param maxY
	 *            The maximum height this ore may spawn at.
	 * @param generateIn
	 *            The type of block this ore spawns in.
	 */
	protected Ore(String name, Material material, int dimensionId, int chance, int minVeinSize, int maxVeinSize, int minY, int maxY) {
		super(name, material);

		Preconditions.checkArgument((minY <= maxY) && (minY >= 0) && (maxY <= 256), "ERROR: Invalid values for minY and maxY!");
		Preconditions.checkArgument(chance > 0,
				"ERROR: Chance to spawn must be greater than 0! Why are you making a new ore only to have it not spawn?");
		Preconditions.checkArgument(chance <= 100, "ERROR: Chance to spawn cannot be greater than 100!");
		this.dimensionId = dimensionId;
		this.chance = chance;
		this.minVeinSize = minVeinSize;
		this.maxVeinSize = maxVeinSize;
		this.minY = minY;
		this.maxY = maxY;

		ModBlocks.ORES.add(this);
	}

	/**
	 * @return The dimension this ore spawns in.<br/>
	 *         0 = Overworld<br/>
	 *         -1 = Nether<br/>
	 *         1 = End<br/>
	 */
	public int getDimensionId() {
		return dimensionId;
	}

	/**
	 * @return The chance this ore may spawn (as a percentage).
	 */
	public int getChance() {
		return chance;
	}

	/**
	 * @return The minimum vein size of this ore.
	 */
	public int getMinVeinSize() {
		return minVeinSize;
	}

	/**
	 * @return The maximum vein size of this ore.
	 */
	public int getMaxVeinSize() {
		return maxVeinSize;
	}

	/**
	 * @return The minimum height this ore may spawn at.
	 */
	public int getMinY() {
		return minY;
	}

	/**
	 * @return The maximum height this ore may spawn at.
	 */
	public int getMaxY() {
		return maxY;
	}
}
