package model.Actors;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

import model.Inventory;

/**
 * 
 */

/**
 * @author Jonathon Davis
 *
 */
public abstract class Actor {
	
	private int health;
	private Position position;
	private boolean idle;
	private LinkedList<Action> queue;
	private Action currentAction;
	private Skills skills;
	private Inventory inventory;
	private boolean alive;

	/**
	 * Creates a new actor
	 * @param health The current health of the actor
	 * @param position The current position of the actor
	 */
	public Actor(int health, Position position){
		this.health = health;
		this.position = position;
		this.idle = true;
		this.queue = new LinkedList<Action>();
		this.inventory = new Inventory();
		this.setAlive(true);
		skills = new Skills();
	}
	
	/**
	 * Adds a new action to this workers personal worker queue
	 * @param action The action this worker needs to perform
	 */
	public void addToActionQueue(Action action){
		queue.add(action);
	}
	
	/**
	 * Adds an action to the front of the queue as it is a priority
	 * @param action Action to be Performed
	 */
	public void priorityAddToActionQueue(Action action){
		queue.addFirst(action);
	}
	
	/**
	 * Perform once per tick, the Actor performs the next action
	 */
	public void update(){
		if(idle)
			if(queue.size() > 0)
				currentAction = queue.poll();
			else
				return;
		idle = currentAction.execute(this);
	}
	
	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
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
	 * @param alive the alive to set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
