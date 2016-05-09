package zevs;

import java.awt.EventQueue;

import zevs.authorization.Authorization;

public class Main {

	public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Authorization window = new Authorization();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		
	}

}
