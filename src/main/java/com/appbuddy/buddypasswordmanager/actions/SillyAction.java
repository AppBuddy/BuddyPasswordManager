package com.appbuddy.buddypasswordmanager.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SillyAction implements ActionListener {

  public void actionPerformed(ActionEvent e) {
    JFrame frame2 = new JFrame("Clicked!");
    frame2.setVisible(true);
    frame2.setSize(200, 200);
    JLabel label2 = new JLabel("Button has been clicked");
    JPanel panel2 = new JPanel();
    frame2.add(panel2);
    panel2.add(label2);
  }
}
