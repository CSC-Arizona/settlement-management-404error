package tests;
import model.building_blocks.*;

import static org.junit.Assert.*;

import org.junit.Test;

import model.actors.Position;
import model.game.Game;
import model.map.Map;

public class BorderlinePositionsTest {
	
	public Map generateMap(int[][] map) {
		BuildingBlock[][] mapTypes = new BuildingBlock[map.length][map[0].length];
		for (int i = 0; i < mapTypes.length; i++) {
			for (int j = 0; j < mapTypes[i].length; j++) {
				if (map[i][j] == 0)
					mapTypes[i][j] = new AirBlock();
				else
					mapTypes[i][j] = new EarthBlock();
			}
		}
		return new Map(mapTypes);
	}

	@Test
	public void test() {
		Game.reset();
		int[][] mapGen = new int[100][1000];
		Game.setMap(generateMap(mapGen));
		Position p = new Position(0,0);
		Position q = new Position(0,999); 
		assertTrue(p.isAdjacent(q));
		assertTrue(q.isAdjacent(p));
		Position P = new Position(1,0);
		Position Q = new Position(0,999);
		assertTrue(p.isAdjacent(Q));
		assertTrue(q.isAdjacent(P));
	}

}
