import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class ArcWithHeadController {
	private List<ArcWithHead> arcsWithHead = new ArrayList<ArcWithHead>();
	private Math m;
	private Point sourcePoint;
	private Point targetPoint;
	private Point von, nach; // die zwei Punkte, die die Pfeil-Linie darstellen
	private double phi; // Rotationswinkel
	private Polygon pfeil;
	private double s; // Seitenlaenge des Pfeils
	private double xAlt, yAlt; // x-,y-Werte vor der Rotation,relativ zur Pfeilspitze
	private double xNeu, yNeu; // x-,y-Werte nach der Rotation,absolut
	private double xSpitze, ySpitze; // x-,y-Werte der Pfeilspitze
	private IModel model;
	private int radius;
	private int sourceX;
	private int sourceY;
	private int targetX;
	private int targetY;
	private ArcsWithHeadModel arcsWithHeadModel;
	private ENode sourceNodeType;
	private ENode targetNodeType;

	public ArcWithHeadController(IModel model, ArcsWithHeadModel arcsWithHeadModel) {
		this.model = model;
		this.arcsWithHeadModel = arcsWithHeadModel;

	}

	public void arcConverter() {
		sourcePoint = null;
		targetPoint = null;
		
		for (Arc a : model.getArcs()) {

			for (Node n : model.getAllNodes()) {

				if (a.getSource().equals(n.getId())) {
					System.out.println("Treffer source");
					sourceX = n.getX();
					sourceY = n.getY();
					sourceNodeType = n.getNodeType();
					sourcePoint = new Point(sourceX, sourceY);
					System.out.println(n.getId());
					System.out.println(sourceX);
					System.out.println(sourceY);
				}
				if (sourcePoint != null) {
					if(a.getTarget().equals(n.getId())) {
						System.out.println("Treffer target");
						targetX = n.getX();
						targetY = n.getY();
						targetNodeType = n.getNodeType();
						targetPoint = new Point(targetX, targetY);
						System.out.println(n.getId());
						System.out.println(targetX);
						System.out.println(targetY);
						radius = n.getRadius();
						arcsWithHeadModel.setArcWithHead(sourcePoint, targetPoint, 10.0d, sourceNodeType, targetNodeType);
						sourcePoint = null;
					}
					
					
				}
			
//				radius = n.getRadius();
//				arcsWithHeadModel.setArcWithHead(new Point(300, 500), new Point(500, 700), 100.0d);
//				sourcePoint = null;
//				targetPoint = null;

			}

		}
//		arcsWithHeadModel.setArcWithHead(new Point(150, 50), new Point(250, 100), 10.0d);
//		arcsWithHeadModel.setArcWithHead(new Point(250, 100), new Point(150, 250), 10.0d);
//		arcsWithHeadModel.setArcWithHead(new Point(50, 200), new Point(150, 50), 10.0d);

	}

}