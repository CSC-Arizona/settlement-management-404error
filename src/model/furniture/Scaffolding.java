package model.furniture;

import java.util.LinkedList;
import java.util.List;

import images.ImageEnum;
import model.items.Item;

public class Scaffolding extends Furniture {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2659920162119419368L;
	private List<Item> ladderMaterials;
	
	public Scaffolding() {
		super(10, 0, "scaffolding", ImageEnum.LADDER);
        ladderMaterials = new LinkedList<>();
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return ladderMaterials;
	}

	@Override
	public String toString() {
		return "Scaffolding";
	}
}
