package nordryd.modplayground;

import java.io.PrintWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import nordryd.modplayground.init.ModBlocks;
import nordryd.modplayground.init.ModItems;
import nordryd.modplayground.util.Reference;
import nordryd.modplayground.world.OreGenerator;

@Mod(Reference.MOD_ID)
public class Main
{
	public static Main instance;
	private static final Logger logger = LogManager.getLogger(Reference.MOD_ID);

	public Main() {
		instance = this;

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		OreGenerator.setupOreGenerator();
		logger.info("setup() method registered");
	}

	private void clientRegistries(final FMLClientSetupEvent event) {
		logger.info("clientRegistries() method registered");
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			for(Item item : ModItems.ITEMS) {
				event.getRegistry().register(item);
			}

			logger.info("Items registered");
		}

		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			for(Block block : ModBlocks.BLOCKS) {
				event.getRegistry().register(block);
			}
			logger.info("Blocks registered");
		}
	}
}
