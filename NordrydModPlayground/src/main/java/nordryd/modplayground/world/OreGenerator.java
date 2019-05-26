package nordryd.modplayground.world;

import com.google.common.base.Predicate;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.CompositeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.placement.CountRange;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraftforge.registries.ForgeRegistries;
import nordryd.modplayground.init.ModBlocks;

/**
 * <p>
 * To generate ores in <i>all</i> biomes, use a for loop over
 * ForgeRegistries.BIOMES and do biome.addFeature for all of them.
 * </p>
 * 
 * <p>
 * To generate ores in specific biomes, specifically declare the biome and do
 * biome.addFeature for each one explicitly.
 * </p>
 * 
 * @author Nordryd
 */
public class OreGenerator
{
	// The lambda is basically putting an object inside the generic
	private static final Predicate<IBlockState> IS_NETHERRACK = state -> state.getBlock() == Blocks.NETHERRACK;
	
	@SuppressWarnings("unused") //remove later
	private static final Predicate<IBlockState> IS_END_STONE = state -> state.getBlock() == Blocks.END_STONE;

	public static void setupOreGenerator() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			// CountRangeConfig(chance to spawn (how many veins per chunk), min height, max
			// height base (true max height), max height (relative to min height))
			CountRangeConfig differentOrePlacement = new CountRangeConfig(10, 20, 20, 100);

			// CompositeFeature<>(feature type, feature itself,
			biome.addFeature(Decoration.UNDERGROUND_ORES,
					new CompositeFeature<>(Feature.MINABLE, new MinableConfig(MinableConfig.IS_ROCK, ModBlocks.different_ore.getDefaultState(), 5),
							new CountRange(), differentOrePlacement));

			CountRangeConfig differentOreNetherPlacement = new CountRangeConfig(10, 20, 0, 256);
			biome.addFeature(Decoration.UNDERGROUND_ORES,
					new DimensionalGenerator<>(Feature.MINABLE,
							new MinableConfig(IS_NETHERRACK, ModBlocks.different_ore_nether.getDefaultState(), 5), new CountRange(),
							differentOreNetherPlacement, DimensionType.NETHER));
		}
	}
}
