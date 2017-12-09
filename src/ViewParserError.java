
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class ViewParserError extends JPanel {

	private Node node;
	private IView iView;

	public ViewParserError() {
		super();

		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setBounds(700, 400, 375, 115);

		// alternative
		// frame.setPreferredSize(new Dimension(300, 90));
		// frame.pack();
		// frame.setLocationRelativeTo(null);

		frame.setTitle("Error while reading file");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		JTextArea text = new JTextArea("Error while reading the file\n\n"
				+ "If error with node ids, they could be corrected,\n"
				+ "if error with arcs ids, maybe couldn't corrected.\n\n"
				+ " Save file to accept changes!");

//		JLabel label = new JLabel("Error while reading the file\n" + 
//				"\n" + 
//				"If error with node ids, they could be corrected, if error with arcs ids, maybe couldn't corrected. Save file to accept changes! ");
		panel.add(text);

//		JTextField tfName = new JTextField(node.getName(), 15);
//		panel.add(tfName);

		JButton buttonOK = new JButton("OK");
		panel.add(buttonOK);

		frame.add(panel);
		frame.setVisible(true);

		Action action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Set new name
//				node.setName(tfName.getText());
//				iView.refresh();
				frame.setVisible(false);
			}
		};

//		tfName.addActionListener(action);
		buttonOK.addActionListener(action);

	}

}
