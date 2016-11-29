package model.Save;

import java.io.Serializable;
import java.util.LinkedList;

import model.Actors.Actor;
import model.Game.Game;
import model.Map.Map;

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
	private LinkedList<Actor> allActors;
	
	public Save(){
		map = Game.getMap();
		allActors = Actor.allActors;
	}
	
	public void setState(){
		Game.setMap(map);
		Actor.allActors = allActors;
	}

}