package com.nordryd.tutorialmod.world.gen.trees;

import java.util.Random;

import com.nordryd.tutorialmod.blocks.variant.Leaves;
import com.nordryd.tutorialmod.blocks.variant.Logs;
import com.nordryd.tutorialmod.blocks.variant.Saplings;
import com.nordryd.tutorialmod.init.ModBlocks;
import com.nordryd.tutorialmod.util.handlers.EnumHandler;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class ChocolateTree extends WorldGenAbstractTree
{
	public static final IBlockState LOG = ModBlocks.LOGS.getDefaultState().withProperty(Logs.VARIANT, EnumHandler.Flavors.CHOCOLATE);
	public static final IBlockState LEAF = ModBlocks.LEAVES.getDefaultState().withProperty(Leaves.VARIANT, EnumHandler.Flavors.CHOCOLATE);
	public static final int HEIGHT_VARIATION = 3;
	private int minHeight;

	public ChocolateTree() {
		super(false);
		this.minHeight = 12;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int height = minHeight + rand.nextInt(HEIGHT_VARIATION);
		boolean flag = true;

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		// Validating that tree can grow here
		for (int yPos = y; yPos <= y + height + 1; yPos++) {
			int b0 = 2;
			if (yPos == y) {
				b0 = 1;
			}

			if (yPos >= y + 1 + height - 2) {
				b0 = 2;
			}

			// BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

			for (int xPos = x - b0; (xPos <= x + b0) && flag; xPos++) {
				for (int zPos = z - b0; (zPos <= z + b0) && flag; zPos++) {
					if ((yPos >= 0) && (yPos < world.getHeight())) {
						if (!this.isReplaceable(world, new BlockPos(xPos, yPos, zPos))) {
							flag = false;
						}
					} else {
						flag = false;
					}
				}
			}
		}

		if (!flag) { // Insufficient space for tree to grow. Terminate.
			return false;
		} else { // Sufficient space for tree to grow. Proceed.
			BlockPos down = pos.down(); // offsets by 1 position down
			IBlockState state = world.getBlockState(down);
			boolean isSoil = state.getBlock().canSustainPlant(state, world, down, EnumFacing.UP, (Saplings) ModBlocks.SAPLINGS);

			// Sufficient space for tree to grow and is on soil. Proceed with growing tree.
			if (isSoil && (y < world.getHeight() - height - 1)) {
				// Look at other trees' code and see if you want other tree generations
				state.getBlock().onPlantGrow(state, world, down, pos);

				// Decipher all of these bX values (including the b0 up above)
				for (int yPos = y - 3 + height; yPos <= y + height; yPos++) {
					int b1 = yPos - (y + height);
					int b2 = 1 - (b1 / 2);

					for (int xPos = x - b2; xPos <= x + b2; xPos++) {
						int b3 = xPos - x;

						for (int zPos = z - b2; zPos <= z + b2; zPos++) {
							int b4 = zPos - z;

							if ((Math.abs(b3) != b2) || (Math.abs(b4) != b2) || (rand.nextInt(2) != 0) && (b1 != 0)) {
								BlockPos treePos = new BlockPos(xPos, yPos, zPos);
								IBlockState treeState = world.getBlockState(treePos);

								// Leaf configuration
								if ((treeState.getBlock().isAir(treeState, world, treePos))
										|| (treeState.getBlock().isAir(treeState, world, treePos))) {
									// generates leaves at 3/4 down the tree and 1/2 down the tree
									this.setBlockAndNotifyAdequately(world, treePos, LEAF);
									this.setBlockAndNotifyAdequately(world, treePos.add(0, -0.25 * height, 0), LEAF);
									this.setBlockAndNotifyAdequately(world, treePos.add(0, -0.5 * height, 0), LEAF);
								}
							}
						}
					}
				}

				// Log Configuration
				for (int logHeight = 0; logHeight < height; logHeight++) {
					BlockPos up = pos.up(logHeight);
					IBlockState logState = world.getBlockState(up);
					if ((logState.getBlock().isAir(logState, world, up)) || (logState.getBlock().isLeaves(logState, world, up))) {
						// generates leaves at 3/4 down the tree and 1/2 down the tree
						this.setBlockAndNotifyAdequately(world, pos.up(logHeight), LOG);
					}
				}

				return true;
			}
		}

		return true;
	}
}
