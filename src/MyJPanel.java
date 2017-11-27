import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
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

import sun.java2d.loops.DrawLine;
import sun.java2d.loops.DrawRect;

public class MyJPanel extends JPanel implements IView {

	private int click1X;
	private int click1Y;
	private int click2X;
	private int click2Y;
	private int clickX;
	private int clickY;
	private String firstClickNodeId;
	private String secondClickNodeId;
	private Boolean firstClick = true;
	private ENode nodeType;
	private IModel model;
	private JPopupMenu rightClickMenu;
	private PopupMenuController popupMenuController;
	private ViewController viewController;
	private ToolBarController toolBarController;
	private SelectedNode selectedNode;
	private Point sourcePoint;
	private Point targetPoint;
	private int radius;
	private ArcsModel arcsModel;
	private ArcsController arcsController;
	private GlobalSizeModel globalSizeModel;
	private StatusBar statusBar;
	private SwitchTransition switchTransition;

	public MyJPanel(IModel model, PopupMenuController popupMenuController, ViewController viewController,
			ToolBarController toolBarController, SelectedNode selectedNode, ArcsModel arcsModel,
			ArcsController arcsController, GlobalSizeModel globalSizeModel, StatusBar statusBar, SwitchTransition switchTransition) {
		this.model = model;
		this.popupMenuController = popupMenuController;
		this.viewController = viewController;
		this.toolBarController = toolBarController;
		this.selectedNode = selectedNode;
		this.arcsModel = arcsModel;
		this.arcsController = arcsController;
		this.globalSizeModel = globalSizeModel;
		this.statusBar = statusBar;
		this.switchTransition = switchTransition;

		// Generate few places
		// model.setNode("S1", 200, 300, 50, ENode.PLACE, "P1", false);
		// model.setNode("S2", 300, 300, 50, ENode.PLACE, "P2", false);
		// model.setNode("S3", 400, 300, 50, ENode.PLACE, "P3", false);
		// model.setNode("S4", 500, 300, 50, ENode.PLACE, "P4", false);

		// Generate few transitions
		// model.setNode("T1", 200, 400, 50, ENode.TRANSITION, "Wohnzimmer aufräumen");
		// model.setNode("T2", 300, 400, 50, ENode.TRANSITION, "Wohnzimmer fegen");
		// model.setNode("T3", 400, 400, 50, ENode.TRANSITION, "Spülmaschiene
		// einräumen");
		// model.setNode("T4", 500, 400, 50, ENode.TRANSITION, "Spülmaschiene starten");

		// Generate few arcs
		// model.setArc("K1", "S1", "T1");
		// model.setArc("K2", "T1", "S2");
		// model.setArc("K3", "S2", "T3");

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

					for (Node n : model.getAllTransitions()) {
						if (n.equals(selectedNode.getSelectedNodeRightClick())) {
							switchTransition.switchTransition(n);
							selectedNode.setSelectedNodeRightClick(null);
						}

					}
					refresh();
				}
				if (event.getActionCommand().equals("Delete Node")) {
					model.deleteNode(clickX, clickY);
					arcsController.removeNotUsedArcs();
					repaint();

				}

				if (event.getActionCommand().equals("Delete Arc")) {
					arcsModel.deleteArc(clickX, clickY);
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

		rightClickMenu.add(item = new JMenuItem("Delete Node"));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		item.addActionListener(menuListener);

		rightClickMenu.add(item = new JMenuItem("Delete Arc"));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		item.addActionListener(menuListener);

		rightClickMenu.add(item = new JMenuItem("Set name"));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		item.addActionListener(menuListener);

	}

	public MyJPanel() {
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
			case 4:
				// Find the node that was clicked
				Node n = model.getNode(e.getX(), e.getY());

				if (firstClick && n != null) {

					nodeType = n.getNodeType();
					firstClickNodeId = n.getId();
					firstClick = false;

				} else if (!firstClick && n != null && nodeType != n.getNodeType()) {
					secondClickNodeId = n.getId();
					viewController.addArc(firstClickNodeId, secondClickNodeId);

					firstClick = true;
					firstClickNodeId = null;
					secondClickNodeId = null;
					nodeType = null;
					
				} else if (n == null || nodeType == n.getNodeType()) {
					firstClick = true;
				}

				break;

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
				if(switchTransition.transitionActive(n.getId())) {
					g2d.setColor(Color.GREEN);
					g2d.fillRect(n.getX(), n.getY(), n.getRadius(), n.getRadius());
					
				}else {
					g2d.drawRect(n.getX(), n.getY(), n.getRadius(), n.getRadius());
				}
					
				
				

			}

		}
	}

	private void drawName(Graphics2D g2d) {
		for (Node n : model.getAllNodes()) {
			g2d.setColor(Color.BLACK);
			if (n.getNodeType() == ENode.PLACE) {
				g2d.drawString(n.getName(), n.getX() + n.getRadius() / 2, n.getY() + n.getRadius() + 20);
			}
			if (n.getNodeType() == ENode.TRANSITION) {
				g2d.drawString(n.getName(), n.getX() - n.getRadius() / 2, n.getY() + n.getRadius() + 20);
			}
		}
	}

	public void drawArc(Graphics2D g2d) {

		for (Arc a : arcsModel.getArcs()) {

			g2d.drawLine(a.getVon().x, a.getVon().y, a.getNach().x, a.getNach().y);
			g2d.fillPolygon(a.getPfeil());
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
		drawName(g2d);

		arcsController.setPosition();

		drawArc(g2d);
		statusBar.setMessage("Hello");
		
		
		
//		if (switchTransition.areAllNetworkElementsOnThePath()) {
//			statusBar.setMessage("All network elements on a path from start place to end place");
//		}
		if (!switchTransition.areAllNetworkElementsOnThePath()) {
			statusBar.setMessage("Not all network elements on a path from start place to end place");
		}
		
		statusBar.setMessage(switchTransition.hasStartingEndingPlaces());
		
		
		// arcWithHeadController.arcConverter();
		// drawArc(g2d);

		// listeA.contains( elementAusListeB ); //true oder false

		//
		// if (a.getSource().equals(n.getName())) {
		// sourcePoint.setLocation(n.getX(), n.getY());
		// System.out.println(sourcePoint);
		// }
		//
		// if (a.getTarget().equals(n.getName())) {
		// targetPoint.setLocation(n.getX(), n.getY());
		// radius = n.getRadius();
		// System.out.println(targetPoint);
		// System.out.println(radius);
		//
		// }
		//
		// }

		// }

	}

	@Override
	public void refresh() {
		repaint();
	}

}
