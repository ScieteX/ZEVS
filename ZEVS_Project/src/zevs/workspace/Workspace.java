package zevs.workspace;

import javax.swing.JFrame;

import zevs.ConnectionDB;

public class Workspace extends ConnectionDB {

	public JFrame frame;

	
	public Workspace() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
