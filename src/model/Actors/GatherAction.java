/**
 * 
 */
package model.Actors;

import model.GameMap;
import model.BuildingBlocks.BuildingBlock;
import model.Items.Item;

/**
 * @author Jonathon Davis
 *
 */
public class GatherAction implements Action {
	Position position;
	int durability;
	MoveAction movement;
	Position moveLocation;
	
	public GatherAction(Position position){
		this.position = position;
		durability = Integer.MAX_VALUE;
	}

	/* (non-Javadoc)
	 * @see model.Actors.Action#execute(model.Actors.Actor)
	 */
	@Override
	public int execute(Actor performer) {
		if(!GameMap.getBlock(position.getRow(), position.getCol()).isDestroyable())
			return Action.CANCELL;
		if(moveLocation == null)
			moveLocation = getMoveLocation();
		
		if(Math.abs(position.getCol() - performer.getPosition().getCol()) <= 1 && 
				Math.abs(position.getRow() - performer.getPosition().getRow()) <= 1){
			BuildingBlock block = GameMap.getBlock(position.getRow(), position.getCol());
			if(durability == Integer.MAX_VALUE)
				durability = block.getDurability();
			durability--;
			if(durability <= 0){
				//remove the block;
				if(block.lootBlock() != null)
					for(Item i : block.lootBlock())
						performer.getInventory().addItem(i);
				
				return Action.COMPLETED;
			}
			return Action.MADE_PROGRESS;
		}	
		
		//delay the action if it can not be moved
		if(moveLocation == null)
			return Action.DELAY;
		
		// if not nearby move to a valid location
		if(movement == null)
			movement = new MoveAction(moveLocation);
		movement.execute(performer);

		return Action.MADE_PROGRESS;
	}
	
	private Position getMoveLocation(){
		// check to see if a nearby location is valid
		for(int r = position.getRow() - 1; r < position.getRow() + 1; r++){
			for(int c = position.getCol() - 1; c < position.getCol() + 1; c++){
				if (r > 0 && c > 0 && r < GameMap.mapHeight() && c < GameMap.mapWidth() 
						&& GameMap.getBlock(r, c).getID().equals("Air") && r + 1 < GameMap.mapHeight() 
						&& !GameMap.getBlock(r+1, c).getID().equals("Air"))
					return new Position(r,c);
			}
		}
		return null;
	}

}
