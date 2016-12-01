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
		if (Game.getMap()
				.getBuildingBlock(position.getRow(), position.getCol()).getFurniture()!= null &&
				Game.getMap().getBuildingBlock(position.getRow(), position.getCol()).getFurniture().getID().equals(type.getID()))
			return Action.COMPLETED;

		if (position.isAdjacent(performer.getPosition())) {
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
			moveLocation = MoveAction.getMoveLocationNear(position);

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

}
