import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Multiselect {
	private List<String> multiselectedNodesIds = new ArrayList<String>();
	private Point multiselectFrom = new Point(0, 0);
	private Point multiselectTo = new Point(0, 0);
	private IModel model;
	private Point coordinatesFrom;
	private Point coordinatesTo;
	private GlobalSizeModel globalSizeModel;

	public Multiselect(IModel model, GlobalSizeModel globalSizeModel) {
		super();
		this.model = model;
		this.globalSizeModel = globalSizeModel;
	}

	public void addNodeMultiselectedNodesIds() {

		for (Node n : model.getAllNodes()) {
			if (multiselectFrom.x <= n.getX() && n.getX() <= multiselectTo.getX() && multiselectFrom.getY() <= n.getY()
					&& n.getY() <= multiselectTo.getY()) {
				multiselectedNodesIds.add(n.getId());
			}

		}
		System.out.println(multiselectedNodesIds);
	}

	public void clearMultiselect() {
		multiselectedNodesIds.clear();
	}

	public List<String> getMultiselectedNodesIds() {
		return multiselectedNodesIds;

	}

	/**
	 * @return the multiselectFrom
	 */
	public Point getMultiselectFrom() {
		return multiselectFrom;
	}

	/**
	 * @param multiselectFrom
	 *            the multiselectFrom to set
	 */
	public void setMultiselectFrom(Point multiselectFrom) {
		this.multiselectFrom = multiselectFrom;
	}

	/**
	 * @return the multiselectTo
	 */
	public Point getMultiselectTo() {
		return multiselectTo;
	}

	/**
	 * @param multiselectTo
	 *            the multiselectTo to set
	 */
	public void setMultiselectTo(Point multiselectTo) {
		this.multiselectTo = multiselectTo;
	}

	/**
	 * @return the coordinatesFrom
	 */
	public Point getCoordinatesFrom() {
		return coordinatesFrom;
	}

	/**
	 * @param coordinatesFrom
	 *            the coordinatesFrom to set
	 */
	public void setCoordinatesFrom(Point coordinatesFrom) {
		this.coordinatesFrom = coordinatesFrom;
	}

	/**
	 * @return the coordinatesTo
	 */
	public Point getCoordinatesTo() {
		return coordinatesTo;
	}

	/**
	 * @param coordinatesTo
	 *            the coordinatesTo to set
	 */
	public void setCoordinatesTo(Point coordinatesTo) {
		this.coordinatesTo = coordinatesTo;
	}

	public void setNewCoordinates() {
		int distanceX = coordinatesTo.x - coordinatesFrom.x;
		int distanceY = coordinatesTo.y - coordinatesFrom.y;

		for (Node n : model.getAllNodes()) {
			for (int i = 0; i < getMultiselectedNodesIds().size(); i++) {
				if (n.getId().equals(getMultiselectedNodesIds().get(i))) {
					if (model.getSmallestPoint().x <= globalSizeModel.getNodesSize()/2) {
						if (n.getX() < (n.getX() + distanceX / 20)) {
							n.setX(n.getX() + distanceX / 20);
						}

					} else {
						n.setX(n.getX() + distanceX / 20);
					}

					if (model.getSmallestPoint().y <= globalSizeModel.getNodesSize()/2) {
						if (n.getY() < (n.getY() + distanceY / 20)) {
							n.setY(n.getY() + distanceY / 20);
						}

					} else {
						n.setY(n.getY() + distanceY / 20);
					}

				}
			}
		}
	}

}
