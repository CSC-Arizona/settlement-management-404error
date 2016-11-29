package model.Actors;

import java.util.LinkedList;

/**
 * The enemy actors used to provide a challenge to 
 * the player
 * @author Jonathon Davis
 *
 */
public class EnemyActor extends Actor {
	
	private static final long serialVersionUID = 2745479477703967043L;
	public static LinkedList<Actor> allActors;
	private static ActionPool enemyActionPool;

	/**
	 * @param health
	 * @param position
	 */
	public EnemyActor(int health, Position position) {
		super(health, position);
		if(enemyActionPool == null)
			enemyActionPool = new ActionPool();
		if(allActors == null)
			allActors = new LinkedList<>();
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
		return enemyActionPool;
	}

}
