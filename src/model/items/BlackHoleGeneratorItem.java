package model.items;

import images.ImageEnum;

public class BlackHoleGeneratorItem extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -270883102150737620L;

	public BlackHoleGeneratorItem() {
		super(false, 50, 0, 100, ImageEnum.BLACKHOLEGENERATOR);
	}

	@Override
	public String toString() {
		return "Retroencabulator";
	}

}
