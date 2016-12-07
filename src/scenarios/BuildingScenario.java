package scenarios;

import java.util.Random;

import controller.Controller;
import model.actors.ConstructAction;
import model.actors.PlayerControlledActor;
import model.actors.Position;
import model.game.Game;
import model.map.MapParameters;
import model.room.BedRoom;
import model.room.HorizontalTunnel;
import model.room.InfirmaryRoom;
import model.room.StoreRoom;
import model.room.VerticalTunnel;

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
		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new VerticalTunnel(new Position(70, 986))));
		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new VerticalTunnel(new Position(72, 986))));
		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new VerticalTunnel(new Position(74, 986))));
		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new VerticalTunnel(new Position(76, 986))));
		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new VerticalTunnel(new Position(78, 986))));
		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new VerticalTunnel(new Position(80, 986))));
		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new StoreRoom(new Position(74, 987))));
		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new BedRoom(new Position(74, 974))));
		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new InfirmaryRoom(new Position(78, 999))));
		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new StoreRoom(new Position(78, 978))));
		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new BedRoom(new Position(78, 987))));
	}

}

