package scenarios;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import controller.Controller;
import model.Actors.Actor;
import model.Actors.AttackAction;
import model.Actors.BreedAction;
import model.Actors.ConstructAction;
import model.Actors.GatherAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.EarthBlock;
import model.BuildingBlocks.IronOreBlock;
import model.Furniture.IncubationChamber;
import model.Furniture.ReinforcedCrate;
import model.Game.Game;
import model.Map.Map;
import model.Map.MapParameters;
import model.Room.VerticalTunnel;

//Author: Maxwell Faridian
//This class models the breed action in the GUI
public class BreedScenario {

	//private int seed = 369403108;
	private int seed = 369403105;
	public static void main(String[] args) {
		new BreedScenario();
	}
	
	public Map generateMap(int[][] map){
		BuildingBlock[][] mapTypes = new BuildingBlock[map.length][map[0].length];
		for (int i = 0; i < mapTypes.length; i++) {
			for (int j = 0; j < mapTypes[i].length; j++) {
				if(map[i][j] == 0)
					mapTypes[i][j] = new AirBlock();
				else if (map[i][j] == 2)
					mapTypes[i][j] = new IronOreBlock();
				else
					mapTypes[i][j] = new EarthBlock();
			}
		}
		mapTypes[2][4].addFurniture(new IncubationChamber());
		return new Map(mapTypes);
	}
	
	public BreedScenario() {
		Game.reset();
		int[][] mapGen = new int[][]	
			{{0,0,0,0,0},
			{0,0,0,0,0},
			{1,1,1,1,0},
			{1,1,1,1,1},
			{1,1,1,1,1}};
		/*Controller controller = new Controller(
				MapParameters.getParametersWithNoMountains(), new Random(seed),
				true);*/
		Controller controller = new Controller(MapParameters.getDefaultParameters(), new Random(seed), true);
		Game.setMap(controller.getMap());
		Game.getMap().addFurniture(new IncubationChamber(), new Position(48, 22));
		
		//Actor performer = new PlayerControlledActor(100, new Position(69,990));
		//Actor mate = new PlayerControlledActor(100, new Position(69, 991));
		
		//performer.addToActionQueue(new BreedAction(mate));
		
		Actor a = PlayerControlledActor.allActors.getLast();
		PlayerControlledActor.addActionToPlayerPool(new BreedAction(a));
	}
}
