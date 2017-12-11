package de.hartschen.wne;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class ViewSetName extends JPanel {

	public ViewSetName(Node node, IView iView) {
		super();

		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setBounds(700, 400, 300, 75);

		// alternative
		// frame.setPreferredSize(new Dimension(300, 90));
		// frame.pack();
		// frame.setLocationRelativeTo(null);

		frame.setTitle("Set name");

		JPanel panel = new JPanel();

		JLabel label = new JLabel("Enter new name: ");
		panel.add(label);

		JTextField tfName = new JTextField(node.getName(), 15);
		panel.add(tfName);

		JButton buttonOK = new JButton("OK");
		panel.add(buttonOK);

		frame.add(panel);
		frame.setVisible(true);

		Action action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Set new name
				node.setName(tfName.getText());
				iView.refresh();
				frame.setVisible(false);
			}
		};

		tfName.addActionListener(action);
		buttonOK.addActionListener(action);

	}
}
