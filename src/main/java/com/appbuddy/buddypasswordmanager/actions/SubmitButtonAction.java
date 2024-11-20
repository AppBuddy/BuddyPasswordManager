package com.appbuddy.buddypasswordmanager.actions;

import com.appbuddy.buddypasswordmanager.configs.Constants;
import com.appbuddy.buddypasswordmanager.services.BuddyPasswordManagerService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmitButtonAction implements ActionListener {
  private JLabel websiteLabelWarning;
  private JLabel usernameLabelWarning;
  private JLabel passwordLabelWarning;
  private JFrame frame;

  public SubmitButtonAction(JLabel websiteLabelWarning,
      JLabel usernameLabelWarning,
      JLabel passwordLabelWarning,
      JFrame frame) {
    this.websiteLabelWarning = websiteLabelWarning;
    this.usernameLabelWarning = usernameLabelWarning;
    this.passwordLabelWarning = passwordLabelWarning;
    this.frame = frame;
  }
  public void actionPerformed(ActionEvent e) {
    var websiteLabel = websiteLabelWarning.getText();
    var usernameLabel = usernameLabelWarning.getText();
    var passwordLabel = passwordLabelWarning.getText();

    if(websiteLabel.isEmpty()) {
      websiteLabelWarning.setVisible(true);
    }
    if(usernameLabel.isEmpty()) {
      usernameLabelWarning.setVisible(true);
    }
    if(passwordLabel.isEmpty()) {
      passwordLabelWarning.setVisible(true);
    }
    if(!websiteLabel.isEmpty()
        && !usernameLabel.isEmpty()
        && !passwordLabel.isEmpty()) {
      BuddyPasswordManagerService.saveAccount(Constants.USERNAME, websiteLabel, usernameLabel, passwordLabel);
      frame.setVisible(false);
      BuddyPasswordManagerService.populateTable();
    }
  }
}
