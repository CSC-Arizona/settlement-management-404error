/**
 * 
 */
package tests;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import model.GameMap;
import model.Map;
import model.Actors.Actor;
import model.Actors.AttackAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.EarthBlock;
import model.BuildingBlocks.IronOreBlock;

/**
 * @author Jonathon Davis
 *
 */
public class AttackActionTest {
	
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
	public void attackTest(){
		int[][] mapGen = new int[][]	{{0,0,0,0,0},
				{0,0,0,0,0},
				{1,1,1,1,0},
				{1,1,1,1,1},
				{1,1,1,1,1}};
		Map map = generateMap(mapGen);
		HashMap<Actor, Position> actors = new HashMap<>();

		Actor tester = new PlayerControlledActor(5, 0, new Position(10,10), map, actors);
		Actor rival = new PlayerControlledActor(5, 0, new Position(11,10), map, actors);
		tester.addToActionQueue(new AttackAction(rival, map));
		assertEquals(5,rival.getHealth());
		assertEquals(0,tester.getSkills().getCombatLevel());
		tester.update();
		assertEquals(0,tester.getSkills().getCombatLevel());
		assertEquals(4,rival.getHealth());
		tester.update();
		assertEquals(0,tester.getSkills().getCombatLevel());
		assertEquals(3,rival.getHealth());
		tester.update();
		assertEquals(1,tester.getSkills().getCombatLevel());
		assertEquals(2,rival.getHealth());
		tester.update();
		assertEquals(1,tester.getSkills().getCombatLevel());
		assertEquals(0,rival.getHealth());
		tester.update();
		assertEquals(0,rival.getHealth());
		tester.update();
		assertEquals(0,rival.getHealth());
		tester.update();
		assertEquals(0,rival.getHealth());
		tester.update();
		assertEquals(0,rival.getHealth());
		tester.addToActionQueue(new AttackAction(rival, map));
		tester.update();
		assertEquals(0,rival.getHealth());
	}
	
	@Test
	public void testMoveAndAttackAction(){
		int[][] mapGen = new int[][]	{{0,0,0,0,0},
										{0,0,0,0,0},
										{1,1,1,1,0},
										{1,1,1,1,1},
										{1,1,1,1,1}};
		Map map = generateMap(mapGen);
		HashMap<Actor, Position> actors = new HashMap<>();
		PlayerControlledActor tester = new PlayerControlledActor(10, 0, new Position(1,1), map, actors);
		Actor rival = new PlayerControlledActor(5, 0, new Position(2,4), map, actors);
		tester.addToActionQueue(new AttackAction(rival, map));
		
		assertEquals(1,tester.getPosition().getRow());
		assertEquals(1,tester.getPosition().getCol());
		
		tester.update();
		assertEquals(1,tester.getPosition().getRow());
		assertEquals(2,tester.getPosition().getCol());
		
		tester.update();
		assertEquals(1,tester.getPosition().getRow());
		assertEquals(3,tester.getPosition().getCol());
		
		assertEquals(5,rival.getHealth());
		assertEquals(0,tester.getSkills().getCombatLevel());
		tester.update();
		assertEquals(0,tester.getSkills().getCombatLevel());
		assertEquals(4,rival.getHealth());
		tester.update();
		assertEquals(0,tester.getSkills().getCombatLevel());
		assertEquals(3,rival.getHealth());
		tester.update();
		assertEquals(1,tester.getSkills().getCombatLevel());
		assertEquals(2,rival.getHealth());
		tester.update();
		assertEquals(1,tester.getSkills().getCombatLevel());
		assertEquals(0,rival.getHealth());
		tester.update();
		assertEquals(0,rival.getHealth());
		tester.update();
		assertEquals(0,rival.getHealth());
		tester.update();
		assertEquals(0,rival.getHealth());
		tester.update();
		assertEquals(0,rival.getHealth());
		tester.addToActionQueue(new AttackAction(rival, map));
		tester.update();
		assertEquals(0,rival.getHealth());
	}

}
