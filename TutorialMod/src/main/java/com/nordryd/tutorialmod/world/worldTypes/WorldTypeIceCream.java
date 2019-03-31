package com.nordryd.tutorialmod.world.worldTypes;

import com.nordryd.tutorialmod.init.ModBiomes;
import com.nordryd.tutorialmod.world.types.layer.GenLayerIceCream;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.layer.GenLayer;

public class WorldTypeIceCream extends WorldType
{
	public WorldTypeIceCream() {
		super("Ice Cream");
	}
	
	@Override
	public BiomeProvider getBiomeProvider(World world) {
		return new BiomeProviderSingle(ModBiomes.ICE_CREAM);
	}
	
	@Override
	public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer, ChunkGeneratorSettings chunkSettings) {
		return new GenLayerIceCream(worldSeed, parentLayer, this, chunkSettings);
	}
}
