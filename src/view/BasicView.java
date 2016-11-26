package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import controller.Designation;
import model.Actors.Actor;
import model.Furniture.Furniture;
import model.Items.Item;
import model.Map.Map;
import model.Map.MapParameters;

public class BasicView extends JFrame {

	private static final long serialVersionUID = -8807654664923090784L;
	private Map map;
	private Box box;
	private JPanel guiPanel;
	private JPanel labelPanel;
	private JPanel buttonPanel;
	private JPanel logPanel;
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
	private int labelPanelHeight = 200;
	private int blockSizeY = (windowHeight - labelPanelHeight) / visibleHeight;
	private int blockSizeX = windowWidth / visibleWidth;

	private Controller controller;

	private PauseButton pauseButton;

	private JButton placeFurnitureButton;
	private JButton constructRoomButton;

	private DesignationButton cutDownTreeButton;
	private DesignationButton removeFurnitureButton;
	private DesignationButton removeRoomButton;
	private DesignationButton fruitButton;
	private DesignationButton digButton;
	private DesignationButton plantsButton;
	private DesignationButton attackButton;
	private DesignationButton removeButton;
	private ArrayList<DesignationButton> buttons;

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
		String mouseDescription = map.getBuildingBlock(mouseY, mouseX)
				.toString();

		mouseDescription += "</html>";

		mouseDescriptionLabel.setText("<html>Selected: " + mouseDescription);
	}

	public BasicView(Controller controller, Map map, MapParameters mapParameters) {
		this.controller = controller;
		this.map = map;
		this.mapWidth = mapParameters.mapWidth;
		this.mapHeight = mapParameters.mapHeight;

		box = new Box(BoxLayout.Y_AXIS);
		box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		box.add(Box.createVerticalGlue());

		guiPanel = new JPanel();
		guiPanel.setLayout(new GridLayout(1, 3));
		box.add(guiPanel);

		addLabelPanel();
		addButtonPanel();
		addLogPanel();
		addDrawingPanel();

		add(box);
		pack();

		this.addKeyListener(new MyKeyListener());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocation(20, 20);
		this.setSize(windowWidth, windowHeight);

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
		labelPanel
				.setPreferredSize(new Dimension(windowWidth, labelPanelHeight));
		guiPanel.add(labelPanel);

	}

	private void addButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 3));

		pauseButton = new PauseButton(controller, this);

		placeFurnitureButton = new JButton(
				"<html><center>Place furniture</center></html>");
		placeFurnitureButton.setFocusable(false);

		constructRoomButton = new JButton(
				"<html><center>Construct room</center></html>");
		constructRoomButton.setFocusable(false);

		buttons = new ArrayList<>();
		cutDownTreeButton = new DesignationButton(controller,
				Designation.CUTTING_DOWN_TREES, buttons);
		removeFurnitureButton = new DesignationButton(controller,
				Designation.REMOVING_FURNITURE, buttons);
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

		buttonPanel.add(placeFurnitureButton);
		buttonPanel.add(constructRoomButton);
		buttonPanel.add(pauseButton);
		buttonPanel.add(cutDownTreeButton);
		buttonPanel.add(removeFurnitureButton);
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
		guiPanel.add(logPanel);
	}

	private void addDrawingPanel() {
		drawingPanel = new DrawingPanel();
		drawingPanel.setPreferredSize(new Dimension(windowWidth,
				2 * windowHeight / 3));
		drawingPanel.addMouseMotionListener(new MyMotionListener());
		box.add(drawingPanel);
	}

	private class DrawingPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 8717360204333004762L;

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

					Color color = map.getBuildingBlock(row, col).getColor();
					g2.setColor(color);
					g2.fillRect(j * blockSizeX, i * blockSizeY, blockSizeX,
							blockSizeY);
					g2.setColor(Color.BLACK);
					g2.drawRect(j * blockSizeX, i * blockSizeY, blockSizeX,
							blockSizeY);

					List<Actor> actors = map.getBuildingBlock(row, col)
							.getActors();
					if (actors != null) {
						int size = actors.size();
						if (size != 0) {
							g2.setColor(Color.RED);
							g2.drawString(Integer.toString(size), j
									* blockSizeX + blockSizeX / 2, (i + 1)
									* blockSizeY);
							g2.setColor(Color.BLACK);
						}
					}

					Furniture furniture = map.getBuildingBlock(row, col)
							.getFurniture();
					if (furniture != null) {
						g2.drawString("F", j * blockSizeX + blockSizeX / 2,
								(i + 1) * blockSizeY);
					}

					if (map.getBuildingBlock(row, col).isMarkedForGathering()) {
						g2.drawString("G", j * blockSizeX + blockSizeX / 2,
								(i + 1) * blockSizeY);
					}

					List<Item> itemsOnGround = map.getBuildingBlock(row, col)
							.itemsOnGround();
					if (itemsOnGround != null) {
						if (itemsOnGround.size() != 0) {
							g2.drawString("#", j * blockSizeX + blockSizeX / 2,
									(i + 1) * blockSizeY);
						}
					}

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

		}
	}

}
