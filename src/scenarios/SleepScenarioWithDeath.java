package scenarios;

import java.util.HashMap;
import java.util.Random;

import model.GameMap;
import model.Map;
import model.MapParameters;
import model.Actors.Actor;
import model.Actors.MoveAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.Furniture.Bed;
import model.Furniture.Furniture;
import controller.Controller;

/**
 * @author Ethan Ward
 *
 *         Add tired actor to the map: actor should die since there is nowhere
 *         to sleep
 */
public class SleepScenarioWithDeath {
	private int seed = 98765;
	private HashMap<Actor, Position> hardCodedActors = new HashMap<>();
	private HashMap<Furniture, Position> hardCodedFurniture = new HashMap<>();

	public static void main(String[] args) {
		new SleepScenarioWithDeath();
	}

	public SleepScenarioWithDeath() {

		Controller controller = new Controller(MapParameters.getDefaultParameters(), hardCodedFurniture,
				hardCodedActors, new Random(seed));

		GameMap.map = controller.getMap();

		PlayerControlledActor actor = new PlayerControlledActor(100, 1090, new Position(50, 985));
		hardCodedActors.put(actor, actor.getPosition());

	}
}
