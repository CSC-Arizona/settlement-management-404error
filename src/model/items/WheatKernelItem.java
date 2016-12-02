package model.items;

//Author: Maxwell Faridian
//This class defines the Wheat Kernel Item, which can be planted to create Wheat Blocks
public class WheatKernelItem extends Item {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2689835269748761304L;
	private final static int attackMod = 1;
	private final static int healthPts = 1;
	
	public WheatKernelItem() {
		super(true, attackMod, healthPts, 70);
	}
	
	@Override
	public String toString() {
		return "Wheat kernel";
	}
}
