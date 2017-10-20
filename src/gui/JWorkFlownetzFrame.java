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
	private int stelleTranstionKante = 5;
	private int transitionX = 50;
	private int transitionY = 50;
	private int transitionW = 20;
	private int transitionH = 20;
	private Graphics2D g2d;
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
					stelleTranstionKante = 0;
					System.out.println("stelleTranstionKante = " + stelleTranstionKante + " Stelle");
				}
			}
		});

		buttonTransitionen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonTransitionen) {
					stelleTranstionKante = 1;
					System.out.println("stelleTranstionKante = " + stelleTranstionKante + " Transtion");
				}
			}
		});

		buttonKanten.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonKanten) {
					stelleTranstionKante = 2;
					System.out.println("stelleTranstionKante = " + stelleTranstionKante + " Kante");
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
				switch (stelleTranstionKante) {
				case 0:
					drawStelle(x, y, g2d);
					break;
				case 1:
					drawTransition(x, y, g2d);
					break;
				case 2:
					drawKante(x, y, g2d);
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
		g2d.drawOval(x, y, 20, 20);
	}

	void drawTransition(int x, int y, Graphics2D g2d) {
		g2d = (Graphics2D) panel.getGraphics();
		g2d.setColor(Color.black);
		g2d.drawRect(x, y, 20, 20);
	}

	void drawKante(int x, int y, Graphics2D g2d) {
		g2d = (Graphics2D) panel.getGraphics();
		g2d.setColor(Color.black);
		g2d.drawString("Hallo", x, y);
		// g2d.drawLine(x1, y1, x2, y2);
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
