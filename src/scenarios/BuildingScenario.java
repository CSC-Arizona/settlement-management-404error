package scenarios;

import java.util.Random;

import controller.Controller;
import model.Game;
import model.MapParameters;
import model.Actors.ConstructAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.Items.IronItem;
import model.Items.StoneItem;
import model.Room.StoreRoom;

//Author: Maxwell Faridian
//This class shows a hard coded demo of building a store room
public class BuildingScenario {

	private int seed = 8412372;

	public static void main(String[] args) {
		new BuildingScenario();
	}

	public BuildingScenario() {

		Controller controller = new Controller(MapParameters.getDefaultParameters(), new Random(seed));
		Game.setMap(controller.getMap());
		PlayerControlledActor actor = new PlayerControlledActor(100, new Position(44, 985));
		for (int i = 0; i < 8; i++) {
			actor.getInventory().addItem(new StoneItem());
			actor.getInventory().addItem(new IronItem());
		}
		actor.addToActionQueue(new ConstructAction(new StoreRoom(new Position(45, 983))));
	}

}
