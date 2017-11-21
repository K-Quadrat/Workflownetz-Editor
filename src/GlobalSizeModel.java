
public class GlobalSizeModel {
	private int nodesSize;
	private int arcsSize;

	public GlobalSizeModel() {
		nodesSize = 40;
		arcsSize = 8;
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
	public int getArcsSize() {
		return arcsSize;
	}

	/**
	 * @param arcsSize the arcsSize to set
	 */
	public void setArcsSize(int arcsSize) {
		this.arcsSize = arcsSize;
	}

}
