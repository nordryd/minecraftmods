package com.nordryd.tutorialmod.world.gen.structures;

import java.util.Random;

import com.nordryd.tutorialmod.util.IStructure;
import com.nordryd.tutorialmod.util.Reference;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

/**
 * Class for a single structure.
 * 
 * @author Nordryd
 * @see {@link com.nordryd.tutorialmod.world.gen.structures.ComplexStructure
 *      ComplexStructure}
 */
public class Structure extends WorldGenerator implements IStructure
{
	private final String structureName;
	private final int maxInstances;
	private int generated = 0;

	protected Structure(String filename) {
		this(filename, 0);
	}

	protected Structure(String filename, int maxInstances) {
		this.structureName = filename;
		this.maxInstances = maxInstances;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		MinecraftServer mcServer = worldIn.getMinecraftServer();
		TemplateManager manager = worldServer.getStructureTemplateManager();

		ResourceLocation location = new ResourceLocation(Reference.MOD_ID, structureName);
		Template template = manager.get(mcServer, location);
		if ((template != null) && !((maxInstances != 0) && (generated >= maxInstances))) {
			this.generateStructure(template, worldIn, rand, position);
			generated++;
			System.out.printf("[%s] Generating %d of %d at " + position + ".\n", structureName, generated, maxInstances);
		}
		return true;
	}

	/**
	 * The generation of the structure itself. <i><b>This may be
	 * <u>overridden</u></b></i> to give different generation specifications.
	 * 
	 * @param template
	 *            The structure itself.
	 * @param world
	 *            The world.
	 * @param pos
	 *            The position in which to spawn the structure.
	 */
	public void generateStructure(Template template, World world, Random rand, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() != Blocks.AIR) {
			world.notifyBlockUpdate(pos, state, state, 3);
			template.addBlocksToWorldChunk(world, pos, settings);
		}
	}

	/**
	 * @return The name of this structure.
	 */
	public String getStructureName() {
		return structureName;
	}

	/**
	 * @return The maximum number of instances of this structure that may exist in a
	 *         single world.
	 */
	protected int getMaxInstances() {
		return maxInstances;
	}
}
