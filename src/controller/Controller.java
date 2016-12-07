package controller;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.actors.Actor;
import model.actors.AttackAction;
import model.actors.EnemyActor;
import model.actors.GatherAction;
import model.actors.PickUpItemAction;
import model.actors.PlayerControlledActor;
import model.actors.Position;
import model.building_blocks.AntTunnelBlock;
import model.building_blocks.AnthillBlock;
import model.building_blocks.AntimatterDefenestratorBlock;
import model.building_blocks.AppleTreeLeafBlock;
import model.building_blocks.AppleTreeTrunkBlock;
import model.building_blocks.BuildingBlock;
import model.building_blocks.EarthBlock;
import model.building_blocks.GrassBlock;
import model.furniture.Furniture;
import model.game.Game;
import model.game.Log;
import model.game.Settings;
import model.items.Item;
import model.items.WheatKernelItem;
import model.map.AppleTree;
import model.map.Map;
import model.map.MapParameters;
import model.room.FarmRoom;
import model.save.SaveFile;
import view.AlternativeView;
import view.BasicView;
import view.StartingView;

/**
 * Display a map
 * 
 * @author Ethan Ward
 *
 */
public class Controller extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6949081386517721969L;
	int time = 0;
	private boolean paused = false;
	private Designation designatingAction = Designation.NONE;

	private Map map;
	private MapParameters mapParameters;
	private Random random;

	private StartingView startingView;
	private AlternativeView basicView;

	private Timer gameTimer;
	private int timeDelta = 100;

	private int windowWidth = 1000;
	private int windowHeight = 700;
	private Controller thisController;

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

				// todo: attack, remove rooms

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

				if (getDesignatingAction() == Designation.ATTACKING) {
					if (map.getBuildingBlock(row, col).getActors() != null) {
						for (Actor actor : map.getBuildingBlock(row, col)
								.getActors()) {
							if (EnemyActor.allActors.contains(actor)) {
								PlayerControlledActor.playerActionPool
										.add(new AttackAction(actor));
							}
						}
					}
				}

				if (getDesignatingAction() == Designation.CONSTRUCTING) {
					map.getBuildingBlock(row, col).addDesignation(
							Designation.CONSTRUCTING);
				}

				if (getDesignatingAction() == Designation.UPGRADING) {
					map.getBuildingBlock(row, col).addDesignation(
							Designation.UPGRADING);
				}

				if (getDesignatingAction() == Designation.DIGGING) {
					String bbID = map.getBuildingBlock(row, col).getID();
					if ((bbID.equals(AntTunnelBlock.id)
							|| bbID.equals(AnthillBlock.id)
							|| bbID.equals(EarthBlock.id) || bbID
								.equals(AntimatterDefenestratorBlock.id))) {
						if (!(map.getBuildingBlock(row, col).getDesignation() == Designation.CONSTRUCTING)) {
							map.getBuildingBlock(row, col).addDesignation(
									Designation.DIGGING);
							PlayerControlledActor.playerActionPool
									.add(new GatherAction(
											new Position(row, col)));
						} else {
							System.out
									.println("Can designate this for digging.");
						}
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
		if (gameTimer != null) {
			this.gameTimer.cancel();
		}
		paused = true;
	}

	public void startTimer() {
		gameTimer = new Timer();
		gameTimer.schedule(new GameTimerTask(), 0, timeDelta);
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
		new SongPlayer().start();
		if (skipLoadScreen) {
			startNewGame(new Settings(Settings.MEDIUM, Settings.NORMAL));
		} else {
			startingView = new StartingView(this);
			this.add(startingView);
			this.revalidate();
		}
		thisController = this;
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
	public void startNewGame(Settings settings) {
		this.saveFile = new SaveFile();
		Game.reset();
		map = new Map(MapParameters.getCustumMapParameters(settings), random);
		Game.setMap(map);
		Log.add("Welcome");
		setUpMap();
	}

	private void setUpMap() {
		getContentPane().removeAll();
		basicView = new AlternativeView(this, mapParameters);
		this.add(basicView);

		startTimer();

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowCloser());

		basicView.setFocusable(true);
		basicView.setRequestFocusEnabled(true);
		basicView.grabFocus();

	}

	private class GameTimerTask extends TimerTask {

		@Override
		public void run() {
			time += 1;
			Game.getMap().updateActors(timeDelta);
			if (time % 100 == 0) {
				Game.getMap().regrowTrees();
			}
			Game.getMap().setTime(time);
			if (PlayerControlledActor.allActors != null
					&& PlayerControlledActor.allActors.size() <= 0) {
				if (!isPaused())
					togglePaused();
				final JOptionPane pane = new JOptionPane(
						"All of your settlers are dead!");
				final JDialog d = pane.createDialog((JFrame) null, "You Lose!");
				d.setLocation(400, 300);
				d.setVisible(true);
				getContentPane().removeAll();
				startingView = new StartingView(thisController);
				SongPlayer.setNewSong(SongPlayer.MAIN);
				add(startingView);
				revalidate();
				repaint();
			}

			updateFarmRooms();
			basicView.updateLog();
			basicView.setTimeLabel(time, paused);
			basicView.setMouseDescriptionLabel();
			basicView.repaint();

		}

		private void updateFarmRooms() {
			TreeMap<Position, FarmRoom> allFarmRooms = Game.getMap()
					.getFarmRooms();
			Iterator<FarmRoom> it = allFarmRooms.values().iterator();
			// Only increment state if greater than 0
			while (it.hasNext()) {
				FarmRoom fr = it.next();
				if (fr.getState() > 0) {
					fr.advanceState();
					// Harvest if ready
					if (fr.getState() >= 200) {
						List<Item> yield = fr.harvest();
						// Drop items on ground
						// Drop items at position of wheat plot, not at corner
						// of room
						Position positionOfRoom = fr.getPosition();
						int newRow = positionOfRoom.getRow() + 2;
						int newCol = positionOfRoom.getCol()
								+ (FarmRoom.getWidth() - 2);
						Position plotPosition = new Position(newRow, newCol);
						BuildingBlock wherePlotIs = Game.getMap()
								.getBuildingBlock(plotPosition);
						// Issue pick up item command for each item dropped
						for (Item curr : yield) {
							wherePlotIs.addItemToGround(curr);
							// TODO: Make sure item is actually being added to
							// itemsOnGround arrayList
							// TODO: Need to remove item from ground when picked
							// up
							Game.getMap().addItemToGround(plotPosition, curr);
							PlayerControlledActor
									.addActionToPlayerPool(new PickUpItemAction(
											plotPosition, curr));
						}
						// Remove seed from furniture
						Furniture plot = wherePlotIs.getFurniture();
						plot.removeItem(new WheatKernelItem());

					}
				}
				// it.remove(); //This is so that we don't get a
				// ConcurrentModificationException
				// Commented out previous line because it was removing room from
				// Map's list of farm rooms
			}
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
