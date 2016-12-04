package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller.Controller;

public class PauseButton extends JButton {

	private static final long serialVersionUID = -243975296052394955L;
	private Controller controller;
	private BasicView view;

	public void toggle() {
		controller.togglePaused();
		setButtonText();
		view.setTimeLabel(controller.getTime(), controller.isPaused());
	}

	public PauseButton(Controller controller, BasicView view) {
		this.controller = controller;
		this.view = view;
		this.addActionListener(new pauseButtonListener());
		this.setFocusable(false);
		setButtonText();
	}

	private void setButtonText() {
		if (controller.isPaused()) {
			this.setText("<html><center>Unpause<br>(SPACE)</center></html>");
		} else {
			this.setText("<html><center>Pause<br>(SPACE)</center></html>");
		}
	}

	private class pauseButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			toggle();
		}

	}
}
