package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.Controller;
import controller.Designation;
import images.ImageEnum;
import model.actors.Actor;
import model.actors.PlayerControlledActor;
import model.actors.Position;
import model.actors.UpgradeRoomAction;
import model.building_blocks.AirBlock;
import model.building_blocks.BuildingBlock;
import model.furniture.Furniture;
import model.game.Game;
import model.game.Log;
import model.items.Item;
import model.map.MapParameters;
import model.room.Room;
import model.room.RoomEnum;

public class AlternativeView extends JPanel {

	private static final long serialVersionUID = -8807654664923090784L;
	private JPanel labelPanel;
	private JPanel buttonPanel;
	private JPanel logPanel;
	private JTextArea logText;
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
	private int visibleHeight = 50;

	private int visibleCornerY = 35;
	private int visibleCornerX = (mapWidth - visibleWidth / 2);
	private int blockSizeY = windowHeight / visibleHeight;
	private int blockSizeX = windowWidth / visibleWidth;

	private Controller controller;
	

	private customDesignationButton cutDownTreeButton;
	private customDesignationButton digButton;
	private customDesignationButton plantsButton;
	private customDesignationButton attackButton;
	private ArrayList<customDesignationButton> buttons;

	private Point designationStart;
	private Point designationEnd;
	private int designationStartRow;
	private int designationStartCol;
	private int designationEndRow;
	private int designationEndCol;
	private boolean currentlyDrawingDesignation = false;

	private boolean currentlyPlacingRoom = false;
	private boolean currentlyChoosingUpgrade = false;
	private TreeMap<Position, Room> highlightMe = new TreeMap<>();
	private Point roomCorner;
	private RoomEnum room;
	private int roomWidth;
	private int roomHeight;
	private int roomX;
	private int roomY;

	private JComboBox<String> constructRoomComboBox;
	private JButton constructRoomButton;
	private JButton upgradeRoomButton;
	private JComboBox<String> craftComboBox;
	private JButton craftButton;

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

	public AlternativeView(Controller controller, MapParameters mapParameters) {
		this.controller = controller;
		this.setVisible(true);
		this.mapWidth = mapParameters.mapWidth;
		this.mapHeight = mapParameters.mapHeight;
		this.setLayout(null);

		addLabelPanel();
		addButtonPanel();
		addLogPanel();

		this.addKeyListener(new MyKeyListener());
		this.addMouseMotionListener(new MyMotionListener());
		this.addMouseListener(new MyMouseListener());

		for (ImageEnum e : ImageEnum.values()) {
			e.createBufferedImages(blockSizeY, blockSizeX);
		}
		repaint();
	}

	private void addLabelPanel() {
		labelPanel = new JPanel();
		labelPanel.setSize(300, 300);
		labelPanel.setBackground(new Color(125, 50, 50, 50));
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		timeLabel = new JLabel();
		labelPanel.add(timeLabel);
		// timeLabel.
		windowCoordinatesLabel = new JLabel();
		setWindowCoordinateLabel();
		labelPanel.add(windowCoordinatesLabel);
		mouseCoordinatesLabel = new JLabel();
		setMouseCoordinatesLabel();
		labelPanel.add(mouseCoordinatesLabel);
		mouseDescriptionLabel = new JLabel();
		setMouseDescriptionLabel();
		labelPanel.add(mouseDescriptionLabel);
		// labelPanel.setOpaque(true);
		// labelPanel.setBackground(new Color(0, 0, 0, 50));
		this.add(labelPanel);

	}

	private void addButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setSize(500, 70);
		buttonPanel.setBackground(new Color(0, 0, 0, 0));
		buttonPanel.setBounds(250, 600, 330, 70);

		constructRoomComboBox = new JComboBox<String>(
				RoomEnum.getAllRoomNames());
		constructRoomComboBox
				.addActionListener(new ConstructionComboBoxListener());
		constructRoomComboBox.setFocusable(false);
		constructRoomComboBox.setPreferredSize(new Dimension(100, 30));
		constructRoomComboBox.setFont(new Font("Arial", Font.PLAIN, 10));
		upgradeRoomButton = new JButton("<html><center>Upgrade rooms (u)</center></html>");
		upgradeRoomButton.setFocusable(false);
		upgradeRoomButton.addActionListener(new UpdateButtonListener());
		buttonPanel.add(constructRoomComboBox);

