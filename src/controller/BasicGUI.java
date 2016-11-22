package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Map;
import model.Actors.Actor;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;

/**
 * Display a map
 * 
 * @author Ethan Ward
 *
 */
public class BasicGUI extends JFrame {

	private DrawingPanel drawingPanel;
	private JLabel windowCoordinatesLabel;
	private JLabel mouseDescriptionLabel;
	private JLabel mouseCoordinatesLabel;

	private int mapHeight = 150;
	private int mapWidth = 1000;
	private int mapDirtDepth = 30;
	private int mapStoneDepth = 50;

	private Map map = new Map(mapHeight, mapWidth, mapDirtDepth, mapStoneDepth,
			9123123);

	private int windowWidth = 1000;
	private int windowHeight = 700;

	private int visibleWidth = 50;
	private int visibleHeight = 50;

	private int visibleCornerY = 35;
	private int visibleCornerX = (mapWidth - visibleWidth / 2);

	private int blockSizeY = windowHeight / visibleHeight;
	private int blockSizeX = windowWidth / visibleWidth;

	public static void main(String[] args) {
		BasicGUI view = new BasicGUI();
		view.setVisible(true);
	}

	private void setWindowCoordinateLabel() {
		windowCoordinatesLabel.setText("Window coordinates: (" + visibleCornerY
				+ ", " + visibleCornerX + ")");
	}

	private void setMouseCoordinatesLabel(int x, int y) {
		mouseCoordinatesLabel.setText("Mouse coordinates: (" + x + ", " + y
				+ ")");
	}

	private void setMouseDescriptionLabel(String id) {
		mouseDescriptionLabel.setText("<html>Selected: " + id);
	}

	public BasicGUI() {
		Box box = new Box(BoxLayout.Y_AXIS);
		box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		box.add(Box.createVerticalGlue());

		JPanel labelPanel = new JPanel();

		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));

		windowCoordinatesLabel = new JLabel();
		setWindowCoordinateLabel();
		labelPanel.add(windowCoordinatesLabel);

		mouseCoordinatesLabel = new JLabel();
		setMouseCoordinatesLabel(-1, -1);
		labelPanel.add(mouseCoordinatesLabel);

		mouseDescriptionLabel = new JLabel();
		setMouseDescriptionLabel("");
		labelPanel.add(mouseDescriptionLabel);

		labelPanel.setPreferredSize(new Dimension(windowWidth, 200));

		box.add(labelPanel);

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
							g2.drawString(Integer.toString(size), j
									* blockSizeX + blockSizeX / 2, (i + 1)
									* blockSizeY);
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

			int x = coords.x / blockSizeX + visibleCornerX;
			x = Math.floorMod(x, mapWidth);
			int y = coords.y / blockSizeY + visibleCornerY;

			String mouseDescription = map.getBuildingBlock(y, x).getID();

			List<Actor> actors = map.getBuildingBlock(y, x).getActors();
			if (actors != null) {
				int size = actors.size();
				for (int i = 0; i < size; i++) {
					mouseDescription += "<br>&nbsp;&nbsp;&nbsp;&nbsp;Actor "
							+ i;
					mouseDescription += "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Health: "
							+ actors.get(i).getHealth();
				}
			}

			mouseDescription += "</html>";

			setMouseDescriptionLabel(mouseDescription);
			setMouseCoordinatesLabel(y, x);

		}
	}

}