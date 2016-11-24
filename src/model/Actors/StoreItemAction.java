package model.Actors;

import model.Map;
import model.Furniture.Furniture;
import model.Items.Item;

public class StoreItemAction implements Action {

	private Map map;
	private Position cratePosition;
	private Item item;
	private int index;

	public StoreItemAction(Position cratePosition, Item item, int index) {
		this.item = item;
		this.index = index;
		this.cratePosition = cratePosition;
	}

	@Override
	public int execute(Actor performer) {
		if (performer.getPosition().equals(cratePosition)) {
			Furniture crate = map.getBuildingBlock(cratePosition)
					.getFurniture();
			if (crate != null && performer.getInventory().size() != 0) {
				if (crate.getRemainingWeightCapacity() >= item.getWeight()) {
					crate.addItem(item);
					performer.getInventory().removeItem(index);
				}
			}
			return Action.COMPLETED;
		} else {
			int action = new MoveAction(cratePosition).execute(performer);
			if (action == Action.COMPLETED) {
				return Action.MADE_PROGRESS;
			}
			return action;
		}
	}
}
