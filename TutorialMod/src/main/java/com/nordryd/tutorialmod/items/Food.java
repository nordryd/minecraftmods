package com.nordryd.tutorialmod.items;

import com.nordryd.tutorialmod.Main;
import com.nordryd.tutorialmod.init.ModCreativeTabs;
import com.nordryd.tutorialmod.init.ModItems;
import com.nordryd.tutorialmod.util.IHasModel;

import net.minecraft.item.ItemFood;

/**
 * Base for all Food items.
 * 
 * Food details: <a href=
 * "https://minecraft.gamepedia.com/Food">https://minecraft.gamepedia.com/Food</a>.
 * 
 * @author Nordryd
 */
public class Food extends ItemFood implements IHasModel
{
	/**
	 * Constructor for Food items.
	 * 
	 * @param amount
	 *            How much the food meter is replenished when this item is
	 *            consumed.<br/>
	 *            <b>NOTE</b>: 1 drumstick = 2 food (up to a max of 20)
	 * @param saturation
	 *            How much saturation is replenished when this item is consumed.
	 * @param isWolfFood
	 *            If wolves can eat this item.
	 */
	public Food(String name, int amount, double saturation, boolean isWolfFood) {
		super(amount, (float) saturation, isWolfFood);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(ModCreativeTabs.FOOD);
		
		ModItems.ITEMS.add(this);
	}
	
	/**
	 * Constructor for Food items.
	 * 
	 * @param amount
	 *            How much the food meter is replenished when this item is
	 *            consumed.<br/>
	 *            <b>NOTE</b>: 1 drumstick = 2 food (up to a max of 20)
	 * @param isWolfFood
	 *            If wolves can eat this item.
	 */
	public Food(String name, int amount, boolean isWolfFood) {
		this(name, amount, 0.6, isWolfFood);
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
