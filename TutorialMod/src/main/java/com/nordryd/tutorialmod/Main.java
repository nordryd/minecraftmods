package com.nordryd.tutorialmod;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nordryd.tutorialmod.init.ModBiomes;
import com.nordryd.tutorialmod.init.ModRecipes;
import com.nordryd.tutorialmod.init.ModWorldGen;
import com.nordryd.tutorialmod.proxy.CommonProxy;
import com.nordryd.tutorialmod.util.Reference;
import com.nordryd.tutorialmod.util.handlers.RegistryHandler;
import com.nordryd.tutorialmod.world.gen.testVillage.MapGenTestVillage;
import com.nordryd.tutorialmod.world.gen.testVillage.TrapHouse;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

/**
 * Initializes the Ice Cream Mod.
 * <ul>
 * <li><b>init</b>: Contains classes pertaining to initialization and retrieval
 * of the mod's items, blocks, and smelting recipes.</li>
 * <li><b>proxy</b>: Contains classes for client and server proxies.</li>
 * <li><b>util</b>: Contains classes and interfaces for referential values and
 * templates.</li>
 * <li><b>items</b>: Contains classes for all the items in the mod</li>
 * <li><b>blocks</b>: Contains classes for all the blocks in the mod</li>
 * <li><b>world</b>: Contains classes for world generation modifications like
 * structures, trees, biomes, and world types.</li>
 * </ul>
 * 
 * @author Nordryd
 */
@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main
{
	// Instance of our Main class
	@Instance
	public static Main instance;

	// Log file
	public static final Logger logger = LogManager.getLogger(Reference.MOD_ID);

	// Use Reference.java to point to the location of the ClientProxy and
	// CommonProxy classes
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		System.out.println("** PRE-INITIALIZATION **");
		RegistryHandler.preInitRegistries();
		//MinecraftForge.EVENT_BUS.register(new VillageTesting());
		//MinecraftForge.TERRAIN_GEN_BUS.register(new VillageTesting());
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
		System.out.println("** INITIALIZATION **");
		ModRecipes.init();
		MapGenVillage.VILLAGE_SPAWN_BIOMES = Arrays.<Biome>asList(Biomes.PLAINS, Biomes.DESERT, Biomes.SAVANNA, Biomes.TAIGA, Biomes.EXTREME_HILLS,
				Biomes.JUNGLE_EDGE, ModBiomes.ICE_CREAM);
		
		MapGenStructureIO.registerStructure(MapGenTestVillage.Start.class, Reference.MOD_ID + ":test_village");
		MapGenStructureIO.registerStructureComponent(TrapHouse.class, "trap_house");
		VillagerRegistry.instance().registerVillageCreationHandler(ModWorldGen.TEST_ADD_VILL_COMP_CREATION_HANDLER);
		

	}

	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {
		System.out.println("** POST-INITIALIZATION **");
		RegistryHandler.postInitRegistries();
	}
}