		buttons = new ArrayList<>();
		constructRoomButton = new customDesignationButton(controller, this,
				Designation.CONSTRUCTING, buttons);
		constructRoomButton.addActionListener(new ConstructionButtonListener());
		cutDownTreeButton = new customDesignationButton(controller, this,
				Designation.CUTTING_DOWN_TREES, buttons);
		digButton = new customDesignationButton(controller, this,
				Designation.DIGGING, buttons);
		plantsButton = new customDesignationButton(controller, this,
				Designation.GATHERING_PLANTS, buttons);
		attackButton = new customDesignationButton(controller, this,
				Designation.ATTACKING, buttons);

		craftComboBox = new JComboBox<String>(new String[] { "item 1",
				"item 2", "item 3" });
		craftButton = new JButton("Craft item: ");

		buttonPanel.add(cutDownTreeButton);
		buttonPanel.add(upgradeRoomButton);
		buttonPanel.add(digButton);
		buttonPanel.add(plantsButton);
		buttonPanel.add(attackButton);
		buttonPanel.add(constructRoomButton);
		buttonPanel.add(constructRoomComboBox);
		buttonPanel.add(craftButton);
		buttonPanel.add(craftComboBox);

		this.add(buttonPanel);
	}

	private void addLogPanel() {
		logPanel = new JPanel();
		logPanel.setSize(350, 200);
		logPanel.setBackground(new Color(125, 50, 50, 50));
		logText = new JTextArea();
		logText.setLineWrap(true);
		logText.setSize(350, 200);
		logPanel.setBounds(650, 0, 350, 200);
		logText.setEditable(false);
		logPanel.add(logText);
		this.add(logPanel);
	}

	public void setLogText(String text) {
		logText.setText(text);
	}

	private void setVisibleTiles(int row, int col) {
		if (Game.getMap().getBuildingBlock(row, col).isOccupiable()
				|| Game.getMap().getBuildingBlock(row, col).getID()
						.equals("Room wall")) {
			for (int k = -1; k < 2; k++) {
				for (int l = -1; l < 2; l++) {
					int newRow = row + k;
					int newCol = col + l;
					newCol = Math.floorMod(newCol, Game.getMap()
							.getTotalWidth());
					if (newRow >= 0 && newRow < Game.getMap().getTotalHeight()) {

						Game.getMap().getBuildingBlock(newRow, newCol)
								.setVisibility(true);
					}
				}
			}
		}
	}

	private void drawBuildingBlock(Graphics2D g2, int row, int col, int i, int j) {
		if (Game.getMap().getBuildingBlock(row, col).getImage() == null) {
			Color color = Game.getMap().getBuildingBlock(row, col).getColor();
			g2.setColor(color);
			g2.fillRect(j * blockSizeX, i * blockSizeY, blockSizeX, blockSizeY);

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
			List<Actor> actorCopy = new ArrayList<>(actors);
			int count = 0;
			Iterator<Actor> iter = actorCopy.iterator();
			while (iter.hasNext()) {
				Actor p = iter.next();
				if (p.getImage() != null && p.isAlive()) {
					g2.drawImage(p.getImage().getRandomBufferedImage(), j
							* blockSizeX, i * blockSizeY, null);
					if (p.isMarkedForAttack()) {
						g2.setColor(Color.RED);
						g2.drawString("A", j * blockSizeX + blockSizeX / 2,
								(i + 1) * blockSizeY);
						g2.setColor(Color.BLACK);
					}
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
			List<PlayerControlledActor> playerActors = PlayerControlledActor.allActors;
			Iterator<PlayerControlledActor> playerIter = playerActors
					.iterator();
			while (playerIter.hasNext()) {
				PlayerControlledActor p = playerIter.next();
				if (p.getPosition().equals(new Position(row, col))) {
					if (p.isHungry()) {
						g2.drawImage(ImageEnum.HUNGER.getRandomBufferedImage(),
								j * blockSizeX, (i - 1) * blockSizeY, null);
					} else if (p.isTired()) {
						g2.drawImage(ImageEnum.TIRED.getRandomBufferedImage(),
								(j - 1) * blockSizeX, (i - 1) * blockSizeY,
								null);
					} else if (p.isHurt()) {
						g2.drawImage(
								ImageEnum.BANDAGE.getRandomBufferedImage(),
								(j - 1) * blockSizeX, (i - 1) * blockSizeY,
								null);
					}
				}
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
				furnitureIcon = furniture.getImage().getRandomBufferedImage();
			if (furnitureIcon != null)
				g2.drawImage(furnitureIcon, j * blockSizeX, i * blockSizeY,
						null);
			else
				g2.drawString("f", j * blockSizeX + blockSizeX / 2, (i + 1)
						* blockSizeY);
		}
	}

	private void drawItemsOnGround(Graphics2D g2, int row, int col, int i, int j) {
		List<Item> itemsOnGround = Game.getMap().getBuildingBlock(row, col)
				.itemsOnGround();
		if (itemsOnGround != null) {
			if (itemsOnGround.size() != 0) {
				for (Item item : itemsOnGround) {
					if (item.getImage() != null) {
						g2.drawImage(item.getImage().getRandomBufferedImage(),
								j * blockSizeX, i * blockSizeY, null);
					} else {
						g2.drawString("#", j * blockSizeX + blockSizeX / 2,
								(i + 1) * blockSizeY);
					}
				}

			}
		}
	}
	
	private void drawUpgradingHighlights(Graphics2D g2, int row, int col, int i, int j) {
		if (highlightMe.containsKey(new Position(row, col))) {
			g2.setColor(Color.GREEN);
			g2.fillRect(j * blockSizeX, i * blockSizeY, blockSizeX,
					blockSizeY);
		}
	}

	private void drawDesignation(Graphics2D g2, int row, int col, int i, int j) {
		if (Game.getMap().getBuildingBlock(row, col).isDesignated()) {
			g2.setColor(Color.WHITE);
			g2.drawString(
					""
							+ Game.getMap().getBuildingBlock(row, col)
									.getDesignation().keyboardShortcut, j
							* blockSizeX + blockSizeX / 2, (i + 1) * blockSizeY);
		}
	}

	private void drawTile(Graphics2D g2, int row, int col, int i, int j) {

		if (Game.getMap().getBuildingBlock(row, col).getVisibility()) {

			drawBuildingBlock(g2, row, col, i, j);

			drawFurniture(g2, row, col, i, j);

			drawActors(g2, row, col, i, j);

			drawItemsOnGround(g2, row, col, i, j);
			
			// TODO: add draw highlighted blocks here
			if (currentlyChoosingUpgrade)
				drawUpgradingHighlights(g2, row, col, i, j);

		} else {
			g2.setColor(Color.black);
			g2.fillRect(j * blockSizeX, i * blockSizeY, blockSizeX, blockSizeY);
		}

		if (currentlyDrawingDesignation) {
			g2.setColor(Color.WHITE);
			g2.drawRect(Math.min(designationStart.x, designationEnd.x),
					Math.min(designationStart.y, designationEnd.y),
					Math.abs(designationStart.x - designationEnd.x),
					Math.abs(designationStart.y - designationEnd.y));

		}
		if (currentlyPlacingRoom) {
			g2.setColor(Color.WHITE);
			if (room.toString().equals("Vertical tunnel")
					|| room.toString().equals("Horizontal tunnel")) {
				g2.drawRect(roomCorner.x, roomCorner.y, roomWidth, roomHeight);
			} else {
				g2.drawRect(roomCorner.x, roomCorner.y, roomWidth,
						(roomHeight * 2));
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
				controller.togglePaused();
				setTimeLabel(controller.getTime(), controller.isPaused());
				break;

			}

			if ((char) e.getKeyChar() == 'c') {
				for (customDesignationButton button : buttons) {
					button.deactivate();
				}
			} else {

				char keyboardSelection = '\u0000';
				boolean madeSelection = false;
				for (customDesignationButton button : buttons) {
					if (button.designation.keyboardShortcut == (char) e
							.getKeyChar()) {
						keyboardSelection = (char) e.getKeyChar();
						madeSelection = true;
						break;
					}
				}

				if (madeSelection) {

					for (customDesignationButton button : buttons) {
						if (button.designation.keyboardShortcut == keyboardSelection) {
							button.toggle();
						} else {
							button.deactivate();
						}
					}
				}
			}

			setWindowCoordinateLabel();
			repaint();

		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

	}

	private class MyMotionListener implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
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
			}
			if (currentlyPlacingRoom) {
				roomCorner = e.getPoint();
				roomX = mouseX;
				roomY = mouseY;
			}
			repaint();
		}
	}

	private class MyMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {

			// roomX = pixel location along x axis
			// roomY = pixel location along y axis
			if (currentlyPlacingRoom) {
				boolean canBuildHere = true;
				BuildingBlock obstacle = new AirBlock();
				// Position appropriatePileLoc = null;
				int blockHeight = room.getHeight();
				int pixHeight = roomHeight;
				if (!room.toString().equals("Vertical tunnel")
						&& !room.toString().equals("Horizontal tunnel")) {
					blockHeight *= 2;
					pixHeight *= 2;
				}
				for (int r = roomX; r < roomX + room.getWidth(); r++) {
					for (int c = roomY; c < roomY + blockHeight; c++) {
						int x = c; // x = row
						int y = Math.floorMod(r, mapWidth); // y = col
						BuildingBlock inQuestion = Game.getMap()
								.getBuildingBlock(x, y);
						if (!inQuestion.isDestroyable()) {
							canBuildHere = false;
							obstacle = inQuestion;
							break;
						}
						if (!inQuestion.getDesignation().equals(
								Designation.NONE)) {
							canBuildHere = false;
							obstacle = null; // not a room yet, the message
												// needs to be different
							break;
						}
					}
				}
				if (canBuildHere) {
					controller.setDesignatingAction(Designation.CONSTRUCTING);
					// adding the rows for room walls with every room type
					// except tunnels
					controller.applyDesignation(roomY, roomX, pixHeight
							/ blockSizeY, roomWidth / blockSizeX);
					// PlayerControlledActor.playerActionPool
					// .add(new ConstructAction(room.constructObject(new
					// Position(roomY, roomX))));
					Game.getMap().addNewDesignatedRoom(
							room.constructObject(new Position(roomY, roomX)));

					controller.setDesignatingAction(Designation.NONE);

					repaint();
				} else {
					String err = "";
					if (obstacle == null)
						err = "There is already a room under construction here.";
					else if (obstacle.getClass().equals(
							new AirBlock().getClass()))
						err = "You can't build a room above ground.";
					else
						err = "You can't build a room over a "
								+ obstacle.getID() + " block.";
					System.out.println(err);
					Log.add(err);
				}

			} else if (currentlyChoosingUpgrade) {
				Room room = highlightMe.get(new Position(mouseY, mouseX));
				if (room != null) {
					System.out.println("You have chosen to upgrade the " + room.getID() + " at " + room.getPosition());
					PlayerControlledActor.addActionToPlayerPool(new UpgradeRoomAction(room));
					deactivateUpgradeSelection();
				}
				else {
					System.out.println("You clicked on a space that wasn't a room.");
				    deactivateUpgradeSelection();
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
					
				}
			}
			repaint();
		}

	}

	private class customDesignationButton extends JButton {

		private static final long serialVersionUID = 3178671132433440884L;
		private Controller controller;
		public Designation designation;
		private boolean active;
		private boolean selectable;
		private ArrayList<customDesignationButton> buttons;

		public void activate() {
			controller.setDesignatingAction(designation);
			active = true;
		}

		public void deactivate() {
			active = false;
			currentlyDrawingDesignation = false;
			controller.setDesignatingAction(Designation.NONE);
			setButtonText();
		}

		public void toggle() {
			if (active) {
				deactivate();
			} else {
				activate();
			}
		}

		public void toggleSelected() {
			if (selectable) {
				this.setEnabled(true);
				selectable = false;
			} else {
				this.setEnabled(false);
				selectable = true;
			}
		}

		public boolean isActive() {
			return active;
		}

		public boolean constructionSelected() {
			return currentlyPlacingRoom;
		}

		public void toggleConstructionSelection() {
			if (currentlyPlacingRoom) {
				deactivateConstructionSelection();
			} else {
				activateConstructionSelection();
			}
		}

		public void activateConstructionSelection() {
			for (customDesignationButton button : buttons) {
				button.deactivate();
			}
			currentlyPlacingRoom = true;
			constructRoomButton.setBackground(new Color(124, 163, 226));
			controller.setDesignatingAction(Designation.CONSTRUCTING);

			String roomChoice = constructRoomComboBox.getSelectedItem()
					.toString();

			if (roomChoice != null) {
				room = RoomEnum.getRoomFromString(roomChoice);
				roomHeight = room.getHeight() * blockSizeY - 1;
				roomWidth = room.getWidth() * blockSizeX - 1;

				roomCorner = new Point(0, 0);
				roomX = 0;
				roomY = 0;

			}
			repaint();

		}

		public void deactivateConstructionSelection() {
			currentlyPlacingRoom = false;
			controller.setDesignatingAction(Designation.NONE);

			repaint();
		}

		public customDesignationButton(Controller controller,
				AlternativeView view, Designation designation,
				ArrayList<customDesignationButton> buttons) {
			this.controller = controller;
			this.designation = designation;
			this.buttons = buttons;

			this.addActionListener(new buttonListener());
			this.setFocusable(false);
			this.setFont(new Font("Arial", Font.PLAIN, 10));
			this.setToolTipText(designation.inactive);
			setButtonText();
			buttons.add(this);
		}

		private void setButtonText() {
			this.setText("(" + designation.keyboardShortcut + ")");
		}

		private class buttonListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (customDesignationButton button : buttons) {
					if (button.designation == designation) {
						button.toggle();
					} else {
						button.toggleSelected();
					}
				}
			}
		}
	}

	public boolean constructionSelected() {
		return currentlyPlacingRoom;
	}

	public void toggleConstructionSelection() {
		if (currentlyPlacingRoom) {
			deactivateConstructionSelection();
		} else {
			activateConstructionSelection();
		}
	}
	
	public void toggleUpgradeSelection() {
		if (currentlyChoosingUpgrade) {
			System.out.println("deactivating upgrade selection");
			deactivateUpgradeSelection();
		} else {
			System.out.println("activating upgrade selection");
			activateUpgradeSelection();
		}
	}
	
	public void activateUpgradeSelection() {
		for (customDesignationButton button : buttons) {
			button.deactivate();
		}
		
		currentlyChoosingUpgrade = true;
		upgradeRoomButton.setBackground(new Color(124, 163, 226));
		
		List<Room> rooms = Game.getMap().getCompletedRooms();
		List<Room> upgradeableRooms = new ArrayList<>(rooms);
		for (Room r : upgradeableRooms) {
			if (r.getUpgradesAllowed() > 0) {
				System.out.println("Adding a room to highlightMe");
				addToHighlightMe(r);
			}
		}
		repaint();
	}
	
	private void addToHighlightMe(Room room) {
		for (Position p : room.getBlocksInThisRoom())
			highlightMe.put(p, room);
	}
	
	public void deactivateUpgradeSelection() {
		currentlyChoosingUpgrade = false;
		highlightMe = new TreeMap<>();
		repaint();
	}

	public void activateConstructionSelection() {

		currentlyPlacingRoom = true;
		controller.setDesignatingAction(Designation.CONSTRUCTING);

		String roomChoice = constructRoomComboBox.getSelectedItem().toString();

		if (roomChoice != null) {
			room = RoomEnum.getRoomFromString(roomChoice);
			roomHeight = room.getHeight() * blockSizeY - 1;
			roomWidth = room.getWidth() * blockSizeX - 1;

			roomCorner = new Point(0, 0);
			roomX = 0;
			roomY = 0;

		}
		repaint();

	}

	public void deactivateConstructionSelection() {
		currentlyPlacingRoom = false;
		controller.setDesignatingAction(Designation.NONE);
		repaint();
	}
	
	private class UpdateButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			for (customDesignationButton button : buttons) {
				button.deactivate();
			}
			controller.setDesignatingAction(Designation.NONE);
			System.out.println("In actionPerformed method");
			toggleUpgradeSelection();
		}
		
	}

	private class ConstructionButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			toggleConstructionSelection();

			String roomChoice = constructRoomComboBox.getSelectedItem()
					.toString();

			if (roomChoice != null) {
				room = RoomEnum.getRoomFromString(roomChoice);
				roomHeight = room.getHeight() * blockSizeY - 1;
				roomWidth = room.getWidth() * blockSizeX - 1;
			}

		}
	}

	private class ConstructionComboBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			constructRoomButton.doClick();
		}

	}

	public void updateLog() {
		if (!logText.getText().equals(Log.getLog()))
			logText.setText(Log.getLog());
		repaint();
	}

}
