import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class ArcsModel {
	private boolean containsPoint = false;

	private List<Arc> arcs = new ArrayList<Arc>();
	private int index;

	public void setArc(String id, String source, String target, Point k1, Point k2, double length) {
		arcs.add(new Arc(id, source, target, k1, k2, length));
	}

	public List<Arc> getArcs() {
		return arcs;

	}

	public void deleteArcById(String id) {
		for (Arc a : getArcs()) {
			if (a.getId().equals(id)) {
				index = arcs.indexOf(a);
			}
		}
		arcs.remove(index);
	}
	
	
	public void deleteArc(int x, int y) {
		for (Arc a : arcs) {
			if (a.containsPoint(x, y)) {
				index = arcs.indexOf(a);
				containsPoint = true;
			}

		}

		if (containsPoint) {
			arcs.remove(index);
			containsPoint = false;
		}

	}
	
	public void clearArcsList() {
		arcs.clear();
	}

}
