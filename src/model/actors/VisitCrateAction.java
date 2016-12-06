/**
 * 
 */

package model.actors;

import java.util.HashMap;

import model.furniture.Crate;
import model.furniture.Furniture;
import model.game.Game;
import model.items.Item;

public class VisitCrateAction extends Action {

	private static final long serialVersionUID = 1454588966658079359L;
	private Position cratePosition;
	private Item item;
	private MoveAction move;
	private StoreItemAction store;

	public VisitCrateAction(Item item) {
		this.item = item;
		cratePosition = getCrateWhichContainsItem(item);
	}

	@Override
	public int execute(Actor performer) {
		if (cratePosition != null) {
			if (performer.getPosition().equals(cratePosition)) {
				Furniture crate = Game.getMap().getBuildingBlock(cratePosition)
						.getFurniture();
				if (crate != null) {
					if (performer.getInventory().canAdd(item)) {
						crate.removeItem(item);
						performer.getInventory().addItem(item);
						return Action.COMPLETED;
					} else {
                        if (store == null)
                        	store = new StoreItemAction(performer.getInventory().getItem(0));
                        int action = store.execute(performer);
                        if (action == Action.COMPLETED)
                        	this.execute(performer);
                        return Action.Pool;
					}
				} else {
					return Action.COMPLETED;
				}
			} else {
				if (move == null)
					move = new MoveAction(cratePosition);
				int action = move.execute(performer);
				if (action == Action.COMPLETED) {
					return Action.MADE_PROGRESS;
				}
				return Action.Pool;
			}
		} else {
		    return Action.COMPLETED;
		}
	}

	public Position getCrateWhichContainsItem(Item it) {
		HashMap<Furniture, Position> mapFurniture = Game.getMap()
				.getFurniture();
		HashMap<Crate, Position> crates = new HashMap<>();
		if (mapFurniture != null) {
			for (Furniture f : mapFurniture.keySet()) {
				if (f.getID().equals("crate")) {
					crates.put((Crate) f, mapFurniture.get(f));
				}
			}
		}
		// returns a crate which is holding the appropriate item
		if (!crates.isEmpty()) {
			for (Crate c : crates.keySet()) {
				if (c.contains(it))
					return crates.get(c);
			}
		}
		return null;
	}
}
