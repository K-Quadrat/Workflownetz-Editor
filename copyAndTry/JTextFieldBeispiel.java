import java.awt.Color;
import javax.swing.*;
 
public class JTextFieldBeispiel {
    public static void main(String[] args) {
        JFrame viewSetName = new JFrame();
        viewSetName.setTitle("Set name");
        viewSetName.setSize(300, 150);
        JPanel panel = new JPanel();
 
        JLabel label = new JLabel("Enter new name: ");
        panel.add(label);
 
        // Textfeld wird erstellt
        // Text und Spaltenanzahl werden dabei direkt gesetzt
        JTextField tfName = new JTextField("Paul Programmierer", 15);

        // Textfeld wird unserem Panel hinzugefügt
        panel.add(tfName);
 
        JButton buttonOK = new JButton("OK");
        panel.add(buttonOK);
 
        viewSetName.add(panel);
        viewSetName.setVisible(true);
 
    }
}