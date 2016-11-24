package model.Actors;

import model.Map;
import model.Items.Item;

public class PickUpItemAction implements Action {

	private Map map;
	private Position itemPosition;
	private Item item;

	public PickUpItemAction(Position itemPosition, Item item,
			Map map) {
		this.map = map;
		this.item = item;
		this.itemPosition = itemPosition;
	}

	@Override
	public int execute(Actor performer) {
		if (performer.getPosition().equals(itemPosition)) {
			map.removeItemFromGround(itemPosition, item);
			performer.getInventory().addItem(item);
			return Action.COMPLETED;
		} else {
			int action = new MoveAction(itemPosition).execute(performer);
			if (action == Action.COMPLETED) {
				return Action.MADE_PROGRESS;
			}
			return action;
		}
	}
}
