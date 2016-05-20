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
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.table.TableRowSorter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.GraphicsEnvironment;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import jess.JessException;
import jess.Rete;
import jess.awt.TextReader;
import jess.swing.JTextAreaWriter;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.RowFilter;



public class Workspace extends ConnectionDB {

	public JFrame frame;
    public 	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    private JTextField textField;
	private Connection connectionUser;
	private JTextArea textArea;
	private Highlighter highlighter;
	private Highlighter.HighlightPainter painter;
	private ArrayList startText = new ArrayList();
	private ArrayList endText = new ArrayList();
	private int step = 0;
	private JLabel label_1;
	private JComboBox comboBox_1;
	private JTextField textField_1;
	private JTextArea textArea_1;
	private int sizeFont = 14;
	private volatile Rete rete = new Rete();
	private JTextAreaWriter jTextAreaWriter;
	private TextReader reader = new TextReader(false);
	private  boolean activJess = false;
	private JDesktopPane desktopPane;
	private boolean isRun = true;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JLabel lblNewLabel_1;
	private Connection connectionAdmin;
	private TableRowSorter sort;
		/**
		 * @wbp.parser.entryPoint
		 */
		public void initialize() {
		try {
			connectionUser = getConnection(login, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		highlighter = new DefaultHighlighter();
		frame = new JFrame();
		frame.setBounds(100, 100, 569, 397);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("����� ������", null, panel, null);
		panel.setLayout(new MigLayout("", "[611px,grow]", "[20px][grow][]"));
		
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
					step ++;
					step(step);
			
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
		
		label_1 = new JLabel("\u041E\u0436\u0438\u0434\u0430\u043D\u0438\u0435 \u0432\u0432\u043E\u0434\u0430...");
		panel.add(label_1, "cell 0 2");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("������������", null, panel_1, null);
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		panel_1.setLayout(new MigLayout("", "[64px][84.00][309.00,grow,right]", "[20px][306px,grow][]"));
	
		final JComboBox comboBox_3 = new JComboBox(environment.getAvailableFontFamilyNames());
		comboBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
textArea_1.setFont(new Font((String) comboBox_3.getSelectedItem(), Font.PLAIN, sizeFont));
			}
		});
		comboBox_3.setToolTipText("\u0422\u0438\u043F \u0448\u0440\u0438\u0444\u0442\u0430");
		panel_1.add(comboBox_3, "cell 0 0,alignx right,aligny center");
		
