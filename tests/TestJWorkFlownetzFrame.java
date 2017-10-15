import gui.*;

import java.awt.EventQueue;

public class TestJWorkFlownetzFrame {


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JWorkFlownetzFrame frame = new JWorkFlownetzFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
