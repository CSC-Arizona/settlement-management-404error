package scenarios;

import java.util.Random;

import controller.Controller;
import model.Actors.ConstructAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.Game.Game;
import model.Map.MapParameters;
import model.Room.StoreRoom;

//Author: Maxwell Faridian
//This class shows a hard coded demo of building a store room
public class BuildingScenario {

	private int seed = 8412372;

	public static void main(String[] args) {
		new BuildingScenario();
	}

	public BuildingScenario() {

		Controller controller = new Controller(MapParameters.getDefaultParameters(), new Random(seed));
		Game.setMap(controller.getMap());
		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new StoreRoom(new Position(45, 983))));
	}

}
