package de.hartschen.wne;

/**
 * Datenhaltungsklasse für die golbalen Größen.
 * 
 * @author Jens Hartschen
 *
 */
public class GlobalSizeModel {
	private int nodesSize;
	private int arcsSize;

	/**
	 * Konstruktor der GlobalSizeModel Klasse.
	 */
	public GlobalSizeModel() {
		nodesSize = 40;
		arcsSize = 8;
	}

	/**
	 * Die Methode liefert die nodesSize zurück.
	 * 
	 * @return the nodesSize
	 */
	public int getNodesSize() {
		return nodesSize;
	}

	/**
	 * Die Methode setzt die nodesSize.
	 * 
	 * @param nodesSize
	 */
	public void setNodesSize(int nodesSize) {
		this.nodesSize = nodesSize;
	}

	/**
	 * Die Methode liefert die arcsSize zurück.
	 * 
	 * @return the arcsSize
	 */
	public int getArcsSize() {
		return arcsSize;
	}

	/**
	 * Die Methode setzt die arcsSize.
	 * 
	 * @param arcsSize
	 */
	public void setArcsSize(int arcsSize) {
		this.arcsSize = arcsSize;
	}

}
