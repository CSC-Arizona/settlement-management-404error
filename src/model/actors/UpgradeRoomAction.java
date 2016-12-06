package model.actors;

import java.util.TreeMap;

import model.furniture.Furniture;
import model.room.Room;

public class UpgradeRoomAction extends Action {

	private Room room;
	private TreeMap<Position, Furniture> roomFurniture;
	private boolean furnitureAssigned;
	
	private static final long serialVersionUID = 2426959828400570427L;

	public UpgradeRoomAction(Room toUpgrade) {
		this.room = toUpgrade;
		roomFurniture = toUpgrade.getFurniture();
		this.furnitureAssigned = false;
	}
	
	@Override
	public int execute(Actor performer) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*
	 * Places the furniture in the correct locations
	 */
	private void placeFurniture(Actor performer) {
		if (!furnitureAssigned) {
			for (Position p : roomFurniture.keySet()){
				Position fp = new Position(room.getPosition().getRow() + p.getRow(), room.getPosition().getCol() + p.getCol());
				Furniture f = room.getFurniture().get(p);
				performer.getActionPool().add(new PlaceFurnitureAction(fp, f));
			}
			furnitureAssigned = true;
		}
	}

}
