package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import controller.Controller;
import controller.Designation;

/**
 * Button for various designations
 * 
 * @author Ethan Ward
 *
 */
public class DesignationButton extends JButton {

	private Controller controller;
	public Designation designation;
	
	public void toggle() {
		if (controller.getDesignatingAction() == Designation.NONE) {
			controller.setDesignatingAction(designation);
			this.setBackground(new Color(124, 163, 226));
		} else if (controller.getDesignatingAction() == designation) {
			controller.setDesignatingAction(Designation.NONE);
			this.setBackground(null);
		}
		setButtonText();
	}

	public DesignationButton(Controller controller,
			Designation designation, ArrayList<DesignationButton> buttons) {
		this.controller = controller;
		this.designation = designation;

		this.addActionListener(new buttonListener());
		this.setFocusable(false);
		this.setFont(new Font("Arial", Font.PLAIN, 10));
		setButtonText();
		buttons.add(this);
	}

	private void setButtonText() {
		if (controller.getDesignatingAction() == designation) {
			this.setText("<html><center>" + designation.active + "<br>("
					+ designation.keyboardShortcut + ")</center></html>");
		} else {
			this.setText("<html><center>" + designation.inactive + "<br>("
					+ designation.keyboardShortcut + ")</center></html>");
		}
	}

	private class buttonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			toggle();
		}

	}
}
