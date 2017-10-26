import gui.*;
import logic.*;
import model.*;

import java.awt.EventQueue;

import javax.security.auth.Refreshable;

public class TestJWorkFlownetzFrame {


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JWorkFlownetzFrame frame = new JWorkFlownetzFrame();
					frame.setVisible(true);
					System.out.println();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
