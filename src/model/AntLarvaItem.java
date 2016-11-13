package model;

public class AntLarvaItem extends Item {

	private final static int attackMod = 1;
	private final static int healthPts = 2;
	
	public AntLarvaItem() {
		super(true, attackMod, healthPts, 0.5);
	}
}
