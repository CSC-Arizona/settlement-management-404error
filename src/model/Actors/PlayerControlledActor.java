/**
 * 
 */
package model.Actors;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * @author Jonathon Davis
 * A basic representation of the AbstractActor
 */
public class PlayerControlledActor extends Actor {
	
	private int fatigue, hunger, happiness;
	private static final int threshold = 1000;
	private static final int death_threshold = 1100;
	private LinkedList<PlayerControlledActor> allActors;
	
	public PlayerControlledActor(int health, Position location){
		super(health,location);
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
		fatigue += 1;
		hunger += 1;
		happiness += 1;
		if(hunger >= threshold)
			this.priorityAddToActionQueue(new HungerAction());
		if(hunger >= death_threshold){
			allActors.remove(this);
			this.setAlive(false);
		}
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
	
	


}
