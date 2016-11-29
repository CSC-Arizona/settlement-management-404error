package scenarios;

import java.util.Random;

import controller.Controller;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.Game.Game;
import model.Map.MapParameters;

/**
 * @author Ethan Ward
 *
 *         Add tired actor to the map: actor should die since there is nowhere
 *         to sleep
 */
public class SleepScenarioWithDeath {
	private int seed = 98765;

	public static void main(String[] args) {
		new SleepScenarioWithDeath();
	}

	public SleepScenarioWithDeath() {

		Controller controller = new Controller(
				MapParameters.getDefaultParameters(), new Random(seed), true);

		Game.setMap(controller.getMap());

		new PlayerControlledActor(100, new Position(50, 985)).setFatigue(1090);

	}
}
