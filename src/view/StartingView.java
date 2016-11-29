package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import model.Map.Map;
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

		loadGameLabel = new JLabel("Load game (not implemented)");
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
