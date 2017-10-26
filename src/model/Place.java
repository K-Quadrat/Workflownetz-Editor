package model;

public class Place {
	String id;
	String name;
	Boolean token;
	int x;
	int y;
	
	
	public Place(String id, String name, Boolean token, int x, int y) {
		this.id = id;
		this.name = name;
		this.token = token;
		this.x = x;
		this.y = y;
	}
}
