package gui;

import gui.*;
import logic.*;
import model.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class JWorkFlownetzFrame extends JFrame {

	private JPanel contentPane;
	private int placeTranstionArcPTArcTP = 5;
	private int transitionX = 50;
	private int transitionY = 50;
	private int transitionW = 20;
	private int transitionH = 20;
	private Graphics2D g2d;
	private int click1X;
	private int click1Y;
	private int click2X;
	private int click2Y;
	private Boolean firstClick = true;
	private JPanel panel = new JPanel();

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

		// Rahmen und Hintergrund des Panels
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));

		contentPane.add(panel, BorderLayout.CENTER);

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

		// ToDo Add MouseListener here
		// MouseListener
		panel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();

				// drawTransition(e.getX(), e.getY(), g2d);
				//
				switch (placeTranstionArcPTArcTP) {
				case 0:
					drawStelle(x, y, g2d);
					break;
				case 1:
					drawTransition(x, y, g2d);
					break;
				case 2:
					if (firstClick) {
						click1X = x;
						click1Y = y;
						firstClick = false;
					} else if (!firstClick) {
						click2X = x;
						click2Y = y;
						drawArcPT(click1X, click1Y, click2X, click2Y, g2d);
						firstClick = true;
					}
					break;
				case 3:
					drawArcTP(x, y, g2d);
					break;

				default:
					break;
				}

			}
		});
		// MouseListener Ende

	}

	void drawStelle(int x, int y, Graphics2D g2d) {
		g2d = (Graphics2D) panel.getGraphics();
		g2d.setColor(Color.black);
		g2d.drawOval(x, y, 50, 50);
	}

	void drawTransition(int x, int y, Graphics2D g2d) {
		g2d = (Graphics2D) panel.getGraphics();
		g2d.setColor(Color.black);
		g2d.drawRect(x, y, 50, 50);
		System.out.println(g2d);
	}

	void drawArcPT(int x1, int y1, int x2, int y2, Graphics2D g2d) {
		g2d = (Graphics2D) panel.getGraphics();
		g2d.setColor(Color.black);
//		g2d.drawString("ArcPT", x, y);
		 g2d.drawLine(click1X, click1Y, click2X, click2Y);
	}

	void drawArcTP(int x, int y, Graphics2D g2d) {
		g2d = (Graphics2D) panel.getGraphics();
		g2d.setColor(Color.black);
		g2d.drawString("ArcTP", x, y);
//	 g2d.drawLine(x1, y1, x2, y2);
	}
	
	
	private void OpenFile() {
		// Create a file chooser
		JFileChooser chooser = new JFileChooser();

		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNML File", "pnml");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(JWorkFlownetzFrame.this);
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
		int returnVal = chooser.showSaveDialog(JWorkFlownetzFrame.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile());
		}
		
	}

	private void paintTransition(int x, int y) {
		int OFFSET = 1;
		if ((transitionX != x) || (transitionY != y)) {
			repaint(transitionX, transitionY, transitionW + OFFSET, transitionH + OFFSET);
			transitionX = x;
			transitionY = y;
			repaint(transitionX, transitionY, transitionW + OFFSET, transitionH + OFFSET);
		}
	}
	
}
