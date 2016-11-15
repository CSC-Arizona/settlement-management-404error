package model;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 */

/**
 * @author Jonathon Davis
 *
 */
public abstract class Actor {
	
	private int health;
	private Point position;
	private boolean idle;
	private Queue<Action> queue;
	private Action currentAction;
	private Skills skills;
	private Inventory inventory;
	//TODO: give each actor an inventory once available
	
	/**
	 * Creates a new actor
	 * @param health The current health of the actor
	 * @param position The current position of the actor
	 */
	public Actor(int health, Point position){
		this.health = health;
		this.position = position;
		this.idle = true;
		this.queue = new LinkedList<Action>();
		this.inventory = new Inventory();
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
	public Point getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Point position) {
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

}
