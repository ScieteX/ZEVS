package zevs.authorization;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import zevs.authorization.registration.Registration;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Authorization {

	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	
	public Authorization() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 438, 222);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 30, 414, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("\u041B\u043E\u0433\u0438\u043D:");
		label.setBounds(10, 5, 46, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C:");
		label_1.setBounds(10, 61, 66, 14);
		frame.getContentPane().add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 86, 414, 20);
		frame.getContentPane().add(passwordField);
		
		JButton button = new JButton("\u0413\u043E\u0441\u0442\u044C");
		button.setBounds(199, 117, 89, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u0412\u0445\u043E\u0434");
		button_1.setBounds(100, 117, 89, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044F");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Registration dialog = new Registration();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				
				//frame.setEnabled(false);
			}
		});
		button_2.setBounds(298, 117, 126, 23);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("\u0412\u042B\u0425\u041E\u0414");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		button_3.setBounds(335, 167, 89, 23);
		frame.getContentPane().add(button_3);
	}
}
