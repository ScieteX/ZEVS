package zevs.authorization.registration;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import zevs.ConnectionDB;

import java.awt.GridLayout;
import java.sql.Connection;

public class Registration  extends JDialog  {


	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
    private ConnectionDB connectionDB = new ConnectionDB();
    private final String Type = "user";
	
	public Registration() {
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 382);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JLabel label = new JLabel("\u0418\u043C\u044F:");
			contentPanel.add(label);
		}
		{
			textField = new JTextField();
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u0424\u0430\u043C\u0438\u043B\u0438\u044F:");
			contentPanel.add(label);
		}
		{
			textField_1 = new JTextField();
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u041E\u0442\u0447\u0435\u0441\u0442\u0432\u043E:");
			contentPanel.add(label);
		}
		{
			textField_2 = new JTextField();
			contentPanel.add(textField_2);
			textField_2.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u041B\u043E\u0433\u0438\u043D:");
			contentPanel.add(label);
		}
		{
			textField_3 = new JTextField();
			contentPanel.add(textField_3);
			textField_3.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C:");
			contentPanel.add(label);
		}
		{
			passwordField = new JPasswordField();
			contentPanel.add(passwordField);
		}
		{
			JLabel label = new JLabel("\u041F\u043E\u0434\u0442\u0432\u0435\u0440\u0436\u0434\u0435\u043D\u0438\u0435 \u043F\u0430\u0440\u043E\u043B\u044F:");
			contentPanel.add(label);
		}
		{
			passwordField_1 = new JPasswordField();
			contentPanel.add(passwordField_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					try {
						Connection connection = connectionDB.getConnection(connectionDB.login, connectionDB.pass);
						if(connectionDB.checkAllInputText(null, textField.getText(), textField_1.getText(), textField_2.getText(), textField_3.getText(), passwordField.getText(), 1) == false)
						{
							JOptionPane.showMessageDialog(Registration.this,"Имеются незаполненные поля","Ошибка",JOptionPane.ERROR_MESSAGE);
							return;
						}
						else
							if(connectionDB.checkInputText(textField.getText()+textField_1.getText()+textField_2.getText() , 2) == false)
							{
								 JOptionPane.showMessageDialog(Registration.this,"Введены недопустимые символы, возможно латиская буква или цифра","Ошибка",JOptionPane.ERROR_MESSAGE);
								 return;
							}
							else
								if(connectionDB.checkInputText(textField_3.getText()+passwordField.getText() , 1) == false)
								{
									JOptionPane.showMessageDialog(Registration.this,"Введены недопустимые символы, возможно кириллические буквы.\n Минимальны длина логина и пароля 3 символа, максимальная 30 символов.","Ошибка",JOptionPane.ERROR_MESSAGE);
									return;
								}
						else 							
                        if(connectionDB.checkLoginPass(connection, textField_3.getText(), false, false) == null)
                        {
                        	if(passwordField.getText().equals(passwordField_1.getText()) == true)
                        		connectionDB.InsertDataUser(connection, null, textField_3.getText(), passwordField.getText(), textField.getText(), textField_1.getText(), textField_2.getText(), Type,0);
                        	else {
                        		 JOptionPane.showMessageDialog(Registration.this,"Пароли не совпадают","Ошибка",JOptionPane.ERROR_MESSAGE);
                        		 return;
							}
                        	JOptionPane.showMessageDialog(Registration.this,"Вы успешно зарегистрированы :)","Поздравляю",JOptionPane.INFORMATION_MESSAGE);
                        	Registration.this.dispose();
                        	System.out.println("OK");
                        }
                        else
                        {
                        	JOptionPane.showMessageDialog(Registration.this,"Логин занят","Ошибка",JOptionPane.ERROR_MESSAGE);
                        	return;
                        }
                        	 
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u0412\u044B\u0445\u043E\u0434");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Registration.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
