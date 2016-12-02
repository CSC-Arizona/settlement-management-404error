package scenarios;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import controller.Controller;
import model.actors.Actor;
import model.actors.AttackAction;
import model.actors.BreedAction;
import model.actors.ConstructAction;
import model.actors.GatherAction;
import model.actors.OldBreedAction;
import model.actors.PlayerControlledActor;
import model.actors.Position;
import model.building_blocks.AirBlock;
import model.building_blocks.BuildingBlock;
import model.building_blocks.EarthBlock;
import model.building_blocks.IronOreBlock;
import model.furniture.IncubationChamber;
import model.furniture.ReinforcedCrate;
import model.game.Game;
import model.map.Map;
import model.map.MapParameters;
import model.room.VerticalTunnel;

//Author: Maxwell Faridian
//This class models the breed action in the GUI
public class BreedScenario {

	//private int seed = 369403108;
	private int seed = 369403105;
	public static void main(String[] args) {
		new BreedScenario();
	}
	
	public BreedScenario() {
		Game.reset();
		/*Controller controller = new Controller(
				MapParameters.getParametersWithNoMountains(), new Random(seed),
				true);*/
		Controller controller = new Controller(MapParameters.getDefaultParameters(), new Random(seed), true);
		Game.setMap(controller.getMap());
		Game.getMap().addFurniture(new IncubationChamber(), new Position(48, 22));
		
		//Actor performer = new PlayerControlledActor(100, new Position(69,990));
		//Actor mate = new PlayerControlledActor(100, new Position(69, 991));
		
		//performer.addToActionQueue(new BreedAction(mate));
		PlayerControlledActor.addActionToPlayerPool(new BreedAction());
		
		//TODO: Try using iterator to go over list of actors or create new actor in map class
	}
}
