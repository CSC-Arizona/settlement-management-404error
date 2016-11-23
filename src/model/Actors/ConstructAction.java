package model.Actors;

import java.util.LinkedList;
import java.util.List;

import model.GameMap;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.CavernBlock;
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
 *
 */
public class ConstructAction implements Action {

	private Position pos;
	private BuildingBlock corner;
	private Room roomType;
	// blocksToChange will contain all of the blocks remaining to be dug out if
	// this
	// action is stalled for any reason
	private List<Position> blocksToChange;

	ConstructAction(Position pos, Room roomType) {
		this.pos = pos;
		this.corner = GameMap.getBlock(pos);
		this.roomType = roomType;
		this.blocksToChange = new LinkedList<>();
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
		if (corner.isOccupiable())
			return Action.CANCELL;
		// cancel if the the room's dimensions aren't available in either
		// direction
		if (isAvailableAsTopLeft()) {
			// gather those squares
			// set those squares to be caverns
			return digOutRoom(performer);
		} else if (isAvailableAsTopRight()) {
			// gather those squares
			// set those squares to be caverns
			return digOutRoom(performer);
		} else {
			// dimensions weren't available
			return Action.CANCELL;
		}
	}

	/*
	 * For every BuildingBlock in the soon to be room, a gather action is
	 * created. If this gather action is stalled for some reason, it stalls this
	 * construct action as well. Else, the room is dug out and each block in the
	 * room is set to be a cavernBlock. (Should we make blocks for each specific
	 * type of room instead??)
	 */
	private int digOutRoom(Actor performer) {
		for (Position p : blocksToChange) {
			GatherAction g = new GatherAction(p);
			int result = g.execute(performer);
			if (result != Action.COMPLETED) {
				return result;
			}
			GameMap.setBuildingBlock(p, new CavernBlock());
			blocksToChange.remove(p);
		}
		return Action.COMPLETED;
	}

	/*
	 * Returns a list of all the blocks that still need to be gathered and
	 * changed to caverns if this action is stalled for some reason.
	 */
	public List<Position> getBlocksToChange() {
		return blocksToChange;
	}

	private boolean isAvailableAsTopLeft() {
		// check to see if the chosen position is the top left corner
		int width = roomType.getRequiredWidth();
		int height = roomType.getRequiredHeight();
		BuildingBlock curr;
		for (int r = pos.getRow(); r < pos.getRow() + height; r++) {
			for (int c = pos.getCol(); c < pos.getCol() + width; c++) {
				curr = GameMap.getBlock(r, c);
				if (curr.isDestroyable() && !curr.isOccupiable()) {
					blocksToChange.add(new Position(r, c));
				} else {
					blocksToChange.clear();
					return false;
				}
			}
		}
		return true;
	}

	private boolean isAvailableAsTopRight() {
		// check to see if the chosen position is the top right corner
		int width = roomType.getRequiredWidth();
		int height = roomType.getRequiredHeight();
		BuildingBlock curr;
		for (int r = pos.getRow(); r < pos.getRow() + height; r++) {
			for (int c = pos.getCol(); c > pos.getCol() - width; c--) {
				curr = GameMap.getBlock(r, c);
				if (curr.isDestroyable() && !curr.isOccupiable()) {
					blocksToChange.add(new Position(r, c));
				} else {
					blocksToChange.clear();
					return false;
				}
			}
		}
		return true;
	}

}
