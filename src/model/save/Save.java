package model.save;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import model.actors.ActionPool;
import model.actors.Actor;
import model.actors.EnemyActor;
import model.actors.PlayerControlledActor;
import model.game.Game;
import model.map.Map;

/**
 * Represents the Save of all necessary data to be used in a save file
 * @author Jonathon Davis
 *
 */
public class Save implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8859099312060885013L;
	private Map map;
	private List<Actor> allActors;
	private ActionPool playerActionPool = new ActionPool();
	private ActionPool enemyActionPool = new ActionPool();
	
	public Save(){
		map = Game.getMap();
		allActors = Actor.allActors;
	}
	
	public void setState(){
		Game.setMap(map);
		Actor.allActors = allActors;
		PlayerControlledActor.playerActionPool = playerActionPool;
		EnemyActor.enemyActionPool = enemyActionPool;
	}

}
