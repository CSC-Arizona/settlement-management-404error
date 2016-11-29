/**
 * 
 */
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Actors.Actor;
import model.Actors.AttackAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.EarthBlock;
import model.BuildingBlocks.IronOreBlock;
import model.Game.Game;
import model.Map.Map;

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

		Actor tester = new PlayerControlledActor(5, new Position(1,1));
		Actor rival = new PlayerControlledActor(5,new Position(1,2));
		tester.addToActionQueue(new AttackAction(rival));
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
		tester.addToActionQueue(new AttackAction(rival));
		tester.update();
		assertEquals(0,rival.getHealth());
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
		PlayerControlledActor tester = new PlayerControlledActor(10, new Position(1,1));
		Actor rival = new PlayerControlledActor(5, new Position(2,4));
		tester.addToActionQueue(new AttackAction(rival));
		
		assertEquals(1,tester.getPosition().getRow());
		assertEquals(1,tester.getPosition().getCol());
		
		tester.update();
		assertEquals(1,tester.getPosition().getRow());
		assertEquals(0,tester.getPosition().getCol());
		
		tester.update();
		assertEquals(2,tester.getPosition().getRow());
		assertEquals(4,tester.getPosition().getCol());
		
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
		tester.addToActionQueue(new AttackAction(rival));
		tester.update();
		assertEquals(0,rival.getHealth());
	}

}
