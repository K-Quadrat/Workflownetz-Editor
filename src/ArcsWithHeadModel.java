import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class ArcsWithHeadModel {

	private List<ArcWithHead> arcsWithHead = new ArrayList<ArcWithHead>();

	/**
	 * @param arcsWithHead
	 *            the arcsWithHead to set
	 */
	public void setArcWithHead(Point source, Point target, double radius) {
		arcsWithHead.add(new ArcWithHead(source, target, radius));

	}

	/**
	 * @return the arcsWithHead
	 */
	public List<ArcWithHead> getArcsWithHead() {
		return arcsWithHead;
	}

}
