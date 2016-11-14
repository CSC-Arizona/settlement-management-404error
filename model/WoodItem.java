package model;

/**
 * WoodItem is dropped when a TreeRootBlock is destroyed.
 * 
 * @author Katherine Walters
 */
public class WoodItem extends Item {

	private final static int attackMod= 2;
	
	public WoodItem() {
		super(false, attackMod, 0, 3.0);
	}
}
