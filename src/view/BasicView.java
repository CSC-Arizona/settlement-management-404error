package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.Controller;
import controller.Designation;
import images.ImageEnum;
import images.imageUtil;
import model.actors.Actor;
import model.actors.ConstructAction;
import model.actors.GatherAction;
import model.actors.PlayerControlledActor;
import model.actors.Position;
import model.building_blocks.AirBlock;
import model.building_blocks.BuildingBlock;
import model.furniture.Furniture;
import model.game.Game;
import model.game.Log;
import model.items.Item;
import model.map.Map;
import model.map.MapParameters;
import model.menus.ConstructMenu;
import model.room.BedRoom;
import model.room.EntertainmentRoom;
import model.room.FarmRoom;
import model.room.HorizontalTunnel;
import model.room.IncubationRoom;
import model.room.InfirmaryRoom;
import model.room.CraftingRoom;
import model.room.RoomEnum;
import model.room.StoreRoom;
import model.room.VerticalTunnel;

public class BasicView extends JPanel {

	private static final long serialVersionUID = -8807654664923090784L;
	private Box box;
	private JPanel guiPanel;
	private JPanel labelPanel;
	private JPanel buttonPanel;
	private JPanel logPanel;
	private JTextArea logText;
	private DrawingPanel drawingPanel;
	private JLabel timeLabel;
	private JLabel windowCoordinatesLabel;
	private JLabel mouseDescriptionLabel;
	private JLabel mouseCoordinatesLabel;

	private int mouseX;
	private int mouseY;

	private int mapWidth;
	private int mapHeight;
	private int windowWidth = 1000;
	private int windowHeight = 700;

	private int visibleWidth = 50;
	private int visibleHeight = 30;

	private int visibleCornerY = 35;
	private int visibleCornerX = (mapWidth - visibleWidth / 2);
	private int labelPanelHeight = 250;
	private int blockSizeY = (windowHeight - labelPanelHeight) / visibleHeight;
	private int blockSizeX = windowWidth / visibleWidth;

	private Controller controller;

	private PauseButton pauseButton;

	private JButton constructRoomButton;

	private DesignationButton cutDownTreeButton;
	private DesignationButton removeRoomButton;
	private DesignationButton fruitButton;
	private DesignationButton digButton;
	private DesignationButton plantsButton;
	private DesignationButton attackButton;
	private DesignationButton removeButton;
	private ArrayList<DesignationButton> buttons;

	private Point designationStart;
	private Point designationEnd;
	private int designationStartRow;
	private int designationStartCol;
	private int designationEndRow;
	private int designationEndCol;
	private boolean currentlyDrawingDesignation = false;

	private boolean currentlyPlacingRoom = false;
	private Point roomCorner;
	private RoomEnum room;
	private int roomWidth;
	private int roomHeight;
	private int roomX;
	private int roomY;

	public void setTimeLabel(int time, boolean paused) {
		if (paused) {
			timeLabel.setText("Time: " + time + " (paused)");
		} else {
			timeLabel.setText("Time: " + time);
		}
	}

	private void setWindowCoordinateLabel() {
		windowCoordinatesLabel.setText("Window coordinates: (" + visibleCornerY
				+ ", " + visibleCornerX + ")");
	}

	private void setMouseCoordinatesLabel() {
		mouseCoordinatesLabel.setText("Mouse coordinates: (" + mouseY + ", "
				+ mouseX + ")");
	}

	public void setMouseDescriptionLabel() {
		String mouseDescription = Game.getMap()
				.getBuildingBlock(mouseY, mouseX).toString();

		mouseDescription += "</html>";

		mouseDescriptionLabel.setText("<html>Selected: " + mouseDescription);
	}

	public BasicView(Controller controller, MapParameters mapParameters) {
		this.controller = controller;
		this.setVisible(true);
		this.mapWidth = mapParameters.mapWidth;
		this.mapHeight = mapParameters.mapHeight;

		guiPanel = new JPanel();
		guiPanel.setLayout(new GridLayout(1, 3));
		guiPanel.setPreferredSize(new Dimension(900, 200));
		this.add(guiPanel);

		addLabelPanel();
		addButtonPanel();
		addLogPanel();
		addDrawingPanel();

		this.addKeyListener(new MyKeyListener());

		for (ImageEnum e : ImageEnum.values()) {
			e.createBufferedImages(blockSizeY, blockSizeX);
		}

		repaint();
	}

