package scenarios;

import java.util.Random;

import controller.Controller;
import model.Actors.ConstructAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.Game.Game;
import model.Map.MapParameters;
import model.Room.EntertainmentRoom;
import model.Room.StoreRoom;
import model.Room.VerticalTunnel;

//Author: Maxwell Faridian
//This class shows a hard coded demo of building a store room
public class BuildingScenario {

	private int seed = 8412372;

	public static void main(String[] args) {
		new BuildingScenario();
	}

	public BuildingScenario() {
        Controller controller = new Controller(MapParameters.getParametersWithNoMountains(), new Random(seed), true);
		Game.setMap(controller.getMap());
		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new VerticalTunnel(new Position(70, 983))));
		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new VerticalTunnel(new Position(72, 983))));
		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new VerticalTunnel(new Position(74, 983))));
		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new StoreRoom(new Position(74, 984))));
//		Controller controller = new Controller(MapParameters.getDefaultParameters(), new Random(seed), true);
//		Game.setMap(controller.getMap());
//		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new VerticalTunnel(new Position(45, 983))));
//		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new VerticalTunnel(new Position(47, 983))));
//		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new VerticalTunnel(new Position(49, 983))));
//		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new StoreRoom(new Position(49, 984))));
	}

}

