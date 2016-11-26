package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import model.Actors.Actor;
import model.Furniture.Furniture;
import model.Items.Item;
import model.Map.Map;
import model.Map.MapParameters;

public class BasicView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8807654664923090784L;
	private Map map;
	private JPanel guiPanel;
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

		Box box = new Box(BoxLayout.Y_AXIS);
		box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		box.add(Box.createVerticalGlue());

		guiPanel = new JPanel();
		guiPanel.setLayout(new GridLayout(1, 3));
		box.add(guiPanel);

		JPanel labelPanel = new JPanel();
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

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 3));

		JButton placeFurnitureButton = new JButton(
				"<html><center>Place furniture</center></html>");
		placeFurnitureButton.setFocusable(false);
		
		JButton constructRoomButton = new JButton(
				"<html><center>Construct room</center></html>");
		constructRoomButton.setFocusable(false);
		
		JButton cutDownTreeButton = new JButton(
				"<html><center>Cut down tree</center></html>");
		cutDownTreeButton.setFocusable(false);
		
		JButton removeFurnitureButton = new JButton(
				"<html><center>Remove furniture</center></html>");
		removeFurnitureButton.setFocusable(false);
		
		JButton removeRoomButton = new JButton(
				"<html><center>Remove room</center></html>");
		removeRoomButton.setFocusable(false);
		
		JButton fruitButton = new JButton(
				"<html><center>Pick fruit from tree</center></html>");
		fruitButton.setFocusable(false);
		
		JButton digButton = new JButton("<html><center>Dig</center></html>");
		digButton.setFocusable(false);
		
		JButton pauseButton = new JButton("<html><center>Pause</center></html>");
		pauseButton.addActionListener(new pauseButtonListener());
		pauseButton.setFocusable(false);
		
		JButton plantsButton = new JButton(
				"<html><center>Gather plants</center></html>");
		plantsButton.setFocusable(false);
		
		JButton attackButton = new JButton("<html><center>Attack</center></html>");
		attackButton.setFocusable(false);
		
		JButton removeButton = new JButton("<html><center>Remove designation</center></html>");
		removeButton.setFocusable(false);
		
		buttonPanel.add(placeFurnitureButton);
		buttonPanel.add(constructRoomButton);
		buttonPanel.add(cutDownTreeButton);
		buttonPanel.add(removeFurnitureButton);
		buttonPanel.add(removeRoomButton);
		buttonPanel.add(fruitButton);
		buttonPanel.add(digButton);
		buttonPanel.add(pauseButton);
		buttonPanel.add(plantsButton);
		buttonPanel.add(attackButton);
		buttonPanel.add(removeButton);
		
		guiPanel.add(buttonPanel);

		JPanel logPanel = new JPanel();
		guiPanel.add(logPanel);

		drawingPanel = new DrawingPanel();

		drawingPanel.setPreferredSize(new Dimension(windowWidth,
				2 * windowHeight / 3));

		box.add(drawingPanel);

		add(box);
		pack();

		this.addKeyListener(new MyKeyListener());
		drawingPanel.addMouseMotionListener(new MyMotionListener());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		setLocation(20, 20);
		setSize(windowWidth, windowHeight);

		repaint();
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
	
	private class pauseButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (controller.isPaused()) {
				controller.startTimer();
			} else {
				controller.stopTimer();
			}
			setTimeLabel(controller.getTime(), controller.isPaused());
		}
		
	}


}
