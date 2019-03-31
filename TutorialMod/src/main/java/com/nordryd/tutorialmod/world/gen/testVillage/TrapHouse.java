package com.nordryd.tutorialmod.world.gen.testVillage;

import java.util.Random;

import com.nordryd.tutorialmod.init.ModBlocks;
import com.nordryd.tutorialmod.util.IStructure;
import com.nordryd.tutorialmod.util.Reference;

import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class TrapHouse extends StructureVillagePieces.Village
{
	public TrapHouse() {
	}

	public TrapHouse(StructureVillagePieces.Start start, int type, Random rand, StructureBoundingBox boundingBox, EnumFacing facing) {
		super(start, type);
		setCoordBaseMode(facing);
		this.boundingBox = boundingBox;
	}

	@Override
	public boolean addComponentParts(World worldIn, Random rand, StructureBoundingBox structBB) {
		if (this.averageGroundLvl < 0) {
			this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structBB);

			if (this.averageGroundLvl < 0) {
				return true;
			}

			this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 7 - 1, 0);
		}
		
		generateTrapHouse(worldIn, rand, structBB);

		generateFromStructureTemplate("cage", worldIn, rand, structBB);
		// set origin block
		// setBlockState(worldIn, testRuby, 0, 0, 0, structBB);
		return true;
	}
	
	private void generateTrapHouse(World worldIn, Random rand, StructureBoundingBox structBB) {
		IBlockState cobblestone = Blocks.MOSSY_COBBLESTONE.getDefaultState();
		IBlockState log = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
		IBlockState planks = Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE);
		;
		IBlockState stoneStairsSouth = this
				.getBiomeSpecificBlockState(Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
		IBlockState testRuby = ModBlocks.RUBY_BLOCK.getDefaultState();
		IBlockState tnt = Blocks.TNT.getDefaultState();
		IBlockState stonePressurePlate = Blocks.STONE_PRESSURE_PLATE.getDefaultState();

		// Add foundation
		fillWithBlocks(worldIn, structBB, 0, 0, 0, 4, 0, 4, log, log, false);

		// Add corner pillars
		fillWithBlocks(worldIn, structBB, 0, 0, 0, 0, 4, 0, log, log, false);
		fillWithBlocks(worldIn, structBB, 4, 0, 0, 4, 4, 0, log, log, false);
		fillWithBlocks(worldIn, structBB, 4, 0, 4, 4, 4, 4, log, log, false);
		fillWithBlocks(worldIn, structBB, 0, 0, 4, 0, 4, 4, log, log, false);

		// Add walls
		fillWithBlocks(worldIn, structBB, 1, 1, 0, 3, 3, 0, planks, planks, false);
		fillWithBlocks(worldIn, structBB, 1, 1, 4, 3, 3, 4, planks, planks, false);
		fillWithBlocks(worldIn, structBB, 0, 1, 1, 0, 3, 3, planks, planks, false);
		fillWithBlocks(worldIn, structBB, 4, 1, 1, 4, 3, 3, planks, planks, false);

		fillWithBlocks(worldIn, structBB, 1, 4, 0, 3, 4, 0, log, log, false);
		fillWithBlocks(worldIn, structBB, 1, 4, 4, 3, 4, 4, log, log, false);
		fillWithBlocks(worldIn, structBB, 0, 4, 1, 0, 4, 3, log, log, false);
		fillWithBlocks(worldIn, structBB, 4, 4, 1, 4, 4, 3, log, log, false);

		// Add roof
		fillWithBlocks(worldIn, structBB, 1, 4, 1, 3, 4, 3, planks, planks, false);

		// Add TNT below and triggers above
		fillWithBlocks(worldIn, structBB, 1, -1, 1, 3, -1, 3, tnt, tnt, false);
		fillWithBlocks(worldIn, structBB, 1, 1, 1, 3, 1, 3, stonePressurePlate, stonePressurePlate, false);

		// Add torch
		placeTorch(worldIn, EnumFacing.NORTH, 2, 3, 1, structBB);
		// setBlockState(worldIn, Blocks.BEACON.getDefaultState(), 2, 3, 1, structBB);

		// Add windows
		setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 3, 2, 0, structBB);
		setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 2, 2, 4, structBB);
		setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 4, 2, 2, structBB);
		setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 0, 2, 2, structBB);

		// Add a door to the house
		createVillageDoor(worldIn, structBB, rand, 1, 1, 0, EnumFacing.NORTH);

		// Check for the structure being on a path
		if (this.getBlockStateFromPos(worldIn, 1, 0, -1, structBB).getMaterial() == Material.AIR
				&& this.getBlockStateFromPos(worldIn, 1, -1, -1, structBB).getMaterial() != Material.AIR) {
			setBlockState(worldIn, stoneStairsSouth, 1, 0, -1, structBB);

			if (this.getBlockStateFromPos(worldIn, 1, -1, -1, structBB).getBlock() == Blocks.GRASS_PATH) {
				this.setBlockState(worldIn, Blocks.GRASS.getDefaultState(), 1, -1, -1, structBB);
			}
		}
	}
	
	private void generateFromStructureTemplate(String structureName, World worldIn, Random rand, StructureBoundingBox structBB) {
		MinecraftServer mcServer = worldIn.getMinecraftServer();
		TemplateManager manager = IStructure.worldServer.getStructureTemplateManager();

		ResourceLocation location = new ResourceLocation(Reference.MOD_ID, structureName);
		Template template = manager.get(mcServer, location);
	}
}
