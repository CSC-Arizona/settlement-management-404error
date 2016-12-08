package model.actors;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.building_blocks.BuildingBlock;
import model.building_blocks.RoomWallBlock;
import model.building_blocks.CeilingTile;
import model.furniture.ConstructionMaterialPile;
import model.furniture.Furniture;
import model.furniture.Ladder;
import model.furniture.Trapdoor;
import model.game.Game;
import model.game.Log;
import model.items.Item;
import model.room.Room;

public class ConstructAction extends Action {

	private static final long serialVersionUID = 1979882260923785059L;
	private Room room;
	private LinkedList<Position> blocksToChange;
	private LinkedList<Position> trapDoorBlocks;
	private LinkedList<Position> roomWallBlocks;
	private LinkedList<Position> sideBlocks;
	private boolean clearArea, wallsBuilt, scaffoldingPlaced, pileMade, wallpapered, furniturePlaced;
	private int height;
	private ConstructionMaterialPile cmp;
	private Position pileLoc;

	public ConstructAction(Room room) {
		System.out.println("New construct action created");
		this.room = room;
		this.blocksToChange = new LinkedList<>();
		this.trapDoorBlocks = new LinkedList<>();
		this.roomWallBlocks = new LinkedList<>();
		this.sideBlocks = new LinkedList<>();

		this.clearArea = false;
		this.wallsBuilt = false;
		this.scaffoldingPlaced = false;
		this.pileMade = false;
		this.wallpapered = false;
		this.furniturePlaced = false;

		int startRow = room.getPosition().getRow();
		if (room.needsWalls()) {
			startRow++;
			height = 4;
		} else {
			height = room.getRequiredHeight();
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
				trapDoorBlocks.add(roof);
				roomWallBlocks.add(floor);
				PlayerControlledActor.addActionToPlayerPool(new PlaceRoomBlockAction(roof, new CeilingTile()));
				PlayerControlledActor.addActionToPlayerPool(new PlaceFurnitureAction(roof, new Trapdoor()));
				PlayerControlledActor.addActionToPlayerPool(new PlaceRoomBlockAction(floor, new RoomWallBlock()));
			}
		}
	}

	@Override
	public int execute(Actor performer) {
		
		// check to see if the action is complete
		if (!clearArea) {
			clearArea = clearedArea();
			return Action.Pool;
		}

		if (!scaffoldingPlaced)
			scaffoldingPlaced(performer);

		if (!wallsBuilt) {
			wallsBuilt = wallsBuilt();
			return Action.Pool;
		}
		
		if (!scaffoldingPlaced) {
			scaffoldingPlaced = scaffoldingPlaced(performer);
			return Action.Pool;
		}

		if (!pileMade) {
			pileMade = makePile();
			return Action.Pool;
		}
		
        if (!cmp.isCompleted()) {
        	checkForActorContributions();
        	return Action.Pool;
        }
        
        if (!cmp.isCompleted()) {
        	checkForCrateContributions(performer);
        	return Action.Pool;
        }
        
        if (!wallpapered) {
        	wallpapered = wallpaper(performer);
        	return Action.Pool;
        }
        
        if (!furniturePlaced) {
        	furniturePlaced = placeFurniture(performer);
        }
        
        Game.getMap().addNewCompletedRoom(room);
		return Action.COMPLETED;
	}

	/*
	 * Clear the area so that the room can be built
	 */
	private boolean clearedArea() {
		boolean cleared = true;
		for (Position p : blocksToChange) {
			if (Game.getMap().getBuildingBlock(p).isDestroyable()) {
				PlayerControlledActor.addActionToPlayerPool(new GatherAction(p));
				cleared = false;
			}
		}
		return cleared;
	}

	private boolean wallsBuilt() {
		if (!room.needsWalls())
			return true;
		for (Position p : trapDoorBlocks) {
			if (!Game.getMap().getBuildingBlock(p).getID().equals("Ceiling tile")) {
				PlayerControlledActor.addActionToPlayerPool(new PlaceRoomBlockAction(p, new CeilingTile()));
				return false;
			}
		}
		for (Position q : roomWallBlocks) {
			if (!Game.getMap().getBuildingBlock(q).getID().equals("Room wall")) {
				PlayerControlledActor.addActionToPlayerPool(new PlaceRoomBlockAction(q, new RoomWallBlock()));
				return false;
			}
		}
		return true;
	}

	private boolean scaffoldingPlaced(Actor performer) {
		if (!room.needsWalls())
			return true;
		scaffoldingPlaced = true;
		for (int r = room.getPosition().getRow() + 1; r < room.getPosition().getRow() + height - 1; r++) {
			Position sPos1 = new Position(r, room.getPosition().getCol());
			Position sPos2 = new Position(r, Math.floorMod(room.getPosition().getCol() + room.getRequiredWidth() - 1,
					Game.getMap().getTotalWidth()));
			performer.getActionPool().add(new PlaceFurnitureAction(sPos1, new Ladder()));
			performer.getActionPool().add(new PlaceFurnitureAction(sPos2, new Ladder()));
			sideBlocks.add(sPos1);
			sideBlocks.add(sPos2);
		}
		for (Position p : trapDoorBlocks) {
			if (Game.getMap().getBuildingBlock(p).getFurniture() == null) {
				Game.getMap().getBuildingBlock(p).addFurniture(new Trapdoor());
				scaffoldingPlaced = false;
			}
		}
		for (Position q : sideBlocks) {
			if (Game.getMap().getBuildingBlock(q).getFurniture() == null) {
				Game.getMap().getBuildingBlock(q).addFurniture(new Ladder());
				scaffoldingPlaced = false;
			}
		}
		return scaffoldingPlaced;
	}

	private boolean makePile() {
		cmp = new ConstructionMaterialPile(room.getRequiredBuildMaterials());
		if (!room.needsWalls())
			return true;
		pileLoc = new Position(room.getPosition().getRow() + 2,
				Math.floorMod(room.getPosition().getCol() + 1, Game.getMap().getTotalWidth()));
		BuildingBlock pileBlock = Game.getMap().getBuildingBlock(pileLoc);
		pileBlock.addFurniture(cmp);
		if (pileBlock.getFurniture() != null && pileBlock.getFurniture().getID().equals("Construction material pile")) {
			String update = "To finish building this " + room.getID() + ", the dragons need to gather " + cmp.toString();
			Log.add(update);
			return true;
		} else {
			return false;
		}
	}
	
	private void checkForActorContributions() {
		List<Item> orig = cmp.getRequiredMaterials();
		List<Item> reqMaterials = new ArrayList<>(orig);
		List<Actor> aOrig = Actor.allActors;
		List<Actor> actors = new ArrayList<>(aOrig);
		
		for (Item i : reqMaterials) {
			for (Actor a : actors) {
				Inventory inv = a.getInventory();
				if (inv.contains(i)) {
					//reqMaterials.remove(i);
					addToPile(a, i);
					break;
				}
			}
		}
	}

	private void checkForCrateContributions(Actor performer) {
		List<Item> orig = cmp.getRequiredMaterials();
		List<Item> reqMat = new ArrayList<>(orig);
		for (Item i : reqMat) {
			Position itPos = VisitCrateAction.getCrateWhichContainsItem(i);
			if (itPos != null) {
				VisitCrateAction vca = new VisitCrateAction(i);
				vca.execute(performer);
			}

		}
	}

	/*
	 * returns true if action should be delayed for this particular performer, false otherwise
	 */
	private void addToPile(Actor performer, Item i) {
		Inventory inv = performer.getInventory();
		if (inv.contains(i)) {
			if (performer.getPosition().isAdjacent(pileLoc)) {
				if (cmp.addItem(i))
					inv.removeItem(i);
			} else {
				MoveAction move = new MoveAction(pileLoc);
				move.execute(performer);
				if (cmp.addItem(i))
					inv.removeItem(i);
			}
		}
	}

	private boolean wallpaper(Actor performer) {
		for (Position p : blocksToChange)
			performer.getActionPool().add(new PlaceRoomBlockAction(p, room.getAppropriateBlock()));
		for (Position q : blocksToChange) {
			if (!Game.getMap().getBuildingBlock(q).getID().equals(room.getAppropriateBlock().getID())) {
				Game.getMap().setBuildingBlock(q, room.getAppropriateBlock());
				return false;
			}
		}
		return true;
	}
	
	private boolean placeFurniture(Actor performer) {
		for (Position p : room.getFurniture().keySet()) {
			Position fp = new Position(room.getPosition().getRow() + p.getRow(), 
					Math.floorMod(room.getPosition().getCol() + p.getCol(), Game.getMap().getTotalWidth()));
			Furniture f = room.getFurniture().get(p);
			performer.getActionPool().add(new PlaceFurnitureAction(fp, f));
		}
		for (Position q : room.getFurniture().keySet()) {
			Position fq = new Position(room.getPosition().getRow() + q.getRow(), 
					Math.floorMod(room.getPosition().getCol() + q.getCol(), Game.getMap().getTotalWidth()));
			if (Game.getMap().getBuildingBlock(fq).getFurniture() == null)
				return false;
		}
		return true;
	}

}
