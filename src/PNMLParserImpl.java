import java.awt.Color;
import java.io.File;

public class PNMLParserImpl extends PNMLParser {
	private IModel model;
	private IView iView;

	// Constructor
	public PNMLParserImpl(File pnml, IModel model, IView iView) {
		super(pnml);
		this.model = model;
		this.iView = iView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PNMLParser#newTransition(java.lang.String)
	 */
	@Override
	public void newTransition(String id) {
		model.setNode(id, 0, 0, 50, ENode.TRANSITION, "");
		iView.refresh();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PNMLParser#newPlace(java.lang.String)
	 */
	@Override
	public void newPlace(String id) {
		model.setNode(id, 0, 0, 50, ENode.PLACE, "", false);
		iView.refresh();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PNMLParser#newArc(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void newArc(String id, String source, String target) {
		model.setArc(id, source, target);
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
				n.setMarking(Boolean.parseBoolean(marking));
			}
		}
		iView.refresh();
	}

}
