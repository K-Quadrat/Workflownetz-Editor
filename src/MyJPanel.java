import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import sun.java2d.loops.DrawRect;

public class MyJPanel extends JPanel {

	private int click1X;
	private int click1Y;
	private int click2X;
	private int click2Y;
	private Boolean firstClick = true;
	private IModel model;
	

	public MyJPanel(IModel model) {
		this.model = model;

		// Generate few places
		model.setNode(200, 300, 50, ENode.PLACE, "Place number 1", false);

		// Generate few transitions
		model.setNode(200, 400, 50, ENode.TRANSITION, "Transition number 2", false);
		
		
		
		addMouseListener(mouseListener);
		addMouseMotionListener(motionListener);

		// Create frame for panel
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);

	}

	// standartcursor für bewegungen
	private Cursor CURSOR_MOVE = new Cursor(Cursor.MOVE_CURSOR);
	// defaultcursor
	private Cursor CURSOR_DEF = new Cursor(Cursor.DEFAULT_CURSOR);

	// Selected node
	private Node selectedNode = null;

	private MouseListener mouseListener = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
			// No node selected anymore!
			selectedNode = null;
			// getContentPane().setCursor(CURSOR_DEF);
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// Find the node that was clicked
			Node n = model.getNode(e.getX(), e.getY());
			if (n != null) {
				selectedNode = n;
				repaint();
				// getContentPane().setCursor(CURSOR_MOVE);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent e) {

			// TODO mouse click
			// int x = e.getX();
			// int y = e.getY();
			//
			// // drawTransition(e.getX(), e.getY(), g2d);
			// //
			// switch (placeTranstionArcPTArcTP) {
			// case 0:
			// drawStelle(x, y, g2d);
			// break;
			// case 1:
			// drawTransition(x, y, g2d);
			// break;
			// case 2:
			// if (firstClick) {
			// click1X = x;
			// click1Y = y;
			// firstClick = false;
			// } else if (!firstClick) {
			// click2X = x;
			// click2Y = y;
			// drawArcPT(click1X, click1Y, click2X, click2Y, g2d);
			// firstClick = true;
			// }
			// break;
			// case 3:
			// drawArcTP(x, y, g2d);
			// break;
			//
			// default:
			// break;
			// }

		}
	};

	private MouseMotionListener motionListener = new MouseMotionListener() {

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (selectedNode != null) {
				// Change coordinates of the node
				selectedNode.setX(e.getX());
				selectedNode.setY(e.getY());
				repaint();
			}
		}
	};

	/**
	 * Sucht einen Kreis mit den übergebenen Koordinaten
	 * 
	 * @param x
	 *            der x-Wert
	 * @param y
	 *            der y-Wert
	 * @return Kreis welcher den Punkt x/y enthält, sonst null
	 */
//	public Place findPlace(int x, int y) {
//		for (Place p : places) {
//			if (p.containsPoint(x, y)) {
//				return p;
//			}
//		}
//		return null;
//	}


	@Override
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}

	// draw all nodes
	private void drawNodes(Graphics2D g2d) {
		for (Node n : model.getAllNodes()) {
			// der momentan ausgewählte node erhält zur besseren übersicht einen roten rand
			// die anderen nodes bekommen einen schwarzen rahmen
			if (n.equals(selectedNode)) {
				g2d.setColor(Color.RED);
			} else {
				g2d.setColor(Color.BLACK);
			}
			if (n.getNodeType() == ENode.PLACE) {
				g2d.drawOval(n.getX(), n.getY(), n.getRadius(), n.getRadius());	
			}
			if (n.getNodeType() == ENode.TRANSITION) {
				g2d.drawRect(n.getX(), n.getY(), n.getRadius(), n.getRadius());

			}
			
		}
	}

	
	
	private void drawArcTest(Graphics2D g2d) {
		for (Node nt : model.getAllTransitions()) {
			for (Node np : model.getAllPlaces()) {
				g2d.setColor(Color.BLACK);
				if(np.getX()<nt.getX()) {
					g2d.drawLine(np.getX()+np.getRadius(), np.getY()+np.getRadius()/2, nt.getX(), nt.getY()+nt.getRadius()/2);
				}
				else if (np.getX()>nt.getX()) {
					g2d.drawLine(np.getX(), np.getY()+np.getRadius()/2, nt.getX()+nt.getRadius(), nt.getY()+nt.getRadius()/2);
				}
				else if (np.getY()>nt.getY()) {
					g2d.drawLine(np.getX()+np.getRadius()/2, np.getY(), nt.getX()+np.getRadius()/2, nt.getY()+nt.getRadius());
				}
				else if (np.getY()<nt.getY()) {
				g2d.drawLine(nt.getX()+nt.getRadius()/2, nt.getY(), np.getX()+np.getRadius()/2, np.getY()+np.getRadius());
				}
			}
		}
	}


	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Copied from Newsgroups?
		// int width = getWidth() - 100;
		// int height = getHeight() - 100;
		// int x = (getWidth() - width) / 2;
		// int y = (getHeight() - height) / 2;
		// g2d.setColor(Color.RED);
		// g2d.drawRect(x, y, width, height);
		// g2d.dispose();
		// weißes quadrat mahlen
//		g2d.setColor(Color.WHITE);
//		g2d.fillRect(0, 0, 8000, 4000);
		

		drawNodes(g2d);

		drawArcTest(g2d);

	}

}
