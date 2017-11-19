
public class GlobalSizeModel {
	private int nodesSize;
	private double arcsSize;

	public GlobalSizeModel() {
		nodesSize = 50;
		arcsSize = 10d;
	}

	/**
	 * @return the nodesSize
	 */
	public int getNodesSize() {
		return nodesSize;
	}

	/**
	 * @param nodesSize the nodesSize to set
	 */
	public void setNodesSize(int nodesSize) {
		this.nodesSize = nodesSize;
	}

	/**
	 * @return the arcsSize
	 */
	public double getArcsSize() {
		return arcsSize;
	}

	/**
	 * @param arcsSize the arcsSize to set
	 */
	public void setArcsSize(double arcsSize) {
		this.arcsSize = arcsSize;
	}

}
