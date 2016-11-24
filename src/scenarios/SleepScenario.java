package scenarios;

import java.util.HashMap;
import java.util.Random;

import controller.Controller;
import model.Game;
import model.MapParameters;
import model.Actors.Actor;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.Furniture.Bed;
import model.Furniture.Furniture;

/**
 * @author Ethan Ward
 *
 *         Add tired actor and a bed to the map: actor should use the bed
 */
public class SleepScenario {
	private int seed = 98765;
	private HashMap<Actor, Position> hardCodedActors = new HashMap<>();
	private HashMap<Furniture, Position> hardCodedFurniture = new HashMap<>();

	public static void main(String[] args) {
		new SleepScenario();
	}

	public SleepScenario() {
		hardCodedFurniture.put(new Bed(), new Position(48, 982));
		hardCodedFurniture.put(new Bed(), new Position(53, 990));

		Controller controller = new Controller(
				MapParameters.getDefaultParameters(), hardCodedFurniture,
				hardCodedActors, new Random(seed), null);

		Game.setMap(controller.getMap());

		PlayerControlledActor actor = new PlayerControlledActor(100, 990, new Position(50, 985), hardCodedActors);
		hardCodedActors.put(actor, actor.getPosition());

	}
}
