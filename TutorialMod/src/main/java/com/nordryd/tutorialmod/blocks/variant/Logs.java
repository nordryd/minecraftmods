package com.nordryd.tutorialmod.blocks.variant;

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

import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Logs extends BlockLog implements IHasModel, IMetaName
{
	// WTF is a predicate in this context??? Decrypt all of this [apparent] nonsense
	// some other time
	public static final PropertyEnum<EnumHandler.Flavors> VARIANT = PropertyEnum.<EnumHandler.Flavors>create("variant",
			EnumHandler.Flavors.class, new Predicate<EnumHandler.Flavors>() {

				public boolean apply(@Nullable EnumHandler.Flavors apply) {
					return apply.getMeta() < EnumHandler.Flavors.values().length;
				}
			});

	public Logs(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.WOOD);
		setCreativeTab(ModCreativeTabs.BLOCKS);

		// Default state is a vanilla block standing vertically
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.Flavors.VANILLA).withProperty(LOG_AXIS, EnumAxis.Y));

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
		IBlockState state = this.getDefaultState().withProperty(VARIANT, EnumHandler.Flavors.byMetaData(meta & 3));
		switch (meta >> 2) {
		case 0:
			return state.withProperty(LOG_AXIS, EnumAxis.Y);
		case 1:
			return state.withProperty(LOG_AXIS, EnumAxis.X);
		case 2:
			return state.withProperty(LOG_AXIS, EnumAxis.Z);
		default:
			return state.withProperty(LOG_AXIS, EnumAxis.NONE);
		}
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0 | ((EnumHandler.Flavors) state.getValue(VARIANT)).getMeta();
		switch ((BlockLog.EnumAxis) state.getValue(LOG_AXIS)) {
		case Y:
			return i;
		case X:
			return i | 4;
		case Z:		
			return i | 8;
		default:		
			return i | 12;
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT, LOG_AXIS });
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
	public void registerModels() {
		for (int i = 0; i < EnumHandler.Flavors.values().length; i++) {
			Main.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "logs_" + EnumHandler.Flavors.values()[i].getName(), "inventory");
		}
	}
}
