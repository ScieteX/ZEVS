package zevs.workspace;

import javax.swing.JFrame;

import zevs.ConnectionDB;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import javax.swing.JTextArea;

import java.awt.Font;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JButton;


import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Workspace extends ConnectionDB {

	public JFrame frame;
    public 	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    private JTextField textField;
	private Connection connectionUser;
	JTextArea textArea;
	private Highlighter highlighter;
	private Highlighter.HighlightPainter painter;
	private ArrayList startText = new ArrayList();
	private ArrayList endText = new ArrayList();
	private int step = 0;
	//private Connection connectionAdmin;
	public Workspace() {
		initialize();
	}

	private void initialize() {
		try {
			connectionUser = getConnection(login, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		highlighter = new DefaultHighlighter();
		frame = new JFrame();
		frame.setBounds(100, 100, 652, 395);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Поиск данных", null, panel, null);
		panel.setLayout(new MigLayout("", "[611px,grow]", "[20px][grow]"));
		
		JLabel label = new JLabel("\u041F\u043E\u0438\u0441\u043A:");
		panel.add(label, "flowx,cell 0 0,alignx left,aligny center");
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				search(Color.YELLOW);
			}
			public void keyReleased(KeyEvent arg0) {
				search(Color.YELLOW);
			}
		});

		textField.setToolTipText("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0442\u0435\u043A\u0441\u0442 \u0434\u043B\u044F \u043F\u043E\u0438\u0441\u043A\u0430");
		panel.add(textField, "cell 0 0,growx,aligny top");
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 0 1,grow");
		
		textArea = new JTextArea();
		textArea.setAutoscrolls(false);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		JButton btnNewButton = new JButton("<<<");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				step --;
				try {
					step(step);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton, "cell 0 0");
		
		JButton btnNewButton_1 = new JButton(">>>");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					step(step);		
					step++;
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton_1, "cell 0 0");
		
		final JComboBox comboBox;
		try {
			comboBox = new JComboBox(getAllTextName(connectionUser,0).toArray());
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						textArea.setText(getTextData(connectionUser, (String)comboBox.getSelectedItem()));
					} catch (Exception e) {
						e.printStackTrace();
					}
					textArea.setCaretPosition(0);
					
				}
			});
		
		comboBox.setToolTipText("\u0412\u044B\u0431\u043E\u0440 \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438 \u043F\u043E\u0438\u0441\u043A\u0430");
		panel.add(comboBox, "cell 0 0,alignx right");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Консультация", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Администрирование", null, panel_2, null);
		
	}
	public void removeTab (int type)
	{
       switch(type)
       {
        case 0: tabbedPane.remove(2);
                tabbedPane.remove(1);//visitor
        break;
        case 1: tabbedPane.remove(2); // user
        default: return;
       }
		
	}
	protected void search(Color color)
	{
		try
		{
		int nextStep = 0;
		startText.clear();
		endText.clear();
		painter = new DefaultHighlighter.DefaultHighlightPainter(color);
		textArea.setHighlighter(highlighter);
		textField.setBackground(Color.WHITE);
		highlighter.removeAllHighlights();
		String textA = textArea.getText().toLowerCase();
		String textF = textField.getText().toLowerCase();
		if(textF.length() == 0)
			return;
		int start = textA.indexOf(textF,0);
		if(start == -1)
		{
			textField.setBackground(Color.PINK);
			return;
		}
		int end = start + textF.length();
		highlighter.addHighlight(start, end, painter);
        textArea.setCaretPosition(start);
		while(true)
		{
			startText.add(""+start);
			endText.add(""+end);
		 nextStep = start + 1;
			start = textA.indexOf(textF,nextStep);
			 end = start + textF.length();
		if (start != -1)
			{
			highlighter.addHighlight(start, end, painter);
			}
			else
				if (start == -1)
							break;						
		 }
		}
	    catch (Exception e)
	       {      	e.printStackTrace();
	            }
	 }
	public void step(int numb) throws BadLocationException
	{
        textArea.setHighlighter(highlighter);
		if( numb <= startText.size())
		{
		int start = Integer.parseInt((String) startText.get(numb));
		int end = Integer.parseInt((String) endText.get(numb));
		painter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
        textArea.getHighlighter().addHighlight(start, end, painter);
		textArea.setCaretPosition(start);
		System.out.println(step);
	    }
		else
			if(numb < 0)
		{
			step = 0;
			search(Color.YELLOW);
		}
		
	}
	
	}
