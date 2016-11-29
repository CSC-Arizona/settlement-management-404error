package model.Actors;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import model.Game.Game;

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
	private String name;
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
		this.name = Names.generateName();
		skills = new Skills();
		if (allActors == null)
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
	
	// the core logic of the update method
	// used for recursion
	private void performAction(int attempts){
		fall();
		
		if(attempts > getActionPool().size() + queue.size())
			return;

		// if idle get a new action
		if (idle) {
			if (queue.size() > 0 && queue.peek() != null) {
				currentAction = queue.peek();
			} else {
				queue.addFirst((getActionPool().get()));
				currentAction = queue.peek();
			}
			if (currentAction == null)
				return;
		}

		// Store the result of the execution
		int result = currentAction.execute(this);

		// if the Action was Completed or Cancelled, poll the action out of the
		// queue
		if (result == Action.COMPLETED) {
			idle = true;
			queue.poll();
		} else if (result == Action.Pool) {
			idle = true;
			getActionPool().add(queue.poll());
			//performAction();
		} else if (result == Action.DELAY) {
			// if the Action needs to be delayed, execute the next action
			idle = true;
			Action attemptedAction = queue.poll();
			performAction(attempts + 1);
			queue.addFirst(attemptedAction);
		} else {
			// if the Action is still in progress then set idle to false
			idle = false;
		}

		fall();
	}

	/**
	 * Perform once per tick, the Actor performs the next action
	 */
	public void update() {
		performAction(0);
	}

	public void fall() {
		// ensure that the actor is not floating in air
		int row = getPosition().getRow(), col = getPosition().getCol();
		if (!Game.validActorLocation(row, col))
			setPosition(new Position(row + 1, col));
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
	public void setAlive(boolean alive, boolean remove) {
		if (alive){
			this.alive = alive;
			return;
		}
		if (remove)
			this.alive = alive;
		allActors.remove(this);
	}

	public int getHealth() {
		return health;
	}

	/**
	 * @param i
	 */
	public void setHealth(int i) {
		this.health = i;

	}

	public String getName() {
		return name;
	}

	public static void reset(){
		PlayerControlledActor.reset();
		EnemyActor.reset();
	}
	
	public abstract ActionPool getActionPool();

	/**
	 * @return the idle
	 */
	public boolean isIdle() {
		return idle;
	}

}