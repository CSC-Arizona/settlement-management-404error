package model.Items;

//Author: Maxwell Faridian
//This class defines the Apple item, which falls from trees and can be eaten to regain health points
public class AppleItem extends Item {
	
	private final static int attackMod = 2;
	private final static int healthPts = 5;
	
	public AppleItem() {
		super(true, attackMod, healthPts, 1);
	}

}
