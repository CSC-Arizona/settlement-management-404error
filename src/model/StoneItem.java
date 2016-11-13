package model;

public class StoneItem extends Item {

    private final static int attackMod = 5;
	
	public StoneItem() {
		super(false, attackMod, 0, 5.0);
	}
}
