package de.hartschen.wne;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

/**
 * Die Klasse implementiert die StatusBar im WNEMainFrame.
 * 
 * @author Jens Hartschen
 *
 */
public class StatusBar extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor der StatusBar Klasse.
	 */
	public StatusBar() {
		super.setPreferredSize(new Dimension(100, 20));
	}

	/**
	 * Die Methode macht die übergebene Nachricht in der StatusBar sichbar.
	 * 
	 * @param message
	 * @param color
	 */
	public void setMessage(String message, Color color) {
		setText(" " + message);
		setForeground(color);
	}
}