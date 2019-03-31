package com.nordryd.tutorialmod.util.handlers;

import net.minecraft.util.IStringSerializable;

/**
 * Handles all the global variants of every single type of block in the mod,
 * <i>e.g.</i> mint, vanilla, strawberry, etc., for each type of object.
 * 
 * Each combination of variants is, in and of itself, one variant. <i>e.g.</i>
 * Flavor.size(2) * Rotation.size(4) = 8 variants.<br/>
 * <br/>
 * 
 * This number cannot exceed <b>16</b>.
 * 
 * @author Nordryd
 */
public class EnumHandler
{
	/**
	 * Flavors of ice cream available in the mod.
	 * @author Nordryd
	 */
	public static enum Flavors implements IStringSerializable
	{
		VANILLA(0, "vanilla"), 		 	// leaves should drop vanilla bean
		MINT(1, "mint"), 				// leaves should drop mint leaves
		STRAWBERRY(2, "strawberry"), 	// leaves should drop strawberries
		CHOCOLATE(3, "chocolate");		// leaves should drop chocolate

		private static final Flavors[] META_LOOKUP = new Flavors[values().length];
		private final int meta;
		private final String name, unlocalizedName;

		private Flavors(int meta, String name) {
			this(meta, name, name);
		}

		private Flavors(int meta, String name, String unlocalizedName) {
			this.meta = meta;
			this.name = name;
			this.unlocalizedName = unlocalizedName;
		}

		public int getMeta() {
			return this.meta;
		}

		public String getName() {
			return this.name;
		}

		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}

		public static Flavors byMetaData(int meta) {
			return META_LOOKUP[meta];
		}

		static {
			for (Flavors enumtype : values()) {
				META_LOOKUP[enumtype.getMeta()] = enumtype;
			}
		}
	}
}
