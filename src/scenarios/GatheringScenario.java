package scenarios;

import java.util.LinkedList;
import java.util.Random;

import controller.Controller;
import model.actors.GatherAction;
import model.actors.PlayerControlledActor;
import model.actors.Position;
import model.furniture.ReinforcedCrate;
import model.game.Game;
import model.items.Item;
import model.map.MapParameters;

public class GatheringScenario {
	private int seed = 8412372;

	public static void main(String[] args) {
		new GatheringScenario();
	}

	public GatheringScenario() {
		Controller controller = new Controller(
				MapParameters.getDefaultParameters(), new Random(seed), true);
		Game.setMap(controller.getMap());
		Game.getMap()
				.addFurniture(new ReinforcedCrate(new LinkedList<Item>()), new Position(44, 985));

		PlayerControlledActor.addActionToPlayerPool(new GatherAction(
				new Position(44, 987)));
		PlayerControlledActor.addActionToPlayerPool(new GatherAction(
				new Position(48, 996)));
		PlayerControlledActor.addActionToPlayerPool(new GatherAction(
				new Position(47, 994)));
		PlayerControlledActor.addActionToPlayerPool(new GatherAction(
				new Position(46, 991)));
		PlayerControlledActor.addActionToPlayerPool(new GatherAction(
				new Position(46, 990)));

	}

}
