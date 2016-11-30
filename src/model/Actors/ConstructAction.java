package model.Actors;

import java.util.LinkedList;
import java.util.List;

import model.Game.Game;
import model.Room.HorizontalTunnel;
import model.Room.Room;
import model.Room.VerticalTunnel;

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
	private List<Position> blocksToChange;
	private Room room;

	public ConstructAction(Room room) {
		this.room = room;
		this.blocksToChange = new LinkedList<>();
		// add all the gather commands to clear out the room
		for (int r = room.getPosition().getRow(); r < room.getPosition()
				.getRow() + room.getRequiredHeight(); r++) {
			for (int c = room.getPosition().getCol(); c < room.getPosition()
					.getCol() + room.getRequiredWidth(); c++) {
				Position p = new Position(r, Math.floorMod(c, Game.getMap()
								.getTotalWidth()));
				blocksToChange.add(p);
				// PlayerControlledActor.addActionToPlayerPool(new
				// GatherAction(p));
				PlayerControlledActor
						.addActionToPlayerPool(new GatherForConstructionAction(
								p, room));
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
		// check to see if the action is complete
		for (Position p : blocksToChange) {
			if (!Game.getMap().getBuildingBlock(p).isOccupiable())
				return Action.Pool;
		}
		// if it is add the furniture
		if (room.getFurniture() != null)
			for (Position p : room.getFurniture().keySet()) {
				Position fp = new Position(room.getPosition().getRow()
						+ p.getRow(), room.getPosition().getCol() + p.getCol());
				Game.getMap().addFurniture(room.getFurniture().get(p), fp);
			}
		// then return completed
		return Action.COMPLETED;

	}

}
