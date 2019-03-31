package com.nordryd.tutorialmod.world.gen.structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.nordryd.tutorialmod.init.ModBiomes;
import com.nordryd.tutorialmod.init.ModBlocks;
import com.nordryd.tutorialmod.util.Reference;
import com.nordryd.tutorialmod.util.variables.Dimensions;
import com.nordryd.tutorialmod.world.gen.trees.ChocolateTree;
import com.nordryd.tutorialmod.world.gen.trees.MintTree;
import com.nordryd.tutorialmod.world.gen.trees.StrawberryTree;
import com.nordryd.tutorialmod.world.gen.trees.VanillaTree;

import net.minecraft.block.Block;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

/**
 * Generator for structures. This includes trees.
 * 
 * @author Nordryd
 */
public class StructureGenerator implements IWorldGenerator
{
	private final WorldGenerator VANILLA = new VanillaTree();
	private final WorldGenerator MINT = new MintTree();
	private final WorldGenerator STRAWBERRY = new StrawberryTree();
	private final WorldGenerator CHOCOLATE = new ChocolateTree();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
		case Dimensions.OVERWORLD:
			generateStructure(VANILLA, world, random, chunkX, chunkZ, 1, ModBlocks.ICE_CREAM_BLOCKS, Biomes.EXTREME_HILLS.getClass(),
					ModBiomes.ICE_CREAM.getClass());
			generateStructure(MINT, world, random, chunkX, chunkZ, 1, ModBlocks.ICE_CREAM_BLOCKS, Biomes.EXTREME_HILLS.getClass(),
					ModBiomes.ICE_CREAM.getClass());
			generateStructure(STRAWBERRY, world, random, chunkX, chunkZ, 1, ModBlocks.ICE_CREAM_BLOCKS, Biomes.EXTREME_HILLS.getClass(),
					ModBiomes.ICE_CREAM.getClass());
			generateStructure(CHOCOLATE, world, random, chunkX, chunkZ, 1, ModBlocks.ICE_CREAM_BLOCKS, Biomes.EXTREME_HILLS.getClass(),
					ModBiomes.ICE_CREAM.getClass());
			break;

		case Dimensions.NETHER:
			break;

		case Dimensions.END:
			break;
		}
	}

	private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock,
			Class<?>... classes) {
		List<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));

		int x = (chunkX * Reference.CHUNK_SIZE) + random.nextInt(Reference.CHUNK_SIZE - 1);
		int z = (chunkZ * Reference.CHUNK_SIZE) + random.nextInt(Reference.CHUNK_SIZE - 1);
		int y = calculateGenerationHeight(world, x, z, topBlock);
		BlockPos pos = new BlockPos(x, y, z);

		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();

		// if (world.getWorldType() != WorldType.FLAT) {
		if (classesList.contains(biome)) {
			if (random.nextInt(chance) == 0) {
				generator.generate(world, random, pos);
			}
		}
		// }
	}

	private static int calculateGenerationHeight(World world, int x, int z, Block topBlock) {
		int y = world.getHeight();
		boolean foundGround = false;

		while ((!foundGround) && (y-- >= 0)) {
			Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
			foundGround = block == topBlock;
		}

		return y;
	}
}