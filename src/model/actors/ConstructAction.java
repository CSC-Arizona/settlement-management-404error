package model.actors;

import java.util.LinkedList;
import java.util.List;

import model.building_blocks.RoomWallBlock;
import model.building_blocks.TrapDoorBlock;
import model.furniture.ConstructionMaterialPile;
import model.furniture.Furniture;
import model.game.Game;
import model.room.Room;

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
	private Position pileLoc;
	
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
	public ConstructAction(Room room, Position pileLoc) {
		this.room = room;
		this.pileLoc = pileLoc;
		System.out.println("About to create the gather action");
	    PlayerControlledActor.addActionToPlayerPool(new GatherAction(pileLoc));
	}
	
	/**
	 * keeps returning the action to the Action pool until the pileLoc block is cleared, 
	 * then places the pile at that location and creates a new MakeConstructionMaterialPileAction,
	 * which takes care of having the actors gather the appropriate resources for the Room.
	 */
	public int execute(Actor performer) {
		if (!Game.getMap().getBuildingBlock(pileLoc).isOccupiable())
			return Action.Pool;
		placePile();
//		if (Game.getMap().getBuildingBlock(pileLoc).getFurniture() != null && 
//				!Game.getMap().getBuildingBlock(pileLoc).getFurniture().getID().equals("Construction material pile")) {
//			System.out.println("The pile still isn't here");
//			return Action.Pool;
//		} else {
		    PlayerControlledActor.addActionToPlayerPool(new MakeConstructionMaterialPileAction(room, pileLoc));
		    return Action.COMPLETED;
//		}
	}
	
	/*
	 * Puts the Construction material pile at the appropriate location if it isn't already
	 * there.
	 */
	private void placePile() {
		System.out.println("calling placePile");
		Furniture inQuestion = Game.getMap().getBuildingBlock(pileLoc).getFurniture();
		if (inQuestion == null)
			Game.getMap().getBuildingBlock(pileLoc).addFurniture(new ConstructionMaterialPile(room.getRequiredBuildMaterials()));
		else if (!inQuestion.toString().equals("Construction material pile"))
			Game.getMap().getBuildingBlock(pileLoc).addFurniture(new ConstructionMaterialPile(room.getRequiredBuildMaterials()));
		else 
			return;
	}

}
