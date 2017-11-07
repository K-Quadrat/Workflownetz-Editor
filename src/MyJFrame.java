import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import gui.JWorkFlownetzFrame;

public class MyJFrame extends JFrame {

	// TODO Methode mit getter und setter bauen
	public int placeTranstionArcPTArcTP = 5;
	private Model model;

	public MyJFrame(Model model) {
		this.model = model;

//		EventQueue.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//						| UnsupportedLookAndFeelException ex) {
//					ex.printStackTrace();
//				}

				JFrame frame = new JFrame("Testing 2");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				// Create contentPane with Border Layout
				JPanel contentPaneForMenu = new JPanel(new BorderLayout());
				JPanel contentPane = new JPanel(new BorderLayout());

				// Create menuBar
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

				// Add contentPane and menuBar to contentPaneForMenu
				contentPaneForMenu.add(contentPane, BorderLayout.CENTER);
				contentPaneForMenu.add(menuBar, BorderLayout.NORTH);

				// Create ScrollPane and add MyJanel to contentPane
				JScrollPane scrollPane = new JScrollPane(new MyJPanel(model));
				contentPane.add(scrollPane, BorderLayout.CENTER);

				// Add ToolBar to contentPane
				JToolBar toolBar = new JToolBar();
				contentPane.add(toolBar, BorderLayout.NORTH);

				JButton buttonSelect = new JButton("Select");
				toolBar.add(buttonSelect);

				JButton buttonMarquee = new JButton("Marquee");
				toolBar.add(buttonMarquee);

				JButton buttonPlace = new JButton("Place");
				toolBar.add(buttonPlace);

				JButton buttonTransition = new JButton("Transition");
				toolBar.add(buttonTransition);

				JButton buttonArcPT = new JButton("ArcPT");
				toolBar.add(buttonArcPT);

				JButton buttonArcTP = new JButton("ArcTP");
				toolBar.add(buttonArcTP);

				frame.add(contentPaneForMenu);

				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);

				menuItemOpen.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == menuItemOpen) {
							System.out.println("menuItemOpen");
							OpenFile();
						}
					}
				});

				menuItemSave.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == menuItemSave) {
							System.out.println("menuItemSave");
							SaveFile();
						}
					}
				});

				buttonSelect.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == buttonSelect) {
							// placeTranstionArcPTArcTP = 1;
							System.out.println("Select");
						}
					}
				});

				buttonMarquee.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == buttonMarquee) {
							// placeTranstionArcPTArcTP = 1;
							System.out.println("Marquee");
						}
					}
				});

				buttonPlace.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == buttonPlace) {
							placeTranstionArcPTArcTP = 0;
							System.out.println("placeTranstionArcPTArcTP = " + placeTranstionArcPTArcTP + " Place");
						}
					}
				});

				buttonTransition.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == buttonTransition) {
							placeTranstionArcPTArcTP = 1;
							System.out.println("placeTranstionArcPTArcTP = " + placeTranstionArcPTArcTP + " Transtion");
						}
					}
				});

				buttonArcPT.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == buttonArcPT) {
							placeTranstionArcPTArcTP = 2;
							System.out.println("placeTranstionArcPTArcTP = " + placeTranstionArcPTArcTP + " ArcPT");
						}
					}
				});

				buttonArcTP.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == buttonArcTP) {
							placeTranstionArcPTArcTP = 3;
							System.out.println("placeTranstionArcPTArcTP = " + placeTranstionArcPTArcTP + " ArcTP");
						}
					}
				});

			}
//		});

//	}

	private void OpenFile() {
		// Create a file chooser
		JFileChooser chooser = new JFileChooser();

		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNML File", "pnml");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(MyJFrame.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile());
		}

		try (BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} catch (Exception e2) {
			// TODO: handle exception
		}

	}

	private void SaveFile() {
		// Create a file chooser
		JFileChooser chooser = new JFileChooser();

		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNML File", "pnml");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(MyJFrame.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile());
		}

	}

}