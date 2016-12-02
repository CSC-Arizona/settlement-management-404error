package model.items;


/**
 * IronItemis the resource dropped by IronOreBlocks. It has an associated attackModifier
 * value and weight.
 * 
 * @author Katherine Walters
 */
public class IronItem extends Item {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6948560675067355700L;
	private final static int attackMod = 10;
	
	public IronItem() {
		super(false, attackMod, 0, 7.0);
	}

	@Override
	public String toString() {
		return "Iron";
	}

}
