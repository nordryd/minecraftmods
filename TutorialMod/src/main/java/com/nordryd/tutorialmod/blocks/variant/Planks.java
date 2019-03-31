package com.nordryd.tutorialmod.blocks.variant;

import com.nordryd.tutorialmod.Main;
import com.nordryd.tutorialmod.init.ModBlocks;
import com.nordryd.tutorialmod.init.ModCreativeTabs;
import com.nordryd.tutorialmod.init.ModItems;
import com.nordryd.tutorialmod.init.blocks.item.ItemBlockVariants;
import com.nordryd.tutorialmod.util.IHasModel;
import com.nordryd.tutorialmod.util.IMetaName;
import com.nordryd.tutorialmod.util.handlers.EnumHandler;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Single class to handle all variants of <b>planks</b> in the mod.
 * 
 * @author Nordryd
 */
public class Planks extends Block implements IHasModel, IMetaName
{
	public static final PropertyEnum<EnumHandler.Flavors> VARIANT = PropertyEnum.<EnumHandler.Flavors>create("variant",
			EnumHandler.Flavors.class);

	public Planks(String name) {
		super(Material.WOOD);
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.WOOD);
		setCreativeTab(ModCreativeTabs.BLOCKS);

		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.Flavors.VANILLA));

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
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
		return this.getDefaultState().withProperty(VARIANT, EnumHandler.Flavors.byMetaData(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumHandler.Flavors) state.getValue(VARIANT)).getMeta();
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumHandler.Flavors.values()[stack.getItemDamage()].getName();
	}

	@Override
	public void registerModels() {
		for (int i = 0; i < EnumHandler.Flavors.values().length; i++) {
			Main.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "planks_" + EnumHandler.Flavors.values()[i].getName(),
					"inventory");
		}
	}
}
