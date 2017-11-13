import java.util.List;

public interface IModel {

	void setNode(int x, int y, int radius, ENode nodeType, String name, boolean markedOrActivated);

	Node getNode(int x, int y);

	List<Node> getAllNodes();

	Node getPlace(int x, int y);

	List<Node> getAllPlaces();

	Node getTransition(int x, int y);

	List<Node> getAllTransitions();
	
}
