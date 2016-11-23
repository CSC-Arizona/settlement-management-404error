package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import view.BasicView;
import model.GameMap;
import model.Map;
import model.MapParameters;
import model.Actors.Actor;
import model.Actors.Position;
import model.Furniture.Furniture;

/**
 * Display a map
 * 
 * @author Ethan Ward
 *
 */
public class Controller {

	int time = 0;
	private boolean paused = false;

	private Map map;

	private BasicView view;

	private Timer timer;
	private int timeDelta = 1000;

	public void stopTimer() {
		this.timer.cancel();
		paused = true;
	}

	public void startTimer() {
		timer = new Timer();
		timer.schedule(new MyTimerTasks(), 0, timeDelta);
		paused = false;
	}

	public boolean isPaused() {
		return paused;
	}

	public int getTime() {
		return time;
	}

	public Controller(MapParameters mapParameters,
			HashMap<Furniture, Position> hardCodedFurniture,
			HashMap<Actor, Position> hardCodedActors, Random random,
			ArrayList<Position> blocksMarkedForGathering) {
		map = new Map(mapParameters, hardCodedFurniture, hardCodedActors,
				random, blocksMarkedForGathering);
		GameMap.map = map;
		view = new BasicView(this, map, mapParameters);
		view.setVisible(true);
		startTimer();
	}

	private class MyTimerTasks extends TimerTask {

		@Override
		public void run() {
			time += 1;
			view.setTimeLabel(time, paused);
			view.setMouseDescriptionLabel();
			map.updateActors(timeDelta);
			view.repaint();
		}
	}

	public Map getMap() {
		return map;
	}

}