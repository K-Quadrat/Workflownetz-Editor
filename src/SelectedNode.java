import java.util.ArrayList;
import java.util.List;

public class SelectedNode {

	private Node selectedNode = null;
	private Node selectedNodeRightClick = null;

	/**
	 * @return the selectedNodeRightClick
	 */
	public Node getSelectedNodeRightClick() {
		return selectedNodeRightClick;
	}

	/**
	 * @param selectedNodeRightClick
	 *            the selectedNodeRightClick to set
	 */
	public void setSelectedNodeRightClick(Node selectedNodeRightClick) {
		this.selectedNodeRightClick = selectedNodeRightClick;
	}

	/**
	 * @return the selectedNode
	 */
	public Node getSelectedNode() {
		return selectedNode;
	}

	/**
	 * @param selectedNode
	 *            the selectedNode to set
	 */
	public void setSelectedNode(Node selectedNode) {
		this.selectedNode = selectedNode;
	}

}
