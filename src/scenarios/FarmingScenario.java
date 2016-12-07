package scenarios;

import java.util.Random;

import controller.Controller;
import model.actors.Actor;
import model.actors.ConstructAction;
import model.actors.PlantAction;
import model.actors.PlayerControlledActor;
import model.actors.Position;
import model.game.Game;
import model.items.WheatKernelItem;
import model.map.MapParameters;
import model.room.FarmRoom;

//Author: Maxwell Faridian
//This class mimics an actor receiving a plant action, planting the appropriate thing, and then sending out a harvest action when 
//the plants are ready to be harvested
public class FarmingScenario {
	
	private int seed = 369403105; //May want to change
	public static void main(String[] args) {
		new FarmingScenario();
	}
	
	public FarmingScenario() {
		Game.reset();
		Controller controller = new Controller(MapParameters.getDefaultParameters(), new Random(seed), true);
		Game.setMap(controller.getMap());
		
		//TODO: Hardcode seeds into all dragons' inventories for testing
		for(Actor a : Actor.allActors) {
			a.getInventory().addItem(new WheatKernelItem());
		}
		//For this to work, need to add new room, not just new furniture
		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new FarmRoom(new Position(48, 22))));
		//Once that is done, have actor plant something
		PlayerControlledActor.addActionToPlayerPool(new PlantAction());
	}
}
