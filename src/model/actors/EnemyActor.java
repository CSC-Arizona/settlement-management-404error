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

import controller.SongPlayer;

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
	public static int timeTillAttack = 15000;
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
			SongPlayer.setNewSong(SongPlayer.ATTACK);
			if(timeSinceLastAttack == timeTillAttack)
				this.getActionPool().add(new EnemyHuntAction());
			if ((timeSinceLastAttack) >= timeTillAttack + attackTime){
				attack = false;
				for(EnemyActor a : allActors)
					a.timeSinceLastAttack = 0;
				SongPlayer.setNewSong(SongPlayer.MAIN);
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
		List<Position> antTunnels = Game.getMap().antTunnelLocations;
		if(allActors.size() <= Game.getMap().getMapParameters().numberOfEnemyActors){
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
