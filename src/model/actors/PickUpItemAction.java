package model.actors;

import model.building_blocks.BuildingBlock;
import model.game.Game;
import model.items.Item;

public class PickUpItemAction extends Action {

	private static final long serialVersionUID = 1677097186240103558L;
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
				BuildingBlock bb = Game.getMap().getBuildingBlock(itemPosition);
				if(bb.contains(item)) {
					int newCol = Math.floorMod(itemPosition.getCol(), Game.getMap().getTotalWidth());
					Position newPos = new Position(itemPosition.getRow(), newCol);
					Game.getMap().removeItemFromGround(newPos, item);
					performer.getInventory().addItem(item);
				}
				return Action.COMPLETED;
			} else {
				if(movement == null)
					movement = new MoveAction(itemPosition);
				int action = movement.execute(performer);
				if(action == Action.Pool && legacyMovement){
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
