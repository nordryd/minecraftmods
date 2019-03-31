package com.nordryd.tutorialmod.world.gen.testVillage;

import java.util.List;
import java.util.Random;

import com.nordryd.tutorialmod.init.ModWorldGen;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class TrapHouseCreationHandler implements VillagerRegistry.IVillageCreationHandler
{	
	@Override
	public PieceWeight getVillagePieceWeight(Random random, int i) {
		System.out.println("Getting additional village component piece weight");
		return new PieceWeight(getComponentClass(), 5, 3);
	}

	@Override
	public Class<? extends Village> getComponentClass() {
		return TrapHouse.class;
	}

	@Override
	public Village buildComponent(PieceWeight villagePiece, Start startPiece, List<StructureComponent> pieces, Random random, int minX, int minY, int minZ, EnumFacing facing, int type) {
		System.out.printf("TestAdditionalVillageComponent buildComponent() at %d, %d, %d\n", minX, minY, minZ);
		StructureBoundingBox structBB = StructureBoundingBox.getComponentToAddBoundingBox(minX, minY, minZ, 0, 0, 0, 9, 7, 12, facing);
		return new TrapHouse(startPiece, type, random, structBB, facing);
	}
}
