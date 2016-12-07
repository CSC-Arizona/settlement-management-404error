package controller;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.actors.GatherAction;
import model.actors.PickUpItemAction;
import model.actors.PlayerControlledActor;
import model.actors.Position;
import model.building_blocks.AirBlock;
import model.building_blocks.AntTunnelBlock;
import model.building_blocks.AnthillBlock;
import model.building_blocks.AntimatterDefenestratorBlock;
import model.building_blocks.AppleTreeLeafBlock;
import model.building_blocks.AppleTreeTrunkBlock;
import model.building_blocks.BuildingBlock;
import model.building_blocks.EarthBlock;
import model.building_blocks.FarmRoomBlock;
import model.building_blocks.GrassBlock;
import model.furniture.Furniture;
import model.game.Game;
import model.game.Log;
import model.items.DragonEggItem;
import model.items.Item;
import model.items.WheatKernelItem;
import model.game.Settings;
import model.map.AppleTree;
import model.map.Map;
import model.map.MapParameters;
import model.room.FarmRoom;
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
	private BasicView basicView;

	private Timer gameTimer;
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
		new SongPlayer().run();
		if (skipLoadScreen) {
			startNewGame(new Settings(Settings.MEDIUM,Settings.NORMAL));
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
		basicView = new BasicView(this, mapParameters);
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
			basicView.updateLog();
			Game.getMap().updateActors(timeDelta);
			if (time % 100 == 0) {
				Game.getMap().regrowTrees();
			}
			Game.getMap().setTime(time);
			basicView.setTimeLabel(time, paused);
			basicView.setMouseDescriptionLabel();
			basicView.repaint();

			updateFarmRooms();
				
			}

		private void updateFarmRooms() {
			TreeMap<Position, FarmRoom> allFarmRooms = Game.getMap().getFarmRooms();
			Iterator<FarmRoom> it = allFarmRooms.values().iterator();
			//Only increment state if greater than 0
			while(it.hasNext()) {
				FarmRoom fr = it.next();
				if(fr.getState() > 0) {
					fr.advanceState();
					//Harvest if ready
					if(fr.getState() >= 200) {
						List<Item> yield = fr.harvest();
						//Drop items on ground
						//Drop items at position of wheat plot, not at corner of room
						Position positionOfRoom = fr.getPosition();
						int newRow = positionOfRoom.getRow() + 2;
						int newCol = positionOfRoom.getCol() + (FarmRoom.getWidth()-2);
						Position plotPosition = new Position(newRow, newCol);
						BuildingBlock wherePlotIs = Game.getMap().getBuildingBlock(plotPosition);
						//Issue pick up item command for each item dropped
						for(Item curr : yield) {
							wherePlotIs.addItemToGround(curr);
							//TODO: Make sure item is actually being added to itemsOnGround arrayList
							//TODO: Need to remove item from ground when picked up
							Game.getMap().addItemToGround(plotPosition, curr);
							PlayerControlledActor.addActionToPlayerPool(new PickUpItemAction(plotPosition, curr));
						}
						//Remove seed from furniture
						Furniture plot = wherePlotIs.getFurniture();
						plot.removeItem(new WheatKernelItem());
						
					}
				}
				//it.remove(); //This is so that we don't get a ConcurrentModificationException
				//Commented out previous line because it was removing room from Map's list of farm rooms
			}
		}
	}

	public Map getMap() {
		return map;
	}
	
	private class SongPlayer implements Runnable{

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/sounds/song.wav").getAbsoluteFile());
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
