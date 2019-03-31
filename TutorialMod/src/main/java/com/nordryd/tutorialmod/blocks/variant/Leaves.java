package com.nordryd.tutorialmod.blocks.variant;

import java.util.List;

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

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Leaves extends BlockLeaves implements IHasModel, IMetaName
{
	public static final PropertyEnum<EnumHandler.Flavors> VARIANT = PropertyEnum.<EnumHandler.Flavors>create("variant",
			EnumHandler.Flavors.class, new Predicate<EnumHandler.Flavors>() {

				public boolean apply(@Nullable EnumHandler.Flavors apply) {
					return apply.getMeta() < EnumHandler.Flavors.values().length;
				}
			});

	public Leaves(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.PLANT);
		setCreativeTab(ModCreativeTabs.BLOCKS);

		// Default state is a vanilla block standing vertically
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.Flavors.VANILLA)
				.withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
	}
	@Override
	public void beginLeavesDecay(IBlockState state, World world, BlockPos pos) {
		// TODO Auto-generated method stub
		super.beginLeavesDecay(state, world, pos);
	}
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
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, EnumHandler.Flavors.byMetaData(meta % EnumHandler.Flavors.values().length));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = ((EnumHandler.Flavors) state.getValue(VARIANT)).getMeta();
		if (!((Boolean) state.getValue(DECAYABLE))) {
			i |= 2;
		}

		if (!((Boolean) state.getValue(CHECK_DECAY))) {
			i |= 4;
		}

		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT, DECAYABLE, CHECK_DECAY });
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return NonNullList.withSize(1, new ItemStack(this, 1, world.getBlockState(pos).getValue(VARIANT).getMeta()));
	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this), 1, ((EnumHandler.Flavors) state.getValue(VARIANT)).getMeta());
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumHandler.Flavors.values()[stack.getItemDamage()].getName();
	}

	@Override
	public EnumType getWoodType(int meta) {
		return null;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
		if (worldIn.rand.nextInt(chance) == 0) {
			switch (state.getValue(VARIANT).getMeta()) {
			case 0:
				spawnAsEntity(worldIn, pos, new ItemStack(ModItems.VANILLA_BEAN));
				break;
			case 1:
				spawnAsEntity(worldIn, pos, new ItemStack(ModItems.MINT_LEAF));
				break;
			case 2:
				spawnAsEntity(worldIn, pos, new ItemStack(ModItems.STRAWBERRY));
				break;
			case 3:
				spawnAsEntity(worldIn, pos, new ItemStack(ModItems.CHOCOLATE));
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void registerModels() {
		for (int i = 0; i < EnumHandler.Flavors.values().length; i++) {
			Main.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "leaves_" + EnumHandler.Flavors.values()[i].getName(),
					"inventory");
		}
	}
}
