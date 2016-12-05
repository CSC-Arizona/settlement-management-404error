package model.actors;

import model.building_blocks.BuildingBlock;
import model.game.Game;

public class PlaceRoomBlockAction extends Action {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1887113712841771133L;
	private Position position;
	private BuildingBlock type;
	private MoveAction movement;
	private Position moveLocation;

	public PlaceRoomBlockAction(Position position, BuildingBlock type) {
		super();
		this.position = position;
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.actors.Action#execute(model.actors.Actor)
	 */
	@Override
	public int execute(Actor performer) {
		// if the block can't be gathered cancel the action
		if (Game.getMap().getBuildingBlock(position.getRow(), position.getCol()).getID().equals(type.getID())) {
			return Action.COMPLETED;
		}
		if (position.isAdjacent(performer.getPosition())) {
			Game.getMap().setBuildingBlock(position, type);
			return Action.COMPLETED;
		}
		if ((type.getID().equals("Room wall") || type.getID().equals("Trap door")) && position.isTwoAbove(performer.getPosition())) {
			Game.getMap().setBuildingBlock(position, type);
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
