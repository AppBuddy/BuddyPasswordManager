package com.appbuddy.buddypasswordmanager.actions.menubar;

import com.appbuddy.buddypasswordmanager.view.windows.AddAccountWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenNewAction implements ActionListener {
  public void actionPerformed(ActionEvent e) {
    new AddAccountWindow();
  }
}
