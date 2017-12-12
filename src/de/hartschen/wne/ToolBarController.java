package de.hartschen.wne;

/**
 * Die Klasse verwaltet die Toolbar.
 * 
 * @author Jens Hartschen
 *
 */
public class ToolBarController {
	private int toolBarSwitch;

	/**
	 * Die Methode liefert die Id des Buttons zurück, welcher durch anklicken
	 * aktiviert wurde.
	 * 
	 * @return button id
	 */
	public int getToolBarSwitch() {
		return toolBarSwitch;
	}

	/**
	 * Die Methode setzt im toolBarSwitch, die Id des buttons, welcher durch
	 * angeklicken aktiviert wurde.
	 * 
	 * @param toolBarSwitch
	 */
	public void setToolBarSwitch(int toolBarSwitch) {
		this.toolBarSwitch = toolBarSwitch;
	}

}
