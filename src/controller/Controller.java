package controller;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import view.BasicView;
import model.GameMap;
import model.Map;

/**
 * Display a map
 * 
 * @author Ethan Ward
 *
 */
public class Controller {

	int time = 0;
	private boolean paused = false;

	private int mapHeight = 150;
	private int mapWidth = 1000;
	private int mapDirtDepth = 30;
	private int mapStoneDepth = 50;

	private Map map;
	private GameMap gameMap;

	private BasicView view;

	private Timer timer;
	private int timeDelta = 1000;

	public static void main(String[] args) {
		new Controller();
	}

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

	public Controller() {
		map = new Map(mapHeight, mapWidth, mapDirtDepth, mapStoneDepth,
				new Random().nextInt(1000000));
		GameMap.map = map;
		view = new BasicView(this, map, mapHeight, mapWidth);
		view.setVisible(true);
		startTimer();
	}

	private class MyTimerTasks extends TimerTask {

		@Override
		public void run() {
			time += 1;
			view.setTimeLabel(time, paused);
			map.updateActors(timeDelta);
			view.repaint();
		}
	}

}