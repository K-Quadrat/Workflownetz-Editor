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
	
}