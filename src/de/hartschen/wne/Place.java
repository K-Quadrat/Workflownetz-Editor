package de.hartschen.wne;


public class Place {
	private int x;
	private int y;
	private int radius;

	public Place(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	// pX, pY mouse click coordinates
	public boolean containsPoint(int pX, int pY) {
		return Math.abs(this.x - pX) <= radius && Math.abs(this.y - pY) <= radius;
	}

}
