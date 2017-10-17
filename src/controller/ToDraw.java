package controller;
import gui.*;
import model.*;
import controller.*;

import java.awt.Color;
import java.awt.Graphics2D;

import gui.*;
import model.*;

public class ToDraw {

	private Graphics2D g2d;

	public void drawRect(int x, int y) {

		g2d.setColor(Color.black);
		// g2d.drawString("Hallo", x, y);
		g2d.drawRect(x, y, 20, 20);
	}

	public Graphics2D getG2d() {
		return g2d;
	}

	public void setG2d(Graphics2D g2d) {
		this.g2d = g2d;
	}
	
	public int meineMethode(int x, int y) {
		int c = x+y;
		return c;

	}

}
