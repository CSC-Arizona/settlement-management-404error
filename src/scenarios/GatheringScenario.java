package scenarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import controller.Controller;
import model.Game;
import model.MapParameters;
import model.Actors.GatherAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.Furniture.Furniture;
import model.Furniture.ReinforcedCrate;

public class GatheringScenario {
	private int seed = 8412372;

	private HashMap<Furniture, Position> hardCodedFurniture = new HashMap<>();

	public static void main(String[] args) {
		new GatheringScenario();
	}

	public GatheringScenario() {

		hardCodedFurniture.put(new ReinforcedCrate(), new Position(44, 985));

		Controller controller = new Controller(MapParameters.getDefaultParameters(), hardCodedFurniture,
				new Random(seed));
		Game.setMap(controller.getMap());
		PlayerControlledActor actor = new PlayerControlledActor(100, new Position(44, 985));

		actor.addToActionQueue(new GatherAction(new Position(44, 987)));
		actor.addToActionQueue(new GatherAction(new Position(48, 996)));
		actor.addToActionQueue(new GatherAction(new Position(47, 994)));
		actor.addToActionQueue(new GatherAction(new Position(46, 991)));
		actor.addToActionQueue(new GatherAction(new Position(46, 990)));

	}

}
