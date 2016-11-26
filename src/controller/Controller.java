package controller;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import model.Map.Map;
import model.Map.MapParameters;
import view.BasicView;

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
			Random random) {
		map = new Map(mapParameters, random);
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