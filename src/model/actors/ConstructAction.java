package model.actors;

import java.util.LinkedList;
import java.util.List;

import model.building_blocks.RoomWallBlock;
import model.building_blocks.TrapDoorBlock;
import model.furniture.Furniture;
import model.game.Game;
import model.room.HorizontalTunnel;
import model.room.Room;
import model.room.VerticalTunnel;

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
	boolean cleared, blocksAsigned, blocksPlaced, furnitureAsigned, furniturePlaced;

	public ConstructAction(Room room) {
		this.room = room;
		this.blocksToChange = new LinkedList<>();
		int startRow = room.getPosition().getRow();
		if (room.needsWalls()) {
			startRow++;
		}

		for (int r = startRow; r < startRow + room.getRequiredHeight(); r++) {
			for (int c = room.getPosition().getCol(); c < room.getPosition().getCol() + room.getRequiredWidth(); c++) {
				Position p = new Position(r, Math.floorMod(c, Game.getMap().getTotalWidth()));
				blocksToChange.add(p);
				PlayerControlledActor.addActionToPlayerPool(new GatherAction(p));
			}
		}
		if (room.needsWalls()) {
			for (int c = room.getPosition().getCol(); c < room.getPosition().getCol() + room.getRequiredWidth(); c++) {
				Position roof = new Position(room.getPosition().getRow(),
						Math.floorMod(c, Game.getMap().getTotalWidth()));
				Position floor = new Position(room.getPosition().getRow() + 3,
						Math.floorMod(c, Game.getMap().getTotalWidth()));
				//if (c == room.getPosition().getCol() || c == room.getPosition().getCol() + room.getRequiredWidth() - 1) {
				if (c == room.getPosition().getCol() || c == Math.floorMod(room.getPosition().getCol() + room.getRequiredWidth() - 1, 
						Game.getMap().getTotalWidth())) {
					PlayerControlledActor.addActionToPlayerPool(new PlaceRoomBlockAction(roof, new TrapDoorBlock()));
					PlayerControlledActor.addActionToPlayerPool(new PlaceRoomBlockAction(floor, new TrapDoorBlock()));
				} else {
					PlayerControlledActor.addActionToPlayerPool(new PlaceRoomBlockAction(roof, new RoomWallBlock()));
					PlayerControlledActor.addActionToPlayerPool(new PlaceRoomBlockAction(floor, new RoomWallBlock()));
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.actors.Action#execute(model.actors.Actor)
	 */
	@Override
	public int execute(Actor performer) {
		// check to see if the action is complete
		if (!clearedArea())
			return Action.Pool;

		// asign blocks for placement
		asignBlockPlacement(performer);

		// check if the walls were built
		if (!wallsBuilt())
			return Action.Pool;
		
		placeFurniture(performer);

		if(furnitureIsPlaced())
			return Action.COMPLETED;
		else
			return Action.Pool;

	}

	private boolean furnitureIsPlaced() {
		if (!furniturePlaced) {
			for (Position p : room.getFurniture().keySet()){
				Position fp = new Position(room.getPosition().getRow() + p.getRow(), room.getPosition().getCol() + p.getCol());
				Furniture f = room.getFurniture().get(p);
				if(Game.getMap().getBuildingBlock(p).getFurniture() != null &&
						Game.getMap().getBuildingBlock(p).getFurniture().getID().equals(f.getID()));
					return false;
			}
			furniturePlaced = true;
		}
		return furniturePlaced;
	}

	/*
	 * Once the area has been cleared the room will need to be built
	 */
	private void asignBlockPlacement(Actor performer) {
		// asign the actors to build the walls
		if (!blocksAsigned) {
			for (Position p : blocksToChange)
				performer.getActionPool().add(new PlaceRoomBlockAction(p, room.getAppropriateBlock()));
			blocksAsigned = true;
		}

	}

	/*
	 * Clear the area so that the room can be built
	 */
	private boolean clearedArea() {
		// check to see if the action is complete
		if (!cleared) {
			cleared = true;
			for (Position p : blocksToChange) {
				if (Game.getMap().getBuildingBlock(p).isDestroyable())
					cleared = false;
			}
		}
		return cleared;
	}

	/*
	 * Checks to see if the walls have been built
	 */
	private boolean wallsBuilt() {
		if (!blocksPlaced) {
			blocksPlaced = true;
			for (Position p : blocksToChange) {
				if (!Game.getMap().getBuildingBlock(p).getID().equals(room.getAppropriateBlock().getID()))
					blocksPlaced = false;
			}
		}
		return blocksPlaced;
	}

	/*
	 * Places the furniture in the correct locations
	 */
	private void placeFurniture(Actor performer) {
		if (!furnitureAsigned) {
			for (Position p : room.getFurniture().keySet()){
				Position fp = new Position(room.getPosition().getRow() + p.getRow(), room.getPosition().getCol() + p.getCol());
				Furniture f = room.getFurniture().get(p);
				performer.getActionPool().add(new PlaceFurnitureAction(fp, f));
			}
			furnitureAsigned = true;
		}
	}

}
