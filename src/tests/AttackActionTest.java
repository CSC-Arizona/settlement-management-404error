/**
 * 
 */
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.actors.Actor;
import model.actors.AttackAction;
import model.actors.EnemyActor;
import model.actors.PlayerControlledActor;
import model.actors.Position;
import model.building_blocks.AirBlock;
import model.building_blocks.BuildingBlock;
import model.building_blocks.EarthBlock;
import model.building_blocks.IronOreBlock;
import model.game.Game;
import model.map.Map;

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
		Game.reset();
		int[][] mapGen = new int[][]	{{0,0,0,0,0},
				{0,0,0,0,0},
				{1,1,1,1,0},
				{1,1,1,1,1},
				{1,1,1,1,1}};
		Game.setMap(generateMap(mapGen));

		Actor tester = new PlayerControlledActor(new Position(1,1));
		Actor rival = new EnemyActor(new Position(1,2));
		tester.addToActionQueue(new AttackAction(rival));
		assertEquals(100,rival.getHealth());
		assertEquals(0,tester.getSkills().getCombatLevel());
		tester.update();
		assertEquals(0,tester.getSkills().getCombatLevel());
		assertEquals(100,rival.getHealth());
		tester.update();
		assertEquals(2,tester.getSkills().getCombatLevel());
		assertEquals(92,rival.getHealth());
		tester.update();
		assertEquals(2,tester.getSkills().getCombatLevel());
		assertEquals(82,rival.getHealth());
		tester.update();
		assertEquals(3,tester.getSkills().getCombatLevel());
		assertEquals(72,rival.getHealth());
		tester.update();
		assertEquals(61,rival.getHealth());
		tester.update();
		assertEquals(50,rival.getHealth());
		tester.update();
		assertEquals(39,rival.getHealth());
		tester.update();
		assertEquals(27,rival.getHealth());
		tester.addToActionQueue(new AttackAction(rival));
		tester.update();
		assertEquals(15,rival.getHealth());
	}
	
	@Test
	public void testMoveAndAttackAction(){
		Game.reset();
		int[][] mapGen = new int[][]	{{0,0,0,0,0},
										{0,0,0,0,0},
										{1,1,1,1,0},
										{1,1,1,1,1},
										{1,1,1,1,1}};
		Game.setMap(generateMap(mapGen));
		PlayerControlledActor tester = new PlayerControlledActor(new Position(1,1));
		Actor rival = new EnemyActor(new Position(2,4));
		tester.addToActionQueue(new AttackAction(rival));
		
		assertEquals(1,tester.getPosition().getRow());
		assertEquals(1,tester.getPosition().getCol());
		
		tester.update();
		assertEquals(1,tester.getPosition().getRow());
		assertEquals(0,tester.getPosition().getCol());
		
		tester.update();
		assertEquals(1,tester.getPosition().getRow());
		assertEquals(0,tester.getPosition().getCol());
		
		assertEquals(100,rival.getHealth());
		assertEquals(0,tester.getSkills().getCombatLevel());
		tester.update();
		assertEquals(2,tester.getSkills().getCombatLevel());
		assertEquals(92,rival.getHealth());
		tester.update();
		assertEquals(2,tester.getSkills().getCombatLevel());
		assertEquals(82,rival.getHealth());
		tester.update();
		assertEquals(3,tester.getSkills().getCombatLevel());
		assertEquals(72,rival.getHealth());
		tester.update();
		assertEquals(3,tester.getSkills().getCombatLevel());
		assertEquals(61,rival.getHealth());
		tester.update();
		assertEquals(50,rival.getHealth());
		tester.update();
		assertEquals(39,rival.getHealth());
		tester.update();
		assertEquals(27,rival.getHealth());
		tester.update();
		assertEquals(15,rival.getHealth());
		tester.addToActionQueue(new AttackAction(rival));
		tester.update();
		assertEquals(3,rival.getHealth());
	}

}
