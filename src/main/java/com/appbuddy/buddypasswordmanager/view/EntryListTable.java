package com.appbuddy.buddypasswordmanager.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

  private static final String[] COLUMN_TITLES = {"Websites", "Usernames", "Passwords"};
  private static final int TABLE_WIDTH = 600;
  private static final int TABLE_HEIGHT = 553;
  private static final int PANEL_HEIGHT = 600;
  private static final int ROW_HEIGHT = 30;
  private static final int DEFAULT_ROW_COUNT = 25;

  public EntryListTable() {

    // Set layout and size
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(TABLE_WIDTH, PANEL_HEIGHT));

    // Initialize table model and table
    modelTable = new DefaultTableModel(COLUMN_TITLES, DEFAULT_ROW_COUNT);
    table = new JTable(modelTable);

    // Configure table properties
    configureTable();

    // Add table to a scroll pane and then to the panel
    JScrollPane tableScroller = new JScrollPane(table);
    add(tableScroller, BorderLayout.CENTER);
  }


  /**
   * Configures the table properties such as selection mode, row height, and header behavior.
   */
  private void configureTable() {
    table.getTableHeader().setResizingAllowed(false);
    table.getTableHeader().setReorderingAllowed(false);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.setRowHeight(ROW_HEIGHT);
  }

  /**
   * Adds a new row to the table with the given data.
   *
   * @param rowData the data to be added as a new row
   */
  public void addRow(Object[] rowData) {
    modelTable.addRow(rowData);
  }

  /**
   * Removes a row from the table at the specified index.
   *
   * @param rowIndex the index of the row to remove
   */
  public void removeRow(int rowIndex) {
    if (rowIndex >= 0 && rowIndex < modelTable.getRowCount()) {
      modelTable.removeRow(rowIndex);
    } else {
      throw new IllegalArgumentException("Invalid row index");
    }
  }

  /**
   * Clears all rows from the table.
   */
  public void clearTable() {
    modelTable.setRowCount(0);
  }
}
