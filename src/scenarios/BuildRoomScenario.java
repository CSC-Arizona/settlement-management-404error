package scenarios;

import java.util.Random;

import model.Game.Game;
import model.Map.MapParameters;
import controller.Controller;

public class BuildRoomScenario {
	private int seed = 369403108;

	public static void main(String[] args) {
		new BuildRoomScenario();
	}

	public BuildRoomScenario() {
		Controller controller = new Controller(
				MapParameters.getParametersWithNoMountains(), new Random(seed), true);
		Game.setMap(controller.getMap());
	}
}
