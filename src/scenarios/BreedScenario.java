package scenarios;


import java.util.Random;

import controller.Controller;
import model.actors.BreedAction;
import model.actors.PlayerControlledActor;
import model.actors.Position;
import model.furniture.IncubationChamber;
import model.game.Game;
import model.map.MapParameters;

//Author: Maxwell Faridian
//This class models the breed action in the GUI
public class BreedScenario {

	private int seed = 369403105;
	public static void main(String[] args) {
		new BreedScenario();
	}
	
	public BreedScenario() {
		Game.reset();
		Controller controller = new Controller(MapParameters.getDefaultParameters(), new Random(seed), true);
		Game.setMap(controller.getMap());
		Game.getMap().addFurniture(new IncubationChamber(), new Position(48, 22));
		
		PlayerControlledActor.addActionToPlayerPool(new BreedAction());
	}
}
