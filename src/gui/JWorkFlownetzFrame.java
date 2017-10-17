package gui;
import gui.*;
import model.*;
import controller.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class JWorkFlownetzFrame extends JFrame {

	private JPanel contentPane;
	private String stelleTranstionKante;
	private int transitionX = 50;
	private int transitionY = 50;
	private int transitionW = 20;
	private int transitionH = 20;
	private Graphics2D g2d;

	/**
	 * Create the frame.
	 */
	public JWorkFlownetzFrame() {
		JPanel panel = new JPanel();

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
		toolBar.add(buttonStellen);

		JButton buttonTransitionen = new JButton("Transitionen");
		toolBar.add(buttonTransitionen);

		JButton buttonKanten = new JButton("Kanten");
		toolBar.add(buttonKanten);

		// Rahmen und Hintergrund des Panels
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));

		contentPane.add(panel, BorderLayout.CENTER);

		buttonStellen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonStellen) {
					stelleTranstionKante = "stelle";
					System.out.println("stelleTranstionKante = " + stelleTranstionKante);
				}
			}
		});

		buttonTransitionen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonTransitionen) {
					stelleTranstionKante = "transition";
					System.out.println("stelleTranstionKante = " + stelleTranstionKante);
				}
			}
		});

		buttonKanten.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonKanten) {
					stelleTranstionKante = "kante";
					System.out.println("stelleTranstionKante = " + stelleTranstionKante);
				}
			}
		});

		// ToDo Add MouseListener here
		// MouseListener
		panel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
//				int x = e.getX();
//				int y = e.getY();
//				g2d = (Graphics2D) panel.getGraphics();
//				g2d.setColor(Color.black);
////				g2d.drawString("Hallo", x, y);
//				g2d.drawRect (e.getX(), e.getY(), 20, 20);
				

				ToDraw ToDraw = new ToDraw();
				ToDraw.drawRect(e.getX(), e.getY());
				
				ToDraw.setG2d((Graphics2D) panel.getGraphics());
//				g2d = (Graphics2D) panel.getGraphics();
				
				
				
			}
		});
		// MouseListener Ende

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
