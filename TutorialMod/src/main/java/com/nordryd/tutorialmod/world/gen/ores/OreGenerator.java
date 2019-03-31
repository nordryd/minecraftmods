package com.nordryd.tutorialmod.world.gen.ores;

import java.util.Random;

import com.nordryd.tutorialmod.blocks.Ore;
import com.nordryd.tutorialmod.init.ModBlocks;
import com.nordryd.tutorialmod.util.Reference;
import com.nordryd.tutorialmod.util.variables.Dimensions;

import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OreGenerator implements IWorldGenerator
{
	public OreGenerator() {
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		for (Ore ore : ModBlocks.ORES) {
			switch (ore.getDimensionId()) {
			case Dimensions.OVERWORLD:
				generateOverworld(ore, world, random, chunkX, chunkZ);
				break;
			case Dimensions.NETHER:
				generateNether(ore, world, random, chunkX, chunkZ);
				break;
			case Dimensions.END:
				generateEnd(ore, world, random, chunkX, chunkZ);
				break;
			default:
				throw new IllegalArgumentException("Dimension " + ore.getDimensionId() + " does not exist!");
			}
		}
	}

	private void generateOverworld(Ore ore, World world, Random rand, int x, int z) {
		generateOre(ore.getDefaultState(), world, rand, x, z, ore.getChance(), ore.getMinVeinSize(), ore.getMaxVeinSize(), ore.getMinY(),
				ore.getMaxY(),
				BlockMatcher.forBlock(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.STONE).getBlock()));
	}

	private void generateNether(Ore ore, World world, Random rand, int x, int z) {
		generateOre(ore.getDefaultState(), world, rand, x, z, ore.getChance(), ore.getMinVeinSize(), ore.getMaxVeinSize(), ore.getMinY(),
				ore.getMaxY(), BlockMatcher.forBlock(Blocks.NETHERRACK));
	}

	private void generateEnd(Ore ore, World world, Random rand, int x, int z) {
		generateOre(ore.getDefaultState(), world, rand, x, z, ore.getChance(), ore.getMinVeinSize(), ore.getMaxVeinSize(), ore.getMinY(),
				ore.getMaxY(), BlockMatcher.forBlock(Blocks.END_STONE));
	}

	private void generateOre(IBlockState block, World world, Random rand, int chunkX, int chunkZ, int chance, int minVeinSize, int maxVeinSize,
			int minY, int maxY, BlockMatcher generateIn) {
		int veinSize = rand.nextInt(maxVeinSize - minVeinSize) + minVeinSize;
		int heightRange = maxY - minY;

		WorldGenMinable gen = new WorldGenMinable(block, veinSize, generateIn);

		for (int i = 0; i < chance; i++) {
			int xRand = (chunkX * Reference.CHUNK_SIZE) + rand.nextInt(Reference.CHUNK_SIZE);
			int yRand = rand.nextInt(heightRange) + minY;
			int zRand = (chunkZ * Reference.CHUNK_SIZE) + rand.nextInt(Reference.CHUNK_SIZE);

			gen.generate(world, rand, new BlockPos(xRand, yRand, zRand));
		}
	}
}
