package com.appbuddy.buddypasswordmanager.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 * @author Gavin Bauman - Christopher Bell - Gregory Degruy - Adrian
 *         Smith-Thompson updated: 5/28/2013
 * @version 2.0
 */


public class GUI extends JFrame {
	private JButton fileBtn;
	private JList fileOptions, viewOptions, editOptions, helpOptions;
	private JScrollPane tableUPWContainer, fileOptionsPane, tableScroller;
	private DefaultTableModel modelTable;
	private JTable table;
	private BPasswordManagerTable bTable;

	private JMenuBar bpmMenuBar;
	private JMenu fileMenu, editMenu, viewMenu, helpMenu;
	private JMenuItem fNew, fOpen, fSave, fClose, fLoad, fExit; // file
	private JMenuItem eCut, eCopy, ePaste, eSelectAll, eExit; // edit
	private JMenuItem vAll, vHidePasswords, vExit; // view
	private JMenuItem hABPMHelp, hABOnline, hContactUs, hExit; // help

	private NewInformationGUI infogui;
	private HelpGUI help;

	public GUI() {
		setTitle("Buddy Password Manager");
		getContentPane();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(600, 400, 600, 600);

		setJMenuBar(new BPasswordManagerMenuBar());
		add(new BPasswordManagerTable());

		setVisible(true);
	}

	public class BPasswordManagerMenuBar extends JMenuBar {
		public BPasswordManagerMenuBar() {

			fileMenu = new JMenu("File");
			fileMenu.setMnemonic(KeyEvent.VK_F);// key board navigation by ALT
			fNew = new JMenuItem("New");
			fOpen = new JMenuItem("Open");
			fSave = new JMenuItem("Save/Coming Soon");
			fLoad = new JMenuItem("Load");
			fExit = new JMenuItem("Exit");
			fileMenu.add(fNew);
			fileMenu.add(fOpen);
			fileMenu.add(fSave);
			fileMenu.add(fLoad);
			fileMenu.addSeparator();
			fileMenu.add(fExit);

			editMenu = new JMenu("Edit");
			editMenu.setMnemonic(KeyEvent.VK_E);
			eCut = new JMenuItem("Cut");
			eCopy = new JMenuItem("Copy");
			ePaste = new JMenuItem("Paste");
			eSelectAll = new JMenuItem("SelectAll");
			editMenu.add(eCut);
			editMenu.add(eCopy);
			editMenu.add(ePaste);
			editMenu.addSeparator();
			editMenu.add(eSelectAll);

			viewMenu = new JMenu("View");
			viewMenu.setMnemonic(KeyEvent.VK_V);
			vAll = new JMenuItem("All");
			vHidePasswords = new JMenuItem("Hide Passwords");
			viewMenu.add(vAll);
			viewMenu.add(vHidePasswords);

			helpMenu = new JMenu("Help");
			helpMenu.setMnemonic(KeyEvent.VK_H);
			hABPMHelp = new JMenuItem("App Buddy Help");
			hABOnline = new JMenuItem("App Buddy Online");
			hContactUs = new JMenuItem("Contact Us");
			helpMenu.add(hABPMHelp);
			helpMenu.add(hABOnline);
			helpMenu.add(hContactUs);

			bpmMenuBar = new JMenuBar();
			bpmMenuBar.add(fileMenu);
			bpmMenuBar.add(editMenu);
			bpmMenuBar.add(viewMenu);
			bpmMenuBar.add(helpMenu);

			add(bpmMenuBar);

			fNew.addActionListener(new openNew());
			hABPMHelp.addActionListener(new openHelpMenu());
			fLoad.addActionListener(new loadFile());
		}
	}

	public class BPasswordManagerTable extends JPanel {
		public BPasswordManagerTable() {
			setLayout(null);
			modelTable = new DefaultTableModel() {
				public boolean isCellEditable(int rowIndex, int mColIndex) {
					return false;
				}
			};

			table = new JTable(modelTable);
			tableScroller = new JScrollPane(table);

			add(tableScroller);
			tableScroller.setBounds(1, 1, 600, 553);

			setUpTable();
			setBounds(0, 0, 600, 600);
		}

		public void setUpTable() {
			String[] colTitles = { "Websites", "Usernames", "Passwords" };
			for (int i = 0; i < 3; i++) {
				modelTable.addColumn(colTitles[i]);
			}

			table.getTableHeader().setResizingAllowed(false);
			table.getTableHeader().setReorderingAllowed(false);
			table.setColumnSelectionAllowed(true);
			table.setRowSelectionAllowed(true);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setRowHeight(30);

			modelTable.setColumnCount(3);
			modelTable.setRowCount(25);

			populateTable();
		}
	}

	public void populateTable() {
		int row = 0;
		int col = 0;
		String nameOfUser = System.getProperty("user.name");
		System.out.println("The name of the user is " + nameOfUser);
		String entry;

		File filePath = new File("/Users/" + nameOfUser + "/Documents/Buddy Information.txt");
		System.out.println("The path that the file will be in is " + filePath);

		try {
			Scanner input = new Scanner(filePath);

			while (input.hasNextLine()) {
				entry = input.nextLine();
				System.out.println(entry);
				if (col >= 3) {
					col = 0;
				}
				modelTable.setValueAt(entry, row, col);

				if (col == 2) {
					row++;
				}
				col++;
			}
		} catch (Exception e) {
			System.err.format("File not Found");
		}
	}

	public class openNew implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			infogui = new NewInformationGUI();
		}
	}

	public class openHelpMenu implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			help = new HelpGUI();
		}
	}

	public class loadFile implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			populateTable();
		}
	}
}
