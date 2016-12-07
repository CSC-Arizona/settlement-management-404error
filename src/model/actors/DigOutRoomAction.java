package model.actors;

import java.util.LinkedList;

import model.building_blocks.RoomWallBlock;
import model.building_blocks.TrapDoorBlock;
import model.furniture.Furniture;
import model.furniture.Scaffolding;
import model.game.Game;
import model.room.Room;

public class DigOutRoomAction extends Action {
	
	private Room room;
	private LinkedList<Position> blocksToChange;
	private boolean cleared, scaffoldingPlaced;
	private int height;

	public DigOutRoomAction(Room room) {
		System.out.println("Created new digOutRoom action for Room at " + room.getPosition());
		this.room = room;
		this.blocksToChange = new LinkedList<>();
		cleared = false;
		scaffoldingPlaced = false;
		
		int startRow = room.getPosition().getRow();
		if (room.needsWalls()) {
			startRow++;
			height = 4;
		} else {
			height = 2;
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

	@Override
	public int execute(Actor performer) {
		System.out.println(performer.getName() + " has accepted the dig out action for the room at " + room.getPosition());

		// check to see if the action is complete
		if (!clearedArea())
			return Action.Pool;
		else {
		
		placeScaffolding(performer);
		
		if (scaffoldingPlaced)
			return Action.COMPLETED;
		else
			return Action.Pool;
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
	
	private void placeScaffolding(Actor performer) {
		if (!scaffoldingPlaced) {
			for (int r = room.getPosition().getRow(); r < room.getPosition().getRow() + height; r++) {
				Position sPos1 = new Position(r, room.getPosition().getCol());
				Position sPos2 = new Position(r, Math.floorMod(room.getPosition().getCol() + room.getRequiredWidth() - 1, Game.getMap().getTotalWidth()));
				performer.getActionPool().add(new PlaceFurnitureAction(sPos1, new Scaffolding()));
				performer.getActionPool().add(new PlaceFurnitureAction(sPos2, new Scaffolding()));
			}
			scaffoldingPlaced = true;
		}
	}

}
