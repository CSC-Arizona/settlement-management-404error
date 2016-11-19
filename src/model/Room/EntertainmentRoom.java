package model.Room;

import java.util.LinkedList;
import java.util.List;

import model.Items.Item;

public class EntertainmentRoom extends Room{

	private List<Furniture> furniture;

	public EntertainmentRoom() {
		super(2, 6);
		this.furniture = new LinkedList<>();
	}

	@Override
	public List<Item> getRequiredMaterials() {
		// TODO Auto-generated method stub
		return null;
	}

}
