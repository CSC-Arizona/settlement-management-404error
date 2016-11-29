/**
 * 
 */
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Actors.Actor;
import model.Actors.AttackAction;
import model.Actors.EnemyActor;
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

		Actor enemy = new EnemyActor(5, new Position(1,1));
		Actor player = new PlayerControlledActor(5,new Position(1,2));

		assertEquals(5,player.getHealth());
		assertEquals(0,enemy.getSkills().getCombatLevel());
		enemy.update();
		assertEquals(0,enemy.getSkills().getCombatLevel());
		assertEquals(4,player.getHealth());
		enemy.update();
		assertEquals(0,enemy.getSkills().getCombatLevel());
		assertEquals(3,player.getHealth());
		enemy.update();
		assertEquals(1,enemy.getSkills().getCombatLevel());
		assertEquals(2,player.getHealth());
		enemy.update();
		assertEquals(1,enemy.getSkills().getCombatLevel());
		assertEquals(0,player.getHealth());
		enemy.update();
		assertEquals(0,player.getHealth());
		enemy.update();
		assertEquals(0,player.getHealth());
		enemy.update();
		assertEquals(0,player.getHealth());
		enemy.update();
		assertEquals(0,player.getHealth());
		enemy.addToActionQueue(new AttackAction(player));
		enemy.update();
		assertEquals(0,player.getHealth());
		
		PlayerControlledActor.allActors = null;
		Actor.allActors = null;
	}

}
