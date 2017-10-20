package controller;
import gui.*;
import model.*;
import controller.*;

import java.awt.Color;
import java.awt.Graphics2D;

import gui.*;
import model.*;

public class ToDraw {

	public Graphics2D g2d;
	
	
    void drawKreuz(int row, int col, Graphics2D g2d) {
        g2d.setColor(Color.MAGENTA);
        g2d.drawLine((col * 100) + 20, (row * 100) + 20, (col * 100) + 80, (row * 100) + 80);
        g2d.drawLine((col * 100) + 20, (row * 100) + 80, (col * 100) + 80, (row * 100) + 20);
    }

	
	

	void drawRect(int x, int y, Graphics2D g2d) {
		g2d.setColor(Color.black);
		g2d.drawRect(x, y, 20, 20);
	}

	
	
	public Graphics2D getG2d() {
		return g2d;
	}

	public void setG2d(Graphics2D g2d) {
		this.g2d = g2d;
	}
	


}
