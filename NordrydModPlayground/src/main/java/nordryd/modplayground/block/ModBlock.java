package nordryd.modplayground.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import nordryd.modplayground.init.ModBlocks;
import nordryd.modplayground.init.ModItems;
import nordryd.modplayground.util.Reference;

public class ModBlock extends Block
{
	public ModBlock(String registryName, Block.Properties blockProperties, Item.Properties itemProperties) {
		super(blockProperties);
		this.setRegistryName(new ResourceLocation(Reference.MOD_ID, registryName));

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this, itemProperties).setRegistryName(this.getRegistryName()));
	}
}
