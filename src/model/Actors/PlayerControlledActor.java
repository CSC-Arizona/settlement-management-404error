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
		if(fatigue >= threshold)
			this.priorityAddToActionQueue(new FatigueAction());
		else if (hunger >= threshold)
			this.priorityAddToActionQueue(new HungerAction());
		else if (happiness >= threshold)
			this.priorityAddToActionQueue(new HapinessAction());
		super.update();
	}
	
	


}
