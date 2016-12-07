package model.save;

import java.io.Serializable;
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
	private List<PlayerControlledActor> playerActors;
	private List<EnemyActor> enemyActors;
	private ActionPool playerActionPool;
	private ActionPool enemyActionPool;
	private int parts;
	
	public Save(){
		map = Game.getMap();
		allActors = Actor.allActors;
		playerActors = PlayerControlledActor.allActors;
		enemyActors = EnemyActor.allActors;
		playerActionPool = PlayerControlledActor.playerActionPool;
		enemyActionPool = EnemyActor.enemyActionPool;
		parts = PlayerControlledActor.remaingParts;
	}
	
	public void setState(){
		Game.setMap(map);
		Actor.allActors = allActors;
		PlayerControlledActor.playerActionPool = playerActionPool;
		EnemyActor.enemyActionPool = enemyActionPool;
		PlayerControlledActor.allActors = playerActors;
		EnemyActor.allActors = enemyActors;
		PlayerControlledActor.remaingParts = parts;
	}

}
