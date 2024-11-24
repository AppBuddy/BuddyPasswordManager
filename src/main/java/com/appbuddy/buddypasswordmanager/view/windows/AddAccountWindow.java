package com.appbuddy.buddypasswordmanager.view.windows;

import com.appbuddy.buddypasswordmanager.actions.SubmitButtonAction;
import java.awt.Color;
import javax.swing.GroupLayout;
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
 */
public class AddAccountWindow {

  private final JFrame frame;
  private final JLabel websiteLabelWarning;
  private final JLabel usernameLabelWarning;
  private final JLabel passwordLabelWarning;
  private final JTextField websiteTextField;
  private final JTextField usernameTextField;
  private final JTextField passwordTextField;

  public AddAccountWindow() {
    frame = new JFrame("Enter New Account");
    websiteLabelWarning = createWarningLabel("Please enter a website.");
    usernameLabelWarning = createWarningLabel("Please enter a username.");
    passwordLabelWarning = createWarningLabel("Please enter a password.");
    websiteTextField = createTextField();
    usernameTextField = createTextField();
    passwordTextField = createTextField();

    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(400, 500);
    frame.setResizable(false);
    frame.add(createMainPanel());
    frame.setVisible(true);
  }

  /**
   * Creates the main panel containing all components.
   *
   * @return the main panel.
   */
  private JPanel createMainPanel() {
    JPanel panel = new JPanel();
    GroupLayout layout = new GroupLayout(panel);
    panel.setLayout(layout);

    // Set gaps between components
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);

    JLabel instructionLabel = new JLabel("Enter Account Information");
    JLabel websiteLabel = new JLabel("Website Name:");
    JLabel usernameLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password:");
    JButton submitButton = createSubmitButton();

    // Horizontal grouping
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
        .addComponent(instructionLabel)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(websiteLabel)
                .addComponent(usernameLabel)
                .addComponent(passwordLabel))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(websiteTextField)
                .addComponent(usernameTextField)
                .addComponent(passwordTextField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(websiteLabelWarning)
                .addComponent(usernameLabelWarning)
                .addComponent(passwordLabelWarning)))
        .addComponent(submitButton));

    // Vertical grouping
    layout.setVerticalGroup(layout.createSequentialGroup()
        .addComponent(instructionLabel)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(websiteLabel)
            .addComponent(websiteTextField)
            .addComponent(websiteLabelWarning))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(usernameLabel)
            .addComponent(usernameTextField)
            .addComponent(usernameLabelWarning))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(passwordLabel)
            .addComponent(passwordTextField)
            .addComponent(passwordLabelWarning))
        .addGap(30)
        .addComponent(submitButton));

    return panel;
  }

  /**
   * Creates a reusable text field.
   *
   * @return a new JTextField instance.
   */
  private JTextField createTextField() {
    return new JTextField(15);
  }

  /**
   * Creates a reusable warning label.
   *
   * @param text the warning message.
   * @return a new JLabel instance.
   */
  private JLabel createWarningLabel(String text) {
    JLabel label = new JLabel("<html>* " + text + "</html>");
    label.setForeground(Color.RED);
    label.setVisible(false);
    return label;
  }

  /**
   * Creates the submit button.
   *
   * @return a new JButton instance.
   */
  private JButton createSubmitButton() {
    JButton submitButton = new JButton("Submit");
    submitButton.addActionListener(new SubmitButtonAction(
        websiteLabelWarning, usernameLabelWarning, passwordLabelWarning,
        websiteTextField, usernameTextField, passwordTextField, frame));
    return submitButton;
  }
}
