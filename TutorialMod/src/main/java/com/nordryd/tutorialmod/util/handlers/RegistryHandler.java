package com.nordryd.tutorialmod.util.handlers;

import com.nordryd.tutorialmod.init.ModBiomes;
import com.nordryd.tutorialmod.init.ModBlocks;
import com.nordryd.tutorialmod.init.ModItems;
import com.nordryd.tutorialmod.init.ModWorldGen;
import com.nordryd.tutorialmod.util.IHasModel;
import com.nordryd.tutorialmod.world.gen.ores.OreGenerator;
import com.nordryd.tutorialmod.world.gen.structures.StructureGenerator;
import com.nordryd.tutorialmod.world.gen.testVillage.TrapHouse;
import com.nordryd.tutorialmod.world.worldTypes.WorldTypeIceCream;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

/**
 * Handler for all things involving the {@link GameRegistry}.
 * 
 * @author Nordryd
 */
@EventBusSubscriber
public class RegistryHandler
{
	/**
	 * Registers all mod items in the registry.
	 * 
	 * @param event
	 */
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}

	/**
	 * Registers all mod blocks in the registry.
	 * 
	 * @param event
	 */
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
	}

	/**
	 * Registers all mod item and block models.
	 * 
	 * @param event
	 */
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {

		for (Item item : ModItems.ITEMS) {
			if (item instanceof IHasModel) {
				((IHasModel) item).registerModels();
			}
		}

		for (Block block : ModBlocks.BLOCKS) {
			if (block instanceof IHasModel) {
				((IHasModel) block).registerModels();
			}
		}
	}

	public static void preInitRegistries() {
		GameRegistry.registerWorldGenerator(new OreGenerator(), 0);
		GameRegistry.registerWorldGenerator(new StructureGenerator(), 0);

		ModBiomes.registerBiomes();
	}

	@SuppressWarnings("unused")
	public static void postInitRegistries() {
		WorldType ICECREAM = new WorldTypeIceCream();
	}
}
