package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Save.SaveFile;
import controller.Controller;

/**
 * This is what is shown when the game is started
 * 
 * @author Ethan Ward
 *
 */
public class StartingView extends JPanel {

	private Controller controller;
	private JPanel panel;
	private JLabel titleLabel;
	private JLabel newGameLabel;
	private JLabel loadGameLabel;
	private int menuSelection = 0;

	public StartingView(Controller controller) {
		panel = new JPanel();
		this.setLayout(new GridBagLayout());
		this.setOpaque(true);
		this.controller = controller;
		this.setVisible(true);
		this.setBackground(Color.black);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setOpaque(true);
		verticalBox.setBackground(Color.black);
		verticalBox.add(Box.createVerticalGlue());

		titleLabel = new JLabel("This is the name of the game");
		titleLabel.setForeground(Color.red);

		newGameLabel = new JLabel("New game");
		newGameLabel.setForeground(Color.red);
		newGameLabel.setBackground(Color.blue);
		newGameLabel.setOpaque(true);

		loadGameLabel = new JLabel("Load game");
		loadGameLabel.setForeground(Color.red);

		verticalBox.add(titleLabel);
		verticalBox.add(newGameLabel);
		verticalBox.add(loadGameLabel);

		verticalBox.add(Box.createVerticalGlue());

		panel.add(verticalBox, new GridBagConstraints());
		this.add(panel);
		this.addKeyListener(new MyKeyListener());

		this.setFocusable(true);
		this.setRequestFocusEnabled(true);
		this.grabFocus();
	}

	private void toggleMenu() {
		if (menuSelection == 0) {
			menuSelection = 1;
			newGameLabel.setBackground(null);
			newGameLabel.setOpaque(false);

			loadGameLabel.setBackground(Color.blue);
			loadGameLabel.setOpaque(true);

		} else {
			menuSelection = 0;

			newGameLabel.setBackground(Color.blue);
			newGameLabel.setOpaque(true);

			loadGameLabel.setBackground(null);
			loadGameLabel.setOpaque(false);
		}
	}

	private class MyKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_DOWN:
				toggleMenu();
				break;
			case KeyEvent.VK_UP:
				toggleMenu();
				break;
			case KeyEvent.VK_ENTER:
				if (menuSelection == 0) {
					controller.startNewGame();
				} else {
					List<String> possibilities = SaveFile.getSavedFiles();
					String[] array = possibilities.toArray(new String[0]);

					String savename = (String) JOptionPane.showInputDialog(
							controller, "Choose a file to load", "",
							JOptionPane.PLAIN_MESSAGE, null, array, "ham");
					if (savename != null) {
						controller.loadGame(new SaveFile(savename));
					}
				}
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
