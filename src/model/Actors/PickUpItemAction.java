package model.Actors;

import model.Game.Game;
import model.Items.Item;

public class PickUpItemAction extends Action {

	private Position itemPosition;
	private StoreItemAction store;
	private MoveAction movement;
	private Item item;
	private boolean legacyMovement;

	public PickUpItemAction(Position itemPosition, Item item) {
		this.item = item;
		this.itemPosition = itemPosition;
		legacyMovement = false;
	}

	@Override
	public int execute(Actor performer) {
		if(performer.getInventory().canAdd(item)){
			if (performer.getPosition().equals(itemPosition)) {
				Game.getMap().removeItemFromGround(itemPosition, item);
				performer.getInventory().addItem(item);
				return Action.COMPLETED;
			} else {
				if(movement == null)
					movement = new MoveAction(itemPosition);
				int action = movement.execute(performer);
				if(action == Action.CANCELL && legacyMovement){
					movement = new MoveAction(itemPosition);
					action = movement.execute(performer);
				}
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