		final JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				sizeFont = Integer.parseInt(spinner.getValue().toString());
				textArea_1.setFont(new Font(textArea_1.getFont().getFontName(), Font.PLAIN, sizeFont));
				System.out.println(sizeFont);
			}
		});
		spinner.setToolTipText("\u0420\u0430\u0437\u043C\u0435\u0440 \u0448\u0440\u0438\u0444\u0442\u0430");
		spinner.setModel(new SpinnerNumberModel(new Integer(14), null, null, new Integer(1)));
		panel_1.add(spinner, "cell 1 0,growx,aligny center");
		try {

			comboBox_1 = new JComboBox(getAllTextName(connectionUser,2).toArray());
			comboBox_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
					textArea_1.setText("");
					killThread();
						setJessCode((String)comboBox_1.getSelectedItem());
						isRun = true;
					activJess = true;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		comboBox_1.setToolTipText("\u041A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u044F \u043A\u043E\u043D\u0441\u0443\u043B\u044C\u0442\u0430\u0446\u0438\u0438");
		panel_1.add(comboBox_1, "cell 2 0,alignx left,aligny center");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, "cell 0 1 3 1,grow");
		textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane_1.setViewportView(textArea_1);
		jTextAreaWriter = new JTextAreaWriter(textArea_1);
		textField_1 = new JTextField();
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField_1.getText().equals("XXX"))
				{
						killThread();
						try {
							rete.eval("");
						} catch (JessException e) {
							e.printStackTrace();
						}
					System.out.println(textField_1.getText());
				}
				else
				reader.appendText(textField_1.getText() + "\n");
			}
		});
		panel_1.add(textField_1, "cell 0 2 3 1,growx");
		textField_1.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("�����������������", null, panel_2, null);
		panel_2.setLayout(new MigLayout("", "[][548px,grow]", "[][331px,grow]"));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel_2.add(toolBar, "cell 0 0");
		
		btnNewButton_2 = new JButton("\u041F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u0438");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserInternalFrame();
			}
		});
		toolBar.add(btnNewButton_2);
		
	    btnNewButton_3 = new JButton("\u0421\u043F\u0440\u0430\u0432\u043E\u0447\u043D\u0430\u044F \u0438\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0438\u044F");
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InformationInternalFrame();
			}
		});
		toolBar.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("\u0424\u0430\u043A\u0442\u044B/\u041F\u0440\u0430\u0432\u0438\u043B\u0430");
		btnNewButton_4.setEnabled(false);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FactsRulesInternalFrame();
			}
		});
		toolBar.add(btnNewButton_4);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		panel_2.add(desktopPane, "cell 0 1 2 1,grow");
		final JInternalFrame internalFrame = new JInternalFrame("\u0420\u0435\u0436\u0438\u043C \u0410\u0434\u043C\u0438\u043D\u0438\u0441\u0442\u0440\u0430\u0442\u043E\u0440\u0430");
		internalFrame.setBounds(10, 61, 424, 166);
		desktopPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(new MigLayout("", "[][408px,grow]", "[14px][][][][]"));
		
		JLabel lblNewLabel = new JLabel("\u041B\u043E\u0433\u0438\u043D:");
		internalFrame.getContentPane().add(lblNewLabel, "cell 0 0,growx,aligny top");
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				lblNewLabel_1.setVisible(false);
			}
		});
		internalFrame.getContentPane().add(textField_2, "cell 0 1 2 1,growx");
		textField_2.setColumns(10);
		
		JLabel label1 = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C:");
		internalFrame.getContentPane().add(label1, "cell 0 2");
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				lblNewLabel_1.setVisible(false);
			}
		});
		internalFrame.getContentPane().add(passwordField, "cell 0 3 2 1,growx");
		
		lblNewLabel_1 = new JLabel("\u041D\u0435\u043F\u0440\u0430\u0432\u0438\u043B\u044C\u043D\u044B\u0439 \u043B\u043E\u0433\u0438\u043D \u0438\u043B\u0438 \u043F\u0430\u0440\u043E\u043B\u044C!!!");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		internalFrame.getContentPane().add(lblNewLabel_1, "cell 0 4");
		lblNewLabel_1.setVisible(false);
		
		JButton btnNewButton_5 = new JButton("\u0412\u0445\u043E\u0434");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String loginStr = textField_2.getText();
					if(loginStr.equals("ZevsUser"))
					{
						lblNewLabel_1.setVisible(true);
						return;
					}
					else
						connectionAdmin = getConnection(loginStr, passwordField.getText());
					if( connectionAdmin != null)
					{
						UserInternalFrame();
						InformationInternalFrame();
						FactsRulesInternalFrame();
						btnNewButton_2.setEnabled(true);
						btnNewButton_3.setEnabled(true);
						btnNewButton_4.setEnabled(true);
						internalFrame.dispose();
					}
					else
					{
						lblNewLabel_1.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		internalFrame.getContentPane().add(btnNewButton_5, "cell 1 4,alignx right");
		internalFrame.setVisible(true);
		rete.addOutputRouter("t", jTextAreaWriter);
		rete.addInputRouter("t", reader, true);
		UserInternalFrame();
	//	InformationInternalFrame();
	//	FactsRulesInternalFrame();
		frame.setVisible(true);
		runJessCode.start();
		System.out.println(Thread.currentThread().getId());
	}
		protected void FactsRulesInternalFrame()
		{
			JInternalFrame internalFrame_2 = new JInternalFrame("\u0424\u0430\u043A\u0442\u044B/\u041F\u0440\u0430\u0432\u0438\u043B\u0430");
			internalFrame_2.setClosable(true);
			internalFrame_2.setIconifiable(true);
			internalFrame_2.setMaximizable(true);
			internalFrame_2.setResizable(true);
			internalFrame_2.setBounds(0, 69, 319, 33);
			internalFrame_2.setVisible(true);
			desktopPane.add(internalFrame_2);
			
		}
		protected void InformationInternalFrame()
		{
			JInternalFrame internalFrame_1 = new JInternalFrame("\u0421\u043F\u0440\u0430\u0432\u043E\u0447\u043D\u0430\u044F \u0438\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0438\u044F");
			internalFrame_1.setClosable(true);
			internalFrame_1.setIconifiable(true);
			internalFrame_1.setMaximizable(true);
			internalFrame_1.setResizable(true);
			internalFrame_1.setBounds(0, 33, 319, 33);
			internalFrame_1.setVisible(true);
			desktopPane.add(internalFrame_1);
		}
	protected void UserInternalFrame()
	{
		JInternalFrame internalFrame = new JInternalFrame("\u041F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u0438");
		internalFrame.setVisible(true);
		internalFrame.setClosable(true);
		internalFrame.setResizable(true);
		internalFrame.setMaximizable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setBounds(0, 0, 319, 33);
		desktopPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(new MigLayout("", "[508px,grow]", "[244px,grow]"));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setContinuousLayout(true);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		internalFrame.getContentPane().add(splitPane, "cell 0 0,grow");
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new MigLayout("", "[][449.00px,grow]", "[20px][146.00,grow]"));
		
		JLabel label = new JLabel("\u0424\u0438\u043B\u044C\u0442\u0440:");
		panel.add(label, "cell 0 0,alignx trailing");
		
		textField_3 = new JTextField();
		panel.add(textField_3, "cell 1 0,growx,aligny top");
		textField_3.setColumns(10);
		textField_3.getDocument().addDocumentListener(new DocumentListener() {
			
			public void removeUpdate(DocumentEvent arg0) {
                  Filt();
			}
			
			public void insertUpdate(DocumentEvent arg0) {
                  Filt();
			}
			
			public void changedUpdate(DocumentEvent arg0) {
                  Filt();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 0 1 2 1,grow");
		try {
			sort = new TableRowSorter(getUserData(connectionUser));
			table = new JTable(getUserData(connectionUser));
			table.setRowSorter(sort);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		JLabel lblNewLabel_2 = new JLabel("\u0420\u0435\u0437\u0443\u043B\u044C\u0442\u0430\u0442:");
		panel_1.add(lblNewLabel_2, "cell 0 0");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, "cell 0 1,grow");
		
		JTextArea textArea_2 = new JTextArea();
		scrollPane_1.setViewportView(textArea_2);
	}
	
	protected void Filt()
	{
		RowFilter  rowFilter = null;
		try {
		rowFilter = RowFilter.regexFilter(textField_3.getText(), null);
		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}
		sort.setRowFilter(rowFilter);
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
	protected Thread runJessCode = new Thread(new Runnable() {
		public void run() {
			isRun = true;
			System.out.println(runJessCode.currentThread().getId());
			while(true)
			{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			while(isRun)
			{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(activJess == true)
				{
					try {
						System.out.println("AKTIV");
					rete.run();
					rete.eval("(clear)");
					activJess = false;
					System.out.println("STOP");
					} catch (JessException e) {
						e.printStackTrace();
					}
				}
				else
				activJess = false;
			}
			}			
		}
	}){
	};
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JTextField textField_3;
	private JTable table;
	public void killThread()
	{
		isRun = false;
		try {
			rete.halt();
			rete.eval("(clear)");
			rete.reset();
		} catch (JessException e) {
			e.printStackTrace();
		}
	}
	protected void setJessCode(String Name)
	{
		try {	
		//System.out.println(getJessCode(connectionUser, Name));
		rete.eval(getJessCode(connectionUser, Name));
		rete.reset();	
		} catch (JessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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
		{
			label_1.setText("�������� �����...");
			return;
		}
		int start = textA.indexOf(textF,0);
		if(start == -1)
		{
			textField.setBackground(Color.PINK);
			label_1.setText("���������� �� �������.");
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
				{
					label_1.setText("���������� ������� " + startText.size() + " �� " + textA.length());
							break;	
				}
		 }
		}
	    catch (Exception e)
	       {      	e.printStackTrace();
	            }
	 }
	public void step(int numb) throws BadLocationException
	{
		
        textArea.setHighlighter(highlighter);
		if( numb < startText.size() && numb >= 0)
		{
		int start = Integer.parseInt((String) startText.get(numb));
		int end = Integer.parseInt((String) endText.get(numb));
		painter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
        textArea.getHighlighter().addHighlight(start, end, painter);
		textArea.setCaretPosition(start);
		System.out.println(step);
	    }
		else
			if(numb < 0 || numb > startText.size())
		{
			step = 0;
			step(step);
			//search(Color.YELLOW);
		}
		
	}
	}
