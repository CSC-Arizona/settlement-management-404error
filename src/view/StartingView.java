package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.Controller;
import images.ImageEnum;
import model.actors.Actor;
import model.actors.EnemyActor;
import model.actors.PlayerControlledActor;
import model.game.Game;
import model.game.Log;
import model.map.Map;
import model.map.MapParameters;
import model.save.SaveFile;

/**
 * This is what is shown when the game is started
 * 
 * @author Ethan Ward
 *
 */
public class StartingView extends JPanel {

	private static final long serialVersionUID = 4699476954482553320L;
	private Controller controller;
	private JButton newGameButton, loadGameButton;
	private int windowWidth = 1000;
	private int windowHeight = 700;
	private Timer update;

	private int visibleWidth = 50;
	private int visibleHeight = 50;

	private int visibleCornerY = 35;
	private int visibleCornerX;
	private int blockSizeY = (windowHeight) / visibleHeight;
	private int blockSizeX = windowWidth / visibleWidth;
	private int timeDelta;
	private int time;

	public StartingView(Controller controller) {
		

		this.controller = controller;
		time = 0;
		for (ImageEnum e : ImageEnum.values()) {
			e.createBufferedImages(blockSizeY, blockSizeX);
		}
		update = new Timer(35, e->{
			this.repaint();
		});
		this.setLayout(new GridBagLayout());
		Game.setMap(new Map(MapParameters.getDefaultParameters(), new Random()));
		visibleCornerX = (Game.getMap().getTotalWidth() - visibleWidth / 2);
		Box verticalBox = Box.createVerticalBox();
		verticalBox.add(Box.createVerticalGlue());
		JLabel titleLabel = new JLabel("This is the name of the game");
		titleLabel.setFont(new Font("Courier", Font.PLAIN, 30));
		titleLabel.setForeground(Color.red);
		newGameButton = new JButton("New game");
		newGameButton.setForeground(Color.green);
		newGameButton.addKeyListener(new MyKeyListener());
		newGameButton.addActionListener(new MyButtonListener());
		loadGameButton = new JButton("Load game");
		loadGameButton.setForeground(Color.green);
		loadGameButton.addKeyListener(new MyKeyListener());
		loadGameButton.addActionListener(new MyButtonListener());
		verticalBox.add(titleLabel);
		verticalBox.add(newGameButton);
		verticalBox.add(loadGameButton);
		this.add(verticalBox);
		controller.getContentPane().add(this);
		controller.pack();
		newGameButton.requestFocusInWindow();
		controller.setVisible(true);
		update.start();
	}

	private void newGame() {
		update.stop();
		Game.reset();
		Log.clear();
		controller.startNewGame();
	}

	private void loadOldGame() {
		List<String> possibilities = SaveFile.getSavedFiles();
		String[] array = possibilities.toArray(new String[0]);

		String savename = (String) JOptionPane.showInputDialog(controller, "Choose a file to load", "",
				JOptionPane.PLAIN_MESSAGE, null, array, "ham");
		if (savename != null) {
			update.stop();
			Game.reset();
			Log.clear();
			controller.loadGame(new SaveFile(savename));
		}
	}

	private class MyButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == newGameButton) {
				newGame();
			} else {
				loadOldGame();
			}
		}
	}

	private void setVisibleTiles(int row, int col) {
		if (Game.getMap().getBuildingBlock(row, col).isOccupiable()
				|| Game.getMap().getBuildingBlock(row, col).getID().equals("Room wall")) {
			for (int k = -1; k < 2; k++) {
				for (int l = -1; l < 2; l++) {
					int newRow = row + k;
					int newCol = col + l;
					newCol = Math.floorMod(newCol, Game.getMap().getTotalWidth());
					if (newRow >= 0 && newRow < Game.getMap().getTotalHeight()) {

						Game.getMap().getBuildingBlock(newRow, newCol).setVisibility(true);
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
			Color bgcolor = Game.getMap().getBuildingBlock(row, col).getBackgroundColor();
			if (bgcolor != null) {
				g2.setColor(bgcolor);
				g2.fillRect(j * blockSizeX, i * blockSizeY, blockSizeX, blockSizeY);

			}
			BufferedImage img = Game.getMap().getBuildingBlock(row, col).getImage().getRandomBufferedImage();
			g2.drawImage(img, j * blockSizeX, i * blockSizeY, null);

		}
	}

	private void drawActors(Graphics2D g2, int row, int col, int i, int j) {
		List<Actor> actors = Game.getMap().getBuildingBlock(row, col).getActors();
		if (actors != null) {
			int count = 0;
			Iterator<Actor> iter = actors.iterator();
			while (iter.hasNext()) {
				Actor p = iter.next();
				if (p.getImage() != null) {
					g2.drawImage(p.getImage().getRandomBufferedImage(), j * blockSizeX, i * blockSizeY, null);
				} else {
					count += 1;
				}
			}
			if (count != 0) {
				g2.setColor(Color.RED);
				g2.drawString(Integer.toString(count), j * blockSizeX + blockSizeX / 2, (i + 1) * blockSizeY);
				g2.setColor(Color.BLACK);
			}
		}
	}

	private void drawTile(Graphics2D g2, int row, int col, int i, int j) {

		if (Game.getMap().getBuildingBlock(row, col).getVisibility()) {

			drawBuildingBlock(g2, row, col, i, j);

			drawActors(g2, row, col, i, j);

		} else {
			g2.setColor(Color.black);
			g2.fillRect(j * blockSizeX, i * blockSizeY, blockSizeX, blockSizeY);
		}

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		visibleCornerX++;
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		Graphics2D g2 = (Graphics2D) g;

		for (int i = 0; i < visibleHeight; i++) {
			int row = visibleCornerY + i;
			for (int j = 0; j < visibleWidth; j++) {
				int col = visibleCornerX + j;
				col = Math.floorMod(col, Game.getMap().getTotalWidth());

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
			case KeyEvent.VK_DOWN:
				if (newGameButton.isFocusOwner())
					loadGameButton.requestFocusInWindow();
				break;
			case KeyEvent.VK_UP:
				if (loadGameButton.isFocusOwner())
					newGameButton.requestFocusInWindow();
				break;
			case KeyEvent.VK_ENTER:
				if (newGameButton.hasFocus())
					newGame();
				else
					loadOldGame();
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
}
