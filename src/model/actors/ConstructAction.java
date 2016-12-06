package model.actors;

import java.util.LinkedList;
import java.util.List;

import model.building_blocks.RoomWallBlock;
import model.building_blocks.TrapDoorBlock;
import model.furniture.ConstructionMaterialPile;
import model.furniture.Furniture;
import model.game.Game;
import model.game.Log;
import model.room.HorizontalTunnel;
import model.room.Room;
import model.room.VerticalTunnel;

/**
 * Prerequisite for ConstructAction: the pileLoc Position which is passed to the constructor
 * needs to be accessible to the Actors. If it's not the action won't be completed. In the GUI,
 * this is checked before creating the ConstructAction.
 * 
 * @author Katherine Walters
 */
public class ConstructAction extends Action {

	private static final long serialVersionUID = 3917613009303294799L;
	private Room room;
	private int width;
	private int height;
	private GatherAction ga;
	private Position gaLoc;
	private int action;
	
	/**
	 * This constructor requires both a Room and a Position, which represents where the 
	 * construction material pile will be built (basically the angle from which the actors can
	 * access and begin building the room). The logic to automatically create a ConstructAction
	 * is contained in the BasicView class, but can be done by hand as well for scenarios and 
	 * tests. Clears the block at the given location so that the Construction material pile can
	 * be 'built' there.
	 * 
	 * @param room: Room object which is to be constructed. "room" contains it's own position
	 *              (top left corner location)
	 * @param pileLoc: Position of one (of the maybe foor) accessible corners of the room 
	 *               which is to be constructed.
	 */
	public ConstructAction(Room room) {
		this.room = room;
		this.width = room.getRequiredWidth();
		this.height = room.getRequiredHeight();
		if (!room.getClass().equals(VerticalTunnel.class) && !room.getClass().equals(HorizontalTunnel.class))
			height += 2;
		ga = null;
		action = -1;
	}
	
	/**
	 * keeps returning the action to the Action pool until the pileLoc block is cleared, 
	 * then places the pile at that location and creates a new MakeConstructionMaterialPileAction,
	 * which takes care of having the actors gather the appropriate resources for the Room.
	 */
	public int execute(Actor performer) {
		if (gaLoc == null) {
			if (!choosePileLoc())
				return Action.Pool;
			else
				return Action.MADE_PROGRESS;
		} else if (Game.getMap().getBuildingBlock(gaLoc).isOccupiable()) {
			placePile(gaLoc);
			PlayerControlledActor.addActionToPlayerPool(new MakeConstructionMaterialPileAction(room, gaLoc, false));
			return Action.COMPLETED;
		} else if (ga == null) {
			System.out.println("gaLocation has been set: " + gaLoc.toString());
			System.out.println("ga is null");
			ga = new GatherAction(gaLoc);
			return Action.MADE_PROGRESS;
		} else {
			int action = ga.execute(performer);
			if (action == Action.COMPLETED) {
				return Action.MADE_PROGRESS;
			} else {
				return Action.Pool;
			}
		}
	}

	/*
	 * Checks all of the blocks that will make up a room. If any are accessible, creates a pile there and returns
	 * true. If none are accessible and no pile is created, return false.
	 */
	private boolean choosePileLoc() {
		if (ga == null) {
			Position topLeft = room.getPosition();
			for (int r = topLeft.getRow(); r < topLeft.getRow() + height; r++) {
				for (int c = topLeft.getCol(); c < topLeft.getCol() + width; c++) {
					Position curr = new Position(r, Math.floorMod(c, Game.getMap().getTotalWidth()));
					if (MoveAction.getMoveLocationNear(curr) != null) {
						gaLoc = curr;
						return true;
					}
				}
			}
		} 
		return false;
	}
	
	private void placePile(Position loc) {
		ConstructionMaterialPile pile = new ConstructionMaterialPile(room.getRequiredBuildMaterials());
		Game.getMap().getBuildingBlock(loc).addFurniture(pile);
		String instruction = "To finish building this room, the Dragons must gather " + pile.toString();
		System.out.println(instruction);
		Log.add(instruction);
	}
}