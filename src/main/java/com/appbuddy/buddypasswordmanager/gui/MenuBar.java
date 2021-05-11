package com.appbuddy.buddypasswordmanager.gui;

import com.appbuddy.buddypasswordmanager.utils.BPMUtils;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 *
 */
public class MenuBar extends JMenuBar {
    public MenuBar() {
        this.createMenuBar(createFileMenu(), createEditMenu(), createViewMenu(), createHelpMenu());
    }

    /**
     *
     * @return fileMenu
     */
    public JMenu createFileMenu() {
        var fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F); // key board navigation by ALT
        var newMenuItem = new JMenuItem("New");
        var openMenuItem = new JMenuItem("Open");
        var saveMenuItem = new JMenuItem("Save/Coming Soon");
        var loadMenuItem = new JMenuItem("Load");
        var exitMenuItem = new JMenuItem("Exit");
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
        var editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        var cutMenuItem = new JMenuItem("Cut");
        var copyMenuItem = new JMenuItem("Copy");
        var pasteMenuItem = new JMenuItem("Paste");
        var selectAllMenuItem = new JMenuItem("SelectAll");
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
        var viewMenu = new JMenu("View");
        viewMenu.setMnemonic(KeyEvent.VK_V);
        var allMenuItem = new JMenuItem("All");
        var hidePasswordsMenuItem = new JMenuItem("Hide Passwords");
        viewMenu.add(allMenuItem);
        viewMenu.add(hidePasswordsMenuItem);

        return viewMenu;
    }

    /**
     *
     * @return helpMenu
     */
    public JMenu createHelpMenu() {
        var helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        var helpMenuItem = new JMenuItem("App Buddy Help");
        var onlineMenuItem = new JMenuItem("App Buddy Online");
        var contactUsMenuItem = new JMenuItem("Contact Us");
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
            new AddEntryGUI();
        }
    }

    public static class OpenHelpMenuAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new HelpGUI();
        }
    }

    public static class LoadFileAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BPMUtils.populateTable();
        }
    }
}
