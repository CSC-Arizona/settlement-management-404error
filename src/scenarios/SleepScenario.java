package scenarios;

import java.util.Random;

import controller.Controller;
import model.actors.PlayerControlledActor;
import model.actors.Position;
import model.furniture.Bed;
import model.game.Game;
import model.map.MapParameters;

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
		Controller controller = new Controller(MapParameters.getDefaultParameters(), new Random(seed), true);

		Game.setMap(controller.getMap());
		Game.getMap().addFurniture(new Bed(), new Position(48, 982));
		Game.getMap().addFurniture(new Bed(), new Position(53, 990));

		new PlayerControlledActor(new Position(50, 985)).setFatigue(990);

	}
}
