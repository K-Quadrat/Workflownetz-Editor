import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ViewSetName extends JFrame {

	private Node node;

	/**
	 * Create the frame.
	 */
	public ViewSetName(Node node) {

		this.node = node;

//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("Set name");

		setBounds(700, 400, 300, 75);
		// setSize(300, 75);
		JPanel panel = new JPanel();

		JLabel label = new JLabel("Enter new name: ");
		panel.add(label);

		JTextField tfName = new JTextField(node.getName(), 15);

		panel.add(tfName);

		JButton buttonOK = new JButton("OK");
		panel.add(buttonOK);

		add(panel);
		setVisible(true);

		buttonOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonOK) {
					
					// Set new name
					node.setName(tfName.getText());
					setVisible(false);
				}
			}
		});

	}
}
