package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Map;

/**
 * Display a map
 * 
 * @author Ethan Ward
 *
 */
public class BasicGUI extends JFrame {

	private DrawingPanel drawingPanel;

	private int mapHeight = 100;
	private int mapWidth = 1000;
	private int mapDirtDepth = 10;
	private int mapStoneDepth = 30;

	private Map map = new Map(mapHeight, mapWidth, mapDirtDepth, mapStoneDepth, (int)(Math.random()*10000));

	private int width = 1000;
	private int height = 700;

	private int drawingPanelWidth = 5000;
	private int drawingPanelHeight = 1000;

	private int blockSizeY = drawingPanelHeight / mapHeight;
	private int blockSizeX = drawingPanelWidth / mapWidth;

	public static void main(String[] args) {
		BasicGUI view = new BasicGUI();
		view.setVisible(true);
	}

	public BasicGUI() {
		drawingPanel = new DrawingPanel();

		drawingPanel.setPreferredSize(new Dimension(drawingPanelWidth,
				drawingPanelHeight));
		JScrollPane scrollPane = new JScrollPane(drawingPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);

		add(scrollPane, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		setLocation(20, 20);
		setSize(width, height);

		repaint();

	}

	private class DrawingPanel extends JPanel {

		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			Graphics2D g2 = (Graphics2D) g;

			for (int row = 0; row < mapHeight; row++) {
				for (int col = 0; col < mapWidth; col++) {

					Color color = map.getBuildingBlock(row, col).getColor();
					g2.setColor(color);
					g2.fillRect(col * blockSizeX, row * blockSizeY, blockSizeX,
							blockSizeY);
					g2.setColor(Color.BLACK);
					g2.drawRect(col * blockSizeX, row * blockSizeY, blockSizeX,
							blockSizeY);

				}
			}
		}
	}

}