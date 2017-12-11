package de.hartschen.wne;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

public class StatusBar extends JLabel {

	/** Creates a new instance of StatusBar */
	public StatusBar() {
		super();
		super.setPreferredSize(new Dimension(100, 20));
	}

	public void setMessage(String message, Color color) {
		setText(" " + message);
		setForeground(color);
	}
}