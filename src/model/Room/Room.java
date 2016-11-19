package model.Room;

import java.util.List;

import model.Items.Item;

public abstract class Room {

	private int requiredHeight;
	private int requiredWidth;
	
	public Room(int requiredHeight, int requiredWidth) {
		this.requiredHeight = requiredHeight;
		this.requiredWidth = requiredWidth;
	}
	
	public int getRequiredWidth() {
		return this.requiredWidth;
	}
	
	public int getRequiredHeight() {
		return this.requiredHeight;
	}
	
	abstract public List<Item> getRequiredMaterials();
}
