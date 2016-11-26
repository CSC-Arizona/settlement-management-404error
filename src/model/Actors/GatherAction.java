/**
 * 
 */
package model.Actors;

import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.BuildingBlock;
import model.Game.Game;
import model.Items.Item;

/**
 * Creates a Action for an Actor to gather a resource
 * 
 * @author Jonathon Davis
 *
 */
public class GatherAction extends Action {

	private static final long serialVersionUID = 5909099133984007954L;
	Position position;
	int durability;
	MoveAction movement;
	Position moveLocation;

	public GatherAction(Position position) {
		this.position = position;
		durability = Integer.MAX_VALUE;
		Game.getMap().markForGathering(position);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Actors.Action#execute(model.Actors.Actor)
	 */
	@Override
	public int execute(Actor performer) {
		// if the block can't be gathered cancel the action
		if (!Game.getMap().getBuildingBlock(position.getRow(), position.getCol()).isDestroyable())
			return Action.CANCELL;

		if (Math.abs(position.getCol() - performer.getPosition().getCol()) <= 1
				&& Math.abs(position.getRow() - performer.getPosition().getRow()) <= 1) {
			BuildingBlock block = Game.getMap().getBuildingBlock(position.getRow(), position.getCol());
			if (durability == Integer.MAX_VALUE)
				durability = block.getDurability();
			durability--;
			if (durability <= 0) {
				Game.getMap().setBuildingBlock(position, new AirBlock());
				if (block.lootBlock() != null)
					for (Item i : block.lootBlock())
						if(performer.getInventory().canAdd(i)){
							performer.getInventory().addItem(i);
						} else {
							Game.getMap().addItemToGround(position, i);
							performer.addActionToPool(new PickUpItemAction(position, i));
						}
				if (Game.getMap().getTotalHeight() > performer.getPosition().getRow() + 1
						&& Game.getMap().getBuildingBlock(performer.getPosition().getRow() + 1, performer.getPosition().getCol()).isOccupiable()) {
					performer.setPosition(new Position(performer.getPosition().getRow() + 1, performer.getPosition().getCol()));
				}
				return Action.COMPLETED;
			}
			return Action.MADE_PROGRESS;
		}
		// if the Move Location has not yet been calculated, calculate position
		if (moveLocation == null)
			moveLocation = getMoveLocation();

		// delay the action if it can not be moved
		if (moveLocation == null)
			return Action.DELAY;

		// if not nearby move to a valid location
		if (movement == null)
			movement = new MoveAction(moveLocation);
		movement.execute(performer);

		return Action.MADE_PROGRESS;
	}

	/**
	 * Finds an adjacent valid location near the block to move the actor to
	 * 
	 * @return The Position to move to
	 */
	private Position getMoveLocation() {
		// check to see if a nearby location is valid
		for (int r = position.getRow() - 1; r < position.getRow() + 1; r++) {
			for (int c = position.getCol() - 1; c < position.getCol() + 1; c++) {
				if (r > 0 && c > 0 && r < Game.getMap().getTotalWidth() && c < Game.getMap().getTotalWidth()
						&& Game.getMap().getBuildingBlock(r, c).getID().equals("Air") && r + 1 < Game.getMap().getTotalHeight()
						&& !Game.getMap().getBuildingBlock(r + 1, c).getID().equals("Air"))
					return new Position(r, c);
			}
		}
		return null;
	}

}
