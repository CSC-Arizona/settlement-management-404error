/**
 * 
 */
package model.actors;

import images.ImageEnum;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Jonathon Davis The Player Controlled Actor will execute commands
 *         given by the player, the ACtor is not directly controlled by the
 *         player but instead will execute commands from a pool of commands.
 */
public class PlayerControlledActor extends Actor {

	private static final long serialVersionUID = 7431678886074775948L;
	private int fatigue, hunger;
	private static final int threshold = 1000;
	private static final int death_threshold = 10000;
	public static List<Actor> allActors;
	public static ActionPool playerActionPool;
	private Random random = new Random();

	/**
	 * Creates a player controlled actor which will execute commands given to
	 * them by the Player
	 * 
	 * @param health
	 *            What health this player will begin with
	 * @param location
	 *            The location this player will begin with
	 */
	public PlayerControlledActor(Position location) {
		super(location, ImageEnum.DRAGON);
		fatigue = 0;
		hunger = 0;
		if (playerActionPool == null)
			playerActionPool = new ActionPool();
		if (allActors == null)
			allActors = Collections.synchronizedList(new LinkedList<>());
		allActors.add(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.actors.Actor#update()
	 */
	@Override
	public void update() {
		if (allActors == null)
			allActors = Collections.synchronizedList(new LinkedList<>());
		// update the needs
		fatigue += 1;
		hunger += 1;
		// if one of the needs gets to high, the actor
		// will ignore his current action and attempt
		// to fulfill that need
		if (hunger == threshold)
			this.priorityAddToActionQueue(new HungerAction());
		if (fatigue == threshold)
			this.priorityAddToActionQueue(new SleepAction());
		if (this.getHealth() < 90)
			this.priorityAddToActionQueue(new HealAction());
		// if one of the needs get to high, then the actor dies
		if (hunger >= death_threshold || fatigue >= death_threshold) {
			System.out.println(allActors);
			allActors.remove(this);
			this.setAlive(false, true);
		}
		// call super.update()
		super.update();
	}

	/**
	 * @return the hunger
	 */
	public int getHunger() {
		return hunger;
	}

	/**
	 * @param hunger
	 *            the hunger to set
	 */
	public void setHunger(int hunger) {
		this.hunger = hunger;
	}

	/**
	 * Sets the fatigue level of this actor
	 * 
	 * @param fatigue
	 *            The level the fatigue will be set
	 */
	public void setFatigue(int fatigue) {
		this.fatigue = fatigue;
	}

	/**
	 * @return the fatigue
	 */
	public int getFatigue() {
		return fatigue;
	}

	/**
	 * Adds an action to the player controlled pool This does not guarantee that
	 * this actor will execute this command, however a actor will eventually
	 * execute this command
	 * 
	 * @param action
	 *            The action that will be added
	 */
	public void addActionToPool(Action action) {
		playerActionPool.add(action);
	}

	public static void addActionToPlayerPool(Action action) {
		playerActionPool.add(action);
	}
	
	@Override
	public void remove() {
		allActors.remove(this);
		super.remove();
	}

	public Action getActionFromPlayerPool() {
		return playerActionPool.get();
	}

	@Override
	public String toString() {
		String result = "Dragon " + this.getName() + ": "
				+ Integer.toString(this.getHealth()) + " health; "
				+ Integer.toString(fatigue) + " fatigue; " + hunger + " hunger";
		return result;
	}

	public static void reset() {
		allActors = null;
		playerActionPool = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.actors.Actor#getActionPool()
	 */
	@SuppressWarnings("static-access")
	@Override
	public ActionPool getActionPool() {
		if (playerActionPool == null) {
			playerActionPool = new ActionPool();
		}
		return this.playerActionPool;
	}

}
