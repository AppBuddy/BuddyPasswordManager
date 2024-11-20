package com.appbuddy.buddypasswordmanager.view;

import com.appbuddy.buddypasswordmanager.services.BuddyPasswordManagerService;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EntryListTable extends JPanel {
  private DefaultTableModel modelTable;
  private final JTable table;

  public EntryListTable() {
    setLayout(null);
    modelTable = new DefaultTableModel();
    table = new JTable(modelTable);
    var tableScroller = new JScrollPane(table);
    tableScroller.setBounds(1, 1, 600, 553);
    add(tableScroller);
    initEntryTable();
    setBounds(0, 0, 600, 600);
  }

  /**
   *
   */
  public void initEntryTable() {
    String[] colTitles = {"Websites", "Usernames", "Passwords"};
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

    BuddyPasswordManagerService.populateTable();
  }
}
