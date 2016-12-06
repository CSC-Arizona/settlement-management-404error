package model.items;

import images.ImageEnum;

public class AntimatterDefenestratorItem extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -270883102150737620L;

	public AntimatterDefenestratorItem() {
		super(false, 50, 0, 500, ImageEnum.ANTIMATTERDEFENESTRATOR);
	}

	@Override
	public String toString() {
		return "Retroencabulator";
	}

}
