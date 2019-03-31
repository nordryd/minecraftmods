package com.nordryd.tutorialmod.blocks.variant;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.nordryd.tutorialmod.Main;
import com.nordryd.tutorialmod.init.ModBlocks;
import com.nordryd.tutorialmod.init.ModCreativeTabs;
import com.nordryd.tutorialmod.init.ModItems;
import com.nordryd.tutorialmod.init.blocks.item.ItemBlockVariants;
import com.nordryd.tutorialmod.util.IHasModel;
import com.nordryd.tutorialmod.util.IMetaName;
import com.nordryd.tutorialmod.util.handlers.EnumHandler;
import com.nordryd.tutorialmod.world.gen.trees.ChocolateTree;
import com.nordryd.tutorialmod.world.gen.trees.MintTree;
import com.nordryd.tutorialmod.world.gen.trees.StrawberryTree;
import com.nordryd.tutorialmod.world.gen.trees.VanillaTree;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class Saplings extends BlockBush implements IGrowable, IHasModel, IMetaName
{
	// Ready to grow or not (0 = sapling not growing, 1 = sapling growing into tree)
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

	protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D,
			0.800000011920929D, 0.8999999761581421D);
	public static final PropertyEnum<EnumHandler.Flavors> VARIANT = PropertyEnum.<EnumHandler.Flavors>create("variant",
			EnumHandler.Flavors.class, new Predicate<EnumHandler.Flavors>() {

				public boolean apply(@Nullable EnumHandler.Flavors apply) {
					return apply.getMeta() < EnumHandler.Flavors.values().length;
				}
			});

	public Saplings(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.PLANT);
		setCreativeTab(ModCreativeTabs.DECOR);

		this.setDefaultState(
				this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.Flavors.VANILLA).withProperty(STAGE, Integer.valueOf(0)));

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
	}

	// Sapling Shape
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return SAPLING_AABB;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	// Variants
	@Override
	public int damageDropped(IBlockState state) {
		return ((EnumHandler.Flavors) state.getValue(VARIANT)).getMeta();
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (EnumHandler.Flavors enumtype$enumflavor : EnumHandler.Flavors.values()) {
			items.add(new ItemStack(this, 1, enumtype$enumflavor.getMeta()));
		}
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumHandler.Flavors.values()[stack.getItemDamage()].getName();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, EnumHandler.Flavors.byMetaData(meta % EnumHandler.Flavors.values().length))
				.withProperty(STAGE, Integer.valueOf(meta >> 3));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0 | ((EnumHandler.Flavors) state.getValue(VARIANT)).getMeta();
		return i | ((Integer) state.getValue(STAGE)).intValue() << 3;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT, STAGE });
	}

	// Tree Growing Code
	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;// (double) worldIn.rand.nextFloat() < 0.450;
	}

	@Override
	protected boolean canSustainBush(IBlockState state) {
		return (state.getBlock() == ModBlocks.ICE_CREAM_BLOCKS) || super.canSustainBush(state);
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		if (((Integer) state.getValue(STAGE)).intValue() == 0) {
			worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
		}
		else {
			this.generateTree(worldIn, rand, pos, state);
		}
	}

	public void generateTree(World world, Random rand, BlockPos pos, IBlockState state) {
		if (TerrainGen.saplingGrowTree(world, rand, pos)) {
			return;
		}

		WorldGenerator gen = (WorldGenerator) (rand.nextInt(10) == 0 ? new WorldGenBigTree(false) : new WorldGenTrees(false));
		boolean flag = false;

		switch ((EnumHandler.Flavors) state.getValue(VARIANT)) {
		case VANILLA:
			gen = new VanillaTree();
			break;
		case MINT:
			gen = new MintTree();
			break;
		case STRAWBERRY:
			gen = new StrawberryTree();
			break;
		case CHOCOLATE:
			gen = new ChocolateTree();
			break;
		}

		IBlockState iblockstate = Blocks.AIR.getDefaultState();
		if (flag) {
			world.setBlockState(pos.add(0, 0, 0), iblockstate, 4);
			world.setBlockState(pos.add(1, 0, 0), iblockstate, 4);
			world.setBlockState(pos.add(0, 0, 1), iblockstate, 4);
			world.setBlockState(pos.add(1, 0, 1), iblockstate, 4);
		}
		else {
			world.setBlockState(pos, iblockstate, 4);
		}

		if (!gen.generate(world, rand, pos)) {
			if (flag) {
				world.setBlockState(pos.add(0, 0, 0), iblockstate, 4);
				world.setBlockState(pos.add(1, 0, 0), iblockstate, 4);
				world.setBlockState(pos.add(0, 0, 1), iblockstate, 4);
				world.setBlockState(pos.add(1, 0, 1), iblockstate, 4);
			}
			else {
				world.setBlockState(pos, iblockstate, 4);
			}
		}
	}

	@Override
	public void registerModels() {
		for (int i = 0; i < EnumHandler.Flavors.values().length; i++) {
			Main.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "sapling_" + EnumHandler.Flavors.values()[i].getName(),
					"inventory");
		}
	}

}
