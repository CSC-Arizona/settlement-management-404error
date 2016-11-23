/**
 * 
 */
package model.Actors;

import java.util.LinkedList;

import model.GameMap;
import model.Map;

/**
 * @author Jonathon Davis
 * The Player Controlled Actor will execute commands
 * given by the player, the ACtor is not directly controlled 
 * by the player but instead will execute commands from a pool
 * of commands.
 */
public class PlayerControlledActor extends Actor {
	
	private int fatigue, hunger, happiness;
	private static final int threshold = 1000;
	private static final int death_threshold = 1100;
	private static LinkedList<PlayerControlledActor> allActors;
	
	/**
	 * 
	 * @param health The health of the actor
	 * @param location The starting location of the Actor
	 */
	public PlayerControlledActor(int health, int fatigue, Position location, Map map){
		super(health, fatigue, location, map);
		fatigue = 0;
		hunger = 0;
		happiness = 0;
		if(allActors == null)
			allActors = new LinkedList<>();
		allActors.add(this);
	}

	/* (non-Javadoc)
	 * @see model.Actors.Actor#update()
	 */
	@Override
	public void update() {
		// update the needs
		fatigue += 1;
		hunger += 1;
		happiness += 1;
		super.setFatigue(fatigue);
		// if one of the needs gets to high, the actor
		// will ignore his current action and attempt
		// to fulfill that need
		if(hunger >= threshold)
			this.priorityAddToActionQueue(new HungerAction());
		if(fatigue >= 10) {
			this.priorityAddToActionQueue(new SleepAction());
		}
		// if one of the needs get to high, then the actor dies
		if(hunger >= death_threshold){
			allActors.remove(this);
			this.setAlive(false);
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
	 * @param hunger the hunger to set
	 */
	public void setHunger(int hunger) {
		this.hunger = hunger;
	}
	
	public void setFatigue(int fatigue) {
		this.fatigue = fatigue;
	}

}
