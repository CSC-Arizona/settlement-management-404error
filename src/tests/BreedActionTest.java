package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Actors.Actor;
import model.Actors.BreedAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.EarthBlock;
import model.BuildingBlocks.IronOreBlock;
import model.Furniture.IncubationChamber;
import model.Game.Game;
import model.Map.Map;

//Author: Maxwell Faridian
//This class tests the Breed Action
public class BreedActionTest {
	
	public BuildingBlock[][] mapTypes;

	public Map generateMap(int[][] map){
		mapTypes = new BuildingBlock[map.length][map[0].length];
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
	
	@Test
	public void breedTest() {
		Game.reset();
		int[][] mapGen = new int[][]	
				{{0,0,0,0,0},
				{0,0,0,0,0},
				{1,1,1,1,0},
				{1,1,1,1,1},
				{1,1,1,1,1}};
		Game.setMap(generateMap(mapGen));
		Actor performer = new PlayerControlledActor(100, new Position(1,3));
		Actor mate = new PlayerControlledActor(100, new Position(1,2));
		
		assertEquals(mapTypes[2][4].getFurniture().getRemainingWeightCapacity(), 3);
		performer.addToActionQueue(new BreedAction(mate));
		
		performer.update();
		performer.update();
		performer.update();
		performer.update();
		performer.update();
		performer.update();
		
		//TODO: Need to make sure mapTypes is updated, or get game map
		Map map = Game.getMap();
		
		assertEquals(map.getBuildingBlock(2, 4).getFurniture().getRemainingWeightCapacity(), 0);
	}
}
