package com.nordryd.tutorialmod.world.gen.testVillage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.nordryd.tutorialmod.init.ModBiomes;

import net.minecraft.init.Biomes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class MapGenTestVillage extends MapGenStructure
{
	public static List<Biome> VILLAGE_SPAWN_BIOMES = Arrays.<Biome>asList(ModBiomes.ICE_CREAM, Biomes.EXTREME_HILLS, Biomes.TAIGA, Biomes.DESERT,
			Biomes.PLAINS);
	private int size;
	private int averageSpacing = 12;

	/**
	 * Constructor
	 */
	public MapGenTestVillage() {
		
	}

	/**
	 * Constructor
	 * 
	 * @param map
	 *            the map
	 */
	public MapGenTestVillage(Map<String, String> map) {
		for (Entry<String, String> entry : map.entrySet()) {
			if (entry.getKey().equals("size")) {
				this.size = MathHelper.getInt(entry.getValue(), size, 0);
			}
			else if (entry.getKey().equals("distance")) {
				averageSpacing = MathHelper.getInt(entry.getValue(), averageSpacing, 9);
			}
		}
	}

	@Override
	public String getStructureName() {
		return "Test Village";
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		int unadjustedX = chunkX;
		int unadjustedZ = chunkZ;

		if (chunkX < 0) {
			chunkX -= averageSpacing - 1;
		}

		if (chunkZ < 0) {
			chunkZ -= averageSpacing - 1;
		}

		int candidateX = chunkX / averageSpacing;
		int candidateZ = chunkZ / averageSpacing;
		Random rand = world.setRandomSeed(candidateX, candidateZ, 10387312);
		candidateX = candidateX * averageSpacing;
		candidateX = candidateX + rand.nextInt(averageSpacing - 8);
		candidateZ = candidateZ * averageSpacing;
		candidateZ = candidateZ + rand.nextInt(averageSpacing - 8);

		if ((unadjustedX == candidateX) && (unadjustedZ == candidateZ)) {
			System.out.println("Is biome viable for village = "
					+ world.getBiomeProvider().areBiomesViable(unadjustedX * 16 + 8, unadjustedZ * 16 + 8, 0, VILLAGE_SPAWN_BIOMES));
			return world.getBiomeProvider().areBiomesViable(unadjustedX * 16 + 8, unadjustedZ * 16 + 8, 0, VILLAGE_SPAWN_BIOMES);
		}

		return false;
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
		world = worldIn;
		return findNearestStructurePosBySpacing(worldIn, this, pos, averageSpacing, 8, 10387312, false, 100, findUnexplored);
	}

	@Override
	public StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenTestVillage.Start(world, rand, chunkX, chunkZ, size);
	}

	public static class Start extends StructureStart
	{
		private boolean hasMoreThanTwoComponents;

		public Start() {

		}

		public Start(World worldIn, Random rand, int x, int z, int size) {
			super(x, z);
			List<StructureVillagePieces.PieceWeight> list = StructureVillagePieces.getStructureVillageWeightedPieceList(rand, size);
			StructureVillagePieces.Start start = new StructureVillagePieces.Start(worldIn.getBiomeProvider(), 0, rand, (x << 4) + 2, (z << 4) + 2,
					list, size);
			components.add(start);

			start.buildComponent(start, components, rand);
			List<StructureComponent> pendingRoads = start.pendingRoads, pendingHouses = start.pendingHouses;

			while (!pendingRoads.isEmpty() || !pendingHouses.isEmpty()) {
				StructureComponent component;

				if (pendingRoads.isEmpty()) {
					int i = rand.nextInt(pendingHouses.size());
					component = pendingHouses.remove(i);
				}
				else {
					int j = rand.nextInt(pendingRoads.size());
					component = pendingRoads.remove(j);
				}

				component.buildComponent(start, components, rand);
			}

			updateBoundingBox();
			int nonRoadComponentCount = 0;

			for (StructureComponent component : components) {
				if (!(component instanceof StructureVillagePieces.Road)) {
					nonRoadComponentCount++;
				}
			}

			hasMoreThanTwoComponents = nonRoadComponentCount > 2;
		}

		@Override
		public boolean isSizeableStructure() {
			return hasMoreThanTwoComponents;
		}

		@Override
		public void writeToNBT(NBTTagCompound tagCompound) {
			super.writeToNBT(tagCompound);
			tagCompound.setBoolean("Valid", hasMoreThanTwoComponents);
		}
		
		@Override
		public void readFromNBT(NBTTagCompound tagCompound) {
			super.readFromNBT(tagCompound);
			hasMoreThanTwoComponents = tagCompound.getBoolean("Valid");
		}
	}
}
