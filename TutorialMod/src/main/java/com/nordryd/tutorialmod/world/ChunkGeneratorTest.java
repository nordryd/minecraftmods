package com.nordryd.tutorialmod.world;

import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGeneratorOverworld;

public class ChunkGeneratorTest extends ChunkGeneratorOverworld{
	public ChunkGeneratorTest(World worldIn, long seed, boolean mapFeaturesEnabledIn, String generatorOptions) {
		super(worldIn, seed, mapFeaturesEnabledIn, generatorOptions);
	}
}