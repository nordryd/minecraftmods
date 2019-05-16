package nordryd.modplayground.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import nordryd.modplayground.lists.ModBlocks;
import nordryd.modplayground.lists.ModItems;
import nordryd.modplayground.util.Reference;

public class ModBlock extends Block
{
	public ModBlock(String name, Block.Properties blockProperties, Item.Properties itemProperties) {
		super(blockProperties);
		
		this.setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
		
		ModBlocks.MOD_BLOCKS.add(this);
		ModItems.MOD_BLOCKS_AS_ITEMS.add(new ItemBlock(this, itemProperties).setRegistryName(this.getRegistryName()));
	}
}
