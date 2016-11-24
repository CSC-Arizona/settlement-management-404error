package scenarios;

import java.util.HashMap;
import java.util.Random;

import controller.Controller;
import model.Game;
import model.MapParameters;
import model.Actors.Actor;
import model.Actors.MoveAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;

/**
 * @author Ethan Ward
 * 
 *         Movement scenario: make the actors move to a specified position
 *
 */
public class MovementScenario {
	private int seed = 555555;

	private HashMap<Actor, Position> hardCodedActors = new HashMap<>();

	public static void main(String[] args) {
		new MovementScenario();
	}

	public MovementScenario() {

		Controller controller = new Controller(MapParameters.getDefaultParameters(), null,
				hardCodedActors, new Random(seed), null);
		
		Game.setMap(controller.getMap());
		
		Position finalPosition = new Position(52,980);
		
		Actor actor1 = new PlayerControlledActor(100, 0, new Position(58, 990), hardCodedActors);
		Actor actor2 = new PlayerControlledActor(100, 0, new Position(59, 991), hardCodedActors);
		Actor actor3 = new PlayerControlledActor(100, 0, new Position(57, 999), hardCodedActors);
		
		// this actor should move across 0 boundary
		Actor actor4 = new PlayerControlledActor(100, 0, new Position(54, 4), hardCodedActors);

		actor1.addToActionQueue(new MoveAction(finalPosition));
		actor2.addToActionQueue(new MoveAction(finalPosition));
		actor3.addToActionQueue(new MoveAction(finalPosition));
		actor4.addToActionQueue(new MoveAction(finalPosition));

		hardCodedActors.put(actor1, actor1.getPosition());
		hardCodedActors.put(actor2, actor2.getPosition());
		hardCodedActors.put(actor3, actor3.getPosition());
		hardCodedActors.put(actor4, actor4.getPosition());
	}

}
