package model.actors;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import model.building_blocks.BuildingBlock;
import model.furniture.ConstructionMaterialPile;
import model.furniture.Furniture;
import model.game.Game;
import model.game.Log;
import model.items.Item;
import model.room.Room;

public class UpgradeRoomAction extends Action {

	private Room room;
	private List<Item> reqMaterials;
	private TreeMap<Position, Furniture> roomFurniture;
	private boolean pileMade, furniturePlaced, pileRemoved;
	private ConstructionMaterialPile cmp;
    private Position pileLoc;
	
	private static final long serialVersionUID = 2426959828400570427L;

	public UpgradeRoomAction(Room toUpgrade) {
		System.out.println("Creating a new upgradeRoomAction");
		this.room = toUpgrade;
		if (!room.upgradeRoom())
			System.out.println("the room couldn't be upgraded");
		reqMaterials = room.getRequiredUpgradeMaterials();
		roomFurniture = room.getFurniture();
		cmp = new ConstructionMaterialPile(reqMaterials);
		this.pileMade = false;
		this.furniturePlaced = false;
		this.pileRemoved = false;
	}
	
	@Override
	public int execute(Actor performer) {
		System.out.println("Executing upgradeRoomaction");
		
		if (!pileMade) {
			pileMade = makePile();
			return Action.Pool;
		}
		
		System.out.println("the pile is made");
		
        if (!cmp.isCompleted()) {
        	checkForActorContributions();
        	return Action.Pool;
        }
        
        System.out.println("the cmp is completed");
        
        if (!cmp.isCompleted()) {
        	checkForCrateContributions(performer);
        	return Action.Pool;
        }
        
        if (!furniturePlaced) {
        	furniturePlaced = placeFurniture(performer);
        }
        
        System.out.println("the furniture is placed");
        if (!pileRemoved)
        	pileRemoved = removePile();
        
		return Action.COMPLETED;
	}
	
	private boolean makePile() {
		//cmp = new ConstructionMaterialPile(room.getRequiredBuildMaterials());
		//if (!room.needsWalls())
			//return true;
		pileLoc = new Position(room.getPosition().getRow() + 2,
				Math.floorMod(room.getPosition().getCol() + room.getRequiredWidth() - 2, Game.getMap().getTotalWidth()));
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

	private boolean removePile() {
		if (Game.getMap().getBuildingBlock(pileLoc).getFurniture() != null &&
				Game.getMap().getBuildingBlock(pileLoc).getFurniture().getID().equals("Construction material pile"))
			Game.getMap().getBuildingBlock(pileLoc).removeFurniture();
		if (Game.getMap().getBuildingBlock(pileLoc).getFurniture() == null ||
				!Game.getMap().getBuildingBlock(pileLoc).getFurniture().getID().equals("Construction material pile"))
			return true;
		else
			return false;
	}
}
