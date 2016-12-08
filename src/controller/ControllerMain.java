package controller;

import java.util.Random;

import model.map.MapParameters;

/**
 * @author Ethan Ward
 * 
 *         Intended to be the main entry for when the game is actually played
 *
 */
public class ControllerMain {

	private static Random random = new Random();

	public static void main(String[] args) {
		new Controller(MapParameters.getDefaultParameters(), random, false);
	}
}