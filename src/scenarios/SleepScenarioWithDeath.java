package scenarios;

import java.util.Random;

import controller.Controller;
import model.actors.PlayerControlledActor;
import model.actors.Position;
import model.game.Game;
import model.map.MapParameters;

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
