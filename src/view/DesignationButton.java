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

	private static final long serialVersionUID = 3178671132433440884L;
	private Controller controller;
	private BasicView view;
	public Designation designation;
	private boolean active;
	private ArrayList<DesignationButton> buttons;

	public void activate() {
		controller.setDesignatingAction(designation);
		this.setBackground(new Color(124, 163, 226));
		active = true;
		setButtonText();
	}

	public void deactivate() {
		this.setBackground(null);
		active = false;
		setButtonText();
	}

	public void toggle() {
		if (active) {
			deactivate();
		} else {
			activate();
		}
	}
	
	public boolean isActive() {
		return active;
	}

	public DesignationButton(Controller controller, BasicView view, Designation designation,
			ArrayList<DesignationButton> buttons) {
		this.view = view;
		this.controller = controller;
		this.designation = designation;
		this.buttons = buttons;

		this.addActionListener(new buttonListener());
		this.setFocusable(false);
		this.setFont(new Font("Arial", Font.PLAIN, 10));
		setButtonText();
		buttons.add(this);
	}

	private void setButtonText() {
		if (active) {
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
			view.deactivateConstructionSelection();
			for (DesignationButton button : buttons) {
				if (button.designation == designation) {
					button.toggle();
				} else {
					button.deactivate();
				}
			}
			

		}

	}
}
