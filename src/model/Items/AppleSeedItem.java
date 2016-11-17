package model.Items;

/**
 * AppleSeedItem is an inedible seed dropped when AppleTreeLeafBlocks are
 * destroyed. It can be used to plant more apple trees.
 * 
 * @author Katherine Walters
 */
public class AppleSeedItem extends Item {

	private final static int attackMod = 1;
	private final static int healthPts = 0;
	
	public AppleSeedItem() {
		super(false, attackMod, healthPts, 0.2);
	}
	
}