package com.nordryd.tutorialmod.world.gen.testVillage;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class VillageTesting
{
	@SubscribeEvent
	public void onVillageBlocks(BiomeEvent.GetVillageBlockID event) {
		IBlockState original = event.getOriginal();

		if (MapGenVillage.VILLAGE_SPAWN_BIOMES.contains(event.getBiome())) {
			if (original.getBlock() == Blocks.GLASS_PANE) {
				event.setReplacement(Blocks.IRON_BARS.getDefaultState());
				event.setResult(Result.DENY);
			}
			else if (original.getBlock() == Blocks.PLANKS) {
				event.setReplacement(Blocks.COBBLESTONE.getDefaultState());
				event.setResult(Result.DENY);
			}
			else if (original.getBlock() == Blocks.OAK_FENCE) {
				event.setReplacement(Blocks.COBBLESTONE_WALL.getDefaultState());
				event.setResult(Result.DENY);
			}
			else if (original.getBlock() == Blocks.WOODEN_PRESSURE_PLATE) {
				event.setReplacement(Blocks.STONE_PRESSURE_PLATE.getDefaultState());
				event.setResult(Result.DENY);
			}
			else if ((original.getBlock() == Blocks.OAK_STAIRS) || (original.getBlock() == Blocks.ACACIA_STAIRS)
					|| (original.getBlock() == Blocks.SANDSTONE_STAIRS)) {
				event.setReplacement(Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, original.getValue(BlockStairs.FACING)));
				event.setResult(Result.DENY);
			}
			else if (original.getBlock() == Blocks.WOOL) {
				event.setReplacement(Blocks.COBBLESTONE.getDefaultState());
				event.setResult(Result.DENY);
			}
			else if (original.getBlock() == Blocks.SANDSTONE) {
				event.setReplacement(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH));
				event.setResult(Result.DENY);
			}
			else if ((original.getBlock() == Blocks.LOG) || (original.getBlock() == Blocks.LOG2)) {
//				if (original.getValue(BlockPlanks.VARIANT) == BlockPlanks.EnumType.OAK) {
					event.setReplacement(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE_SMOOTH));
					event.setResult(Result.DENY);
//				}
//				else if (original.getValue(BlockPlanks.VARIANT) == BlockPlanks.EnumType.ACACIA) {
//					event.setReplacement(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE_SMOOTH));
//					event.setResult(Result.DENY);
//				}
			}
		}
	}
}
