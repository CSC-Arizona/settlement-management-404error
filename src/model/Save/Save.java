package model.Save;

import java.io.Serializable;
import java.util.LinkedList;

import model.Game;
import model.Map;
import model.Actors.Actor;

/**
 * Represents the Save of all necessary data to be used in a save file
 * @author Jonathon Davis
 *
 */
public class Save implements Serializable {
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
