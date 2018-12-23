package gUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entityCreator.EntitiesGeneratorForDatabase;
import graphdb.CreateConnection;
import main.MainClass;
import query.QueryInfo;
import relationshipCreator.RelationshipGeneratorForDatabase;
import utility.FileReaderAndWriter;
import utility.InputAndOutputReaderForGUI;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;

public class UserInterface {

	public JFrame frame;
	private final ButtonGroup queryButtonGroup = new ButtonGroup();
	static InputAndOutputReaderForGUI reader;
	private JComboBox querySelectionList;
	private final ButtonGroup repoButtonGroup = new ButtonGroup();
	private boolean isCustom = false; 
	private boolean isBasic = false;
	private String repoID;
	private String prefixString = new String("prefix person: <person:>\r\n" + 
			"prefix event: <event:>\r\n" + 
			"prefix time: <time:>\r\n" + 
			"prefix country: <country:>\r\n" + 
			"prefix location: <location:>\r\n" + 
			"prefix organization: <organization:>\r\n" + 
			"prefix object: <object:>\r\n" + 
			"prefix pp: <pp:>\r\n" + 
			"prefix pe: <pe:>\r\n" + 
			"prefix pt: <pt:>\r\n" + 
			"prefix pc: <pc:>\r\n" + 
			"prefix pl: <pl:>\r\n" + 
			"prefix po: <po:>\r\n" + 
			"prefix ep: <ep:>\r\n" + 
			"prefix ee: <ee:>\r\n" + 
			"prefix et: <et:>\r\n" + 
			"prefix ec: <ec:>\r\n" + 
			"prefix el: <el:>\r\n" + 
			"prefix eo: <eo:>\r\n" + 
			"prefix tp: <tp:>\r\n" + 
			"prefix te: <te:>\r\n" + 
			"prefix tc: <tc:>\r\n" + 
			"prefix tl: <tl:>\r\n" + 
			"prefix to: <to:>\r\n" + 
			"prefix cp: <cp:>\r\n" + 
			"prefix cc: <cc:>\r\n" + 
			"prefix ce: <ce:>\r\n" + 
			"prefix cl: <cl:>\r\n" + 
			"prefix co: <co:>\r\n" + 
			"prefix ct: <ct:>\r\n" + 
			"prefix lp: <lp:>\r\n" + 
			"prefix le: <le:>\r\n" + 
			"prefix lc: <lc:>\r\n" + 
			"prefix lo: <lo:>\r\n" + 
			"prefix lt: <lt:>\r\n" + 
			"prefix ll: <ll:>\r\n" + 
			"prefix op: <op:>\r\n" + 
			"prefix oe: <oe:>\r\n" + 
			"prefix oc: <oc:>\r\n" + 
			"prefix ol: <ol:>\r\n" + 
			"prefix ot: <ot:> \r\n" + 
			"prefix oo: <oo:> ");
	private JTextArea textArea_1;
	private JTextArea textArea;
	private JTextField numOfEntity;
	private JTextField numOfRelationship;
	
	
	
	/* Launch the application. For testing purpose only
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	/*
	 * use this method to add entities and relationships to database
	 * @param: n == num of entites, m == num of relationships
	 */
	private void addEntityAndRelationship(int n, int m) {
		BufferedWriter writer = null;
		writer = FileReaderAndWriter.writer("data\\query\\output.txt", false);
		long start = System.currentTimeMillis();		
		// entity generator
		EntitiesGeneratorForDatabase e = new EntitiesGeneratorForDatabase();
		e.generateEntitiesForDatabase(n);
		// relationship generator
		RelationshipGeneratorForDatabase r = new RelationshipGeneratorForDatabase();
		r.generateRelationshipForDatabase(m);
		long end = System.currentTimeMillis();
		String output = new String("Repository " + repoID + " Successfully created " + n + " entites and " + m +
								   " relationships in " + (end - start) + "ms");
		try {
			writer.write(output);
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void setRepoID(String id) {
		this.repoID = new String(id);
	}

	/**
	 * Create the application.
	 */
	public UserInterface() {
		reader = new InputAndOutputReaderForGUI();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("OOP Project");
		BorderLayout borderLayout = (BorderLayout) frame.getContentPane().getLayout();
		frame.setBounds(100, 100, 876, 657);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel interacablePanel = new JPanel();
		frame.getContentPane().add(interacablePanel, BorderLayout.NORTH);
		interacablePanel.setLayout(new GridLayout(1, 4, 0, 0));
		
		JPanel databasePanel = new JPanel();
		interacablePanel.add(databasePanel);
		databasePanel.setLayout(new GridLayout(5, 1, 0, 0));
		
		JRadioButton databaseButton1 = new JRadioButton("E = 100 ; R = 200");
		databaseButton1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		repoButtonGroup.add(databaseButton1);
		databaseButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRepoID("E_100_R_200");
				MainClass.connection = CreateConnection.getRepositoryConnection("E_100_R_200");
			}
		});
		databasePanel.add(databaseButton1);
		
