package scenarios;

import java.util.Random;

import model.actors.ConstructAction;
import model.actors.PlayerControlledActor;
import model.actors.Position;
import model.game.Game;
import model.map.MapParameters;
import model.room.VerticalTunnel;
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
