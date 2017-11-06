import java.util.ArrayList;
import java.util.List;

public class Model implements IModel {

	private List<Place> testPlaces = new ArrayList<Place>();
	
	public  Model() {
		testPlaces.add(new Place(100, 200, 50));
	}


	@Override
	public void setPlace(int x, int y, int radius) {
		testPlaces.add(new Place(x, y, radius));

	}


	@Override
	public Place getPlace() {
		// TODO Auto-generated method stub
		return null;
	}



}
