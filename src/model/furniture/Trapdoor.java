package model.furniture;

import java.util.LinkedList;
import java.util.List;

import images.ImageEnum;
import model.items.Item;

public class Trapdoor extends Furniture {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2659920162119419368L;
	private List<Item> ladderMaterials;
	
	public Trapdoor() {
		super(10, 0, "trap door", ImageEnum.TRAPDOOR);
        ladderMaterials = new LinkedList<>();
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return ladderMaterials;
	}

	@Override
	public String toString() {
		return "Trap door";
	}
}
