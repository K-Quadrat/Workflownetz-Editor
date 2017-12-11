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
	private SetStartMark setStartMark;
	private AnimationMode animationMode;
	private Warshall warshall;
	private Multiselect multiselect;
	private ArcsController arcsController;

	public MyJFrame(GlobalSizeController globalSizeController, MyJPanel myJPanel, ToolBarController toolBarController,
			IModel model, IView iView, ArcsModel arcsModel, GlobalSizeModel globalSizeModel, ID id, StatusBar statusBar,
			SetStartMark setStartMark, AnimationMode animationMode, Warshall warshall, Multiselect multiselect, ArcsController arcsController) {
		this.globalSizeController = globalSizeController;
		this.myJPanel = myJPanel;
		this.toolBarController = toolBarController;
		this.iView = iView;
		this.arcsModel = arcsModel;
		this.globalSizeModel = globalSizeModel;
		this.id = id;
		this.statusBar = statusBar;
		this.setStartMark = setStartMark;
		this.animationMode = animationMode;
		this.warshall = warshall;
		this.multiselect = multiselect;
		this.arcsController = arcsController;
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

		JFrame frame = new JFrame("Workflownetz Editor Jens Hartschen 6970770");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1024, 768));

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

		JButton buttonSelect = new JButton("Select component");
		toolBar.add(buttonSelect);

		JButton buttonMultiselect = new JButton("Multiselect components");
		toolBar.add(buttonMultiselect);

		JButton buttonPlace = new JButton("Add a place");
		toolBar.add(buttonPlace);

		JButton buttonTransition = new JButton("Add a transition");
		toolBar.add(buttonTransition);

		JButton buttonArc = new JButton("Add a Arc");
		toolBar.add(buttonArc);

		JButton buttonSetStartMarker = new JButton("Set start marker");
		toolBar.add(buttonSetStartMarker);

		JButton buttonBigger = new JButton("Zoom out");
		toolBar.add(buttonBigger);

		JButton buttonSmaller = new JButton("Zoom in");
		toolBar.add(buttonSmaller);

		frame.add(contentPaneForMenu);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		menuItemOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == menuItemOpen) {
					animationMode.setAnimationMode(false);
					OpenFile(model);
				}
			}
		});

		menuItemSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == menuItemSave) {
					SaveFile(model);
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
					//TODO
//					model.deleteNodeById("S2");
					arcsController.removeNotUsedArcs();
					iView.refresh();
					// System.out.println("Select " + toolBarController.getToolBarSwitch());

				}
			}
		});

		buttonMultiselect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonMultiselect) {
					toolBarController.setToolBarSwitch(1);
					multiselect.deleteMultiselectedNodes();
					arcsController.removeNotUsedArcs();
					setStartMark.setStartMarking();
					iView.refresh();
					// System.out.println("Multiselect " + toolBarController.getToolBarSwitch());
				}
			}
		});

		buttonPlace.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonPlace) {
					animationMode.setAnimationMode(false);
					toolBarController.setToolBarSwitch(2);
					// System.out.println("Place " + toolBarController.getToolBarSwitch());
				}
			}
		});

		buttonTransition.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonTransition) {
					animationMode.setAnimationMode(false);
					toolBarController.setToolBarSwitch(3);
					// System.out.println("Transition " + toolBarController.getToolBarSwitch());
				}
			}
		});

		buttonArc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonArc) {
					animationMode.setAnimationMode(false);
					toolBarController.setToolBarSwitch(4);
					// System.out.println("Arc " + toolBarController.getToolBarSwitch());
				}
			}
		});

		buttonSetStartMarker.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonSetStartMarker) {
					setStartMark.setStartMarking();
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

		myJPanel.setScrollPaneReference(scrollPane);

	}

	private boolean databaseConditionCheckNodesId(IModel model) {
		boolean error = false;
		if (!model.getAllNodes().isEmpty()) {

			for (Node n : model.getAllNodes()) {
				// for (int i = 0; i < model.getAllNodes().size(); i++) {

				if (!n.getId().substring(0, 1).matches("[ST]")) {
					error = true;
//					System.out.println("Error1");

				}
				if (!n.getId().substring(1).matches("[0-9][0-9]?[0-9]?[0-9]?")) {
					error = true;
//					System.out.println("Error2");
				}
				// }
			}
		}

		if (error) {
			for (Node n : model.getAllNodes()) {
				if (n.getNodeType() == ENode.PLACE) {
					n.setId(id.getNextPlaceIdString());

				}
				if (n.getNodeType() == ENode.TRANSITION) {
					n.setId(id.getNextTransitionIdString());

				}
			}
		}

		return error;

	}


	private void OpenFile(IModel model) {
		boolean parserError = false;

		// Create a file chooser
		// TODO set to currentPath
		JFileChooser chooser = new JFileChooser("/home/jens/FernUniHagen/ProPra/Aufgabenstellung/Beispiele");
		// JFileChooser chooser = new JFileChooser(currentPath);

		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNML File", "pnml");
		chooser.setFileFilter(filter);

		int returnVal = chooser.showOpenDialog(MyJFrame.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {

			model.clearNodesList();
			arcsModel.clearArcsList();

			System.out.println("You chose to open this file: " + chooser.getSelectedFile());
			currentPath = chooser.getCurrentDirectory();

			File pnmlDatei = new File(chooser.getSelectedFile(), "");
			if (pnmlDatei.exists()) {
				PNMLParserImpl pnmlParserImpl = new PNMLParserImpl(pnmlDatei, model, iView, arcsModel, globalSizeModel,
						id);
				pnmlParserImpl.initParser();
				pnmlParserImpl.parse();
			} else {
				System.err.println("Die Datei " + pnmlDatei.getAbsolutePath() + " wurde nicht gefunden!");
			}

			id.setBothIdForParser();
			id.setArcIdForParser();
			

		} else {
			System.out.println("Bitte eine Datei als Parameter angeben!");
		}
		setStartMark.setStartMarking();
	}

	private void SaveFile(IModel model) {
		// Create a file chooser
		// TODO set to currentPath
		JFileChooser chooser = new JFileChooser("/home/jens/FernUniHagen/ProPra/Aufgabenstellung/SaveTest");
		// JFileChooser chooser = new JFileChooser(currentPath);

		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNML File", "pnml");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(MyJFrame.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to save this file: " + chooser.getSelectedFile());
			currentPath = chooser.getCurrentDirectory();
			File pnmlDatei = new File(chooser.getSelectedFile() + ".pnml", "");
			PNMLWriterImpl pnmlWriterImpl = new PNMLWriterImpl(pnmlDatei, model, arcsModel);
			pnmlWriterImpl.writeAllToFile();

		}

		else {
			System.out.println("Bitte eine Datei zum Speichern angeben!");
		}

	}

}