package zevs.authorization;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import zevs.ConnectionDB;
import zevs.authorization.registration.Registration;
import zevs.workspace.Workspace;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Authorization extends ConnectionDB {

	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	
	public Authorization() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
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
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startWorkspace(0);
			}
		});
		button.setBounds(199, 117, 89, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u0412\u0445\u043E\u0434");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String result = checkLoginPass(getConnection(login, pass), textField.getText(),true, true);
					System.out.println(result);
					if(result == null)
					{
				JOptionPane.showMessageDialog(frame,"Неправильный логин или пароль!","Ошибка",JOptionPane.ERROR_MESSAGE);
						
						return;
					}
					
					if(result.equals(textField.getText() + passwordField.getText() + "user"))
					{
					startWorkspace(1);
					}
					else					
					if(result.equals(textField.getText() + passwordField.getText() + "admin"))
					{
					startWorkspace(3);
					return;
					}
					else
					{
						JOptionPane.showMessageDialog(frame,"Неправильный логин или пароль!","Ошибка",JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		button_1.setBounds(100, 117, 89, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044F");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Registration dialog = new Registration();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				dialog.addWindowListener(new WindowAdapter() {
					public void windowOpened(WindowEvent e)
					{
						frame.setEnabled(false);
					}
					public void windowClosed(WindowEvent e)
					{
						frame.setEnabled(true);
					}
				});
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
	protected void startWorkspace(final int number)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Workspace window = new Workspace();
					window.frame.setVisible(true);
					window.removeTab(number);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		frame.dispose();
		return;
	}
}
