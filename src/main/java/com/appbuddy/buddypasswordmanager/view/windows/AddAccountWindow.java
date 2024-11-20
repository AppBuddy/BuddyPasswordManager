package com.appbuddy.buddypasswordmanager.view.windows;

import com.appbuddy.buddypasswordmanager.actions.SubmitButtonAction;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Gavin Bauman
 * @author Christopher Bell
 * @author Gregory Degruy
 * @author Adrian Smith-Thompson
 * @version 2.0
 */

public class AddAccountWindow {
  private final JFrame frame;
  private JLabel websiteLabelWarning;
  private JLabel usernameLabelWarning;
  private JLabel passwordLabelWarning;
  private JTextField websiteTextField;
  private JTextField usernameTextField;
  private JTextField passwordTextField;

  public AddAccountWindow() {
    frame = new JFrame();
    frame.setTitle("Enter New Account");
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setSize(350, 500);
    frame.getContentPane();
    frame.add(createPanel());
  }

  private JPanel createPanel() {
    var panel = new JPanel(null);
    panel.setBounds(0, 0, 300, 500);
    panel.add(createInstructionLabel());
    panel.add(createWebsiteLabel());
    panel.add(createWebsiteWarningLabel());
    panel.add(createWebsiteTextField());
    panel.add(createUsernameLabel());
    panel.add(createUsernameWarningLabel());
    panel.add(createUsernameTextField());
    panel.add(createPasswordLabel());
    panel.add(createPasswordWarningLabel());
    panel.add(createPasswordTextField());
    panel.add(createSubmitButton());
    return panel;
  }

  private JLabel createInstructionLabel() {
    var instructionsLabel = new JLabel("Enter Account Information");
    instructionsLabel.setBounds(5, 0, 200, 50);
    return instructionsLabel;
  }

  private JButton createSubmitButton() {
    var submitButton = new JButton("Submit");
    submitButton.setBounds(100, 370, 100, 40);
    submitButton.addActionListener(new SubmitButtonAction(websiteLabelWarning, usernameLabelWarning,
        passwordLabelWarning, websiteTextField, usernameTextField, passwordTextField, frame));
    return submitButton;
  }

  private JLabel createPasswordLabel() {
    var passwordLabel = new JLabel("Password");
    passwordLabel.setBounds(120, 230, 70, 50);
    return passwordLabel;
  }

  private JTextField createPasswordTextField() {
    passwordTextField = new JTextField();
    passwordTextField.setBounds(70, 290, 170, 40);
    return passwordTextField;
  }

  private JLabel createPasswordWarningLabel() {
    passwordLabelWarning = new JLabel("<html>*Please enter a " + "  password.<html>");
    passwordLabelWarning.setForeground(Color.RED);
    passwordLabelWarning.setVisible(false);
    passwordLabelWarning.setBounds(250, 230, 100, 50);
    return passwordLabelWarning;
  }

  private JLabel createWebsiteLabel() {
    var websiteLabel = new JLabel("Website Name");
    websiteLabel.setBounds(110, 35, 100, 50);
    return websiteLabel;
  }

  private JTextField createWebsiteTextField() {
    websiteTextField = new JTextField();
    websiteTextField.setBounds(70, 80, 170, 40);
    return websiteTextField;
  }

  private JLabel createWebsiteWarningLabel() {
    websiteLabelWarning = new JLabel("<html>*Please enter a " + "   website.<html>");
    websiteLabelWarning.setForeground(Color.RED);
    websiteLabelWarning.setVisible(false);
    websiteLabelWarning.setBounds(250, 35, 100, 50);
    return websiteLabelWarning;
  }

  private JLabel createUsernameLabel() {
    JLabel usernameLabel = new JLabel("Username");
    usernameLabel.setBounds(120, 120, 70, 50);
    return usernameLabel;
  }

  private JTextField createUsernameTextField() {
    usernameTextField = new JTextField();
    usernameTextField.setBounds(70, 180, 170, 40);
    return usernameTextField;
  }

  private JLabel createUsernameWarningLabel() {
    usernameLabelWarning = new JLabel("<html>*Please enter a " + "  username.<html>");
    usernameLabelWarning.setForeground(Color.RED);
    usernameLabelWarning.setVisible(false);
    usernameLabelWarning.setBounds(250, 120, 100, 50);
    return usernameLabelWarning;
  }
}
