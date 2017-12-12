package de.hartschen.wne;

import java.awt.*;

/**
 * Controller Klasse für die Pfeile
 * 
 * @author Jens Hartschen
 *
 */
public class ArcsController {
	private Point sourcePoint;
	private Point targetPoint;
	private IModel model;
	private ArcsModel arcsModel;
	private GlobalSizeModel globalSizeModel;
	private boolean hit;
	private boolean hitSource;
	private boolean hitTarget;

	/**
	 * Konstruktor der ArcsController Klasse.
	 * 
	 * @param model
	 * @param arcsModel
	 * @param globalSizeModel
	 */
	public ArcsController(IModel model, ArcsModel arcsModel, GlobalSizeModel globalSizeModel) {
		this.model = model;
		this.arcsModel = arcsModel;
		this.globalSizeModel = globalSizeModel;

	}

	/**
	 * Die Methode setzt die Pfeil Koordinaten an die Au­ßen­kan­te der nodes.
	 */
	public void setPosition() {

		for (Arc a : arcsModel.getArcs()) {

			for (Node n : model.getAllNodes()) {
				if (n.getId().equals(a.getSource())) {
					sourcePoint = new Point(n.getX(), n.getY());
					hit = true;
				}
				if (hit) {
					for (Node ntarget : model.getAllNodes()) {
						if (ntarget.getId().equals(a.getTarget())) {
							targetPoint = new Point(ntarget.getX(), ntarget.getY());

							if (sourcePoint.x < targetPoint.x) {
								sourcePoint.x = sourcePoint.x + globalSizeModel.getNodesSize();
								sourcePoint.y = sourcePoint.y + globalSizeModel.getNodesSize() / 2;
								targetPoint.y = targetPoint.y + globalSizeModel.getNodesSize() / 2;

							} else if (sourcePoint.x > targetPoint.x) {
								sourcePoint.y = sourcePoint.y + globalSizeModel.getNodesSize() / 2;
								targetPoint.x = targetPoint.x + globalSizeModel.getNodesSize();
								targetPoint.y = targetPoint.y + globalSizeModel.getNodesSize() / 2;

							} else if (sourcePoint.y > targetPoint.y) {
								sourcePoint.x = sourcePoint.x + globalSizeModel.getNodesSize() / 2;
								targetPoint.x = targetPoint.x + globalSizeModel.getNodesSize() / 2;
								targetPoint.y = targetPoint.y + globalSizeModel.getNodesSize();

							} else if (sourcePoint.y < targetPoint.y) {
								sourcePoint.x = sourcePoint.x + globalSizeModel.getNodesSize() / 2;
								targetPoint.x = targetPoint.x + globalSizeModel.getNodesSize() / 2;
								sourcePoint.y = sourcePoint.y + globalSizeModel.getNodesSize();
							}

							a.modifyArc(sourcePoint, targetPoint, globalSizeModel.getArcsSize());
							hit = false;
						}
					}

				}
			}

		}

	}

	/**
	 * Die Methode entfernt alle Pfeile ohne Verbindung zu eine node.
	 */
	public void removeNotUsedArcs() {

		for (int i = 0; i < arcsModel.getArcs().size(); i++) {
			Arc a = arcsModel.getArcs().get(i);

			for (int j = 0; j < model.getAllNodes().size(); j++) {
				Node n = model.getAllNodes().get(j);
				if (n.getId().equals(a.getSource())) {
					hitSource = true;
				}
				if (n.getId().equals(a.getTarget())) {
					hitTarget = true;
				}

			}

			if (!hitSource || !hitTarget) {
				arcsModel.deleteArcById(a.getId());
				i--;
			}
			hitSource = false;
			hitTarget = false;

		}

	}
}