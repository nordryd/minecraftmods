package com.nordryd.tutorialmod.init;

import com.nordryd.tutorialmod.world.biome.BiomeIceCream;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

/**
 * Contains all the biomes for the mod. Add a new biome:
 * <ol>
 * <li>Create biome object in this class:<br/>
 * {@code public static final Biome [name] = new [BiomeObject]()}</li>
 * <li>Initialize the biome within {@code registerBiomes()}:<br/>
 * {@code initBiome([object], [name of biome], [BiomeType.[]], Type...)}<br/>
 * F3 Types and BiomeType in order to see their parameters for each biome type
 * and gain some perspective</li>
 * <li>Create Biome[type] class in com.nordryd.tutorialmod.world.biome</li>
 * <li>Add properties to the biome with:<br/>
 * BiomeProperty(biomename).properties...</li>
 * </ol>
 * 
 * @author Nordryd
 */
public class ModBiomes
{	
	public static final Biome ICE_CREAM = new BiomeIceCream("ICE CREAM!!!");
	
	public static void registerBiomes() {
		initBiome(ICE_CREAM, "ICE CREAM!!!", BiomeType.COOL, Type.HILLS, Type.COLD);
	}

	private static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type... types) {
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 10));
		BiomeManager.addSpawnBiome(biome);
		return biome;
	}
}
