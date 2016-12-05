package view;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Controller;
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

	public StartingView(Controller controller) {
	    this.controller = controller;
	    this.setLayout(new GridBagLayout());
	    this.setBackground(Color.black);
	    Box verticalBox = Box.createVerticalBox();
		verticalBox.setBackground(Color.black);
		verticalBox.add(Box.createVerticalGlue());
		JLabel titleLabel = new JLabel("This is the name of the game");
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
	}
	
	private void newGame() {
		controller.startNewGame();
	}
	
	private void loadOldGame() {
		List<String> possibilities = SaveFile.getSavedFiles();
		String[] array = possibilities.toArray(new String[0]);

		String savename = (String) JOptionPane.showInputDialog(
				controller, "Choose a file to load", "",
				JOptionPane.PLAIN_MESSAGE, null, array, "ham");
		if (savename != null) {
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
