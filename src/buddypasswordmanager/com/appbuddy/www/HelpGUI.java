package buddypasswordmanager.com.appbuddy.www;

/**
@author Gavin Bauman - Christopher Bell - Gregory Degruy - Adrian Smith-Thompson
updated: 5/28/2013
@version 2.0
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HelpGUI {
	JFrame frame;
	JPanel panel;
	JLabel label;
	JTextArea textArea;
	JScrollPane scroll;
	JButton button;

	public HelpGUI() {
		frame = new JFrame("Help");
		frame.setSize(350, 370);
		frame.setVisible(true);
		frame.getContentPane();

		label = new JLabel("About this Program");
	
		textArea = new JTextArea(5, 20);
		textArea.setText("This is the help section");
		textArea.setFont(new Font("Comic Sans", Font.BOLD, 20));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);

		scroll = new JScrollPane(textArea);

		frame.add(scroll);

		scroll.setBounds(0, 0, 350, 370);

		button = new JButton("Button");

		button.addActionListener(new Action());	
	}

	static class Action implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			JFrame frame2 = new JFrame("Clicked!");
			frame2.setVisible(true);
			frame2.setSize(200,200);
			JLabel label2 = new JLabel("Button has been clicked");
			JPanel panel2 = new JPanel();
			frame2.add(panel2);
			panel2.add(label2);
		}
	}
}
