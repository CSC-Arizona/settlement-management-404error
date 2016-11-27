package controller;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import model.BuildingBlocks.EarthBlock;
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
	private Designation designatingAction = Designation.NONE;

	private Map map;

	private BasicView view;

	private Timer timer;
	private int timeDelta = 1000;

	public void togglePaused() {
		if (isPaused()) {
			startTimer();
		} else {
			stopTimer();
		}
	}

	public void setDesignatingAction(Designation designation) {
		this.designatingAction = designation;
	}

	public Designation getDesignatingAction() {
		return designatingAction;
	}

	public void applyDesignation(int startRow, int startCol, int height,
			int width) {
		for (int row = startRow; row <= startRow+height; row++) {
			for (int j = startCol; j <= startCol+width; j++) {
				int col = Math.floorMod(j, map.getTotalWidth());

				if (getDesignatingAction() == Designation.REMOVING_DESIGNATIONS) {
					map.getBuildingBlock(row, col).removeDesignation();
				}
				if (getDesignatingAction() == Designation.DIGGING) {
					if (map.getBuildingBlock(row, col).getID()
							.equals(EarthBlock.id)) {
						map.getBuildingBlock(row, col).addDesignation(
								Designation.DIGGING);
					}
				}
			}
		}
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

	public Controller(MapParameters mapParameters, Random random) {
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