package model.Actors;

import java.util.HashMap;

import model.Game;
import model.Furniture.Furniture;
import model.Items.Item;

public class StoreItemAction extends Action {

	private Position cratePosition;
	private Item item;
	private MoveAction move;

	public StoreItemAction(Item item) {
		this.item = item;
		cratePosition = getCrateWithCapacityGreaterThan(item.getWeight());
	}

	@Override
	public int execute(Actor performer) {
		if (performer.getPosition().equals(cratePosition)) {
			Furniture crate = Game.getMap().getBuildingBlock(cratePosition)
					.getFurniture();
			if (crate != null && performer.getInventory().size() != 0) {
				if (crate.getRemainingWeightCapacity() >= item.getWeight()) {
					crate.addItem(item);
					performer.getInventory().removeItem(item);
				}
			}
			return Action.COMPLETED;
		} else {
			if (move == null)
				move =  new MoveAction(cratePosition);
			int action = move.execute(performer);
			if (action == Action.COMPLETED) {
				return Action.MADE_PROGRESS;
			}
			return action;
		}
	}
	
	public Position getCrateWithCapacityGreaterThan(double target) {
		HashMap<Furniture, Position> mapFurniture = Game.getMap().getFurniture();
		if (mapFurniture != null) {
			for (Furniture f : mapFurniture.keySet()) {
				if (f.getID().equals("crate")) {
					if (f.getRemainingWeightCapacity() >= target) {
						return mapFurniture.get(f);
					}
				}
			}
		}
		return null;
	}
}
