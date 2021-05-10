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
public class BPMMenuBar extends JMenuBar {
    public BPMMenuBar() {
        var fileMenu = this.createFileMenu();
        var editMenu = this.createEditMenu();
        var viewMenu = this.createViewMenu();
        var helpMenu = this.createHelpMenu();
        this.createMenuBar(fileMenu, editMenu, viewMenu, helpMenu);
    }

    public JMenu createFileMenu() {
        var fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);// key board navigation by ALT
        JMenuItem fNew = new JMenuItem("New");
        JMenuItem fOpen = new JMenuItem("Open");
        JMenuItem fSave = new JMenuItem("Save/Coming Soon");
        JMenuItem fLoad = new JMenuItem("Load");
        JMenuItem fExit = new JMenuItem("Exit");
        fileMenu.add(fNew);
        fileMenu.add(fOpen);
        fileMenu.add(fSave);
        fileMenu.add(fLoad);
        fileMenu.addSeparator();
        fileMenu.add(fExit);

        fNew.addActionListener(new OpenNewAction());
        fLoad.addActionListener(new LoadFileAction());

        return fileMenu;
    }

    public JMenu createEditMenu() {
        var editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        JMenuItem eCut = new JMenuItem("Cut");
        JMenuItem eCopy = new JMenuItem("Copy");
        JMenuItem ePaste = new JMenuItem("Paste");
        JMenuItem eSelectAll = new JMenuItem("SelectAll");
        editMenu.add(eCut);
        editMenu.add(eCopy);
        editMenu.add(ePaste);
        editMenu.addSeparator();
        editMenu.add(eSelectAll);

        return editMenu;
    }

    public JMenu createViewMenu() {
        var viewMenu = new JMenu("View");
        viewMenu.setMnemonic(KeyEvent.VK_V);
        JMenuItem vAll = new JMenuItem("All");
        JMenuItem vHidePasswords = new JMenuItem("Hide Passwords");
        viewMenu.add(vAll);
        viewMenu.add(vHidePasswords);

        return viewMenu;
    }

    public JMenu createHelpMenu() {
        var helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        JMenuItem hABPMHelp = new JMenuItem("App Buddy Help");
        JMenuItem hABOnline = new JMenuItem("App Buddy Online");
        JMenuItem hContactUs = new JMenuItem("Contact Us");
        helpMenu.add(hABPMHelp);
        helpMenu.add(hABOnline);
        helpMenu.add(hContactUs);
        hABPMHelp.addActionListener(new OpenHelpMenuAction());

        return helpMenu;
    }

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
            var infogui = new NewInformationGUI();
        }
    }

    public static class OpenHelpMenuAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            var help = new HelpGUI();
        }
    }

    public static class LoadFileAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BPMUtils.populateTable();
        }
    }
}
