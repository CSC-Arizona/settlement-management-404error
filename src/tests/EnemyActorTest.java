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
public class EnemyActorTest {
	
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
		PlayerControlledActor.allActors = null;

		Actor enemy = new EnemyActor(new Position(1,1));
		
		Actor player = new PlayerControlledActor(new Position(1,2));
		enemy.addToActionQueue(new AttackAction(player));
		
		assertEquals(100,player.getHealth());
		assertEquals(0,enemy.getSkills().getCombatLevel());
		enemy.update();
		assertEquals(2,enemy.getSkills().getCombatLevel());
		assertEquals(92,player.getHealth());
		enemy.update();
		assertEquals(2,enemy.getSkills().getCombatLevel());
		assertEquals(82,player.getHealth());
		enemy.update();
		assertEquals(3,enemy.getSkills().getCombatLevel());
		assertEquals(72,player.getHealth());
		enemy.update();
		assertEquals(3,enemy.getSkills().getCombatLevel());
		assertEquals(61,player.getHealth());
		enemy.update();
		assertEquals(50,player.getHealth());
		enemy.update();
		assertEquals(39,player.getHealth());
		enemy.update();
		assertEquals(27,player.getHealth());
		enemy.update();
		assertEquals(15,player.getHealth());
		enemy.addToActionQueue(new AttackAction(player));
		enemy.update();
		assertEquals(3,player.getHealth());
		
		PlayerControlledActor.allActors = null;
		Actor.allActors = null;
	}

}
