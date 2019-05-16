package nordryd.modplayground;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import nordryd.modplayground.init.ModArmorMaterial;
import nordryd.modplayground.init.ModBlocks;
import nordryd.modplayground.init.ModItemGroups;
import nordryd.modplayground.init.ModItems;
import nordryd.modplayground.init.ModToolMaterial;
import nordryd.modplayground.item.tool.ModItemAxe;
import nordryd.modplayground.item.tool.ModItemPickaxe;
import nordryd.modplayground.util.Reference;

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
			event.getRegistry().registerAll(
					ModItems.start_item = new Item(new Item.Properties().group(ModItemGroups.PLAYGROUND)).setRegistryName(getLocation("start_item")),
					ModItems.start_block = new ItemBlock(ModBlocks.start_block, new Item.Properties().group(ModItemGroups.PLAYGROUND))
							.setRegistryName(ModBlocks.start_block.getRegistryName()),
					ModItems.different_axe = new ModItemAxe(ModToolMaterial.DIFFERENT, -1.0f, 6.0f,
							new Item.Properties().group(ModItemGroups.PLAYGROUND)).setRegistryName(getLocation("different_axe")),
					ModItems.different_hoe = new ItemHoe(ModToolMaterial.DIFFERENT, 6.0f, new Item.Properties().group(ModItemGroups.PLAYGROUND))
							.setRegistryName(getLocation("different_hoe")),
					ModItems.different_pickaxe = new ModItemPickaxe(ModToolMaterial.DIFFERENT, -2, 6.0f,
							new Item.Properties().group(ModItemGroups.PLAYGROUND)).setRegistryName(getLocation("different_pickaxe")),
					ModItems.different_shovel = new ItemSpade(ModToolMaterial.DIFFERENT, -3.0f, 6.0f,
							new Item.Properties().group(ModItemGroups.PLAYGROUND)).setRegistryName(getLocation("different_shovel")),
					ModItems.different_sword = new ItemSword(ModToolMaterial.DIFFERENT, 0, 6.0f,
							new Item.Properties().group(ModItemGroups.PLAYGROUND)).setRegistryName(getLocation("different_sword")),
					ModItems.different_helmet = new ItemArmor(ModArmorMaterial.DIFFERENT, EntityEquipmentSlot.HEAD,
							new Item.Properties().group(ModItemGroups.PLAYGROUND)).setRegistryName(getLocation("different_helmet")),
					ModItems.different_helmet = new ItemArmor(ModArmorMaterial.DIFFERENT, EntityEquipmentSlot.CHEST,
							new Item.Properties().group(ModItemGroups.PLAYGROUND)).setRegistryName(getLocation("different_chestpiece")),
					ModItems.different_helmet = new ItemArmor(ModArmorMaterial.DIFFERENT, EntityEquipmentSlot.LEGS,
							new Item.Properties().group(ModItemGroups.PLAYGROUND)).setRegistryName(getLocation("different_leggings")),
					ModItems.different_helmet = new ItemArmor(ModArmorMaterial.DIFFERENT, EntityEquipmentSlot.FEET,
							new Item.Properties().group(ModItemGroups.PLAYGROUND)).setRegistryName(getLocation("different_boots")));

			logger.info("Items registerd");
		}

		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			event.getRegistry()
					.registerAll(ModBlocks.start_block = new Block(
							Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 5.0f).lightValue(10).sound(SoundType.SLIME))
									.setRegistryName(getLocation("start_block")));

			logger.info("Blocks registerd");
		}
	}

	private static ResourceLocation getLocation(String name) {
		return new ResourceLocation(Reference.MOD_ID, name);
	}
}
