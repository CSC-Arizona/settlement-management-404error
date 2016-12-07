package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.actors.Actor;
import model.actors.BreedAction;
import model.actors.PlayerControlledActor;
import model.actors.Position;
import model.building_blocks.AirBlock;
import model.building_blocks.BuildingBlock;
import model.building_blocks.EarthBlock;
import model.building_blocks.IronOreBlock;
import model.furniture.IncubationChamber;
import model.game.Game;
import model.map.Map;

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
		Actor performer = new PlayerControlledActor(new Position(1,3));
		new PlayerControlledActor(new Position(1,2));
		
		assertEquals(mapTypes[2][4].getFurniture().getRemainingWeightCapacity(), 3, 1e-10);
		performer.addToActionQueue(new BreedAction());
		
		performer.update();
		performer.update();
		performer.update();
		performer.update();
		performer.update();
		performer.update();
		
		//TODO: Need to make sure mapTypes is updated, or get game map
		Map map = Game.getMap();
		
		assertEquals(map.getBuildingBlock(2, 4).getFurniture().getRemainingWeightCapacity(), 3, 1e-10);
	}
}
