package ilya.classutils;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Main {
	
	private JFrame frame;
	private JPanel panel;
	
	public void go() {
		frame = new JFrame("Class 15 Utilities v0.1.0");
		panel = new JPanel();
		
		JButton extractButton = new JButton("随机抽取");
		extractButton.addActionListener(a -> {
			new Extract().go();
		});
		panel.add(extractButton);
		
		JButton aboutButton = new JButton("关于");
		aboutButton.addActionListener(a -> {
			Msgbox.info("Class 15 Utilities v0.1.0\nby IlyaYezelovsky", "关于");
		});
		panel.add(aboutButton);
		
		frame.getContentPane().add(panel);
		frame.setSize(500, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Main().go();
	}

}
