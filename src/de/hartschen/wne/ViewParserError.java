package de.hartschen.wne;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class ViewParserError extends JPanel {

	public ViewParserError() {
		super();

		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setBounds(700, 400, 375, 115);
		frame.setTitle("Error while reading file");

		JPanel panel = new JPanel();
		JTextArea text = new JTextArea(
				"Error while reading the file\n\n" + "If error with node ids, they could be corrected,\n"
						+ "if error with arcs ids, maybe couldn't corrected.\n\n" + " Save file to accept changes!");

		panel.add(text);

		JButton buttonOK = new JButton("OK");
		panel.add(buttonOK);

		frame.add(panel);
		frame.setVisible(true);

		Action action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		};

		buttonOK.addActionListener(action);

	}

}
