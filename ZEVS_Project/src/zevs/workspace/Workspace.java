package zevs.workspace;

import javax.swing.JFrame;

import zevs.ConnectionDB;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.TextArea;

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
import javax.swing.border.TitledBorder;
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
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.UIManager;

public class Workspace extends ConnectionDB {

	public JFrame frmZevs;
	public  String title = "";
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
	private JComboBox comboBox_2;
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
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JTextField textField_3;
	private JTable table;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JComboBox comboBox;
	private String varId = null;
	private String LoginU = null;
	private String nameTextData = null;
	private JButton button_2;
	private TableRowSorter sort;
	private TableRowSorter sortInfrom;
	private TableRowSorter sortFR;
	private JTextArea textArea_2;
	private JTextArea textArea_3;
	private JButton button_3;
	private JButton button_1;
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
		frmZevs = new JFrame();
		frmZevs.setTitle("ZEVS. " + title);
		frmZevs.setBounds(100, 100, 868, 644);
		frmZevs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmZevs.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));		
		frmZevs.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Поиск данных по электробезопасности", null, panel, null);
		panel.setLayout(new MigLayout("", "[509.00,grow][155.00][128.00,grow]", "[34.00px][grow][]"));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "\u0412\u0432\u043E\u0434 \u0434\u0430\u043D\u043D\u044B\u0445 \u0434\u043B\u044F \u043F\u043E\u0438\u0441\u043A\u0430", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_3, "cell 0 0,growx,aligny center");
		panel_3.setLayout(new MigLayout("", "[][384.00,grow,fill]", "[][]"));
		
		textField = new JTextField();
		panel_3.add(textField, "cell 0 1 2 1,growx");
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				search(Color.YELLOW);
			}
			public void keyReleased(KeyEvent arg0) {
				search(Color.YELLOW);
			}
		});
		
				textField.setToolTipText("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0442\u0435\u043A\u0441\u0442 \u0434\u043B\u044F \u043F\u043E\u0438\u0441\u043A\u0430");
				textField.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "\u041F\u043E\u0448\u0430\u0433\u043E\u0432\u044B\u0439 \u043F\u043E\u0438\u0441\u043A", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_4, "cell 1 0,growx,aligny center");
		panel_4.setLayout(new MigLayout("", "[][12.00][][]", "[]"));
		JButton btnNewButton = new JButton("<<<");
		panel_4.add(btnNewButton, "cell 0 0,aligny center");
		btnNewButton.setToolTipText("\u041D\u0430\u0439\u0442\u0438 \u043F\u0440\u0435\u0434\u044B\u0434\u0443\u0449\u0435\u0435");
		JButton btnNewButton_1 = new JButton(">>>");
		panel_4.add(btnNewButton_1, "cell 2 0,aligny center");
		btnNewButton_1.setToolTipText("\u041D\u0430\u0439\u0442\u0438 \u0441\u043B\u0435\u0434\u0443\u044E\u0449\u0438\u0435");
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
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u041A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u044F \u043F\u043E\u0438\u0441\u043A\u0430", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.add(panel_5, "cell 2 0,grow");
		panel_5.setLayout(new MigLayout("", "[108.00,grow]", "[]"));
		try
		{
		comboBox_2 = new JComboBox(getAllTextName(connectionUser,0).toArray());
		panel_5.add(comboBox_2, "cell 0 0,growx,aligny center");
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					textArea.setText(getTextData(connectionUser, (String)comboBox_2.getSelectedItem()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				textArea.setCaretPosition(0);
			}
		});
		
		comboBox_2.setToolTipText("\u0412\u044B\u0431\u043E\u0440 \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438 \u043F\u043E\u0438\u0441\u043A\u0430");
		}catch (Exception e) {
			e.printStackTrace();
		}
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 0 1 3 1,grow");
		
		textArea = new JTextArea();
		textArea.setBorder(new TitledBorder("Результат поиска"));
		textArea.setAutoscrolls(false);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		try {
		
		label_1 = new JLabel("\u041E\u0436\u0438\u0434\u0430\u043D\u0438\u0435 \u0432\u0432\u043E\u0434\u0430...");
		panel.add(label_1, "cell 0 2 3 1");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Консультация по электробезопасности", null, panel_1, null);
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		panel_1.setLayout(new MigLayout("", "[64px,grow][185.00,grow][314.00][309.00,grow,right]", "[20px][grow][]"));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "\u0422\u0438\u043F \u0448\u0440\u0438\u0444\u0442\u0430", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_6, "cell 0 0,grow");
		panel_6.setLayout(new MigLayout("", "[grow,fill]", "[]"));
		
			final JComboBox comboBox_3 = new JComboBox(environment.getAvailableFontFamilyNames());
			panel_6.add(comboBox_3, "cell 0 0");
			comboBox_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
