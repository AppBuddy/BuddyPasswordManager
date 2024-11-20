package com.appbuddy.buddypasswordmanager.view.windows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import lombok.Data;

/**
 * @author Gavin Bauman
 * @author Christopher Bell
 * @author Gregory Degruy
 * @author Adrian Smith-Thompson
 * @version 2.0
 */
@Data
public class HelpWindow {
  private final JFrame frame;
  private final JTextArea textArea;

  public HelpWindow() {
    this.frame = new JFrame("Help");
    var label = new JLabel("About this Program");
    this.textArea = new JTextArea(5, 20);
    var scroll = new JScrollPane(textArea);
    var button = new JButton("Button");
    this.initFrame();
    this.initTextArea();
    this.frame.add(scroll);
    scroll.setBounds(0, 0, 350, 370);
    button.addActionListener(new Action());
  }

  private void initFrame() {
    this.frame.setSize(350, 370);
    this.frame.setVisible(true);
    this.frame.getContentPane();
  }

  private void initTextArea() {
    this.textArea.setText("Help!");
    this.textArea.setFont(new Font("Comic Sans", Font.BOLD, 20));
    this.textArea.setWrapStyleWord(true);
    this.textArea.setLineWrap(true);
    this.textArea.setEditable(false);
  }

  private static class Action implements ActionListener {
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
}