	private void addLabelPanel() {
		labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		timeLabel = new JLabel();
		labelPanel.add(timeLabel);
		windowCoordinatesLabel = new JLabel();
		setWindowCoordinateLabel();
		labelPanel.add(windowCoordinatesLabel);
		mouseCoordinatesLabel = new JLabel();
		setMouseCoordinatesLabel();
		labelPanel.add(mouseCoordinatesLabel);
		mouseDescriptionLabel = new JLabel();
		setMouseDescriptionLabel();
		labelPanel.add(mouseDescriptionLabel);

		guiPanel.add(labelPanel);

	}

	private void addButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3, 3));

		pauseButton = new PauseButton(controller, this);

		constructRoomButton = new JButton(
				"<html><center>Construct room</center></html>");
		constructRoomButton.setFocusable(false);
		constructRoomButton.addActionListener(new ConstructionListener());

		buttons = new ArrayList<>();
		cutDownTreeButton = new DesignationButton(controller,
				Designation.CUTTING_DOWN_TREES, buttons);
		removeRoomButton = new DesignationButton(controller,
				Designation.REMOVING_ROOMS, buttons);
		fruitButton = new DesignationButton(controller,
				Designation.GATHERING_FRUIT, buttons);
		digButton = new DesignationButton(controller, Designation.DIGGING,
				buttons);
		plantsButton = new DesignationButton(controller,
				Designation.GATHERING_PLANTS, buttons);
		attackButton = new DesignationButton(controller, Designation.ATTACKING,
				buttons);
		removeButton = new DesignationButton(controller,
				Designation.REMOVING_DESIGNATIONS, buttons);

		buttonPanel.add(constructRoomButton);
		buttonPanel.add(pauseButton);
		buttonPanel.add(cutDownTreeButton);
		buttonPanel.add(removeRoomButton);
		buttonPanel.add(fruitButton);
		buttonPanel.add(digButton);
		buttonPanel.add(plantsButton);
		buttonPanel.add(attackButton);
		buttonPanel.add(removeButton);

		guiPanel.add(buttonPanel);
	}

	private void addLogPanel() {
		logPanel = new JPanel();
		logText = new JTextArea();
		logText.setEditable(false);
		logPanel.add(logText);
		guiPanel.add(logPanel);
	}

	public void setLogText(String text) {
		logText.setText(text);
	}

	private void addDrawingPanel() {
		drawingPanel = new DrawingPanel();
		drawingPanel.setPreferredSize(new Dimension(windowWidth,
				4 * windowHeight / 5));
		drawingPanel.addMouseMotionListener(new MyMotionListener());
		drawingPanel.addMouseListener(new MyMouseListener());
		this.add(drawingPanel);
	}

	private class DrawingPanel extends JPanel {

		private static final long serialVersionUID = 8717360204333004762L;

		private void setVisibleTiles(int row, int col) {
			if (Game.getMap().getBuildingBlock(row, col).isOccupiable()) {
				for (int k = -1; k < 2; k++) {
					for (int l = -1; l < 2; l++) {
						int newRow = row + k;
						int newCol = col + l;
						newCol = Math.floorMod(newCol, Game.getMap()
								.getTotalWidth());
						if (newRow >= 0
								&& newRow < Game.getMap().getTotalHeight()) {

							Game.getMap().getBuildingBlock(newRow, newCol)
									.setVisibility(true);
						}
					}
				}
			}
		}

		private void drawBuildingBlock(Graphics2D g2, int row, int col, int i,
				int j) {
			if (Game.getMap().getBuildingBlock(row, col).getImage() == null) {
				Color color = Game.getMap().getBuildingBlock(row, col)
						.getColor();
				g2.setColor(color);
				g2.fillRect(j * blockSizeX, i * blockSizeY, blockSizeX,
						blockSizeY);

			} else {
				Color bgcolor = Game.getMap().getBuildingBlock(row, col)
						.getBackgroundColor();
				if (bgcolor != null) {
					g2.setColor(bgcolor);
					g2.fillRect(j * blockSizeX, i * blockSizeY, blockSizeX,
							blockSizeY);

				}

				BufferedImage img = Game.getMap().getBuildingBlock(row, col)
						.getImage().getRandomBufferedImage();
				g2.drawImage(img, j * blockSizeX, i * blockSizeY, null);

			}
		}

		private void drawActors(Graphics2D g2, int row, int col, int i, int j) {
			List<Actor> actors = Game.getMap().getBuildingBlock(row, col)
					.getActors();
			if (actors != null) {
				int count = 0;

				for (Actor actor : actors) {
					if (actor.getImage() != null) {
						g2.drawImage(actor.getImage().getRandomBufferedImage(),
								j * blockSizeX, i * blockSizeY, null);
					} else {
						count += 1;
					}
				}
				if (count != 0) {
					g2.setColor(Color.RED);
					g2.drawString(Integer.toString(count), j * blockSizeX
							+ blockSizeX / 2, (i + 1) * blockSizeY);
					g2.setColor(Color.BLACK);
				}
			}
		}

		private void drawFurniture(Graphics2D g2, int row, int col, int i, int j) {
			Furniture furniture = Game.getMap().getBuildingBlock(row, col)
					.getFurniture();
			if (furniture != null) {
				ImageEnum furnitureType = furniture.getImage();
				BufferedImage furnitureIcon = null;
				if (furnitureType != null)
					furnitureIcon = furniture.getImage()
							.getRandomBufferedImage();
				if (furnitureIcon != null)
					g2.drawImage(furnitureIcon, j * blockSizeX, i * blockSizeY,
							null);
				else
					g2.drawString("f", j * blockSizeX + blockSizeX / 2, (i + 1)
							* blockSizeY);
			}
		}

		private void drawItemsOnGround(Graphics2D g2, int row, int col, int i,
				int j) {
			List<Item> itemsOnGround = Game.getMap().getBuildingBlock(row, col)
					.itemsOnGround();
			if (itemsOnGround != null) {
				if (itemsOnGround.size() != 0) {
					g2.drawString("#", j * blockSizeX + blockSizeX / 2, (i + 1)
							* blockSizeY);
				}
			}
		}

		private void drawDesignation(Graphics2D g2, int row, int col, int i,
				int j) {
			if (Game.getMap().getBuildingBlock(row, col).isDesignated()) {
				g2.setColor(Color.BLACK);
				g2.drawString(""
						+ Game.getMap().getBuildingBlock(row, col)
								.getDesignation().keyboardShortcut, j
						* blockSizeX + blockSizeX / 2, (i + 1) * blockSizeY);
			}
		}

		private void drawTile(Graphics2D g2, int row, int col, int i, int j) {

			if (Game.getMap().getBuildingBlock(row, col).getVisibility()) {

				drawBuildingBlock(g2, row, col, i, j);

				drawActors(g2, row, col, i, j);

				drawFurniture(g2, row, col, i, j);

				drawItemsOnGround(g2, row, col, i, j);

			} else {
				g2.setColor(Color.black);
				g2.fillRect(j * blockSizeX, i * blockSizeY, blockSizeX,
						blockSizeY);
			}

			if (currentlyDrawingDesignation) {

				g2.drawRect(Math.min(designationStart.x, designationEnd.x),
						Math.min(designationStart.y, designationEnd.y),
						Math.abs(designationStart.x - designationEnd.x),
						Math.abs(designationStart.y - designationEnd.y));

			}
			if (currentlyPlacingRoom) {
				if (room.toString().equals("Vertical tunnel") || room.toString().equals("Horizontal tunnel")) {
				    g2.drawRect(roomCorner.x, roomCorner.y, roomWidth, roomHeight);
				} else {
					g2.drawRect(roomCorner.x, roomCorner.y, roomWidth, (roomHeight * 2));
				}
			}

			drawDesignation(g2, row, col, i, j);

		}

		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			Graphics2D g2 = (Graphics2D) g;

			for (int i = 0; i < visibleHeight; i++) {
				int row = visibleCornerY + i;
				for (int j = 0; j < visibleWidth; j++) {
					int col = visibleCornerX + j;
					col = Math.floorMod(col, mapWidth);

					setVisibleTiles(row, col);
					drawTile(g2, row, col, i, j);
				}
			}

		}
	}

	private class MyKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_UP:
				if (visibleCornerY != 0) {
					visibleCornerY -= 1;
				}
				break;
			case KeyEvent.VK_DOWN:
				if (visibleCornerY != mapHeight - visibleHeight) {
					visibleCornerY += 1;
				}
				break;
			case KeyEvent.VK_LEFT:
				visibleCornerX -= 1;
				if (visibleCornerX < 0) {
					visibleCornerX = Math.floorMod(visibleCornerX, mapWidth);
				}
				break;
			case KeyEvent.VK_RIGHT:
				visibleCornerX += 1;
				if (visibleCornerX >= mapWidth) {
					visibleCornerX = 0;
				}

				break;

			case KeyEvent.VK_SPACE:
				pauseButton.toggle();
				break;

			}

			for (DesignationButton button : buttons) {
				if (button.designation.keyboardShortcut == (char) e
						.getKeyChar()) {
					button.toggle();
					break;
				}
			}

			setWindowCoordinateLabel();
			repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class MyMotionListener implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			Point coords = e.getPoint();

			mouseX = coords.x / blockSizeX + visibleCornerX;
			mouseX = Math.floorMod(mouseX, mapWidth);
			mouseY = coords.y / blockSizeY + visibleCornerY;

			setMouseDescriptionLabel();
			setMouseCoordinatesLabel();

			if (currentlyDrawingDesignation) {
				designationEnd = e.getPoint();
				repaint();
			}
			if (currentlyPlacingRoom) {
				roomCorner = e.getPoint();
				roomX = mouseX;
				roomY = mouseY;
				repaint();
			}

		}
	}

	private class MyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			if (currentlyPlacingRoom) {
				boolean canBuildHere = true;
				BuildingBlock obstacle = new AirBlock();
				for (int r = roomX; r < roomX + room.getWidth(); r++) {
					for (int c = roomY; c < roomY + room.getHeight(); c++) {
						int x = Math.floorMod(c, mapHeight);
						int y = Math.floorMod(r, mapWidth);
						BuildingBlock inQuestion = Game.getMap()
								.getBuildingBlock(x, y);
						if (!inQuestion.isDestroyable()) {
							canBuildHere = false;
							obstacle = inQuestion;
							break;
						}
					}
				}
				if (canBuildHere) {
					currentlyPlacingRoom = false;
					controller.setDesignatingAction(Designation.CONSTRUCTING);

					controller.applyDesignation(roomY, roomX, roomHeight
							/ blockSizeY, roomWidth / blockSizeX);

					PlayerControlledActor.playerActionPool
							.add(new ConstructAction(
									room.constructObject(new Position(roomY,
											roomX))));

					controller.setDesignatingAction(Designation.NONE);

					repaint();
				} else {
					String err = "";
					if (obstacle.getClass().equals(new AirBlock().getClass()))
						err = "You can't build a room above ground.";
					else
						err = "You can't build a room over a "
								+ obstacle.getID() + " block.";
					Log.add(err);
				}

			} else {

				if (controller.getDesignatingAction() != Designation.NONE) {
					if (currentlyDrawingDesignation) {
						designationEnd = e.getPoint();

						designationEndCol = designationEnd.x / blockSizeX
								+ visibleCornerX;
						designationEndRow = designationEnd.y / blockSizeY
								+ visibleCornerY;

						int startRow = Math.min(designationStartRow,
								designationEndRow);
						int startCol = Math.min(designationStartCol,
								designationEndCol);
						int height = Math.abs(designationStartRow
								- designationEndRow);
						int width = Math.abs(designationStartCol
								- designationEndCol);

						controller.applyDesignation(startRow, startCol, height,
								width);

						repaint();
					} else {
						designationStart = e.getPoint();

						designationStartCol = designationStart.x / blockSizeX
								+ visibleCornerX;
						designationStartRow = designationStart.y / blockSizeY
								+ visibleCornerY;

						designationEnd = e.getPoint();
					}
					currentlyDrawingDesignation = !currentlyDrawingDesignation;
				} else {
					// test if clicked on a crafting room
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class ConstructionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!currentlyDrawingDesignation) {
				currentlyPlacingRoom = true;
				roomCorner = new Point(0, 0);
				if (!controller.isPaused()) {
					pauseButton.toggle();
				}

				String[] roomNames = RoomEnum.getAllRoomNames();

				String roomChoice = (String) JOptionPane.showInputDialog(
						controller, "Choose a room to construct", "",
						JOptionPane.PLAIN_MESSAGE, null, roomNames,
						roomNames[0]);

				if (roomChoice != null) {
					room = RoomEnum.getRoomFromString(roomChoice);
					roomHeight = room.getHeight() * blockSizeY - 1;
					roomWidth = room.getWidth() * blockSizeX - 1;
				} else {
					currentlyPlacingRoom = false;
				}
				if (controller.isPaused()) {
					pauseButton.toggle();
				}
			}

		}

	}

}
