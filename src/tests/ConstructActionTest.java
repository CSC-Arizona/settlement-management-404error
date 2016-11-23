/**
 * 
 */
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Map;
import model.Actors.ConstructAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.EarthBlock;
import model.BuildingBlocks.IronOreBlock;
import model.Room.VerticalTunnel;

/**
 * @author Jonathon Davis
 *
 */
public class ConstructActionTest {

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
		return new Map(mapTypes);
	}
	
	@Test
	public void testMoveAndGatherAction(){
		int[][] mapGen = new int[][]	{{0,0,0,0,0},
										{0,0,0,0,0},
										{1,1,1,1,1},
										{1,1,1,1,1},
										{1,1,1,1,1}};
		Map map = generateMap(mapGen);
		PlayerControlledActor test = new PlayerControlledActor(10, 0, new Position(1,1), null, map);
		test.addToActionQueue(new ConstructAction(new VerticalTunnel(new Position(2,3)), map));
		
		assertEquals(1,test.getPosition().getRow());
		assertEquals(1,test.getPosition().getCol());
		
		test.update();
		assertEquals(1,test.getPosition().getRow());
		assertEquals(2,test.getPosition().getCol());
		
		for(int i = 0; i < map.getBuildingBlock(new Position(2,3)).getDurability() - 1; i++){
			test.update();
			assertEquals(1,test.getPosition().getRow());
			assertEquals(2,test.getPosition().getCol());
		}
		
		test.update();
		assertEquals(2,test.getPosition().getRow());
		assertEquals(3,test.getPosition().getCol());
		assertEquals("Air",map.getBuildingBlock(new Position(2,3)).getID());
		
		for(int i = 0; i < map.getBuildingBlock(new Position(3,3)).getDurability(); i++){
			test.update();
			assertEquals(2,test.getPosition().getRow());
			assertEquals(3,test.getPosition().getCol());
		}
		
		assertEquals("Air",map.getBuildingBlock(new Position(3,3)).getID());
		

	}

}
