package com.appbuddy.buddypasswordmanager.gui;

import javax.swing.JFrame;

/**
 * @author Gavin Bauman
 * @author Christopher Bell
 * @author Gregory Degruy
 * @author Adrian Smith-Thompson
 * @version 2.0
 */
public final class MainGUI extends JFrame {
    public MainGUI() {
        this.setTitle("Buddy Password Manager");
        this.getContentPane();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(600, 400, 600, 600);
        this.setJMenuBar(new MenuBar());
        this.add(new EntryListTable());
        setVisible(true);
    }
}
