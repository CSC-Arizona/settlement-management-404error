package controller;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.actors.ConstructAction;
import model.actors.GatherAction;
import model.actors.PlayerControlledActor;
import model.actors.Position;
import model.building_blocks.AppleTreeLeafBlock;
import model.building_blocks.AppleTreeTrunkBlock;
import model.building_blocks.EarthBlock;
import model.building_blocks.GrassBlock;
import model.game.Game;
import model.game.Log;
import model.map.AppleTree;
import model.map.Map;
import model.map.MapParameters;
import model.save.SaveFile;
import view.BasicView;
import view.StartingView;

/**
 * Display a map
 * 
 * @author Ethan Ward
 *
 */
public class Controller extends JFrame {

	int time = 0;
	private boolean paused = false;
	private Designation designatingAction = Designation.NONE;

	private Map map;
	private MapParameters mapParameters;
	private Random random;

	private StartingView startingView;
	private BasicView basicView;

	private Timer timer;
	private int timeDelta = 100;

	private int windowWidth = 1000;
	private int windowHeight = 700;

	private SaveFile saveFile;
	
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
		for (int row = startRow; row <= startRow + height; row++) {
			for (int j = startCol; j <= startCol + width; j++) {
				int col = Math.floorMod(j, map.getTotalWidth());

				// todo: attack, remove rooms, remove furniture

				if (getDesignatingAction() == Designation.REMOVING_DESIGNATIONS) {
					map.getBuildingBlock(row, col).removeDesignation();

					if (map.getBuildingBlock(row, col).getID()
							.equals(AppleTreeTrunkBlock.id)) {
						for (Position pos : map.getTrees().keySet()) {
							if (pos.getRow() == row && pos.getCol() == col) {
								AppleTree tree = map.getTrees().get(pos);
								tree.removeDesignation();
								break;
							}
						}
					}
				}
				
				if (getDesignatingAction() == Designation.CONSTRUCTING) {
					map.getBuildingBlock(row, col).addDesignation(
							Designation.CONSTRUCTING);
				}

				if (getDesignatingAction() == Designation.DIGGING) {
					if (map.getBuildingBlock(row, col).getID()
							.equals(EarthBlock.id)) {
						map.getBuildingBlock(row, col).addDesignation(
								Designation.DIGGING);
						PlayerControlledActor.playerActionPool
								.add(new GatherAction(new Position(row, col)));
					}
				}

				if (getDesignatingAction() == Designation.GATHERING_PLANTS) {
					if (map.getBuildingBlock(row, col).getID()
							.equals(GrassBlock.id)) {
						map.getBuildingBlock(row, col).addDesignation(
								Designation.GATHERING_PLANTS);
						PlayerControlledActor.playerActionPool
								.add(new GatherAction(new Position(row, col)));

					}

				}

				if (getDesignatingAction() == Designation.GATHERING_FRUIT) {
					if (map.getBuildingBlock(row, col).getID()
							.equals(AppleTreeLeafBlock.id)) {
						map.getBuildingBlock(row, col).addDesignation(
								Designation.GATHERING_FRUIT);
					}
				}

				if (getDesignatingAction() == Designation.CUTTING_DOWN_TREES) {
					if (map.getBuildingBlock(row, col).getID()
							.equals(AppleTreeTrunkBlock.id)) {
						for (Position pos : map.getTrees().keySet()) {
							if (pos.getRow() == row && pos.getCol() == col) {
								AppleTree tree = map.getTrees().get(pos);
								tree.designate();
								PlayerControlledActor.playerActionPool
										.add(new GatherAction(new Position(row,
												col)));
								break;
							}
						}
					}
				}

			}
		}
	}

	public void stopTimer() {
		if (timer != null) {
			this.timer.cancel();
		}
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

	public Controller(MapParameters mapParameters, Random random,
			boolean skipLoadScreen) {
		this.mapParameters = mapParameters;
		this.random = random;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocation(20, 20);
		this.setPreferredSize(new Dimension(windowWidth, windowHeight));
		this.setSize(new Dimension(windowWidth, windowHeight));

		this.setVisible(true);

		if (skipLoadScreen) {
			startNewGame();
		} else {

			startingView = new StartingView(this);
			this.add(startingView);
			this.revalidate();
		}
	}

	public void loadGame(SaveFile saveFile) {
		this.saveFile = saveFile;
		this.saveFile.load(this.saveFile.getSavename());
		time = Game.getMap().getTime();
		setUpMap();
		
	}

	/**
	 * Start a new game from scratch
	 */
	public void startNewGame() {
		this.saveFile = new SaveFile();

		map = new Map(mapParameters, random);
		Game.reset();
		Game.setMap(map);
		Log.add("Welcome");
		setUpMap();
	}

	private void setUpMap() {
		getContentPane().removeAll();
		basicView = new BasicView(this, mapParameters);
		this.add(basicView);

		startTimer();

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowCloser());
		
		basicView.setFocusable(true);
		basicView.setRequestFocusEnabled(true);
		basicView.grabFocus();
        
	}

	private class MyTimerTasks extends TimerTask {

		@Override
		public void run() {
			time += 1;
			basicView.setTimeLabel(time, paused);
			basicView.setMouseDescriptionLabel();
			basicView.setLogText(Log.getLog());
			Game.getMap().updateActors(timeDelta);
			Game.getMap().setTime(time);
			basicView.repaint();
		}
	}

	public Map getMap() {
		return map;
	}

	private class WindowCloser implements WindowListener {

		@Override
		public void windowClosed(WindowEvent e) {
		}

		@Override
		public void windowOpened(WindowEvent e) {
		}

		@Override
		public void windowClosing(WindowEvent e) {
			stopTimer();

			int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;

			int response = JOptionPane.showConfirmDialog(null,
					"Do you want to save the game?", "Warning", dialogButton);

			if (response == 0) {
				// yes
				saveFile.save();
				System.exit(0);
				Game.reset();
			} else if (response == 1) {
				// no
				System.exit(0);
				Game.reset();
			} else {
				startTimer();
			}
		}

		@Override
		public void windowIconified(WindowEvent e) {
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
		}

		@Override
		public void windowActivated(WindowEvent e) {
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
		}
	}

}