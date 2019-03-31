package com.nordryd.tutorialmod.world.biome;

import java.util.Random;

import com.nordryd.tutorialmod.blocks.variant.IceCreamBlocks;
import com.nordryd.tutorialmod.init.ModBlocks;
import com.nordryd.tutorialmod.util.handlers.EnumHandler;
import com.nordryd.tutorialmod.world.gen.trees.ChocolateTree;
import com.nordryd.tutorialmod.world.gen.trees.MintTree;
import com.nordryd.tutorialmod.world.gen.trees.StrawberryTree;
import com.nordryd.tutorialmod.world.gen.trees.VanillaTree;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class BiomeIceCream extends Biome
{
	// check the tree tutorial
	public BiomeIceCream(String name) {
		// Water color, copy decimal from
		// https://www.mathsisfun.com/hexadecimal-decimal-colors.html
		super(new BiomeProperties(name).setBaseHeight(0.1F).setHeightVariation(0.2F).setTemperature(0.2F).setRainDisabled().setWaterColor(16711935));

		topBlock = ModBlocks.ICE_CREAM_BLOCKS.getDefaultState().withProperty(IceCreamBlocks.VARIANT, EnumHandler.Flavors.VANILLA);
		fillerBlock = ModBlocks.ICE_CREAM_BLOCKS.getDefaultState().withProperty(IceCreamBlocks.VARIANT, EnumHandler.Flavors.STRAWBERRY);

		// Any coal that would spawn is now the block you specify
		this.decorator.coalGen = new WorldGenMinable(ModBlocks.COOKIE_ORE.getDefaultState(), 10);
		this.decorator.andesiteGen = new WorldGenMinable(
				ModBlocks.ICE_CREAM_BLOCKS.getDefaultState().withProperty(IceCreamBlocks.VARIANT, EnumHandler.Flavors.MINT), 10);
		this.decorator.graniteGen = new WorldGenMinable(
				ModBlocks.ICE_CREAM_BLOCKS.getDefaultState().withProperty(IceCreamBlocks.VARIANT, EnumHandler.Flavors.MINT), 10);
		this.decorator.dioriteGen = new WorldGenMinable(
				ModBlocks.ICE_CREAM_BLOCKS.getDefaultState().withProperty(IceCreamBlocks.VARIANT, EnumHandler.Flavors.MINT), 10);
		this.decorator.treesPerChunk = 0;
		this.decorator.extraTreeChance = 0.05F;

		// Clear default spawn lists
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();

		// Add creature to spawn list: new SpawnListEntry(<entity>.class, weight,
		// minPackSize, maxPackSize)
		this.spawnableCreatureList.add(new SpawnListEntry(EntityVillager.class, 10, 1, 5));
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		switch (rand.nextInt(3)) {
		case 0:
			return new VanillaTree();
		case 1:
			return new MintTree();
		case 2:
			return new StrawberryTree();
		case 3:
			return new ChocolateTree();
		default:
			return new VanillaTree();
		}
	}
}
