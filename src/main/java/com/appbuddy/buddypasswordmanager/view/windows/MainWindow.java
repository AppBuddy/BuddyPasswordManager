package com.appbuddy.buddypasswordmanager.view.windows;

import com.appbuddy.buddypasswordmanager.view.EntryListTable;
import com.appbuddy.buddypasswordmanager.view.MenuBar;
import javax.swing.JFrame;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Gavin Bauman
 * @author Christopher Bell
 * @author Gregory Degruy
 * @author Adrian Smith-Thompson
 * @version 2.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public final class MainWindow extends JFrame {

  private EntryListTable entryListTable;
  private MenuBar customMenuBar;
  public static final String TITLE = "Buddy Password Manager";
  public static final int DEFAULT_WINDOW_BOUNDS_X = 600;
  public static final int DEFAULT_WINDOW_BOUNDS_Y = 400;
  public static final int DEFAULT_WINDOW_WIDTH = 600;
  public static final int DEFAULT_WINDOW_HEIGHT = 600;

  public MainWindow() {
    this.entryListTable = new EntryListTable();
    this.customMenuBar = new MenuBar();
  }

  public void initWindow() {
    this.setTitle(TITLE);
    this.getContentPane();
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setBounds(DEFAULT_WINDOW_BOUNDS_X, DEFAULT_WINDOW_BOUNDS_Y, DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
    this.setJMenuBar(customMenuBar);
    this.add(entryListTable);
    setVisible(true);
  }
}
