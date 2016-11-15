package model.Items;


/**
 * StoneItem is a resource item that is dropped when a StoneBlock is destroyed.
 * 
 * @author Katherine Walters
 */
public class StoneItem extends Item {

    private final static int attackMod = 5;
	
	public StoneItem() {
		super(false, attackMod, 0, 5.0);
	}
}
