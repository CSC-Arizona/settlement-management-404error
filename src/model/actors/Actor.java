package model.actors;

import images.ImageEnum;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import model.game.Game;
import model.game.Log;

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
	public static List<Actor> allActors;
	private ImageEnum image;

	/**
	 * Creates a new actor
	 * 
	 * @param health
	 *            The current health of the actor
	 * @param position
	 *            The current position of the actor
	 */
	public Actor(Position position, ImageEnum image) {
		this.health = 100;
		this.position = position;
		this.image = image;
		this.idle = true;
		this.queue = new LinkedList<Action>();
		this.inventory = new Inventory();
		this.alive = true;
		this.name = Names.generateName();
		skills = new Skills();
		if (allActors == null)
			allActors = Collections.synchronizedList(new LinkedList<>());
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
	private void performAction(int attempts) {
		if (!Game.validActorLocation(position.getRow(), getPosition().getCol()))
			fall();
		if (position.getRow() >= Game.getMap().getTotalHeight()
				|| Game.getMap().getBuildingBlock(position).getID()
						.equals("Lava"))
			this.health = 0;
		if (attempts > getActionPool().size() + queue.size())
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
			getActionPool().add(currentAction);
			currentAction = null;
			queue.poll();
			performAction(attempts + 1);
		} else if (result == Action.DELAY) {
			// if the Action needs to be delayed, execute the next action
			idle = true;
			Action attemptedAction = queue.poll();
			currentAction = null;
			performAction(attempts + 1);
			queue.addFirst(attemptedAction);
		} else {
			// if the Action is still in progress then set idle to false
			idle = false;
		}
	}

	/**
	 * Perform once per tick, the Actor performs the next action
	 */
	public void update() {
		if (this.health <= 0) {
			this.setAlive(false, true);
			return;
		}
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
	 * @return the queue
	 */
	public LinkedList<Action> getQueue() {
		return queue;
	}

	/**
	 * @param alive
	 *            the alive to set
	 */
	public void setAlive(boolean alive, boolean remove) {
		if (alive) {
			this.alive = alive;
			return;
		}
		if (remove) {
			this.remove();
			return;
		}
		if (!alive) {
			this.alive = alive;
			return;
		}
	}

	public void remove() {
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

	public static void reset() {
		PlayerControlledActor.reset();
		EnemyActor.reset();
	}

	public abstract ActionPool getActionPool();

	private void setImage(ImageEnum image) {
		this.image = image;
	}

	public abstract ImageEnum getLeftImage();

	public abstract ImageEnum getRightImage();

	public void setLeft() {
		setImage(getLeftImage());
	}

	public void setRight() {
		setImage(getRightImage());
	}

	/**
	 * @return the idle
	 */
	public boolean isIdle() {
		return idle;
	}

	public ImageEnum getImage() {
		return image;
	}

}
