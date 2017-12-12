package de.hartschen.wne;

/**
 * Diese Klasse implementiert den AnimationMode
 * 
 * @author Jens Hartschen
 *
 */
public class AnimationMode {
	private boolean animationMode;

	/**
	 * Die Methode liefert den Status des AnimationMode zurück.
	 * 
	 * @return true oder false
	 */
	public boolean isAnimationMode() {
		return animationMode;
	}

	/**
	 * Die Methode setzt den AnimationMode auf true oder false.
	 * 
	 * @param true
	 *            oder false
	 * 
	 * 
	 */
	public void setAnimationMode(boolean animationMode) {
		this.animationMode = animationMode;
	}

}
