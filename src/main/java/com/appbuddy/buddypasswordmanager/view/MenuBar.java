package com.appbuddy.buddypasswordmanager.view;

import com.appbuddy.buddypasswordmanager.services.BuddyPasswordManagerService;
import com.appbuddy.buddypasswordmanager.view.windows.HelpWindow;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Represents a custom menu bar that will appear on near the top of the main window.
 */
public class MenuBar extends JMenuBar {

  public static final String MENU_NAME_FILE = "File";
  public static final String MENU_ITEM_TEXT_NEW = "New";
  public static final String MENU_ITEM_TEXT_OPEN = "Open";
  public static final String MENU_ITEM_TEXT_SAVE = "Save";
  public static final String MENU_ITEM_TEXT_LOAD = "Load";
  public static final String MENU_ITEM_TEXT_EXIT = "Exit";

  public static final String MENU_NAME_EDIT = "Edit";
  public static final String MENU_ITEM_TEXT_CUT = "Cut";
  public static final String MENU_ITEM_TEXT_COPY = "Copy";
  public static final String MENU_ITEM_TEXT_PASTE = "Paste";
  public static final String MENU_ITEM_TEXT_SELECT_ALL = "Select All";

  public static final String MENU_NAME_VIEW = "View";
  public static final String MENU_ITEM_TEXT_SHOW_ALL = "Show All";
  public static final String MENU_ITEM_TEXT_HIDE_ALL = "Hide All";

  public static final String MENU_NAME_HELP = "Help";
  public static final String MENU_ITEM_TEXT_APP_BUDDY_HELP = "App Buddy Help";
  public static final String MENU_ITEM_TEXT_APP_BUDDY_ONLINE = "App Buddy Online";
  public static final String MENU_ITEM_TEXT_APP_CONTACT_US = "Contact Us";

  public MenuBar() {
    this.createMenuBar(createFileMenu(), createEditMenu(), createViewMenu(), createHelpMenu());
  }

  /**
   *
   * @return fileMenu
   */
  public JMenu createFileMenu() {

    var fileMenu = new JMenu(MENU_NAME_FILE);
    fileMenu.setMnemonic(KeyEvent.VK_F); // keyboard navigation by ALT
    var newMenuItem = new JMenuItem(MENU_ITEM_TEXT_NEW);
    var openMenuItem = new JMenuItem(MENU_ITEM_TEXT_OPEN);
    var saveMenuItem = new JMenuItem(MENU_ITEM_TEXT_SAVE);
    var loadMenuItem = new JMenuItem(MENU_ITEM_TEXT_LOAD);
    var exitMenuItem = new JMenuItem(MENU_ITEM_TEXT_EXIT);
    newMenuItem.addActionListener(new OpenNewAction());
    loadMenuItem.addActionListener(new LoadFileAction());
    fileMenu.add(newMenuItem);
    fileMenu.add(openMenuItem);
    fileMenu.add(saveMenuItem);
    fileMenu.add(loadMenuItem);
    fileMenu.addSeparator();
    fileMenu.add(exitMenuItem);

    return fileMenu;
  }

  /**
   *
   * @return editMenu
   */
  public JMenu createEditMenu() {
    var editMenu = new JMenu(MENU_NAME_EDIT);
    editMenu.setMnemonic(KeyEvent.VK_E);
    var cutMenuItem = new JMenuItem(MENU_ITEM_TEXT_CUT);
    var copyMenuItem = new JMenuItem(MENU_ITEM_TEXT_COPY);
    var pasteMenuItem = new JMenuItem(MENU_ITEM_TEXT_PASTE);
    var selectAllMenuItem = new JMenuItem(MENU_ITEM_TEXT_SELECT_ALL);
    editMenu.add(cutMenuItem);
    editMenu.add(copyMenuItem);
    editMenu.add(pasteMenuItem);
    editMenu.addSeparator();
    editMenu.add(selectAllMenuItem);

    return editMenu;
  }

  /**
   *
   * @return viewMenu
   */
  public JMenu createViewMenu() {
    var viewMenu = new JMenu(MENU_NAME_VIEW);
    viewMenu.setMnemonic(KeyEvent.VK_V);
    var allMenuItem = new JMenuItem(MENU_ITEM_TEXT_SHOW_ALL);
    var hidePasswordsMenuItem = new JMenuItem(MENU_ITEM_TEXT_HIDE_ALL);
    viewMenu.add(allMenuItem);
    viewMenu.add(hidePasswordsMenuItem);

    return viewMenu;
  }

  /**
   *
   * @return helpMenu
   */
  public JMenu createHelpMenu() {
    var helpMenu = new JMenu(MENU_NAME_HELP);
    helpMenu.setMnemonic(KeyEvent.VK_H);
    var helpMenuItem = new JMenuItem(MENU_ITEM_TEXT_APP_BUDDY_HELP);
    var onlineMenuItem = new JMenuItem(MENU_ITEM_TEXT_APP_BUDDY_ONLINE);
    var contactUsMenuItem = new JMenuItem(MENU_ITEM_TEXT_APP_CONTACT_US);
    helpMenuItem.addActionListener(new OpenHelpMenuAction());
    helpMenu.add(helpMenuItem);
    helpMenu.add(onlineMenuItem);
    helpMenu.add(contactUsMenuItem);

    return helpMenu;
  }

  /**
   *
   * @param fileMenu
   * @param editMenu
   * @param viewMenu
   * @param helpMenu
   */
  public void createMenuBar(JMenu fileMenu, JMenu editMenu, JMenu viewMenu, JMenu helpMenu) {
    var menuBar = new JMenuBar();
    menuBar.add(fileMenu);
    menuBar.add(editMenu);
    menuBar.add(viewMenu);
    menuBar.add(helpMenu);
    this.add(menuBar);
  }

  public static class OpenNewAction implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      new AddAccountGUI();
    }
  }

  public static class OpenHelpMenuAction implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      new HelpWindow();
    }
  }

  public static class LoadFileAction implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      BuddyPasswordManagerService.populateTable();
    }
  }
}
