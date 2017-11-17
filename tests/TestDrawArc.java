import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestDrawArc {

	
	 public static void main(String args[]) { 
	        JFrame f = new JFrame("Pfeil Demo"); 
	        final ArcWithHead pf1 = new ArcWithHead(new Point(150,50), new Point(250,100), 10.0d); 
	        final ArcWithHead pf2 = new ArcWithHead(new Point(250,100), new Point(150,250), 10.0d); 
	        final ArcWithHead pf3 = new ArcWithHead(new Point(150,250), new Point(50,200), 10.0d); 
	        final ArcWithHead pf4 = new ArcWithHead(new Point(50,200), new Point(150,50), 10.0d); 
	        final Stroke s = new BasicStroke(1.0f,   // Width 
	                BasicStroke.CAP_SQUARE,    // End cap 
	                BasicStroke.JOIN_MITER,    // Join style 
	                10.0f,                     // Miter limit 
	                new float[] {16.0f,10.0f}, // Dash pattern 
	                0.0f);                     // Dash phase 
	                
	                JPanel p = new JPanel(){ 
	                    public void paintComponent(Graphics g){ 
	                        Graphics2D g2d = (Graphics2D)g; 
	                        
//	                        g2d.setStroke(s); 
//	                        g2d.setColor(Color.blue); 
	                        pf1.zeichnen(g2d); 
	                        pf2.zeichnen(g2d); 
	                        pf3.zeichnen(g2d); 
	                        pf4.zeichnen(g2d); 
	                    } 
	                }; 
	                f.getContentPane().add(p); 
	                f.setSize(350, 350); 
	                f.setLocationRelativeTo(null); 
	                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	                f.setVisible(true); 
	    } 
}
