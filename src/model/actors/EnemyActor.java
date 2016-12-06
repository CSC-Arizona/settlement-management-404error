package model.actors;

import images.ImageEnum;
import model.armor.AntArmor;
import model.building_blocks.BuildingBlock;
import model.game.Game;
import model.weapons.AntSword;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * The enemy actors used to provide a challenge to 
 * the player
 * @author Jonathon Davis
 *
 */
public class EnemyActor extends Actor {
	
	private static final long serialVersionUID = 2745479477703967043L;
	public static List<EnemyActor> allActors;
	public static ActionPool enemyActionPool;
	public static boolean attack = false;
	private int timeSinceLastAttack = 0;
	private int timeTillAttack = 100;
	public static ArrayList<Position> antTunnels;
	private static int attackTime = 500;

	/**
	 * @param health
	 * @param position
	 */
	public EnemyActor(Position position) {
		super(position, ImageEnum.ANT_LEFT);
		if(enemyActionPool == null)
			enemyActionPool = new ActionPool();
		if(allActors == null)
			allActors = Collections.synchronizedList(new LinkedList<>());
		allActors.add(this);
		
		//Give all enemy actors an Ant Sword and Ant Armor upon creation
		this.getInventory().addItem(new AntSword());
		this.getInventory().addItem(new AntArmor());
	}

	
	public static void reset(){
		if(allActors == null)
			return;
		Iterator<EnemyActor> actors = allActors.iterator();
		while (actors.hasNext()) {
			Actor a = actors.next();
			actors.remove();
			a.remove();
		}
		allActors = null;
		enemyActionPool = null;
	}

	@Override
	public void update() {
		spawn();
		timeSinceLastAttack++;
		if(timeSinceLastAttack >= timeTillAttack){
			attack = true;
			if(timeSinceLastAttack == timeTillAttack)
				this.getActionPool().add(new EnemyHuntAction());
			if ((timeSinceLastAttack / allActors.size()) >= timeTillAttack + attackTime){
				attack = false;
				timeSinceLastAttack = 0;
			}
		}
		if (getActionPool().size() <= 0 && this.getQueue().size() <= 0)
			this.addToActionQueue(new EnemyIdleAction());
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
	
	
	
	@Override
	public void remove() {
		allActors.remove(this);
		super.remove();
	}


	//spawns new actors if not alive
	public void spawn(){
		if(antTunnels == null){
			antTunnels = new ArrayList<>();
			for(int row = 0; row < Game.getMap().getTotalHeight(); row++){
				for(int col = 0; col < Game.getMap().getTotalWidth(); col ++){
					if(Game.getMap().getBuildingBlock(row, col).getID().equals("Ant tunnel")){
						antTunnels.add(new Position(row, col));
					}
				}
			}
		}
		if(allActors.size() <= Game.getMap().getMapParameters().numberOfStartingActors){
			Random rand = new Random();
			for(int i = allActors.size(); i <= Game.getMap().getMapParameters().numberOfStartingActors; i++){
				new EnemyActor(antTunnels.get(rand.nextInt(antTunnels.size())));
			}
		}
	}


	@Override
	public ImageEnum getLeftImage() {
		return ImageEnum.ANT_LEFT;
	}


	@Override
	public ImageEnum getRightImage() {
		return ImageEnum.ANT_RIGHT;
	}
}
