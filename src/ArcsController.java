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

			for (Node n : model.getAllNodes()) {
				if (n.getId().equals(a.getSource())) {
					sourcePoint = new Point(n.getX(), n.getY());
					treffer = true;
				}
				if (treffer) {
					for (Node ntarget : model.getAllNodes()) {
						if (ntarget.getId().equals(a.getTarget())) {
							targetPoint = new Point(ntarget.getX(), ntarget.getY());

							if (sourcePoint.x < targetPoint.x) {
								sourcePoint.x = sourcePoint.x + globalSizeModel.getNodesSize();
								sourcePoint.y = sourcePoint.y + globalSizeModel.getNodesSize()/2;
								targetPoint.y = targetPoint.y + globalSizeModel.getNodesSize()/2;

							} else if (sourcePoint.x > targetPoint.x) {
								sourcePoint.y = sourcePoint.y + globalSizeModel.getNodesSize()/2;
								targetPoint.x = targetPoint.x + globalSizeModel.getNodesSize();
								targetPoint.y = targetPoint.y + globalSizeModel.getNodesSize()/2;
								
							} else if (sourcePoint.y > targetPoint.y) {
								sourcePoint.x = sourcePoint.x + globalSizeModel.getNodesSize()/2;
								targetPoint.x = targetPoint.x + globalSizeModel.getNodesSize()/2;
								targetPoint.y = targetPoint.y + globalSizeModel.getNodesSize();

							} else if (sourcePoint.y < targetPoint.y) {
								sourcePoint.x = sourcePoint.x + globalSizeModel.getNodesSize()/2;
								targetPoint.x = targetPoint.x + globalSizeModel.getNodesSize()/2;
								sourcePoint.y = sourcePoint.y + globalSizeModel.getNodesSize();
//								
							}

							a.modifyArc(sourcePoint, targetPoint, globalSizeModel.getArcsSize());
							treffer = false;
						}
					}

				}
			}

		}

	}
}