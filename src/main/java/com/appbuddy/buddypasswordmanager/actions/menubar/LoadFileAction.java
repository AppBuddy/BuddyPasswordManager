package com.appbuddy.buddypasswordmanager.actions.menubar;

import com.appbuddy.buddypasswordmanager.services.BuddyPasswordManagerService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoadFileAction implements ActionListener {

  public void actionPerformed(ActionEvent e) {
    BuddyPasswordManagerService.populateTable();
  }
}
