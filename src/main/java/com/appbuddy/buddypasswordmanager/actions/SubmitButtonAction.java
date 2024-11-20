package com.appbuddy.buddypasswordmanager.actions;

import com.appbuddy.buddypasswordmanager.configs.Constants;
import com.appbuddy.buddypasswordmanager.services.BuddyPasswordManagerService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SubmitButtonAction implements ActionListener {

  private final JLabel websiteLabelWarning;
  private final JLabel usernameLabelWarning;
  private final JLabel passwordLabelWarning;
  private final JFrame frame;

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

    attemptSaveAndReloadWindow(websiteLabel, usernameLabel, passwordLabel);
  }

  public void attemptSaveAndReloadWindow(String websiteLabel, String usernameLabel, String passwordLabel) {

    if(!websiteLabel.isEmpty()
        && !usernameLabel.isEmpty()
        && !passwordLabel.isEmpty()) {

      frame.setVisible(false);
      BuddyPasswordManagerService.saveAccount(Constants.USERNAME, websiteLabel, usernameLabel, passwordLabel);
      BuddyPasswordManagerService.populateTable();
    }
  }
}
