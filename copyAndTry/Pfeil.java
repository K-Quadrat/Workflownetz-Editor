/*********************************************************************/ 
/*  Author   : Erik Krebs                                            */ 
/*  Datum    : 30.09.98                                              */ 
/*  Datei    : Pfeil.java                                            */ 
/*********************************************************************/ 
import java.awt.*; 
import javax.swing.*; 
class Pfeil { 
    private Math m; 
    private Point von, nach; // die zwei Punkte, die die Pfeil-Linie darstellen 
    private double phi;      // Rotationswinkel 
    private Polygon pfeil; 
    private double s;      // Seitenlaenge des Pfeils 
    private double xAlt, yAlt;          // x-,y-Werte vor der Rotation,relativ zur Pfeilspitze 
    private double xNeu, yNeu;          // x-,y-Werte nach der Rotation,absolut 
    private double xSpitze, ySpitze;    // x-,y-Werte der Pfeilspitze 
    public Pfeil(Point k1, Point k2, double length) { 
        von = k1; 
        nach = k2; 
        s = length;
        phi = m.atan( ((double)nach.y  -  (double)von.y ) / ((double)nach.x  -  (double)von.x ) ); 
        if(nach.x < von.x ) 
            phi = phi + m.PI; 
        // den Pfeil zeichnen 
        pfeil = new Polygon(); 
        pfeil.addPoint(nach.x , nach.y );  // Pfeilspitze 
        // Die beiden anderen Punkte des Pfeils muessen um den Winkel phi relative zur 
        // Pfeilspitze rotiert werden. 
        // x_rot = x*cos(phi) - y*sin(phi) 
        // y_rot = x*sin(phi) + y*cos(phi) 
        xSpitze = (double)nach.x; 
        ySpitze = (double)nach.y; 
        // erster Punkt 
        xAlt = (double)nach.x - s - xSpitze; 
        yAlt = (double)nach.y + s/2.5 - ySpitze; 
        xNeu = xAlt * m.cos(phi) - yAlt * m.sin(phi) + xSpitze; 
        yNeu = xAlt * m.sin(phi) + yAlt * m.cos(phi) + ySpitze; 
        pfeil.addPoint((int)xNeu, (int)yNeu); 
        // zweiter Punkt 
        yAlt = (double)nach.y - s/2.5 - ySpitze; 
        xNeu = xAlt * m.cos(phi) - yAlt * m.sin(phi) + xSpitze; 
        yNeu = xAlt * m.sin(phi) + yAlt * m.cos(phi) + ySpitze; 
        pfeil.addPoint((int)xNeu, (int)yNeu); 
        
    } 
    public void zeichnen(Graphics2D g) { 
        g.drawLine(von.x, von.y, nach.x , nach.y ); 
        g.fillPolygon(pfeil); 
    } 
    public static void main(String args[]) { 
        JFrame f = new JFrame("Pfeil Demo"); 
        final Pfeil pf1 = new Pfeil(new Point(150,50), new Point(250,100), 10.0d); 
        final Pfeil pf2 = new Pfeil(new Point(250,100), new Point(150,250), 10.0d); 
        final Pfeil pf3 = new Pfeil(new Point(150,250), new Point(50,200), 10.0d); 
        final Pfeil pf4 = new Pfeil(new Point(50,200), new Point(150,50), 10.0d); 
        final Stroke s = new BasicStroke(1.0f,   // Width 
                BasicStroke.CAP_SQUARE,    // End cap 
                BasicStroke.JOIN_MITER,    // Join style 
                10.0f,                     // Miter limit 
                new float[] {16.0f,10.0f}, // Dash pattern 
                0.0f);                     // Dash phase 
                
                JPanel p = new JPanel(){ 
                    public void paintComponent(Graphics g){ 
                        Graphics2D g2d = (Graphics2D)g; 
                        
//                        g2d.setStroke(s); 
//                        g2d.setColor(Color.blue); 
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