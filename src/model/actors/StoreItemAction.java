package model.actors;

import java.util.HashMap;

import model.furniture.Furniture;
import model.game.Game;
import model.items.Item;

public class StoreItemAction extends Action {

	private static final long serialVersionUID = 1454588966658079359L;
	private Position cratePosition;
	private Item item;
	private MoveAction move;

	public StoreItemAction(Item item) {
		System.out.println(item.toString());
		this.item = item;
		cratePosition = getCrateWithCapacityGreaterThan(item.getWeight());
	}

	@Override
	public int execute(Actor performer) {
		if (cratePosition != null) {
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
					move = new MoveAction(cratePosition);
				int action = move.execute(performer);
				if (action == Action.COMPLETED) {
					return Action.MADE_PROGRESS;
				}
				return action;
			}
		}
		return Action.DELAY;
	}

	public Position getCrateWithCapacityGreaterThan(double target) {
		HashMap<Furniture, Position> mapFurniture = Game.getMap()
				.getFurniture();
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
