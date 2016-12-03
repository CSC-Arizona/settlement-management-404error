package model.items;

/**
 * AppleSeedItem is an inedible seed dropped when AppleTreeLeafBlocks are
 * destroyed. It can be used to plant more apple trees.
 * 
 * @author Katherine Walters
 */
public class AppleSeedItem extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4118212108574740830L;
	private final static int attackMod = 1;
	private final static int healthPts = 0;
	
	public AppleSeedItem() {
		super(false, attackMod, healthPts, 0.2);
	}

	@Override
	public String toString() {
		return "Apple seed";
	}
	
}
