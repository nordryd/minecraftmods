package com.nordryd.tutorialmod.util.wrappers;

import net.minecraft.util.math.BlockPos;

/**
 * A wrapper for the {@code Iterable<BlockPos>} object returned from
 * {@link BlockPos#getAllInBox(BlockPos, BlockPos)}.
 * 
 * @author Nordryd
 */
public class BlockArea
{
	private final Iterable<BlockPos> area;

	/**
	 * Constructor
	 * 
	 * @param from
	 *            Block 1
	 * @param to
	 *            Block 2
	 */
	public BlockArea(BlockPos from, BlockPos to) {
		this(from.getX(), from.getY(), from.getZ(), to.getX(), to.getY(), to.getZ());
	}

	/**
	 * Constructor
	 * 
	 * @param x1
	 *            Block 1 X coordinate
	 * @param y1
	 *            Block 1 Y coordinate
	 * @param z1
	 *            Block 1 Z coordinate
	 * @param x2
	 *            Block 2 X coordinate
	 * @param y2
	 *            Block 2 Y coordinate
	 * @param z2
	 *            Block 2 Z coordinate
	 */
	public BlockArea(int x1, int y1, int z1, int x2, int y2, int z2) {
		this.area = BlockPos.getAllInBox(x1, y1, z1, x2, y2, z2);
	}

	/**
	 * @return the area as an iterable list of BlockPos objects.
	 */
	public Iterable<BlockPos> getArea() {
		return area;
	}
}
