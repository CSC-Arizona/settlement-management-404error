package model.Actors;

import controller.Designation;
import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.CavernBlock;
import model.Game.Game;
import model.Items.Item;
import model.Map.AppleTree;
import model.Room.Room;

public class GatherForConstructionAction extends Action {

	private static final long serialVersionUID = 5909099133984007954L;
	private Position position;
	private int durability;
	private MoveAction movement;
	private Position moveLocation;
	private Designation designation;
	private Room room;

	public GatherForConstructionAction(Position position, Room room) {
		this.position = position;
		this.room = room;
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

		if (isAdjacent(performer)) {
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
			moveLocation = getMoveLocation();

		// delay the action if it can not be moved
		if (moveLocation == null)
			return Action.DELAY;

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
		return Action.MADE_PROGRESS;
	}

	/*
	 * Checks if the actor is adjacent to the block
	 */
	private boolean isAdjacent(Actor performer) {
		return Math.abs(position.getCol() - performer.getPosition().getCol()) <= 1
				&& Math.abs(position.getRow()
						- performer.getPosition().getRow()) <= 1;
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
				if (performer.getInventory().canAdd(i)) {
					performer.getInventory().addItem(i);
				} else {
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
			Game.getMap().setBuildingBlock(position, room.getAppropriateBlock());
		else if (designation == Designation.CUTTING_DOWN_TREES) {
			AppleTree tree = Game.getMap().getTree(position);
			if (tree != null) {
				tree.removeFromMap();
			}
		} else
			Game.getMap().setBuildingBlock(position, room.getAppropriateBlock());
			//Game.getMap().setBuildingBlock(position, new AirBlock());
	}

	/**
	 * Finds an adjacent valid location near the block to move the actor to
	 * 
	 * @return The Position to move to
	 */
	private Position getMoveLocation() {
		// check to see if a nearby location is valid
		for (int r = position.getRow() - 1; r < position.getRow() + 1; r++) {
			for (int c = position.getCol() - 1; c < position.getCol() + 1; c++) {
				if (Game.validActorLocation(r, c))
					return new Position(r, c);
			}
		}
		return null;
	}

}
