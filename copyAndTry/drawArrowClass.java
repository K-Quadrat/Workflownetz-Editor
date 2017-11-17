import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Stroke;

import com.sun.prism.BasicStroke;

public class drawArrowClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	 private final void drawArrow(Graphics screen, Color area, Color border) {
	        Graphics2D g = (Graphics2D)screen;
	        BasicStroke pen = new BasicStroke(2F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 0);
	        Polygon p = new Polygon();
	        double peakLength = 0.4;
	        double tailThickness = 0.3;  
	       
			double x = d.getWidth();
	        double y = d.getHeight();
	 
	        p.addPoint(1, (int) y/2);
	        p.addPoint((int) (x * peakLength), 1);
	        p.addPoint((int) (x * peakLength), (int) ((y - (y * tailThickness))/2));
	        p.addPoint((int) (x-1), (int) ((y - (y * tailThickness))/2));
	        p.addPoint((int) (x-1),
	                (int) ((y - (y * tailThickness))/2 + (y * tailThickness)));
	        p.addPoint((int) (x * peakLength),
	                (int) ((y - (y * tailThickness))/2 + (y * tailThickness)));
	        p.addPoint((int) (x * peakLength), (int) y-1);
	               
	        g.setStroke(s););(s);( pen );
	        g.setColor( area );
	        g.fillPolygon( p );
	        g.setColor( border );
	        g.drawPolygon( p );
	    }	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
