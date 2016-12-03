/**
 * 
 */
package model.actors;

import controller.Designation;
import model.building_blocks.AirBlock;
import model.building_blocks.BuildingBlock;
import model.building_blocks.CavernBlock;
import model.game.Game;
import model.items.Item;
import model.map.AppleTree;

/**
 * Creates a Action for an Actor to gather a resource
 * 
 * @author Jonathon Davis, Ethan Ward
 *
 */
public class GatherAction extends Action {

	private static final long serialVersionUID = 5909099133984007954L;
	private Position position;
	private int durability;
	private MoveAction movement;
	private Position moveLocation;
	private Designation designation;

	public GatherAction(Position position) {
		this.position = position;
		durability = Integer.MAX_VALUE;
		if (Game.getMap()
				.getBuildingBlock(position.getRow(), position.getCol())
				.isDestroyable()) {
			this.designation = Game.getMap().getBuildingBlock(position)
					.getDesignation();

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Actors.Action#execute(model.Actors.Actor)
	 */
	@Override
	public int execute(Actor performer) {
		// if the block can't be gathered cancel the action
		if (!Game.getMap()
				.getBuildingBlock(position.getRow(), position.getCol())
				.isDestroyable())
			return Action.COMPLETED;

		if (position.isAdjacent(performer.getPosition())) {
			BuildingBlock block = Game.getMap().getBuildingBlock(
					position.getRow(), position.getCol());
			// reduce the durability
			gather(performer, block);
			if (durability <= 0) {
				// replace the block
				replace();
				// gather the loot from the block
				lootBlock(performer, block);
				return Action.COMPLETED;
			}
			return Action.MADE_PROGRESS;
		}
		return move(performer);
	}

	/**
	 * @return
	 * 
	 */
	private int move(Actor performer) {
		// if the Move Location has not yet been calculated, calculate position
		if (moveLocation == null)
			moveLocation = MoveAction.getMoveLocationNear(position);

		// delay the action if it can not be moved
		if (moveLocation == null)
			return Action.Pool;

		// if not nearby move to a valid location
		if (movement == null)
			movement = new MoveAction(moveLocation);
		int result = movement.execute(performer);
		// cancel the action if this actor can not perform
		if (result == Action.Pool) {
			movement = null;
			moveLocation = null;
			return Action.Pool;
		}
		if(result == Action.DELAY)
			return Action.Pool;
		return Action.MADE_PROGRESS;
	}

	/*
	 * Takes out the loot from the block And adds it to the inventory of the
	 * Actor, or drops it on the ground, and waits for another Actor to go and
	 * pick it up
	 */
	private void lootBlock(Actor performer, BuildingBlock block) {
		if (block.lootBlock() != null)
			for (Item i : block.lootBlock())
				// place the loot in the inventory if possible otherwise the
				// ground
				if (!performer.getInventory().addItem(i)) {
					Game.getMap().addItemToGround(position, i);
					performer.getActionPool().add(
							new PickUpItemAction(position, i));
				}
	}

	/*
	 * Reduces the durability of the block being gathered and increases the
	 * actors gather skill
	 */
	private void gather(Actor performer, BuildingBlock block) {
		if (durability == Integer.MAX_VALUE)
			durability = block.getDurability();
		durability -= performer.getSkills().getGatheringLevel() + 1;
		performer.getSkills().addGatheringXP(1);
	}

	/*
	 * Replaces the block with the correct replacement
	 */
	private void replace() {
		if (designation == Designation.DIGGING)
			Game.getMap().setBuildingBlock(position, new CavernBlock());
		else if (designation == Designation.CUTTING_DOWN_TREES) {
			AppleTree tree = Game.getMap().getTree(position);
			if (tree != null) {
				tree.removeFromMap();
			}
		} else {
			BuildingBlock inQuestion = Game.getMap().getBuildingBlock(position.getRow(), position.getCol());
			Game.getMap().setBuildingBlock(position, inQuestion.getAppropriateReplacement());
			// Game.getMap().setBuildingBlock(position, new AirBlock()); TODO: get appropriate block from BuildingBlock
		}
	}

}
