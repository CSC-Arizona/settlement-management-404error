package model.actors;

import images.ImageEnum;
import model.armor.AntArmor;
import model.weapons.AntSword;

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
	public static boolean attack = false;
	private int timeSinceLastAttack = 0;
	private int timeTillAttack = 10000;
	private static int attackTime = 500;

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
		
		//Give all enemy actors an Ant Sword and Ant Armor upon creation
		this.getInventory().addItem(new AntSword());
		this.getInventory().addItem(new AntArmor());
	}


	
	public static void reset(){
		allActors = null;
		enemyActionPool = null;
	}

	@Override
	public void update() {
		timeSinceLastAttack++;
		if(timeSinceLastAttack >= timeTillAttack){
			attack = true;
			if ((timeSinceLastAttack / allActors.size()) >= timeTillAttack + attackTime){
				attack = false;
				timeSinceLastAttack = 0;
			}
		}
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
