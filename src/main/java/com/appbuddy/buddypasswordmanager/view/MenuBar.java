package com.appbuddy.buddypasswordmanager.view;

import com.appbuddy.buddypasswordmanager.actions.menubar.LoadFileAction;
import com.appbuddy.buddypasswordmanager.actions.menubar.OpenHelpMenuAction;
import com.appbuddy.buddypasswordmanager.actions.menubar.OpenNewAction;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;

/**
 * Represents a custom menu bar that will appear on near the top of the main window.
 */
public class MenuBar extends JMenuBar {

  /**
   * Initializes the custom menu bar with predefined menus.
   */
  public MenuBar() {
    add(createFileMenu());
    add(createEditMenu());
    add(createViewMenu());
    add(createHelpMenu());
  }

  /**
   * Creates the "File" menu with its items and actions.
   *
   * @return the configured "File" menu
   */
  private JMenu createFileMenu() {
    JMenu fileMenu = createMenu("File", KeyEvent.VK_F);
    fileMenu.add(createMenuItem("New", new OpenNewAction()));
    fileMenu.add(createMenuItem("Open", null));
    fileMenu.add(createMenuItem("Save", null));
    fileMenu.add(createMenuItem("Load", new LoadFileAction()));
    fileMenu.addSeparator();
    fileMenu.add(createMenuItem("Exit", null));
    return fileMenu;
  }

  /**
   * Creates the "Edit" menu with its items.
   *
   * @return the configured "Edit" menu
   */
  private JMenu createEditMenu() {
    JMenu editMenu = createMenu("Edit", KeyEvent.VK_E);
    List.of("Cut", "Copy", "Paste", "Select All").forEach(itemText ->
        editMenu.add(createMenuItem(itemText, null))
    );
    return editMenu;
  }

  /**
   * Creates the "View" menu with its items.
   *
   * @return the configured "View" menu
   */
  private JMenu createViewMenu() {
    JMenu viewMenu = createMenu("View", KeyEvent.VK_V);
    viewMenu.add(createMenuItem("Show All", null));
    viewMenu.add(createMenuItem("Hide All", null));
    return viewMenu;
  }

  /**
   * Creates the "Help" menu with its items and actions.
   *
   * @return the configured "Help" menu
   */
  private JMenu createHelpMenu() {
    JMenu helpMenu = createMenu("Help", KeyEvent.VK_H);
    helpMenu.add(createMenuItem("App Buddy Help", new OpenHelpMenuAction()));
    helpMenu.add(createMenuItem("App Buddy Online", null));
    helpMenu.add(createMenuItem("Contact Us", null));
    return helpMenu;
  }

  /**
   * Helper method to create a menu with a title and mnemonic key.
   *
   * @param title    the menu title
   * @param mnemonic the mnemonic key for the menu
   * @return the configured menu
   */
  private JMenu createMenu(String title, int mnemonic) {
    JMenu menu = new JMenu(title);
    menu.setMnemonic(mnemonic);
    return menu;
  }

  /**
   * Helper method to create a menu item with text and an optional action listener.
   *
   * @param text     the menu item text
   * @param listener the action listener for the menu item (can be null)
   * @return the configured menu item
   */
  private JMenuItem createMenuItem(String text, ActionListener listener) {
    JMenuItem menuItem = new JMenuItem(text);
    if (listener != null) {
      menuItem.addActionListener(listener);
    }
    return menuItem;
  }
}
