package model.actors;

import images.ImageEnum;

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
		super(health, position, ImageEnum.ANT);
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

	@Override
	public void update() {
		if(enemyActionPool.size() <= 0)
			enemyActionPool.add(new EnemyHuntAction());
		super.update();
	}

	/* (non-Javadoc)
	 * @see model.actors.Actor#getActionPool()
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
