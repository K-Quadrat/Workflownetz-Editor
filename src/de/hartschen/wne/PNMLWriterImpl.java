package de.hartschen.wne;

import java.io.File;

public class PNMLWriterImpl extends PNMLWriter {

	private IModel model;
	private ArcsModel arcsModel;

	public PNMLWriterImpl(File pnml, IModel model, ArcsModel arcsModel) {
		super(pnml);
		this.model = model;
		this.arcsModel = arcsModel;
	}

	public void writeAllToFile() {

		startXMLDocument();
		for (Node n : model.getAllTransitions()) {
			addTransition(n.getId(), n.getName(), Integer.toString(n.getX()), Integer.toString(n.getY()));
		}

		for (Node n : model.getAllPlaces()) {
			addPlace(n.getId(), n.getName(), Integer.toString(n.getX()), Integer.toString(n.getY()),
					String.valueOf(n.getMarking()));
		}

		for (Arc a : arcsModel.getArcs()) {
			addArc(a.getId(), a.getSource(), a.getTarget());

		}
		finishXMLDocument();
	}
}
