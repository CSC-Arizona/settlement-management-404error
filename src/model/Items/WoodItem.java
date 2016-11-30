package model.Items;


/**
 * WoodItem is dropped when a TreeRootBlock is destroyed.
 * 
 * @author Katherine Walters
 */
public class WoodItem extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7140756816668455004L;
	private final static int attackMod= 2;
	
	public WoodItem() {
		super(false, attackMod, 0, 3.0);
	}

	@Override
	public String toString() {
		return "Wood";
	}
}
