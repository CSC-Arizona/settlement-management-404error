package model;

public class IronItem extends Item {

    private final static int attackMod = 10;
	
	public IronItem() {
		super(false, attackMod, 0, 7.0);
	}

}
