package de.hartschen.wne;

import java.awt.Color;
import java.awt.Point;
import java.io.File;

public class PNMLParserImpl extends PNMLParser {
	private IModel model;
	private IView iView;
	private ArcsModel arcsModel;
	private GlobalSizeModel globalSizeModel;

	// Constructor
	public PNMLParserImpl(File pnml, IModel model, IView iView, ArcsModel arcsModel, GlobalSizeModel globalSizeModel) {
		super(pnml);
		this.model = model;
		this.iView = iView;
		this.arcsModel = arcsModel;
		this.globalSizeModel = globalSizeModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PNMLParser#newTransition(java.lang.String)
	 */
	@Override
	public void newTransition(String id) {
		model.setNode(id, 0, 0, globalSizeModel.getNodesSize(), ENode.TRANSITION, "");
		iView.refresh();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PNMLParser#newPlace(java.lang.String)
	 */
	@Override
	public void newPlace(String id) {
		model.setNode(id, 0, 0, globalSizeModel.getNodesSize(), ENode.PLACE, "", false);
		iView.refresh();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PNMLParser#newArc(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void newArc(String id, String source, String target) {
		arcsModel.setArc(id, source, target, new Point(0, 0), new Point(0, 0), 0);
		iView.refresh();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PNMLParser#setPosition(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void setPosition(String id, String x, String y) {
		for (Node n : model.getAllNodes()) {
			if (n.getId().equals(id)) {
				n.setX(Integer.parseInt(x));
				n.setY(Integer.parseInt(y));
			}
		}
		iView.refresh();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PNMLParser#setName(java.lang.String, java.lang.String)
	 */
	@Override
	public void setName(String id, String name) {
		for (Node n : model.getAllNodes()) {
			if (n.getId().equals(id)) {
				n.setName(name);
			}
		}
		iView.refresh();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PNMLParser#setMarking(java.lang.String, java.lang.String)
	 */
	@Override
	public void setMarking(String id, String marking) {
		for (Node n : model.getAllNodes()) {
			if (n.getId().equals(id)) {
				if (marking.equals("0")) {
					n.setMarking(false);
				}
				if (marking.equals("1")) {
					n.setMarking(true);
				}
			}
		}
		iView.refresh();
	}

}
