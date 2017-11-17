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

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import sun.java2d.loops.DrawRect;

public class MyJPanel extends JPanel implements IView {

	private int click1X;
	private int click1Y;
	private int click2X;
	private int click2Y;
	private int clickX;
	private int clickY;
	private Boolean firstClick = true;
	private IModel model;
	private JPopupMenu rightClickMenu;
	private PopupMenuController popupMenuController;
	private ViewController viewController;
	private ToolBarController toolBarController;
	private SelectedNode selectedNode;

	public MyJPanel(IModel model, PopupMenuController popupMenuController, ViewController viewController,
			ToolBarController toolBarController, SelectedNode selectedNode) {
		this.model = model;
		this.popupMenuController = popupMenuController;
		this.viewController = viewController;
		this.toolBarController = toolBarController;
		this.selectedNode = selectedNode;

		// Generate few places
		model.setNode("S1", 200, 300, 50, ENode.PLACE, "P1", false);
		model.setNode("S2", 300, 300, 50, ENode.PLACE, "P2", false);
		model.setNode("S3", 400, 300, 50, ENode.PLACE, "P3", false);
		model.setNode("S4", 500, 300, 50, ENode.PLACE, "P4", false);

		// Generate few transitions
		model.setNode("T1", 200, 400, 50, ENode.TRANSITION, "Wohnzimmer aufräumen", false);
		model.setNode("T2", 300, 400, 50, ENode.TRANSITION, "Wohnzimmer fegen", false);
		model.setNode("T3", 400, 400, 50, ENode.TRANSITION, "Spülmaschiene einräumen", false);
		model.setNode("T4", 500, 400, 50, ENode.TRANSITION, "Spülmaschiene starten", false);

		addMouseListener(mouseListener);
		addMouseMotionListener(motionListener);

		// Create frame for panel
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);

		// Create JPopupMenu
		rightClickMenu = new JPopupMenu();

		ActionListener menuListener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand().equals("Connect")) {
					popupMenuController.connect();
				}
				if (event.getActionCommand().equals("Delete")) {
					model.deleteNode(clickX, clickY);
					repaint();

				}
				if (event.getActionCommand().equals("Set name")) {

					for (Node n : model.getAllNodes()) {
						if (n.equals(selectedNode.getSelectedNodeRightClick())) {
							ViewSetName viewSetName = new ViewSetName(n);
							selectedNode.setSelectedNodeRightClick(null);
						}

					}
				}
			}
		};

		JMenuItem item;
		rightClickMenu.add(item = new JMenuItem("Connect"));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		item.addActionListener(menuListener);

		rightClickMenu.addSeparator();

		rightClickMenu.add(item = new JMenuItem("Delete"));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		item.addActionListener(menuListener);

		rightClickMenu.add(item = new JMenuItem("Set name"));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		item.addActionListener(menuListener);

	}

	// standartcursor für bewegungen
	private Cursor CURSOR_MOVE = new Cursor(Cursor.MOVE_CURSOR);
	// defaultcursor
	private Cursor CURSOR_DEF = new Cursor(Cursor.DEFAULT_CURSOR);

	private MouseListener mouseListener = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
			// No node selected anymore!
			selectedNode.setSelectedNode(null);
			// getContentPane().setCursor(CURSOR_DEF);
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// Right Click
			if (e.isPopupTrigger()) {
				// Find the node that was clicked
				Node n = model.getNode(e.getX(), e.getY());
				if (n != null) {
					selectedNode.setSelectedNodeRightClick(n);
				}
				rightClickMenu.show(e.getComponent(), clickX = e.getX(), clickY = e.getY());
			} else {
				// Find the node that was clicked
				Node n = model.getNode(e.getX(), e.getY());
				if (n != null) {
					selectedNode.setSelectedNode(n);
					repaint();
					// getContentPane().setCursor(CURSOR_MOVE);
				}

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

			switch (toolBarController.getToolBarSwitch()) {
			case 2:
				viewController.addPlace(e.getX(), e.getY());
				break;
			case 3:
				viewController.addTransition(e.getX(), e.getY());
				break;
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

			default:
				break;
			}

		}

	};

	private MouseMotionListener motionListener = new MouseMotionListener() {

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (selectedNode.getSelectedNode() != null) {
				// Change coordinates of the node
				selectedNode.getSelectedNode().setX(e.getX());
				selectedNode.getSelectedNode().setY(e.getY());
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
	// public Place findPlace(int x, int y) {
	// for (Place p : places) {
	// if (p.containsPoint(x, y)) {
	// return p;
	// }
	// }
	// return null;
	// }

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}

	// draw all nodes
	private void drawNodes(Graphics2D g2d) {
		for (Node n : model.getAllNodes()) {
			// der momentan ausgewählte node erhält zur besseren übersicht einen roten rand
			// die anderen nodes bekommen einen schwarzen rahmen
			if (n.equals(selectedNode.getSelectedNode())) {
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
				if (np.getX() < nt.getX()) {
					g2d.drawLine(np.getX() + np.getRadius(), np.getY() + np.getRadius() / 2, nt.getX(),
							nt.getY() + nt.getRadius() / 2);
				} else if (np.getX() > nt.getX()) {
					g2d.drawLine(np.getX(), np.getY() + np.getRadius() / 2, nt.getX() + nt.getRadius(),
							nt.getY() + nt.getRadius() / 2);
				} else if (np.getY() > nt.getY()) {
					g2d.drawLine(np.getX() + np.getRadius() / 2, np.getY(), nt.getX() + np.getRadius() / 2,
							nt.getY() + nt.getRadius());
				} else if (np.getY() < nt.getY()) {
					g2d.drawLine(nt.getX() + nt.getRadius() / 2, nt.getY(), np.getX() + np.getRadius() / 2,
							np.getY() + np.getRadius());
				}
			}
		}
	}

	private void drawName(Graphics2D g2d) {
		for (Node n : model.getAllNodes()) {
			if (n.getNodeType() == ENode.PLACE) {
				g2d.drawString(n.getName(), n.getX() + n.getRadius() / 2, n.getY() + n.getRadius() + 20);
			}
			if (n.getNodeType() == ENode.TRANSITION) {
				g2d.drawString(n.getName(), n.getX() - n.getRadius() / 2, n.getY() + n.getRadius() + 20);
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
		// g2d.setColor(Color.WHITE);
		// g2d.fillRect(0, 0, 8000, 4000);

		drawNodes(g2d);
		// drawArcTest(g2d);

		drawName(g2d);

	}

	@Override
	public void refresh() {
		repaint();
	}

}
