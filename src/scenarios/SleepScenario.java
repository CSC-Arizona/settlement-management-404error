package scenarios;

import java.util.Random;

import controller.Controller;
import model.Game;
import model.MapParameters;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.Furniture.Bed;

/**
 * @author Ethan Ward
 *
 *         Add tired actor and a bed to the map: actor should use the bed
 */
public class SleepScenario {
	private int seed = 98765;

	public static void main(String[] args) {
		new SleepScenario();
	}

	public SleepScenario() {
		Controller controller = new Controller(MapParameters.getDefaultParameters(), new Random(seed));

		Game.setMap(controller.getMap());
		Game.getMap().addFurniture(new Bed(), new Position(53, 990));
		Game.getMap().addFurniture(new Bed(), new Position(48, 982));

		new PlayerControlledActor(100, new Position(50, 985)).setFatigue(990);

	}
}
