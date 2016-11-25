package model.Actors;

import java.util.LinkedList;

/**
 * The enemy actors used to provide a challenge to 
 * the player
 * @author Jonathon Davis
 *
 */
public class EnemyActor extends Actor {
	
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
		this.addActionToPool(new EnemyHuntAction());
	}


	/* (non-Javadoc)
	 * @see model.Actors.Actor#addActionToPool(model.Actors.Action)
	 */
	@Override
	public void addActionToPool(Action action) {
		enemyActionPool.add(action);
	}

	/* (non-Javadoc)
	 * @see model.Actors.Actor#getActionFromPool()
	 */
	@Override
	public Action getActionFromPool() {
		return enemyActionPool.get();
	}

}
