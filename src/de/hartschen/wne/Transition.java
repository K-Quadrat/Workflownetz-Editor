package de.hartschen.wne;


public class Transition {
	private int x;
	private int y;
	private int radius;

	public Transition(int x, int y, int radius) {
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

	public boolean containsPoint(int pX, int pY) {
		return Math.abs(this.x - pX) <= (radius / 2) && Math.abs(this.y - pY) <= (radius / 2);
	}

}
