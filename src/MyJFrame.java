import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
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

	private GlobalSizeController globalSizeController;
	private MyJPanel myJPanel;
	private ToolBarController toolBarController;
	private File currentPath;
	private IModel model;
	private IView iView;
	private ArcsModel arcsModel;
	private GlobalSizeModel globalSizeModel;
	private ID id;
	private StatusBar statusBar;

	public MyJFrame(GlobalSizeController globalSizeController, MyJPanel myJPanel, ToolBarController toolBarController,
			IModel model, IView iView, ArcsModel arcsModel, GlobalSizeModel globalSizeModel, ID id,
			StatusBar statusBar) {
		this.globalSizeController = globalSizeController;
		this.myJPanel = myJPanel;
		this.toolBarController = toolBarController;
		this.iView = iView;
		this.arcsModel = arcsModel;
		this.globalSizeModel = globalSizeModel;
		this.id = id;
		this.statusBar = statusBar;
		// EventQueue.invokeLater(new Runnable() {
		// @Override
		// public void run() {
		// try {
		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// } catch (ClassNotFoundException | InstantiationException |
		// IllegalAccessException
		// | UnsupportedLookAndFeelException ex) {
		// ex.printStackTrace();
		// }

		JFrame frame = new JFrame("Workflownetz Editor");
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
		JScrollPane scrollPane = new JScrollPane(myJPanel);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		// Add statusBar
		contentPaneForMenu.add(statusBar, java.awt.BorderLayout.SOUTH);

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

		JButton buttonArc = new JButton("Arc");
		toolBar.add(buttonArc);

		JButton buttonBigger = new JButton("Bigger +");
		toolBar.add(buttonBigger);

		JButton buttonSmaller = new JButton("Smaller -");
		toolBar.add(buttonSmaller);

		frame.add(contentPaneForMenu);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		menuItemOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == menuItemOpen) {
					OpenFile(model);
				}
			}
		});

		menuItemSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == menuItemSave) {
					SaveFile();
				}
			}
		});

		menuItemExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == menuItemExit) {
					System.exit(0);
				}
			}
		});

		buttonSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonSelect) {
					toolBarController.setToolBarSwitch(0);
//					System.out.println("Select " + toolBarController.getToolBarSwitch());
				}
			}
		});

		buttonMarquee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonMarquee) {
					toolBarController.setToolBarSwitch(1);
//					System.out.println("Marquee " + toolBarController.getToolBarSwitch());
				}
			}
		});

		buttonPlace.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonPlace) {
					toolBarController.setToolBarSwitch(2);
//					System.out.println("Place " + toolBarController.getToolBarSwitch());
				}
			}
		});

		buttonTransition.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonTransition) {
					toolBarController.setToolBarSwitch(3);
//					System.out.println("Transition " + toolBarController.getToolBarSwitch());
				}
			}
		});

		buttonArc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonArc) {
					toolBarController.setToolBarSwitch(4);
//					System.out.println("Arc " + toolBarController.getToolBarSwitch());
				}
			}
		});

		buttonBigger.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonBigger) {
					globalSizeController.nodesBigger();
				}
			}
		});

		buttonSmaller.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonSmaller) {
					globalSizeController.nodesSmaller();
				}
			}
		});

	}
	// });

	// }

	private void OpenFile(IModel model) {
		// Create a file chooser
		System.out.println(currentPath);
		// TODO set to currentPath
		JFileChooser chooser = new JFileChooser("/home/jens/FernUniHagen/ProPra/Aufgabenstellung/Beispiele");
		// JFileChooser chooser = new JFileChooser(currentPath);

		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNML File", "pnml");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(MyJFrame.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile());
			currentPath = chooser.getCurrentDirectory();

			File pnmlDatei = new File(chooser.getSelectedFile(), "");
			if (pnmlDatei.exists()) {
				PNMLParserImpl pnmlParserImpl = new PNMLParserImpl(pnmlDatei, model, iView, arcsModel, globalSizeModel);
				pnmlParserImpl.initParser();
				pnmlParserImpl.parse();
			} else {
				System.err.println("Die Datei " + pnmlDatei.getAbsolutePath() + " wurde nicht gefunden!");
			}
		} else {
			System.out.println("Bitte eine Datei als Parameter angeben!");
		}
		id.setBothIdForParser();
		id.setArcIdForParser();

		// try (BufferedReader br = new BufferedReader(new
		// FileReader(chooser.getSelectedFile()))) {
		//
		// String sCurrentLine;
		//
		// while ((sCurrentLine = br.readLine()) != null) {
		// System.out.println(sCurrentLine);
		// }
		//
		// } catch (Exception e2) {
		// // TODO: handle exception
		// }

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