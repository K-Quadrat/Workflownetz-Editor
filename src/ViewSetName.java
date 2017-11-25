import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ViewSetName extends MyJPanel {

	private Node node;


	public ViewSetName(Node node) {
		super();
		this.node = node;
		
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setBounds(700, 400, 300, 75);
		frame.setTitle("Set name");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();

		JLabel label = new JLabel("Enter new name: ");
		panel.add(label);

		JTextField tfName = new JTextField(node.getName(), 15);
		panel.add(tfName);

		JButton buttonOK = new JButton("OK");
		panel.add(buttonOK);

		frame.add(panel);
		frame.setVisible(true);

		buttonOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonOK) {
					
					// Set new name
					node.setName(tfName.getText());

					// Why not working?
					refresh();
					frame.setVisible(false);
					
				}
			}
		});

	}
}
