package tests;

import java.util.HashMap;
import java.util.Random;

import model.MapParameters;
import model.Actors.Actor;
import model.Actors.MoveAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.Furniture.Bed;
import model.Furniture.Furniture;
import controller.Controller;
import controller.ControllerMain;

/**
 * @author Ethan Ward
 * 
 *         Movement scenario: make the actors move to a specified position
 *
 */
public class Scenario {
	private int seed = 555555;

	private HashMap<Actor, Position> hardCodedActors = new HashMap<>();

	public static void main(String[] args) {
		new Scenario();
	}

	public Scenario() {

		Position finalPosition = new Position(52,980);
		
		Actor actor1 = new PlayerControlledActor(100, new Position(58, 990));
		Actor actor2 = new PlayerControlledActor(100, new Position(59, 991));
		Actor actor3 = new PlayerControlledActor(100, new Position(57, 999));
		
		// this actor should move across 0 boundary
		Actor actor4 = new PlayerControlledActor(100, new Position(54, 4));

		actor1.addToActionQueue(new MoveAction(finalPosition));
		actor2.addToActionQueue(new MoveAction(finalPosition));
		actor3.addToActionQueue(new MoveAction(finalPosition));
		actor4.addToActionQueue(new MoveAction(finalPosition));

		hardCodedActors.put(actor1, actor1.getPosition());
		hardCodedActors.put(actor2, actor2.getPosition());
		hardCodedActors.put(actor3, actor3.getPosition());
		hardCodedActors.put(actor4, actor4.getPosition());

		new Controller(MapParameters.getDefaultParameters(), null,
				hardCodedActors, new Random(seed));
	}

}
