package model.actors;

import java.util.HashMap;

import model.furniture.Furniture;
import model.game.Game;
import model.items.DragonEggItem;

public class BreedAction extends Action{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7811588793188166971L;
	private Position toGoTo;
	private MoveAction ma;

	@Override
	public int execute(Actor performer) {
		if(toGoTo == null) {
			findEmptyIncubationChamber();
			if(toGoTo == null) {
				return Action.Pool;
			}
		}
		if(performer.getPosition().equals(toGoTo)) {
			Furniture chamber = Game.getMap().getBuildingBlock(toGoTo).getFurniture();
			if(chamber.getRemainingWeightCapacity() == 3) {
				chamber.addItem(new DragonEggItem());
				PlayerControlledActor pca = new PlayerControlledActor(toGoTo);
				pca.setAlive(false, false);
				pca.addToActionQueue(new IncubateAction());
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
//FInd nearest empty incubation chamber
//Drop off egg, wait, and then new egg will hatch

	private void findEmptyIncubationChamber() {
		HashMap<Furniture, Position> mapFurniture = Game.getMap().getFurniture();
		if (mapFurniture != null) {
			for (Furniture f : mapFurniture.keySet()) {
				if (f.getID().equals("incubation chamber")) {
					if (f.getRemainingWeightCapacity() == 3) {
						toGoTo = mapFurniture.get(f);
						return;
					}
				}
			}
		}
	}
	

}
