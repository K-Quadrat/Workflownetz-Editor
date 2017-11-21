import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class ArcsController {
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
	private ArcsModel arcsModel;
	private ENode sourceNodeType;
	private ENode targetNodeType;
	private GlobalSizeModel globalSizeModel;
	private boolean treffer;

	public ArcsController(IModel model, ArcsModel arcsModel, GlobalSizeModel globalSizeModel) {
		this.model = model;
		this.arcsModel = arcsModel;
		this.globalSizeModel = globalSizeModel;

	}

	public void setPosition() {

		for (Arc a : arcsModel.getArcs()) {
//			System.out.println(a.getId());
//			System.out.println(a.getSource());
//			System.out.println(a.getTarget());

			for (Node n : model.getAllNodes()) {
				if (n.getId().equals(a.getSource())) {
//					System.out.println("Treffer source");
					sourcePoint = new Point(n.getX(), n.getY());
//					System.out.println(n.getId());
//					System.out.println(sourcePoint);
					treffer = true;
				}
				if (treffer) {
					for (Node ntarget : model.getAllNodes()) {
						if (ntarget.getId().equals(a.getTarget())) {
//							System.out.println("Treffer target");
							targetPoint = new Point(ntarget.getX(), ntarget.getY());
//							System.out.println(ntarget.getId());
//							System.out.println(targetPoint);
							a.modifyArc(sourcePoint, targetPoint, globalSizeModel.getArcsSize());
							treffer = false;
						}
					}

				}
//				System.out.println(n.getId());
//				System.out.println(n.getX());
//				System.out.println(n.getY());
			}

		}

		
		
		
	}
}