		JRadioButton databaseButton2 = new JRadioButton("E = 5000; R = 7000");
		databaseButton2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		repoButtonGroup.add(databaseButton2);
		databaseButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRepoID("E_5000_R_7000");
				MainClass.connection = CreateConnection.getRepositoryConnection("E_5000_R_7000");
			}
		});
		databasePanel.add(databaseButton2);
		
		JRadioButton databaseButton3 = new JRadioButton("E = 60K; R = 80K");
		databaseButton3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		repoButtonGroup.add(databaseButton3);
		databaseButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRepoID("E_60k_R_80k");
				MainClass.connection = CreateConnection.getRepositoryConnection("E_60k_R_80k");
			}
		});
		databasePanel.add(databaseButton3);
		
		JRadioButton databaseButton4 = new JRadioButton("E = 1M; R = 2M");
		databaseButton4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		repoButtonGroup.add(databaseButton4);
		databaseButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRepoID("E_1M_R_2M");
				MainClass.connection = CreateConnection.getRepositoryConnection("E_1M_R_2M");
			}
		});
		databasePanel.add(databaseButton4);
		
		JRadioButton databaseButton5 = new JRadioButton("E = 15M; R = 17M");
		databaseButton5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		repoButtonGroup.add(databaseButton5);
		databaseButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRepoID("E_15M_R_17M");
				MainClass.connection = CreateConnection.getRepositoryConnection("E_15M_R_17M");
			}
		});
		databasePanel.add(databaseButton5);
		
		JPanel queryTypeSelectionList = new JPanel();
		interacablePanel.add(queryTypeSelectionList);
		queryTypeSelectionList.setLayout(new GridLayout(0, 1, 0, 0));
		
		JRadioButton basicQueryRadioButton = new JRadioButton("Truy V\u1EA5n C\u01A1 B\u1EA3n");
		basicQueryRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		basicQueryRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isCustom = false;
				isBasic = true;
				getQuerySelectionList().removeAllItems();
				for(int i = 0; i < 10; i++) {
					getQuerySelectionList().addItem(reader.basicQueryName.get(i).toString());
				}
			}
		});
		queryButtonGroup.add(basicQueryRadioButton);
		queryTypeSelectionList.add(basicQueryRadioButton);
		
		JRadioButton advancedQueryRadioButton = new JRadioButton("Truy V\u1EA5n N\u00E2ng Cao");
		advancedQueryRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		advancedQueryRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isCustom = false;
				isBasic = false;
				getQuerySelectionList().removeAllItems();;
				for(int i = 0; i < 10; i++) {
					getQuerySelectionList().addItem(reader.advancedQueryName.get(i).toString());
				}
			}
		});
		queryButtonGroup.add(advancedQueryRadioButton);
		queryTypeSelectionList.add(advancedQueryRadioButton);
		
		JRadioButton customQueryRadioButton = new JRadioButton("Truy V\u1EA5n T\u00F9y Ch\u1ECDn");
		customQueryRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		customQueryRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isCustom = true;
				getQuerySelectionList().removeAllItems();
			}
		});
		queryButtonGroup.add(customQueryRadioButton);
		queryTypeSelectionList.add(customQueryRadioButton);
		
		JScrollPane scrollPane = new JScrollPane();
		queryTypeSelectionList.add(scrollPane);
		
		querySelectionList = new JComboBox();
		querySelectionList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(querySelectionList);
		querySelectionList.setMaximumRowCount(10);
		
		
		JScrollPane queryCustomString = new JScrollPane();
		interacablePanel.add(queryCustomString);
		
		textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Roboto", Font.PLAIN, 24));
		textArea_1.setToolTipText("Enter custom query string");
		queryCustomString.setViewportView(textArea_1);
		textArea_1.setRows(5);
		textArea_1.setColumns(4);
		
		JScrollPane resultPanel = new JScrollPane();
		frame.getContentPane().add(resultPanel, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Roboto", Font.PLAIN, 20));
		textArea.setEditable(false);
		resultPanel.setViewportView(textArea);
		
		JPanel buttonPanel = new JPanel();
		frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new GridLayout(1, 3, 0, 0));
		
		JButton queryButton = new JButton("Query");
		queryButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		queryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String queryString = new String();
				if(isCustom) {
					queryString = prefixString + getTextArea_1().getText();
				}
				else{
					if(isBasic) {
						queryString = prefixString + reader.basicQueryString.get(getQuerySelectionList().getSelectedIndex());
					}
					else {
						queryString = prefixString + reader.advancedQueryString.get(getQuerySelectionList().getSelectedIndex());
					}
				}	
				QueryInfo queryController = new QueryInfo();
				queryController.query(queryString);
				getTextArea().setText(reader.outputReader().toString());
			}
		});
		
		JPanel generatePanel = new JPanel();
		buttonPanel.add(generatePanel);
		generatePanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel infoPanel = new JPanel();
		generatePanel.add(infoPanel);
		infoPanel.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel entity = new JLabel("Entity");
		entity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		entity.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(entity);
		
		numOfEntity = new JTextField();
		numOfEntity.setHorizontalAlignment(SwingConstants.CENTER);
		numOfEntity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		infoPanel.add(numOfEntity);
		numOfEntity.setColumns(1);
		
		JLabel relationship = new JLabel("Relationship");
		relationship.setFont(new Font("Tahoma", Font.PLAIN, 15));
		relationship.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(relationship);
		
		numOfRelationship = new JTextField();
		numOfRelationship.setHorizontalAlignment(SwingConstants.CENTER);
		numOfRelationship.setFont(new Font("Tahoma", Font.PLAIN, 15));
		infoPanel.add(numOfRelationship);
		numOfRelationship.setColumns(1);
		
		JButton generateButton = new JButton("Generate");
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int entity = Integer.parseInt(getNumOfEntity().getText());
				int relationship = Integer.parseInt(getNumOfRelationship().getText());
				addEntityAndRelationship(entity, relationship);
				getTextArea().setText(reader.outputReader().toString());
			}
		});
		generateButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		generatePanel.add(generateButton);
		buttonPanel.add(queryButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		buttonPanel.add(exitButton);
	}
	public JComboBox getQuerySelectionList() {
		return querySelectionList;
	}
	public JTextArea getTextArea_1() {
		return textArea_1;
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	public JTextField getNumOfEntity() {
		return numOfEntity;
	}
	public JTextField getNumOfRelationship() {
		return numOfRelationship;
	}
}
