package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class JWorkFlownetzFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public JWorkFlownetzFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		JMenuItem menuItemOpen = new JMenuItem("Open File...");
		menuFile.add(menuItemOpen);
		
		JMenuItem menuItemSave = new JMenuItem("Save As...");
		menuFile.add(menuItemSave);
		
		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuFile.add(menuItemExit);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton buttonStellen = new JButton("Stellen");
		buttonStellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		toolBar.add(buttonStellen);
		
		JButton buttonTransitionen = new JButton("Transitionen");
		toolBar.add(buttonTransitionen);
		
		JButton buttonKanten = new JButton("Kanten");
		toolBar.add(buttonKanten);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
	}

}
