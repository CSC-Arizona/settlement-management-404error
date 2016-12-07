package model.items;

import images.ImageEnum;

public class RetroencabulatorItem extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -270883102150737620L;

	public RetroencabulatorItem() {
		super(false, 50, 0, 100, ImageEnum.RETROENCABULATOR);
	}

	@Override
	public String toString() {
		return "Retroencabulator";
	}

}
