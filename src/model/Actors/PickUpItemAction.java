package model.Actors;

import model.Game;
import model.Items.Item;

public class PickUpItemAction implements Action {

	private Position itemPosition;
	private StoreItemAction store;
	private Item item;

	public PickUpItemAction(Position itemPosition, Item item) {
		this.item = item;
		this.itemPosition = itemPosition;
	}

	@Override
	public int execute(Actor performer) {
		if(performer.getInventory().canAdd(item)){
			if (performer.getPosition().equals(itemPosition)) {
				Game.getMap().removeItemFromGround(itemPosition, item);
				performer.getInventory().addItem(item);
				return Action.COMPLETED;
			} else {
				int action = new MoveAction(itemPosition).execute(performer);
				if (action == Action.COMPLETED) {
					return Action.MADE_PROGRESS;
				}
				return action;
			}
		} else {
			if(store == null)
				store = new StoreItemAction(performer.getInventory().getItem(0));
			store.execute(performer);
			return Action.MADE_PROGRESS;
		}
	}
}