textArea_1.setFont(new Font((String) comboBox_3.getSelectedItem(), Font.PLAIN, sizeFont));
				}
			});
			comboBox_3.setToolTipText("\u0422\u0438\u043F \u0448\u0440\u0438\u0444\u0442\u0430");
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "\u0420\u0430\u0437\u043C\u0435\u0440 \u0448\u0440\u0438\u0444\u0442\u0430", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_7, "cell 1 0,grow");
		panel_7.setLayout(new MigLayout("", "[84.00,grow,fill]", "[]"));
		
		final JSpinner spinner = new JSpinner();
		panel_7.add(spinner, "cell 0 0,growx");
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				sizeFont = Integer.parseInt(spinner.getValue().toString());
				textArea_1.setFont(new Font(textArea_1.getFont().getFontName(), Font.PLAIN, sizeFont));
			}
		});
		spinner.setToolTipText("\u0420\u0430\u0437\u043C\u0435\u0440 \u0448\u0440\u0438\u0444\u0442\u0430");
		spinner.setModel(new SpinnerNumberModel(new Integer(14), null, null, new Integer(1)));
		try {
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "\u041A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u044F \u043A\u043E\u043D\u0441\u0443\u043B\u044C\u0442\u0430\u0446\u0438\u0438", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_8, "cell 2 0,grow");
		panel_8.setLayout(new MigLayout("", "[220.00,grow]", "[]"));
		
					comboBox_1 = new JComboBox(getAllTextName(connectionUser,2).toArray());
					panel_8.add(comboBox_1, "cell 0 0,growx");
					comboBox_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							try {
							textArea_1.setText("");
							killThread();
								setJessCode((String)comboBox_1.getSelectedItem());
								isRun = true;
							activJess = true;
							comboBox_1.setEnabled(false);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		comboBox_1.setToolTipText("\u041A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u044F \u043A\u043E\u043D\u0441\u0443\u043B\u044C\u0442\u0430\u0446\u0438\u0438");
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "\u0412\u043E\u043F\u0440\u043E\u0441\u044B \u0441\u0438\u0441\u0442\u0435\u043C\u044B", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_9, "cell 0 1 4 1,grow");
		panel_9.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_9.add(scrollPane_2, "cell 0 0,grow");
		textArea_1 = new JTextArea();
		scrollPane_2.setViewportView(textArea_1);
		textArea_1.setEditable(false);
		textArea_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		jTextAreaWriter = new JTextAreaWriter(textArea_1);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(null, "\u0412\u0432\u043E\u0434 \u043E\u0442\u0432\u0435\u0442\u0430", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_10, "cell 0 2 4 1,grow");
		panel_10.setLayout(new MigLayout("", "[grow,fill]", "[]"));
		textField_1 = new JTextField();
		panel_10.add(textField_1, "cell 0 0");
		textField_1.setToolTipText("\u041F\u043E\u043B\u0435 \u0434\u043B\u044F \u0432\u0432\u043E\u0434\u0430 \u043E\u0442\u0432\u0435\u0442\u0430.");
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reader.appendText(textField_1.getText() + "\n");
				textField_1.setText("");
			}
		});
		textField_1.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Администрирование", null, panel_2, null);
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
		internalFrame.setBounds(155, 116, 424, 166);
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
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
					try {
						comboBox_2.setModel(new DefaultComboBoxModel(getAllTextName(connectionUser, 0).toArray()));
						comboBox_1.setModel(new DefaultComboBoxModel(getAllTextName(connectionUser, 2).toArray()));
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
		rete.addOutputRouter("t", jTextAreaWriter);
		rete.addInputRouter("t", reader, true);
		frmZevs.setVisible(true);
		runJessCode.start();
		//InformationInternalFrame();
		//FactsRulesInternalFrame();
		//UserInternalFrame();
	}
		protected void FactsRulesInternalFrame()
		{
			JInternalFrame internalFrame_2 = new JInternalFrame("\u0424\u0430\u043A\u0442\u044B/\u041F\u0440\u0430\u0432\u0438\u043B\u0430");
			internalFrame_2.setClosable(true);
			internalFrame_2.setIconifiable(true);
			internalFrame_2.setMaximizable(true);
			internalFrame_2.setResizable(true);
			internalFrame_2.setBounds(0, 69, 334, 33);
			internalFrame_2.setVisible(true);
			desktopPane.add(internalFrame_2);
			internalFrame_2.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
			
			JScrollPane scrollPane_1 = new JScrollPane();
			internalFrame_2.getContentPane().add(scrollPane_1, "cell 0 0,grow");
			
			JSplitPane splitPane = new JSplitPane();
			scrollPane_1.setViewportView(splitPane);
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			
			JPanel panel = new JPanel();
			splitPane.setLeftComponent(panel);
			panel.setLayout(new MigLayout("", "[][grow]", "[][259.00,grow]"));
			
			JLabel label = new JLabel("\u0424\u0438\u043B\u044C\u0442\u0440\u0430\u0446\u0438\u044F \u0442\u0430\u0431\u043B\u0438\u0446\u044B:");
			panel.add(label, "cell 0 0,alignx trailing");
			
			textField_13 = new JTextField();
			panel.add(textField_13, "cell 1 0,growx");
			textField_13.setColumns(10);
			textField_13.getDocument().addDocumentListener(new DocumentListener() {
				
				public void removeUpdate(DocumentEvent arg0) {
					FiltFR();
				}
				
				public void insertUpdate(DocumentEvent arg0) {
					FiltFR();
				}
				
				public void changedUpdate(DocumentEvent arg0) {
					FiltFR();
				}
			});
			JScrollPane scrollPane = new JScrollPane();
			panel.add(scrollPane, "cell 0 1 2 1,grow");
			try {
				sortFR = new TableRowSorter(getJessTableData(connectionAdmin));
				table_2 = new JTable(getJessTableData(connectionAdmin));
				table_2.setRowSorter(sortFR);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
					table_2.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent arg0) {
							int Row = table_2.getRowSorter().convertRowIndexToModel(table_2.getSelectedRow());
						    varId = table_2.getModel().getValueAt(Row, 0).toString();
						    nameTextData = table_2.getModel().getValueAt(Row, 1).toString();
						      textField_14.setText(table_2.getModel().getValueAt(Row, 0).toString());
							  textField_15.setText(table_2.getModel().getValueAt(Row, 1).toString());
							  textArea_3.setText(table_2.getModel().getValueAt(Row, 2).toString());
							  textArea_3.setCaretPosition(0);
							  button_1.setEnabled(true);
						}
					});
					scrollPane.setViewportView(table_2);
					
					JPanel panel_1 = new JPanel();
					splitPane.setRightComponent(panel_1);
					panel_1.setLayout(new MigLayout("", "[][grow][][][]", "[][][grow]"));
					
					JLabel lblNewLabel_5 = new JLabel("\u0418\u0434\u0435\u043D\u0442\u0438\u0444\u0438\u043A\u0430\u0442\u043E\u0440 \u042D\u0421:");
					panel_1.add(lblNewLabel_5, "flowx,cell 0 0");
					
					JLabel lblNewLabel_6 = new JLabel("\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435 \u042D\u0421:");
					panel_1.add(lblNewLabel_6, "flowx,cell 1 0");
					
					textField_15 = new JTextField();
					panel_1.add(textField_15, "cell 1 0,growx");
					textField_15.setColumns(10);
					
					final JButton button = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							String id = textField_14.getText();
							String name = textField_15.getText();
							String jessCode = textArea_3.getText();
							if(name.isEmpty() || jessCode.isEmpty())
							{
								JOptionPane.showMessageDialog(button,"Имеются незаполненные поля\n Строка Идентификатор ЭС может быть пустым.","Ошибка",JOptionPane.ERROR_MESSAGE);
							return;
							}
							else
							if(id.isEmpty())
							{
								try {
									if (chekJessData(connectionAdmin, name) == false)
									{
		                InsertJessData(connectionAdmin, null, checkApostrophe(name), checkApostrophe(jessCode), 0);
		                JOptionPane.showMessageDialog(button, "Данные успешно добавлены.","Поздравляю", JOptionPane.INFORMATION_MESSAGE);
		                clearTextField();
		                UpdateTableFR();
		                button_1.setEnabled(false);
		                return;
									}
									else {
										JOptionPane.showMessageDialog(button,"Введённое Наименование ЭС уже занято","Ошибка",JOptionPane.ERROR_MESSAGE);
										return;
									}
								} catch (HeadlessException e) {
									e.printStackTrace();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							} else
								try {
									if(checkInputText(id, 0) == true)
									{
									if(chekJessID(connectionAdmin, id) == false)
		                               {
										if (chekJessData(connectionAdmin, name) == false)
										{
		                            InsertJessData(connectionAdmin, id, checkApostrophe(name), checkApostrophe(jessCode), 1);
		                            JOptionPane.showMessageDialog(button, "Данные успешно добавлены.","Поздравляю", JOptionPane.INFORMATION_MESSAGE);
		                            clearTextField();
		                            UpdateTableFR();
		                            button_1.setEnabled(false);
										}
										else {
											JOptionPane.showMessageDialog(button,"Введёное Наименование ЭС уже занято","Ошибка",JOptionPane.ERROR_MESSAGE);
											return;
										}
		                               }
		                         else {
		                        	 JOptionPane.showMessageDialog(button, "Введённый Идентификатор ЭС занят!!!","Ошибка", JOptionPane.ERROR_MESSAGE);
		                        	 return;
		                          }
									}
									else
									{
										JOptionPane.showMessageDialog(button,"Введены недопустимые символы.\n Строка Идентификатор ЭС может содержать только цифры.","Ошибка",JOptionPane.ERROR_MESSAGE);
										return;
									}
										
								} catch (HeadlessException e) {
									e.printStackTrace();
								} catch (SQLException e) {
									e.printStackTrace();
								}
						}
					});
					panel_1.add(button, "cell 2 0");
					
					button_1 = new JButton("\u041E\u0431\u043D\u043E\u0432\u0438\u0442\u044C");
					button_1.setEnabled(false);
					button_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							String idTextdata = textField_14.getText();
							String Name = checkApostrophe(textField_15.getText());
							String jessCode = checkApostrophe(textArea_3.getText());
							if(jessCode.isEmpty())
							{
								JOptionPane.showMessageDialog(button_1,"Имеются незаполненные поля.","Ошибка",JOptionPane.ERROR_MESSAGE);
								return;
							} 
							else
								try {
									if(idTextdata.isEmpty() == false && Name.isEmpty() == false)
									if(chekJessID(connectionAdmin, idTextdata) == false)
									{
									if(chekJessData(connectionAdmin, Name) == false)
									{
		                           UpdateJessDATA(button_1,idTextdata, Name, jessCode);
		                           return;
									}
									else
									{
										JOptionPane.showMessageDialog(button_1, "Введённое Наименование ЭС уже занято!!!","Ошибка", JOptionPane.ERROR_MESSAGE);
			              	              return;
									}
									}
		                       else {
									JOptionPane.showMessageDialog(button_1, "Введённый Идентификатор ЭС занят!!!","Ошибка", JOptionPane.ERROR_MESSAGE);
		              	              return;
		                              }
									if(idTextdata.isEmpty() == false)
									{
										if(chekJessID(connectionAdmin, idTextdata) == false)
										{
											 UpdateJessDATA(button_3,idTextdata, nameTextData, jessCode);
										}
										else {
											JOptionPane.showMessageDialog(button, "Введённый Идентификатор ЭС занят!!!","Ошибка", JOptionPane.ERROR_MESSAGE);
				              	              return;
										}
									}
									else
										if(Name.isEmpty() == false)
										{
											if(chekJessData(connectionAdmin, Name) == false)
											{
				                           UpdateJessDATA(button_3,varId, Name, jessCode);
											}
											else
											{
												JOptionPane.showMessageDialog(button, "Введённое Наименование ЭС занято!!!","Ошибка", JOptionPane.ERROR_MESSAGE);
					              	              return;
											}
										}
									else
										if(idTextdata.isEmpty() && Name.isEmpty())
										{
											  UpdateJessDATA(button_3,varId, nameTextData, jessCode);
										}							
								} catch (HeadlessException e) {
									e.printStackTrace();
								} catch (SQLException e) {
									e.printStackTrace();
								}
						}
					});
					panel_1.add(button_1, "cell 3 0");
					
					final JButton button_4 = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
					button_4.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							String text = textField_14.getText();
							int ans;
							ans = JOptionPane.showConfirmDialog(button_4, "Вы уверены что хотите удалить запись с Идентификатором ЭС = "+text+"","Предупреждение!!!",JOptionPane.YES_NO_OPTION);
							if(ans == JOptionPane.YES_OPTION)
							{
							if(checkInputText(text, 0) == true)
							{
								try {
									if(chekJessID(connectionAdmin, text) == true)
									{
										deleteJessData(connectionAdmin, text);
										UpdateTableFR();
										JOptionPane.showMessageDialog(button_4, "Выбранные данные успешно удалены.","Поздравляю", JOptionPane.INFORMATION_MESSAGE);
										clearTextField();
										button_1.setEnabled(false);
									}
									else
									{
										JOptionPane.showMessageDialog(button_4,"Данных с ввведённым Идентификатором ЭС = "+text+", не существует.","Ошибка",JOptionPane.ERROR_MESSAGE);
									}
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							else
							{
								JOptionPane.showMessageDialog(button_4,"Некорректный ввод данных.","Ошибка",JOptionPane.ERROR_MESSAGE);
								return;
							}
							}
							else
								return;
						}
					});
					panel_1.add(button_4, "cell 4 0");
					
					JLabel lblNewLabel_7 = new JLabel("\u0420\u0430\u0437\u043C\u0435\u0440 \u0448\u0440\u0438\u0444\u0442\u0430:");
					panel_1.add(lblNewLabel_7, "flowx,cell 0 1");
				    
				    JScrollPane scrollPane_2 = new JScrollPane();
				    panel_1.add(scrollPane_2, "cell 0 2 5 1,grow");
					
				    textArea_3 = new JTextArea();
				    textArea_3.setBorder(new TitledBorder("Исходный код экспертной системы"));
				    textArea_3.setFont(new Font("Monospaced", Font.PLAIN, 14));
				    scrollPane_2.setViewportView(textArea_3);
					
					textField_14 = new JTextField();
					panel_1.add(textField_14, "cell 0 0,growx");
					textField_14.setColumns(10);
					
				final	JSpinner spinner = new JSpinner();
					spinner.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent arg0) {
							int fontsize = Integer.parseInt(spinner.getValue().toString());
							textArea_3.setFont(new Font("Monospaced", Font.PLAIN, fontsize));
						}
					});
					spinner.setModel(new SpinnerNumberModel(new Integer(14), new Integer(0), null, new Integer(1)));
					spinner.setToolTipText("\u0420\u0430\u0437\u043C\u0435\u0440 \u0448\u0440\u0438\u0444\u0442\u0430.");
					panel_1.add(spinner, "cell 0 1,growx");
			
		}
		protected void InformationInternalFrame()
		{
			JInternalFrame internalFrame_1 = new JInternalFrame("\u0421\u043F\u0440\u0430\u0432\u043E\u0447\u043D\u0430\u044F \u0438\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0438\u044F");
			//internalFrame_1.setMaximum(true);
			internalFrame_1.setClosable(true);
			internalFrame_1.setIconifiable(true);
			internalFrame_1.setMaximizable(true);
			internalFrame_1.setResizable(true);
			internalFrame_1.setBounds(0, 33, 334, 33);
			internalFrame_1.setVisible(true);
			desktopPane.add(internalFrame_1);
			internalFrame_1.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
			
			JScrollPane scrollPane = new JScrollPane();
			internalFrame_1.getContentPane().add(scrollPane, "cell 0 0,grow");
			
			JSplitPane splitPane = new JSplitPane();
			scrollPane.setViewportView(splitPane);
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			
			JPanel panel = new JPanel();
			splitPane.setLeftComponent(panel);
			panel.setLayout(new MigLayout("", "[][grow]", "[][154.00,grow]"));
			
			JLabel label = new JLabel("\u0424\u0438\u043B\u044C\u0442\u0440\u0430\u0446\u0438\u044F \u0442\u0430\u0431\u043B\u0438\u0446\u044B:");
			panel.add(label, "cell 0 0,alignx trailing");
			
			textField_10 = new JTextField();
			panel.add(textField_10, "cell 1 0,growx");
			textField_10.setColumns(10);
			textField_10.getDocument().addDocumentListener(new DocumentListener() {
				
				public void removeUpdate(DocumentEvent arg0) {
			         FiltInform();	
				}
				
				public void insertUpdate(DocumentEvent arg0) {
					FiltInform();
				}
				
				public void changedUpdate(DocumentEvent arg0) {
					FiltInform();					
				}
			});
			JScrollPane scrollPane_1 = new JScrollPane();
			panel.add(scrollPane_1, "cell 0 1 2 1,grow");
			
			try {
				sortInfrom = new TableRowSorter(getInformationTextData(connectionAdmin));
				table_1 = new JTable(getInformationTextData(connectionAdmin));
				table_1.setRowSorter(sortInfrom);
				table_1.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
				    int Row = table_1.getRowSorter().convertRowIndexToModel(table_1.getSelectedRow());
				    varId = table_1.getModel().getValueAt(Row, 0).toString();
				    nameTextData = table_1.getModel().getValueAt(Row, 1).toString();
				      textField_11.setText(table_1.getModel().getValueAt(Row, 0).toString());
					  textField_12.setText(table_1.getModel().getValueAt(Row, 1).toString());
					  textArea_2.setText(table_1.getModel().getValueAt(Row, 2).toString());
					  textArea_2.setCaretPosition(0);
					  button_3.setEnabled(true);
					  
					}
				});
			} catch (SQLException e) {
				e.printStackTrace();
			}
			scrollPane_1.setViewportView(table_1);
			
			JPanel panel_1 = new JPanel();
			splitPane.setRightComponent(panel_1);
			panel_1.setLayout(new MigLayout("", "[][][grow][][][]", "[][][grow]"));
			
			JLabel lblNewLabel_2 = new JLabel("\u0418\u0434\u0435\u043D\u0442\u0438\u0444\u0438\u043A\u0430\u0442\u043E\u0440 \u0442\u0435\u043A\u0441\u0442\u0430:");
			panel_1.add(lblNewLabel_2, "cell 0 0,alignx trailing");
			
			textField_11 = new JTextField();
			panel_1.add(textField_11, "cell 1 0,alignx left");
			textField_11.setColumns(10);
			
			JLabel lblNewLabel_3 = new JLabel("\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435 \u0442\u0435\u043A\u0441\u0442\u0430:");
			panel_1.add(lblNewLabel_3, "flowx,cell 2 0");
			
			textField_12 = new JTextField();
			panel_1.add(textField_12, "cell 2 0,growx");
			textField_12.setColumns(10);
			
			final JButton button = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String id = textField_11.getText();
					String name = textField_12.getText();
					String text = textArea_2.getText();
					if(name.isEmpty() || text.isEmpty())
					{
						JOptionPane.showMessageDialog(button,"Имеются незаполненные поля\n Строка Идентификатор текста может быть пустым.","Ошибка",JOptionPane.ERROR_MESSAGE);
					return;
					}
					else
					if(id.isEmpty())
					{
						try {
							if (chekInformationTextData(connectionAdmin, name) == false)
							{
                InsertTextData(connectionAdmin, null, checkApostrophe(name), checkApostrophe(text), 0);
                clearTextField();
                UpdateTableInform();
                JOptionPane.showMessageDialog(button, "Данные успешно добавлены.","Поздравляю", JOptionPane.INFORMATION_MESSAGE);
                button_3.setEnabled(false);
                return;
							}
							else {
								JOptionPane.showMessageDialog(button,"Введённое Наименование текста занято","Ошибка",JOptionPane.ERROR_MESSAGE);
								return;
							}
						} catch (HeadlessException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} else
						try {
							if(checkInputText(id, 0) == true)
							{
							if(chekInformationDataID(connectionAdmin, id) == false)
                               {
								if (chekInformationTextData(connectionAdmin, name) == false)
								{
                            InsertTextData(connectionAdmin, id, checkApostrophe(name), checkApostrophe(text), 1);
                            clearTextField();
                            UpdateTableInform();
                            JOptionPane.showMessageDialog(button, "Данные успешно добавлены.","Поздравляю", JOptionPane.INFORMATION_MESSAGE);
                            button_3.setEnabled(false);
								}
								else {
									JOptionPane.showMessageDialog(button,"Введённое Наименование текста занято","Ошибка",JOptionPane.ERROR_MESSAGE);
									return;
								}
                               }
                         else {
                        	 JOptionPane.showMessageDialog(button, "Введённый Идентификатор текста занят!!!","Ошибка", JOptionPane.ERROR_MESSAGE);
                        	 return;
                          }
							}
							else
							{
								JOptionPane.showMessageDialog(button,"Введены недопустимые символы.\n Строка Идентификатор текста может содержать только цифры.","Ошибка",JOptionPane.ERROR_MESSAGE);
								return;
							}
								
						} catch (HeadlessException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}
				}
			});
			panel_1.add(button, "cell 3 0");
			
			button_3 = new JButton("\u041E\u0431\u043D\u043E\u0432\u0438\u0442\u044C");
			button_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String idTextdata = textField_11.getText();
					String Name = checkApostrophe(textField_12.getText());
					String Text = checkApostrophe(textArea_2.getText());
					if(Text.isEmpty())
					{
						JOptionPane.showMessageDialog(button,"Имеются незаполненные поля.","Ошибка",JOptionPane.ERROR_MESSAGE);
						return;
					} 
					else
						try {
							if(idTextdata.isEmpty() == false && Name.isEmpty() == false)
							if(chekInformationDataID(connectionAdmin, idTextdata) == false)
							{
							if(chekInformationTextData(connectionAdmin, Name) == false)
							{
                           UpdateTextDATA(button_3,idTextdata, Name, Text);
                           return;
							}
							else
							{
								JOptionPane.showMessageDialog(button, "Введённое Наименование текста уже занято!!!","Ошибка", JOptionPane.ERROR_MESSAGE);
	              	              return;
							}
							}
                       else {
							JOptionPane.showMessageDialog(button, "Введённый Идентификатор текста занят!!!","Ошибка", JOptionPane.ERROR_MESSAGE);
              	              return;
                              }
							if(idTextdata.isEmpty() == false)
							{
								if(chekInformationDataID(connectionAdmin, idTextdata) == false)
								{
									 UpdateTextDATA(button_3,idTextdata, nameTextData, Text);
								}
								else {
									JOptionPane.showMessageDialog(button, "Введённый Идентификатор текста занят!!!","Ошибка", JOptionPane.ERROR_MESSAGE);
		              	              return;
								}
							}
							else
								if(Name.isEmpty() == false)
								{
									if(chekInformationTextData(connectionAdmin, Name) == false)
									{
		                           UpdateTextDATA(button_3,varId, Name, Text);
									}
									else
									{
										JOptionPane.showMessageDialog(button, "Введённое Наименование текста занято!!!","Ошибка", JOptionPane.ERROR_MESSAGE);
			              	              return;
									}
								}
							else
								if(idTextdata.isEmpty() && Name.isEmpty())
								{
									  UpdateTextDATA(button_3,varId, nameTextData, Text);
								}
								
							
						} catch (HeadlessException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}
				}
			});
			button_3.setEnabled(false);
			panel_1.add(button_3, "cell 4 0");
			
			final JButton button_1 = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String text = textField_11.getText();
					int ans;
					ans = JOptionPane.showConfirmDialog(button_1, "Вы уверены что хотите удалить запись с Идентификатором текста = "+text+"","Предупреждение!!!",JOptionPane.YES_NO_OPTION);
					if(ans == JOptionPane.YES_OPTION)
					{
					if(checkInputText(text, 0) == true)
					{
						try {
							if(chekInformationDataID(connectionAdmin, text) == true)
							{
								deleteTextData(connectionAdmin, text);
								UpdateTableInform();
								JOptionPane.showMessageDialog(button_1, "Выбранные данные успешно удалены.","Поздравляю", JOptionPane.INFORMATION_MESSAGE);
								clearTextField();
								button_3.setEnabled(false);
							}
							else
							{
								JOptionPane.showMessageDialog(button_1,"Данных с введённым Идентификатором текста = "+text+", не существует.","Ошибка",JOptionPane.ERROR_MESSAGE);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(button_1,"Некорректный ввод данных.","Ошибка",JOptionPane.ERROR_MESSAGE);
						return;
					}
					}
					else
						return;
				}
			});
			panel_1.add(button_1, "cell 5 0");
			
			JLabel lblNewLabel_4 = new JLabel("\u0420\u0430\u0437\u043C\u0435\u0440 \u0448\u0440\u0438\u0444\u0442\u0430:");
			panel_1.add(lblNewLabel_4, "flowx,cell 0 1,alignx right");
			
			final JSpinner spinner = new JSpinner();
			spinner.setToolTipText("\u0420\u0430\u0437\u043C\u0435\u0440 \u0448\u0440\u0438\u0444\u0442\u0430.");
			spinner.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					int fontsize = Integer.parseInt(spinner.getValue().toString());
					textArea_2.setFont(new Font("Monospaced", Font.PLAIN, fontsize));
					
				}
			});
			spinner.setModel(new SpinnerNumberModel(new Integer(14), new Integer(0), null, new Integer(1)));
			panel_1.add(spinner, "cell 1 1,growx");
			
			JScrollPane scrollPane_2 = new JScrollPane();
			panel_1.add(scrollPane_2, "cell 0 2 6 1,grow");
			
			textArea_2 = new JTextArea();
			textArea_2.setBorder(new TitledBorder("Текстовые данные"));
			textArea_2.setFont(new Font("Monospaced", Font.PLAIN, 14));
			scrollPane_2.setViewportView(textArea_2);
		}
	protected void UserInternalFrame()
	{
		JInternalFrame internalFrame = new JInternalFrame("\u041F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u0438");
		internalFrame.setVisible(true);
		internalFrame.setClosable(true);
		internalFrame.setResizable(true);
		internalFrame.setMaximizable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setBounds(0, 0, 334, 39);
		desktopPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(new MigLayout("", "[508px,grow]", "[244px,grow]"));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		internalFrame.getContentPane().add(scrollPane_1, "cell 0 0,grow");
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setContinuousLayout(true);
		scrollPane_1.setViewportView(splitPane);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new MigLayout("", "[][261.00px,grow][74.00,right][67.00][]", "[20px][257.00,grow]"));
		
		JLabel label = new JLabel("\u0424\u0438\u043B\u044C\u0442\u0440\u0430\u0446\u0438\u044F \u0442\u0430\u0431\u043B\u0438\u0446\u044B:");
		panel.add(label, "cell 0 0,alignx trailing");
		
		textField_3 = new JTextField();
		panel.add(textField_3, "cell 1 0 4 1,grow");
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
		panel.add(scrollPane, "cell 0 1 5 1,grow");
		try {
			sort = new TableRowSorter(getUserData(connectionUser));
			table = new JTable(getUserData(connectionUser));
			table.setRowSorter(sort);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
			  int selRow = table.getRowSorter().convertRowIndexToModel(table.getSelectedRow());
			  varId = table.getModel().getValueAt(selRow, 0).toString();
			  LoginU = table.getModel().getValueAt(selRow, 1).toString();
	          textField_4.setText(table.getModel().getValueAt(selRow, 0).toString());
			  textField_5.setText(table.getModel().getValueAt(selRow, 1).toString());
			  textField_6.setText(table.getModel().getValueAt(selRow, 2).toString());
			  textField_7.setText(table.getModel().getValueAt(selRow, 3).toString());
			  textField_8.setText(table.getModel().getValueAt(selRow, 4).toString());
			  textField_9.setText(table.getModel().getValueAt(selRow, 5).toString());
			  if(table.getModel().getValueAt(selRow, 6).equals("admin"))
			  {
				  comboBox.setModel(new DefaultComboBoxModel(new String []{"admin","user"}));
			  }
			  else
				  comboBox.setModel(new DefaultComboBoxModel(new String []{"user","admin"}));
			  button_2.setEnabled(true);
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new MigLayout("", "[319.00][][][][][grow][grow][][][]", "[][][][][][][]"));
		
		JLabel lblIduser = new JLabel("\u0418\u0434\u0435\u043D\u0442\u0438\u0444\u0438\u043A\u0430\u0442\u043E\u0440 \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F:");
		panel_1.add(lblIduser, "flowx,cell 0 0");
		
		JLabel lblName = new JLabel("\u0418\u043C\u044F \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F:");
		panel_1.add(lblName, "cell 1 0");
		
		textField_4 = new JTextField();
		panel_1.add(textField_4, "cell 0 1,growx");
		textField_4.setColumns(10);
		
		textField_7 = new JTextField();
		panel_1.add(textField_7, "cell 1 1 9 1,growx");
		textField_7.setColumns(10);
		
		JLabel lblLogin = new JLabel("\u041B\u043E\u0433\u0438\u043D \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F:");
		panel_1.add(lblLogin, "cell 0 2");
		
		JLabel lblNewLabel = new JLabel("\u0424\u0430\u043C\u0438\u043B\u0438\u044F \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F:");
		panel_1.add(lblNewLabel, "cell 1 2");
		
		textField_5 = new JTextField();
		panel_1.add(textField_5, "cell 0 3,growx");
		textField_5.setColumns(10);
		
		textField_8 = new JTextField();
		panel_1.add(textField_8, "cell 1 3 9 1,growx");
		textField_8.setColumns(10);
		
		JLabel lblPassword = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F:");
		panel_1.add(lblPassword, "cell 0 4");
		
		JLabel lblPatronymic = new JLabel("\u041E\u0442\u0447\u0435\u0441\u0442\u0432\u043E \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F:");
		panel_1.add(lblPatronymic, "cell 1 4");
		
		textField_6 = new JTextField();
		panel_1.add(textField_6, "cell 0 5,growx");
		textField_6.setColumns(10);
		
		textField_9 = new JTextField();
		panel_1.add(textField_9, "cell 1 5 9 1,growx");
		textField_9.setColumns(10);
		
		JLabel lblType = new JLabel("\u0422\u0438\u043F \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F:");
		panel_1.add(lblType, "flowx,cell 0 6");
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"user", "admin"}));
		panel_1.add(comboBox, "cell 0 6,alignx left");
		
		final JButton button = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = textField_4.getText();
				int ans;
				if(id.isEmpty())
				{
					JOptionPane.showMessageDialog(button,"Строка Идентификатор пользователя пустая.","Ошибка",JOptionPane.ERROR_MESSAGE);
				return;
				}
				else
				ans = JOptionPane.showConfirmDialog(button, "Вы уверены что хотите удалить пользователя с Идентификатором = "+id+"","Предупреждение!!!",JOptionPane.YES_NO_OPTION);
				if(ans == JOptionPane.YES_OPTION)
				{
					if(checkInputText(id, 0) == false)
					{
						JOptionPane.showMessageDialog(button,"Введены недопустимые символы.\n Строка Идентификатор пользователя может содержать только цифры.","Ошибка",JOptionPane.ERROR_MESSAGE);
						button_2.setEnabled(false);
						return;
					} else
						try {
							if(chekUserID(connectionAdmin, id) == true)
							{
							deleteUser(connectionAdmin, id);
							UpdateTable();
							JOptionPane.showMessageDialog(button, "Выбранный пользователь успешно удален.","Поздравляю", JOptionPane.INFORMATION_MESSAGE);
							clearTextField();
							button_2.setEnabled(false);
							}
							else
							{
								JOptionPane.showMessageDialog(button,"Пользователя с введённым Идентификатором = "+id+", не существует.","Ошибка",JOptionPane.ERROR_MESSAGE);
								button_2.setEnabled(false);
								return;
							}
						} catch (HeadlessException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}
				}
				else 
				{
					return;
				}
			}
		});
		
		final JButton button_1 = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CheckInsertUserInputData(button_1);
				button_2.setEnabled(false);
			}
		});
		panel_1.add(button_1, "flowx,cell 7 6");
		panel_1.add(button, "cell 8 6,alignx right");
		
		button_2 = new JButton("\u041E\u0431\u043D\u043E\u0432\u0438\u0442\u044C");
		button_2.setEnabled(false);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CheckUpdateUserInputData(button_2);
			}
		});
		panel_1.add(button_2, "cell 9 6,alignx right");
		
	}
	protected void UpdateJessDATA(JButton button, String id,String Name, String jessCode)
	{
		UpdateJessData(connectionAdmin, id, Name, jessCode, varId);
        UpdateTableFR();
        clearTextField();
		JOptionPane.showMessageDialog(button, "Данные  успешно изменены.","Поздравляю", JOptionPane.INFORMATION_MESSAGE);
		button_1.setEnabled(false);
		return;
	}
	protected void UpdateTextDATA(JButton button, String id,String Name, String Text)
	{
		UpdateTextData(connectionAdmin, id, Name, Text, varId);
        UpdateTableInform();
        clearTextField();
		JOptionPane.showMessageDialog(button, "Данные  успешно изменены.","Поздравляю", JOptionPane.INFORMATION_MESSAGE);
		button_3.setEnabled(false);
		return;
	}
	protected void CheckUpdateUserInputData(JButton button)
	{
		try {
			String text = textField_4.getText();
			if(checkAllInputText("", "", textField_6.getText(), textField_7.getText(), textField_8.getText(),textField_9.getText(), 2) == false)
			{
				JOptionPane.showMessageDialog(button,"Имеются незаполненные поля\nСтрока Идентификатор пользователя  может быть пустым.","Ошибка",JOptionPane.ERROR_MESSAGE);
				return;
			}
			else
				if(checkInputText(textField_7.getText()+textField_8.getText()+textField_9.getText() , 2) == false)
				{
					 JOptionPane.showMessageDialog(button,"Введены недопустимые символы, возможно латиская буква или цифра","Ошибка",JOptionPane.ERROR_MESSAGE);
					 return;
				}
				else
					if(checkInputText(textField_5.getText(), 1) == false && textField_5.getText().isEmpty() == false)
					{
						JOptionPane.showMessageDialog(button,"Введены недопустимые символы, возможно кириллические буквы.\nМинимальны длина логина 3 символа, максимальная 30 символов.","Ошибка",JOptionPane.ERROR_MESSAGE);
						return;
					}
					else
						if(checkInputText(textField_6.getText() , 1) == false)
						{
							JOptionPane.showMessageDialog(button,"Введены недопустимые символы, возможно кириллические буквы.\nМинимальны длина пароля 3 символа, максимальная 30 символов.","Ошибка",JOptionPane.ERROR_MESSAGE);
							return;
						}
			else 
			if(chekUserID(connectionAdmin, text) == false)
			{
				if(checkLoginPass(connectionAdmin, textField_5.getText(), false, false) == null)
				{
			if(text.isEmpty() || textField_5.getText().isEmpty())
			{
				UpdateDataUser(connectionAdmin, varId, LoginU, textField_6.getText(), textField_7.getText(), textField_8.getText(),textField_9.getText(), (String)comboBox.getSelectedItem(),varId);	
				UpdateTable();
				JOptionPane.showMessageDialog(button, "Данные успешно изменены.","Поздравляю", JOptionPane.INFORMATION_MESSAGE);
				clearTextField();
				button_2.setEnabled(false);
				return;
			}
			else
				if(checkInputText(text, 0) == false)
				{
					JOptionPane.showMessageDialog(button,"Введены недопустимые символы.\nСтрока Идентификатор пользователя может содержать только цифры.","Ошибка",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
			{
			UpdateDataUser(connectionAdmin, text, textField_5.getText(), textField_6.getText(), textField_7.getText(), textField_8.getText(),textField_9.getText(), (String)comboBox.getSelectedItem(),varId);
			UpdateTable();
			JOptionPane.showMessageDialog(button, "Данные успешно изменены.","Поздравляю", JOptionPane.INFORMATION_MESSAGE);
			clearTextField();
			button_2.setEnabled(false);
			return;
			}
				}
				else
					JOptionPane.showMessageDialog(button,"Логин занят","Ошибка",JOptionPane.ERROR_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(button, "Введённый Идентификатор пользователя занят!!!","Ошибка", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void CheckInsertUserInputData(JButton button)
	{
		try {
			String text = textField_4.getText();
			if(checkAllInputText("", textField_5.getText(), textField_6.getText(), textField_7.getText(), textField_8.getText(),textField_9.getText(), 1) == false)
			{
				JOptionPane.showMessageDialog(button,"Имеются незаполненные поля\n Строка Идентификатор пользователя может быть пустым.","Ошибка",JOptionPane.ERROR_MESSAGE);
				return;
			}
			else
				if(checkInputText(textField_7.getText()+textField_8.getText()+textField_9.getText() , 2) == false)
				{
					 JOptionPane.showMessageDialog(button,"Введены недопустимые символы, возможно латиская буква или цифра","Ошибка",JOptionPane.ERROR_MESSAGE);
					 return;
				}
				else
					if(checkInputText(textField_5.getText(), 1) == false)
					{
						JOptionPane.showMessageDialog(button,"Введены недопустимые символы, возможно кириллические буквы.\nМинимальны длина логина и пароля 3 символа, максимальная 30 символов.","Ошибка",JOptionPane.ERROR_MESSAGE);
						return;
					}
					else
						if(checkInputText(textField_6.getText() , 1) == false)
						{
							JOptionPane.showMessageDialog(button,"Введены недопустимые символы, возможно кириллические буквы.\nМинимальны длина логина и пароля 3 символа, максимальная 30 символов.","Ошибка",JOptionPane.ERROR_MESSAGE);
							return;
						}
			else 
			if(chekUserID(connectionAdmin, text) == false)
			{
				if(checkLoginPass(connectionAdmin, textField_5.getText(), false, false) == null)
				{
			if(text.isEmpty())
			{
				InsertDataUser(connectionAdmin, "", textField_5.getText(), textField_6.getText(), textField_7.getText(), textField_8.getText(),textField_9.getText(), (String)comboBox.getSelectedItem(),0);	
				UpdateTable();
				JOptionPane.showMessageDialog(button, "Новый пользователь успешно добавлен.","Поздравляю", JOptionPane.INFORMATION_MESSAGE);
				clearTextField();
				return;
			}
			else
				if(checkInputText(text, 0) == false)
				{
					JOptionPane.showMessageDialog(button,"Введены недопустимые символы.\nСтрока Идентификатор пользователя может содержать только цифры.","Ошибка",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
			{
			InsertDataUser(connectionAdmin, text, textField_5.getText(), textField_6.getText(), textField_7.getText(), textField_8.getText(),textField_9.getText(), (String)comboBox.getSelectedItem(),1);
			UpdateTable();
			JOptionPane.showMessageDialog(button, "Новый пользователь успешно добавлен.","Поздравляю", JOptionPane.INFORMATION_MESSAGE);
			clearTextField();
			return;
			}
				}
				else
					JOptionPane.showMessageDialog(button,"Логин занят","Ошибка",JOptionPane.ERROR_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(button, "Введённый Идентификатор пользователя занят!!!","Ошибка", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void UpdateTableFR()
	{
		try {
			sortFR = new TableRowSorter(getJessTableData(connectionAdmin));
			table_2.setModel(getJessTableData(connectionAdmin));
			table_2.setRowSorter(sortFR);
			textArea_3.setText("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void UpdateTableInform()
	{
		try {
			sortInfrom = new TableRowSorter(getInformationTextData(connectionAdmin));
			table_1.setModel(getInformationTextData(connectionAdmin));
			table_1.setRowSorter(sortInfrom);
			textArea_2.setText("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void UpdateTable()
	{
		try {
			sort = new TableRowSorter(getUserData(connectionAdmin));
			table.setModel(getUserData(connectionAdmin));
			table.setRowSorter(sort);			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void clearTextField()
	{
		textField_4.setText("");
		textField_5.setText("");
		textField_6.setText("");
		textField_7.setText("");
		textField_8.setText("");
		textField_9.setText("");	
		textField_10.setText("");
		textField_11.setText("");
		textField_12.setText("");
		textField_14.setText("");
		textField_15.setText("");
	}
	protected void Filt()
	{
		RowFilter  rowFilter = null;
		try {
		rowFilter = RowFilter.regexFilter(textField_3.getText(), new int[]{});
		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}
		sort.setRowFilter(rowFilter);
		button_2.setEnabled(false);
	}
	protected void FiltInform()
	{
		RowFilter  rowFilter = null;
		try {
		rowFilter = RowFilter.regexFilter(textField_10.getText(), new int[]{});
		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}
		sortInfrom.setRowFilter(rowFilter);
		button_3.setEnabled(false);
		
	}
	protected void FiltFR()
	{
		RowFilter  rowFilter = null;
		try {
		rowFilter = RowFilter.regexFilter(textField_13.getText(), new int[]{});
		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}
		sortFR.setRowFilter(rowFilter);
		button_1.setEnabled(false);
		
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
					rete.run();
					rete.eval("(clear)");
					activJess = false;
					comboBox_1.setEnabled(true);
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
	private JTextField textField_10;
	private JTable table_1;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTable table_2;
	private JTextField textField_14;
	private JTextField textField_15;

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
			label_1.setText("Ожидание ввода...");
			return;
		}
		int start = textA.indexOf(textF,0);
		if(start == -1)
		{
			textField.setBackground(Color.PINK);
			label_1.setText("Совпадений не найдено.");
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
					label_1.setText("Совпадений найдено " + startText.size() + " из " + textA.length());
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
	    }
		else
			if(numb < 0 || numb > startText.size())
		{
			step = 0;
			step(step);
		}
	}
	}
