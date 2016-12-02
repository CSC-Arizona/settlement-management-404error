package model.actors;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * The enemy actors used to provide a challenge to 
 * the player
 * @author Jonathon Davis
 *
 */
public class EnemyActor extends Actor {
	
	private static final long serialVersionUID = 2745479477703967043L;
	public static List<Actor> allActors;
	public static ActionPool enemyActionPool;

	/**
	 * @param health
	 * @param position
	 */
	public EnemyActor(int health, Position position) {
		super(health, position);
		if(enemyActionPool == null)
			enemyActionPool = new ActionPool();
		if(allActors == null)
			allActors = Collections.synchronizedList(new LinkedList<>());
		allActors.add(this);
		enemyActionPool.add(new EnemyHuntAction());
	}


	
	public static void reset(){
		allActors = null;
		enemyActionPool = null;
	}



	/* (non-Javadoc)
	 * @see model.Actors.Actor#getActionPool()
	 */
	@Override
	public ActionPool getActionPool() {
		if (enemyActionPool== null) {
			enemyActionPool = new ActionPool();
		}
		return enemyActionPool;
	}
	
	public String toString() {
		return "Fire Ant";
	}
}
