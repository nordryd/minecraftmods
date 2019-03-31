package com.nordryd.tutorialmod.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Class for all smelting recipes in the Tutorial Mod mod.
 * 
 * @author Nordryd
 */
public class ModRecipes
{
	public static void init() {
		GameRegistry.addSmelting(ModBlocks.RUBY_ORE, new ItemStack(ModItems.RUBY, 1), 1.5F);
		GameRegistry.addSmelting(ModItems.RUBY, new ItemStack(ModBlocks.RUBY_BLOCK, 1), 1.5F);
		GameRegistry.addSmelting(ModBlocks.RUBY_BLOCK, new ItemStack(Blocks.DIAMOND_BLOCK, 2), 3.0F);
		
		GameRegistry.addSmelting(ModBlocks.COOKIE_ORE, new ItemStack(ModItems.OREO_COOKIE, 1), 1.5F);
		GameRegistry.addSmelting(ModItems.OREO, new ItemStack(Items.GUNPOWDER), 1.5F);
		GameRegistry.addSmelting(ModBlocks.OREO_BLOCK, new ItemStack(Items.GUNPOWDER, 9), (float)(1.5 * 9.0));
	}
}
