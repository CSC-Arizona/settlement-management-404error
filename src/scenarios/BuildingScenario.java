package scenarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import controller.Controller;
import model.Game;
import model.Map;
import model.MapParameters;
import model.Actors.Actor;
import model.Actors.ConstructAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.Furniture.Furniture;
import model.Items.IronItem;
import model.Items.StoneItem;
import model.Room.StoreRoom;

//Author: Maxwell Faridian
//This class shows a hard coded demo of building a store room
public class BuildingScenario {
	
	private int seed = 8412372;
	
	private HashMap<Actor, Position> hardCodedActors = new HashMap<>();
	private ArrayList<Position> blocksMarkedForGathering = new ArrayList<>();
	private HashMap<Furniture, Position> hardCodedFurniture = new HashMap<>();
	
	public static void main(String[] args) {
		new BuildingScenario();
	}
	
	public BuildingScenario() {
		
		Controller controller = new Controller (
			MapParameters.getDefaultParameters(), hardCodedFurniture, hardCodedActors,
			new Random(seed), blocksMarkedForGathering);
		Game.setMap(controller.getMap());
		PlayerControlledActor actor = new PlayerControlledActor(100, 0, new Position(44, 985), hardCodedActors);
		for (int i = 0; i < 8; i++) {
			actor.getInventory().addItem(new StoneItem());
			actor.getInventory().addItem(new IronItem());
		}
		actor.addToActionQueue(new ConstructAction(new StoreRoom(new Position(45, 983))));
		hardCodedActors.put(actor, actor.getPosition());
	}

}
