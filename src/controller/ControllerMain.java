package controller;

import java.util.ArrayList;
import java.util.Random;

import model.MapParameters;
import model.Actors.Actor;
import model.Actors.MoveAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.EarthBlock;

/**
 * @author Ethan Ward
 * 
 *         Any actual random (not seeded or hardcoded) generation should go here
 *
 */
public class ControllerMain {

	private static Random random = new Random();

	public static void main(String[] args) {
		new Controller(MapParameters.getDefaultParameters(), null, null, random);
	}
}
