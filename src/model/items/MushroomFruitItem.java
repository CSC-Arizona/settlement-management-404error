package model.items;

import images.ImageEnum;

public class MushroomFruitItem extends Item {

	private static final long serialVersionUID = -4118212108574740830L;
	private final static int attackMod = 1;
	private final static int healthPts = -50;
	
	public MushroomFruitItem() {
		super(false, attackMod, healthPts, 0.1, ImageEnum.MUSHROOMFRUIT);
	}

	@Override
	public String toString() {
		return "Mushroom fruit";
	}
	
}
