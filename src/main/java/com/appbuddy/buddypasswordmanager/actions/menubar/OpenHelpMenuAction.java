package com.appbuddy.buddypasswordmanager.actions.menubar;

import com.appbuddy.buddypasswordmanager.view.windows.HelpWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenHelpMenuAction implements ActionListener {
  public void actionPerformed(ActionEvent e) {
    new HelpWindow();
  }
}
