package model.Items;

//Author: Maxwell Faridian
//This class defines the Wheat Stem item, which can be used to make furniture
public class WheatStemItem extends Item {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1060229103591146820L;
	private final static int attackMod = 1;

	public WheatStemItem() {
		super(false, attackMod, 0, 1);
	}
}
