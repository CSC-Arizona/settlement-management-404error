package scenarios;

import java.util.Random;

import controller.Controller;
import model.actors.Actor;
import model.actors.MoveAction;
import model.actors.PlayerControlledActor;
import model.actors.Position;
import model.game.Game;
import model.map.MapParameters;

/**
 * @author Ethan Ward
 * 
 *         Movement scenario: make the actors move to a specified position
 *
 */
public class MovementScenario {
	private int seed = 555555;

	public static void main(String[] args) {
		new MovementScenario();
	}

	public MovementScenario() {

		Controller controller = new Controller(
				MapParameters.getDefaultParameters(), new Random(seed), true);

		Game.setMap(controller.getMap());

		Position finalPosition = new Position(52, 980);

		Actor actor1 = new PlayerControlledActor(new Position(58, 990));
		Actor actor2 = new PlayerControlledActor(new Position(59, 991));
		Actor actor3 = new PlayerControlledActor(new Position(57, 999));

		// this actor should move across 0 boundary
		Actor actor4 = new PlayerControlledActor(new Position(54, 4));

		// only need one movement action, any more is wasteful and
		// computationally expensive
		MoveAction move = new MoveAction(finalPosition);
		actor1.addToActionQueue(move);
		actor2.addToActionQueue(move);
		actor3.addToActionQueue(move);
		actor4.addToActionQueue(move);
	}

}
