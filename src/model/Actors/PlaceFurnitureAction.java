package model.Actors;

import model.BuildingBlocks.BuildingBlock;
import model.Furniture.Furniture;
import model.Game.Game;

public class PlaceFurnitureAction extends Action {
	
	private Position position;
	private Furniture type;
	private MoveAction movement;
	private Position moveLocation;

	public PlaceFurnitureAction(Position position, Furniture type) {
		super();
		this.position = position;
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Actors.Action#execute(model.Actors.Actor)
	 */
	@Override
	public int execute(Actor performer) {
		// if the block can't be gathered cancel the action
		if (!Game.getMap()
				.getBuildingBlock(position.getRow(), position.getCol())
				.isDestroyable())
			return Action.COMPLETED;

		if (isAdjacent(performer)) {
			Game.getMap().getBuildingBlock(position).addFurniture(type);
			return Action.COMPLETED;
		}
		return move(performer);
	}

	/**
	 * @return
	 * 
	 */
	private int move(Actor performer) {
		// if the Move Location has not yet been calculated, calculate position
		if (moveLocation == null)
			moveLocation = getMoveLocation();

		// delay the action if it can not be moved
		if (moveLocation == null)
			return Action.Pool;

		// if not nearby move to a valid location
		if (movement == null)
			movement = new MoveAction(moveLocation);
		int result = movement.execute(performer);
		// cancel the action if this actor can not perform
		if (result == Action.Pool) {
			movement = null;
			moveLocation = null;
			return Action.Pool;
		}
		if(result == Action.DELAY)
			return Action.Pool;
		return Action.MADE_PROGRESS;
	}

	/**
	 * Finds an adjacent valid location near the block to move the actor to
	 * 
	 * @return The Position to move to
	 */
	private Position getMoveLocation() {
		// check to see if a nearby location is valid
		for (int r = position.getRow() - 1; r <= position.getRow() + 1; r++) {
			for (int c = position.getCol() - 1; c <= position.getCol() + 1; c++) {
				if (Game.validActorLocation(r, Math.floorMod(c, Game.getMap().getTotalWidth())))
					return new Position(r, Math.floorMod(c, Game.getMap().getTotalWidth()));
			}
		}
		return null;
	}

	/*
	 * Checks if the actor is adjacent to the block
	 */
	private boolean isAdjacent(Actor performer) {
		return Math
				.abs(Math.floorMod(position.getCol(), Game.getMap().getTotalWidth() - 1)
						- Math.floorMod(performer.getPosition().getCol(), Game.getMap().getTotalWidth() - 1)) <= 1
				&& Math.abs(position.getRow() - performer.getPosition().getRow()) <= 1;
	}

}
