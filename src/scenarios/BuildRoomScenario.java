package scenarios;

import java.util.Random;

import model.Actors.ConstructAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.Game.Game;
import model.Map.MapParameters;
import model.Room.VerticalTunnel;
import controller.Controller;

/**
 * Flat map with no ant hills to test building room
 * 
 * @author Ethan Ward
 *
 */
public class BuildRoomScenario {
	private int seed = 369403108;

	public static void main(String[] args) {
		new BuildRoomScenario();
	}

	public BuildRoomScenario() {
		Controller controller = new Controller(
				MapParameters.getParametersWithNoMountains(), new Random(seed),
				true);
		Game.setMap(controller.getMap());
		PlayerControlledActor.addActionToPlayerPool(new ConstructAction(new VerticalTunnel(new Position(70, 994))));

	}
}
