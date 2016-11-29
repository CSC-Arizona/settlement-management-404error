package model.Actors;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.BuildingBlocks.BuildingBlock;
import model.Game.Game;
import model.Room.Room;

/**
 * Prerequisite for ConstructAction: the square that is selected needs to have a
 * path leading to it (occupiable spaces adjacent to whatever square is chosen).
 * I think this would be easier to implement on the map side once we actually
 * put in a menu that shows when a square is selected.
 * 
 * @author Katherine Walters
 * @author Jonathon Davis
 */
public class ConstructAction extends Action {

	private static final long serialVersionUID = 3917613009303294799L;
	private BuildingBlock corner;
	private List<Action> GatherCommands;
	private List<Position> blocksToChange;

	public ConstructAction(Room room) {
		this.corner = Game.getMap().getBuildingBlock(room.getPosition());
		this.blocksToChange = new LinkedList<>();
		for (int r = room.getPosition().getRow(); r < room.getPosition().getRow() + room.getRequiredHeight(); r++) {
			for (int c = room.getPosition().getCol(); c < room.getPosition().getCol() + room.getRequiredWidth(); c++) {
				PlayerControlledActor.addActionToPlayerPool(new GatherAction(new Position(r,c)));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Actors.Action#execute(model.Actors.Actor)
	 */
	@Override
	public int execute(Actor performer) {
		// cancel the action if the room is destined to be build on already-used
		// space
		return Action.COMPLETED;
		/*
		if (corner.isOccupiable())
			return Action.CANCELL;

		if (GatherCommands == null) {
			GatherCommands = new LinkedList<>();
			for (Position block : blocksToChange)
				GatherCommands.add(new GatherPlantsAction(block));
		}

		// Mine out the area
		int result = Action.DELAY;
		Iterator<Action> it = GatherCommands.iterator();
		while (result == Action.DELAY && GatherCommands.size() > 0 && it.hasNext()) {
			Action action = it.next();
			result = action.execute(performer);
			if (result == Action.COMPLETED || result == Action.CANCELL) {
				it.remove();
				break;
			}
			if (result == Action.MADE_PROGRESS)
				return Action.MADE_PROGRESS;
		}

		// TODO: fill the room with furniture
		if (GatherCommands.size() <= 0)
			return Action.COMPLETED;
		// if no progress could be made then delay
		return Action.DELAY;
		*/
	}

}
