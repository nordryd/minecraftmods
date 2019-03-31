package com.nordryd.tutorialmod.util;

import com.nordryd.tutorialmod.init.ModBlocks;

import net.minecraft.block.Block;

/**
 * Contains versioning and referential information about the TutorialMod
 * mod.<br/>
 * <b>NOTE</b>: Don't forget to change the {@code mcmod.info} file accordingly.
 * 
 * @author Nordryd
 */
public class Reference
{
	// First letter of each word of your mod
	public static final String MOD_ID = "tm";

	// Name of your mod
	public static final String NAME = "Ice Cream Mod";

	// Version of your mod
	public static final String VERSION = "1.0";

	// Minecraft versions your mod can run on (use brackets in the string)"
	public static final String ACCEPTED_VERSIONS = "[1.13.2]";

	// Location of client proxy class (what runs the mod on the client side)
	public static final String CLIENT_PROXY_CLASS = "com.nordryd.tutorialmod.proxy.ClientProxy";

	// Location of common proxy class
	public static final String COMMON_PROXY_CLASS = "com.nordryd.tutorialmod.proxy.CommonProxy";
	
	// Constants
	public static final int CHUNK_SIZE = 16;
	public static final Block TEST_BLOCK = ModBlocks.RUBY_BLOCK;
}
