package model.Actors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import model.GameMap;
import model.Inventory;
import model.Map;
import model.Furniture.Furniture;
import model.Items.Item;

/**
 * 
 */

/**
 * @author Jonathon Davis
 *
 */
public abstract class Actor {

	private int health;
	private int fatigue;
	private Position position;
	private boolean idle;
	private LinkedList<Action> queue;
	private Action currentAction;
	private Skills skills;
	private Inventory inventory;
	private boolean alive;
	private Map map;

	/**
	 * Creates a new actor
	 * 
	 * @param health
	 *            The current health of the actor
	 * @param position
	 *            The current position of the actor
	 */
	public Actor(int health, int fatigue, Position position, Map map) {
		this.map = map;
		this.health = health;
		this.fatigue = fatigue;
		this.position = position;
		this.idle = true;
		this.queue = new LinkedList<Action>();
		this.inventory = new Inventory(this, map);
		this.setAlive(true);
		skills = new Skills();
	}

	/**
	 * Adds a new action to this workers personal worker queue
	 * 
	 * @param action
	 *            The action this worker needs to perform
	 */
	public void addToActionQueue(Action action) {
		queue.add(action);
	}

	/**
	 * Adds an action to the front of the queue as it is a priority
	 * 
	 * @param action
	 *            Action to be Performed
	 */
	public void priorityAddToActionQueue(Action action) {
		queue.addFirst(action);
	}

	/**
	 * Perform once per tick, the Actor performs the next action
	 */
	public void update() {
		// if idle get a new action
		if (idle)
			if (queue.size() > 0)
				currentAction = queue.peek();
			else {
				Position gather = getRandomBlockForGathering();
				if (gather != null) {
					currentAction = new GatherAction(gather, map);
				} else {
					if (getInventory().size() != 0) {
						for (int index = 0; index < getInventory().size(); index++) {
							Item item = getInventory().getItem(index);
							Position emptyCratePosition = getCrateWithCapacityGreaterThan(item
									.getWeight());
							if (emptyCratePosition != null) {
								currentAction = new StoreItemAction(
										emptyCratePosition, item, index, map);
								break;
							}
						}
					}
					else {
						Position itemOnGroundPosition = getItemOnGroundPosition();
						System.out.println(itemOnGroundPosition);
						List<Item> items = map.getBuildingBlock(
								itemOnGroundPosition).itemsOnGround();
						if (items.size() != 0) {
							Item item = items.get(0);
							if (itemOnGroundPosition != null) {
								currentAction = new PickUpItemAction(
										itemOnGroundPosition, item, map);
							}
						}
					}
				}
			}
		if (currentAction == null) {
			return;
		}
		// Store the result of the execution
		int result = currentAction.execute(this);

		// if the Action was Completed or Cancelled, poll the action out of the
		// queue
		if (result == Action.COMPLETED || result == Action.CANCELL) {
			idle = true;
			queue.poll();
		} else if (result == Action.DELAY) {
			// if the Action needs to be delayed, execute the next action
			idle = true;
			Action attemptedAction = queue.poll();
			update();
			queue.addFirst(attemptedAction);
		} else {
			// if the Action is still in progress then set idle to false
			idle = false;
		}
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health
	 *            the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	public void setFatigue(int fatigue) {
		this.fatigue = fatigue;
	}

	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * @return the skills
	 */
	public Skills getSkills() {
		return skills;
	}

	/**
	 * @return the inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * @param alive
	 *            the alive to set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Position getNearestBed() {
		HashMap<Furniture, Position> mapFurniture = map.getFurniture();
		if (mapFurniture != null) {
			if (mapFurniture.size() != 0) {
				// todo: actually calculate the distance
				return mapFurniture.get(mapFurniture.keySet().toArray()[0]);
			}
		}
		return null;
	}

	public Position getCrateWithCapacityGreaterThan(double target) {
		HashMap<Furniture, Position> mapFurniture = map.getFurniture();
		if (mapFurniture != null) {
			for (Furniture f : mapFurniture.keySet()) {
				if (f.getID().equals("crate")) {
					if (f.getRemainingWeightCapacity() >= target) {
						return mapFurniture.get(f);
					}
				}
			}
		}
		return null;
	}

	public Position getRandomBlockForGathering() {
		ArrayList<Position> blocksMarkedForGathering = map
				.getBlocksMarkedForGathering();
		if (blocksMarkedForGathering != null) {
			if (blocksMarkedForGathering.size() != 0) {
				return blocksMarkedForGathering.get(new Random()
						.nextInt(blocksMarkedForGathering.size()));
			}
		}
		return null;
	}

	public Position getItemOnGroundPosition() {
		ArrayList<Position> itemsOnGround = map.getItemsOnGround();
		if (itemsOnGround.size() > 0) {
			return itemsOnGround.get(0);
		}
		return null;
	}

	@Override
	public String toString() {
		String result = Integer.toString(health) + " health; "
				+ Integer.toString(fatigue) + " fatigue; inventory: "
				+ this.inventory.toString();
		return result;
	}

}
