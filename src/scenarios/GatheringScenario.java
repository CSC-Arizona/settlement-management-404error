package scenarios;

import java.util.Random;

import controller.Controller;
import model.Actors.GatherAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.Furniture.ReinforcedCrate;
import model.Game.Game;
import model.Map.MapParameters;

public class GatheringScenario {
	private int seed = 8412372;


	public static void main(String[] args) {
		new GatheringScenario();
	}

	public GatheringScenario() {
		Controller controller = new Controller(MapParameters.getDefaultParameters(), new Random(seed));
		Game.setMap(controller.getMap());
		Game.getMap().addFurniture(new ReinforcedCrate(), new Position(44, 985));
		PlayerControlledActor actor = new PlayerControlledActor(100, new Position(44, 985));
		new PlayerControlledActor(100, new Position(44, 983));

		actor.addActionToPool(new GatherAction(new Position(44, 987)));
		actor.addActionToPool(new GatherAction(new Position(48, 996)));
		actor.addActionToPool(new GatherAction(new Position(47, 994)));
		actor.addActionToPool(new GatherAction(new Position(46, 991)));
		actor.addActionToPool(new GatherAction(new Position(46, 990)));

	}

}
