package model.actors;

import java.util.HashMap;
import java.util.TreeMap;

import model.furniture.Furniture;
import model.game.Game;
import model.items.WheatKernelItem;
import model.room.FarmRoom;

//Author: Maxwell Faridian
//This action has a performer collect seeds, move to an empty plot, and plant the seeds
public class PlantAction extends Action {

	private static final long serialVersionUID = -7248414302857557504L;
	private Position toGoTo;
	private MoveAction ma;
	private MoveAction comeBack;
	private VisitCrateAction goToCrate;
	

	@Override
	public int execute(Actor performer) {
		if(toGoTo == null) {
			findEmptyPlot();
			if(toGoTo == null) {
				return Action.Pool;
			}
		}
		
		//Check if performer has seeds
		Inventory performerInventory = performer.getInventory();
		if(!performerInventory.contains(new WheatKernelItem())) {
			//Have actor visit crate to find item
			if(goToCrate == null) {
				goToCrate = new VisitCrateAction(new WheatKernelItem());
			}
			int result = goToCrate.execute(performer);
			if(result == Action.COMPLETED) {
				comeBack = new MoveAction(toGoTo);
				int comeBackResult = comeBack.execute(performer);
				if (comeBackResult == Action.COMPLETED) {
					//TODO: Rest of plant action
					//What if I wrote Action.MADE_PROGRESS?
					return Action.MADE_PROGRESS;
				} else {
					return comeBackResult;
				}
			}else
				return Action.Pool;
		}
		if(performer.getPosition().equals(toGoTo)) {
			Furniture plot = Game.getMap().getBuildingBlock(toGoTo).getFurniture();
			if(plot.getRemainingWeightCapacity() == 1) {
				//Remove item from inventory
				performerInventory = performer.getInventory();
				performerInventory.removeItem(new WheatKernelItem());
				plot.addItem(new WheatKernelItem());
				TreeMap<Position, FarmRoom> farmRooms = Game.getMap().getFarmRooms();
				//Room position: (0,0)
				//toGoTo posiiton: (2,6)
				int newRow = toGoTo.getRow() - 2;
				int newCol = toGoTo.getCol() - 6;
				Position roomPosition = new Position(newRow, newCol);
				FarmRoom current = farmRooms.get(roomPosition);
				current.plant(1, 0);
				//From here, controller will check state of FarmRoom every tick until ready to be harvested
				return Action.COMPLETED;

			}
			return Action.Pool;
		}
		if(ma == null) {
			ma = new MoveAction(toGoTo);
		}
		int result = ma.execute(performer);
		if(result == Action.COMPLETED)
			return Action.MADE_PROGRESS;
		else
			return result;
	}

//Find nearest empty plot
	private void findEmptyPlot() {
		HashMap<Furniture, Position> mapFurniture = Game.getMap().getFurniture();
		if (mapFurniture != null) {
			for (Furniture f : mapFurniture.keySet()) {
				if (f.getID().equals("wheat plot")) {
					if (f.getRemainingWeightCapacity() == 1) {
						toGoTo = mapFurniture.get(f);
						return;
					}
				}
			}
		}
		
	}

}
