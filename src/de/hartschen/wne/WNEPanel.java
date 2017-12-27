package de.hartschen.wne;

import java.awt.Color;
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
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

/**
 * Die Klasse WNEPanel definiert ein JPane zum Zeichnen.
 * 
 * @author Jens Hartschen
 *
 */
public class WNEPanel extends JPanel implements IView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int clickX;
	private int clickY;
	private String firstClickNodeId;
	private String secondClickNodeId;
	private Boolean firstClick = true;
	private ENode nodeType;
	private IModel model;
	private JPopupMenu rightClickMenu;
	private JPopupMenu rightClickMenuMultiselect;;
	private ViewController viewController;
	private ToolBarController toolBarController;
	private SelectedNode selectedNode;
	private ArcsModel arcsModel;
	private ArcsController arcsController;
	private SwitchTransition switchTransition;
	private Multiselect multiselect;
	private SetStartMark setStartMark;
	private IView iView;
	private Warshall warshall;

	/**
	 * Konstruktor der WNEPanel Klasse.
	 * 
	 * @param model
	 * @param viewController
	 * @param toolBarController
	 * @param selectedNode
	 * @param arcsModel
	 * @param arcsController
	 * @param globalSizeModel
	 * @param statusBar
	 * @param switchTransition
	 * @param multiselect
	 * @param animationMode
	 * @param setStartMark
	 * @param warshall
	 */
	public WNEPanel(IModel model, ViewController viewController, ToolBarController toolBarController,
			SelectedNode selectedNode, ArcsModel arcsModel, ArcsController arcsController,
			GlobalSizeModel globalSizeModel, StatusBar statusBar, SwitchTransition switchTransition,
			Multiselect multiselect, AnimationMode animationMode, SetStartMark setStartMark, Warshall warshall) {
		this.model = model;
		this.viewController = viewController;
		this.toolBarController = toolBarController;
		this.selectedNode = selectedNode;
		this.arcsModel = arcsModel;
		this.arcsController = arcsController;
		this.switchTransition = switchTransition;
		this.multiselect = multiselect;
		this.setStartMark = setStartMark;
		this.warshall = warshall;

		addMouseListener(mouseListener);
		addMouseMotionListener(motionListener);

		// Create frame for panel
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);

		// Create JPopupMenu
		rightClickMenu = new JPopupMenu();

		// Create JPopupMenu for multiselect
		rightClickMenuMultiselect = new JPopupMenu();

		ActionListener menuListener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand().equals("Switch transition")) {

					for (Node n : model.getAllTransitions()) {
						if (n.equals(selectedNode.getSelectedNodeRightClick())) {
							switchTransition.switchTransition(n);
							selectedNode.setSelectedNodeRightClick(null);
						}

					}
					refresh();
				}
				if (event.getActionCommand().equals("Delete Node")) {
					animationMode.setAnimationMode(false);
					model.deleteNode(clickX, clickY);
					arcsController.removeNotUsedArcs();
					setStartMark.setStartMarking();
					refresh();

				}

				if (event.getActionCommand().equals("Delete Arc")) {
					arcsModel.deleteArc(clickX, clickY);
					setStartMark.setStartMarking();
					refresh();

				}

				if (event.getActionCommand().equals("Set name")) {

					for (Node n : model.getAllNodes()) {
						if (n.equals(selectedNode.getSelectedNodeRightClick())) {
							new ViewSetName(n, iView);
							selectedNode.setSelectedNodeRightClick(null);
						}

					}
				}

				if (event.getActionCommand().equals("Delete all selected items")) {
					animationMode.setAnimationMode(false);
					multiselect.deleteMultiselectedNodes();
					arcsController.removeNotUsedArcs();
					setStartMark.setStartMarking();
					refresh();

				}

			}
		};

		JMenuItem item;
		rightClickMenu.add(item = new JMenuItem("Switch transition"));
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

		rightClickMenuMultiselect.add(item = new JMenuItem("Delete all selected items"));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		item.addActionListener(menuListener);

	}

	/**
	 * Standard Konstruktor der WNEPanel Klasse.
	 */
	public WNEPanel() {
	}

	private MouseListener mouseListener = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
			// if multiselect is activated
			if (toolBarController.getToolBarSwitch() == 1) {
				if (e.isPopupTrigger()) {
					rightClickMenuMultiselect.show(e.getComponent(), clickX = e.getX(), clickY = e.getY());
				}
			}

			// Right Click
			else if (e.isPopupTrigger()) {
				// Find the node that was clicked
				Node n = model.getNode(e.getX(), e.getY());
				if (n != null) {
					selectedNode.setSelectedNodeRightClick(n);
				}
				rightClickMenu.show(e.getComponent(), clickX = e.getX(), clickY = e.getY());
			} else {
				// No node selected anymore!
				selectedNode.setSelectedNode(null);

			}
			refresh();

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// if multiselect is activated
			if (toolBarController.getToolBarSwitch() == 1) {
				if (e.isPopupTrigger()) {
					rightClickMenuMultiselect.show(e.getComponent(), clickX = e.getX(), clickY = e.getY());
				}

				// Is shift down
				else if (e.isShiftDown()) {
					// Set from point for multiselect
					multiselect.setMultiselectFrom(new Point(e.getX(), e.getY()));
					// System.out.println(multiselect.getMultiselectFrom());

				} else {
					multiselect.setCoordinatesFrom(new Point(e.getX(), e.getY()));

				}

			} else {
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
						refresh();
					}

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
			case 0:
				multiselect.clearMultiselect();
				refresh();
				break;
			case 1:
				multiselect.clearMultiselect();
				refresh();
				break;
			case 2:
				viewController.addPlace(e.getX(), e.getY());
				setStartMark.setStartMarking();
				refresh();
				break;
			case 3:
				viewController.addTransition(e.getX(), e.getY());
				setStartMark.setStartMarking();
				refresh();
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
				setStartMark.setStartMarking();
				refresh();
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
			if (selectedNode.getSelectedNode() != null && toolBarController.getToolBarSwitch() != 1) {
				// Change coordinates of the node
				selectedNode.setNewCoordinatesForSelectedNode(new Point(e.getX(), e.getY()));
			}

			if (toolBarController.getToolBarSwitch() == 1) {
				// Right Click
				if (e.isShiftDown()) {
					// Set to point for multiselect
					multiselect.setMultiselectTo(new Point(e.getX(), e.getY()));
					System.out.println(multiselect.getMultiselectTo());
					multiselect.clearMultiselect();
					multiselect.addNodeMultiselectedNodesIds();

				} else {
					multiselect.setCoordinatesTo(new Point(e.getX(), e.getY()));
					multiselect.setNewCoordinates();

				}
			}
			refresh();
		}
	};

	/**
	 * Die Methode stellt alle nodes auf dem panel da.
	 * 
	 * @param g2d
	 */
	private void drawNodes(Graphics2D g2d) {
		for (Node n : model.getAllNodes()) {
			// der momentan ausgewählte node erhält zur besseren übersicht einen roten rand
			// die anderen nodes bekommen einen schwarzen rahmen
			if (n.equals(selectedNode.getSelectedNode()) && toolBarController.getToolBarSwitch() != 1) {
				g2d.setColor(Color.RED);
			} else {
				g2d.setColor(Color.BLACK);
			}

			if (toolBarController.getToolBarSwitch() == 1) {
				for (int i = 0; i < multiselect.getMultiselectedNodesIds().size(); i++) {
					if (n.getId().equals(multiselect.getMultiselectedNodesIds().get(i))) {
						g2d.setColor(Color.RED);
					}
				}
			}

			if (n.getNodeType() == ENode.PLACE) {
				g2d.drawOval(n.getX(), n.getY(), n.getRadius(), n.getRadius());
			}
			if (n.getNodeType() == ENode.TRANSITION) {
				if (switchTransition.transitionActive(n.getId()) && !switchTransition.contact(n.getId())) {
					g2d.setColor(Color.GREEN);
					g2d.fillRect(n.getX(), n.getY(), n.getRadius(), n.getRadius());

				}
				if (switchTransition.transitionActive(n.getId()) && switchTransition.contact(n.getId())) {
					g2d.setColor(Color.RED);
					g2d.fillRect(n.getX(), n.getY(), n.getRadius(), n.getRadius());

				}

				else {
					g2d.drawRect(n.getX(), n.getY(), n.getRadius(), n.getRadius());
				}

			}
			if (n.getId().equals(switchTransition.getStartNodeClass())) {
				g2d.setColor(Color.GRAY);
				g2d.fillOval(n.getX(), n.getY(), n.getRadius(), n.getRadius());
			}

			if (n.getId().equals(switchTransition.getEndNodeClass())) {
				g2d.setColor(Color.DARK_GRAY);
				g2d.fillOval(n.getX(), n.getY(), n.getRadius(), n.getRadius());
			}

		}
	}

	/**
	 * Die Methode stellt alle namen der nodes auf dem panel da.
	 * 
	 * @param g2d
	 */
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

	/**
	 * Die Methode stellt alle Pfeile auf dem panel da.
	 * 
	 * @param g2d
	 */
	public void drawArc(Graphics2D g2d) {

		for (Arc a : arcsModel.getArcs()) {

			g2d.drawLine(a.getFrom().x, a.getFrom().y, a.getTo().x, a.getTo().y);
			g2d.fillPolygon(a.getArrow());
		}
	}

	/**
	 * Die Methode stellt die Markierung auf den places da.
	 * 
	 * @param g2d
	 */
	public void drawMarking(Graphics2D g2d) {

		for (Node n : model.getAllPlaces()) {
			if (n.getMarking()) {
				g2d.setColor(Color.BLACK);
				g2d.fillOval(n.getX() + n.getRadius() / 2 - n.getRadius() / 15,
						n.getY() + n.getRadius() / 2 - n.getRadius() / 15, n.getRadius() / 6, n.getRadius() / 6);

			}

		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		arcsController.setPosition();
		switchTransition.deadlock();
		switchTransition.isWorkflowNet();
		switchTransition.reachedTheEndMarking();

		drawNodes(g2d);
		drawName(g2d);
		drawArc(g2d);
		drawMarking(g2d);
		setPreferredSize(new Dimension(model.getLargestPoint().x + 100, model.getLargestPoint().y + 100));

		warshall.check();
		switchTransition.deadlock();
		revalidate();
	}

	/**
	 * Die Methode setzt eine Referenz des iView Objekts.
	 * 
	 * @param iView
	 */
	public void setIViewReference(IView iView) {
		this.iView = iView;

	}

	/**
	 * Die Methode setzt eine Referenz des ScrollPane.
	 * 
	 * @param scrollPane
	 */
	public void setScrollPaneReference(JScrollPane scrollPane) {
	}

	@Override
	public void refresh() {
		revalidate();
		repaint();
	}

}
