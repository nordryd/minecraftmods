package com.nordryd.tutorialmod.init;

import com.nordryd.tutorialmod.world.gen.testVillage.TrapHouseCreationHandler;

import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class ModWorldGen
{	
	public static final IVillageCreationHandler TEST_ADD_VILL_COMP_CREATION_HANDLER = new TrapHouseCreationHandler();
}
