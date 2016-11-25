package scenarios;

import java.util.HashMap;
import java.util.Random;

import controller.Controller;
import model.Game;
import model.MapParameters;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.Furniture.Furniture;

/**
 * @author Ethan Ward
 *
 *         Add tired actor to the map: actor should die since there is nowhere
 *         to sleep
 */
public class SleepScenarioWithDeath {
	private int seed = 98765;
	private HashMap<Furniture, Position> hardCodedFurniture = new HashMap<>();

	public static void main(String[] args) {
		new SleepScenarioWithDeath();
	}

	public SleepScenarioWithDeath() {

		Controller controller = new Controller(MapParameters.getDefaultParameters(), hardCodedFurniture, new Random(seed), null);

		Game.setMap(controller.getMap());

		new PlayerControlledActor(100, new Position(50, 985)).setFatigue(1090);

	}
}
