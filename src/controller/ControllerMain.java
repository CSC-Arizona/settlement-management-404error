package controller;

import java.util.Random;

import model.Map.MapParameters;

/**
 * @author Ethan Ward
 * 
 *         Any actual random (not seeded or hardcoded) generation should go here
 *
 */
public class ControllerMain {

	private static Random random = new Random();

	public static void main(String[] args) {
		new Controller(MapParameters.getDefaultParameters(), random);
	}
}
