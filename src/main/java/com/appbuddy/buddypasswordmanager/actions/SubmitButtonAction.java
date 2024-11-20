package com.appbuddy.buddypasswordmanager.actions;

import com.appbuddy.buddypasswordmanager.configs.Constants;
import com.appbuddy.buddypasswordmanager.services.BuddyPasswordManagerService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Data
@Slf4j
public class SubmitButtonAction implements ActionListener {

  private final JLabel websiteLabelWarning;
  private final JLabel usernameLabelWarning;
  private final JLabel passwordLabelWarning;
  private final JTextField websiteTextField;
  private final JTextField usernameTextField;
  private final JTextField passwordTextField;
  private final JFrame frame;

  public void actionPerformed(ActionEvent e) {
    var website = websiteTextField.getText();
    var username = usernameTextField.getText();
    var password = passwordTextField.getText();

    if(website.isEmpty()) {
      websiteLabelWarning.setVisible(true);
    }

    if(username.isEmpty()) {
      usernameLabelWarning.setVisible(true);
    }

    if(password.isEmpty()) {
      passwordLabelWarning.setVisible(true);
    }

    attemptSaveAndReloadWindow(website, username, password);
  }

  public void attemptSaveAndReloadWindow(String website, String username, String password) {

    if(!website.isEmpty()
        && !username.isEmpty()
        && !password.isEmpty()) {

      frame.setVisible(false);
      BuddyPasswordManagerService.saveAccount(Constants.USERNAME, website, username, password);
      BuddyPasswordManagerService.populateTable();
    }
  }
}
