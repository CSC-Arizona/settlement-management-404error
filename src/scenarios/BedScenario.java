package scenarios;

import java.util.HashMap;
import java.util.Random;

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
 *         Add tired actor and a bed to the map: actor should use the bed
 */
public class BedScenario {
	private int seed = 98765;
	private HashMap<Actor, Position> hardCodedActors = new HashMap<>();
	private HashMap<Furniture, Position> hardCodedFurniture = new HashMap<>();

	public static void main(String[] args) {
		new BedScenario();
	}

	public BedScenario() {
		hardCodedFurniture.put(new Bed(), new Position(48, 982));
		hardCodedFurniture.put(new Bed(), new Position(53, 990));

		Controller controller = new Controller(
				MapParameters.getDefaultParameters(), hardCodedFurniture,
				hardCodedActors, new Random(seed));

		Map map = controller.getMap();

		PlayerControlledActor actor = new PlayerControlledActor(100, 0,
				new Position(50, 985), map);
		actor.setFatigue(5);
		hardCodedActors.put(actor, actor.getPosition());

	}
}
