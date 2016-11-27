package model.Items;

//Author: Maxwell Faridian
//This class defines the Dragon Egg Item, which is formed when 2 dragons breed. The egg will hatch after incubating for a set amount of
//time
public class DragonEggItem extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4688694131618496787L;

	private final static boolean edible = false;
	private final static int attackMod = 0;
	private final static int healthPts = 0;
	private final static double weight = 3.0;

	public DragonEggItem() {
		super(edible, attackMod, healthPts, weight);
	}

}
