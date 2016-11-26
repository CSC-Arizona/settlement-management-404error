package model.Actors;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * 
 */

/**
 * @author Jonathon Davis
 *
 */
public abstract class Actor implements Serializable {

	private static final long serialVersionUID = -2476564871890336854L;
	private int health;
	private Position position;
	private boolean idle;
	private LinkedList<Action> queue;
	private Action currentAction;
	private Skills skills;
	private Inventory inventory;
	private boolean alive;
	public static LinkedList<Actor> allActors;

	/**
	 * Creates a new actor
	 * 
	 * @param health
	 *            The current health of the actor
	 * @param position
	 *            The current position of the actor
	 */
	public Actor(int health, Position position) {
		this.health = health;
		this.position = position;
		this.idle = true;
		this.queue = new LinkedList<Action>();
		this.inventory = new Inventory();
		this.alive = true;
		skills = new Skills();
		if(allActors == null)
			allActors = new LinkedList<>();
		allActors.add(this);
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
		if (idle){
			if (queue.size() > 0)
				currentAction = queue.peek();
			else
				currentAction = getActionFromPool();
			if(currentAction == null)
				return; // TODO: set to idle action
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
	
	public abstract void addActionToPool(Action action);

	/**
	 * @param alive
	 *            the alive to set
	 */
	public void setAlive(boolean alive) {
		if(alive)
			return;
		this.alive = alive;
		allActors.remove(this);
		
	}
	
	public int getHealth(){
		return health;
	}

	/**
	 * @param i
	 */
	public void setHealth(int i) {
		this.health = i;
		
	}
	
	public abstract Action getActionFromPool();
	
	

}
