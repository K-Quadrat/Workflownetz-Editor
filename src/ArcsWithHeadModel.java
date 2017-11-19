import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class ArcsWithHeadModel {

	private List<ArcWithHead> arcsWithHead = new ArrayList<ArcWithHead>();

	/**
	 * @param arcsWithHead
	 *            the arcsWithHead to set
	 */
	public void setArcWithHead(Point source, Point target, double radius, ENode sourceNodeType, ENode targetNodeType) {
		arcsWithHead.add(new ArcWithHead(source, target, radius, sourceNodeType, targetNodeType));

	}

	/**
	 * @return the arcsWithHead
	 */
	public List<ArcWithHead> getArcsWithHead() {
		return arcsWithHead;
	}
	
	
	public void clearArcWithHead() {
		arcsWithHead.clear();
	}

}
