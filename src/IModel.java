import java.util.List;

public interface IModel {

	void setNode(String id, int x, int y, int radius, ENode nodeType, String name, boolean markedOrActivated);
	
	void deleteNode(int x, int y);

	Node getNode(int x, int y);

	List<Node> getAllNodes();

	Node getPlace(int x, int y);

	List<Node> getAllPlaces();

	Node getTransition(int x, int y);

	List<Node> getAllTransitions();
	
	void setArc(String id, String source, String target);
	
	List<Arc> getArcs();
	
}
