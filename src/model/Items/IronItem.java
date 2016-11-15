package model.Items;


/**
 * IronItemis the resource dropped by IronOreBlocks. It has an associated attackModifier
 * value and weight.
 * 
 * @author Katherine Walters
 */
public class IronItem extends Item {

    private final static int attackMod = 10;
	
	public IronItem() {
		super(false, attackMod, 0, 7.0);
	}

}
