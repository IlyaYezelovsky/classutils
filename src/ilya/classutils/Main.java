package ilya.classutils;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Main {
	
	private JFrame frame;
	private JPanel panel;
	
	public void go() {
		frame = new JFrame("Class 15 Utilities v1.0.0");
		panel = new JPanel();
		
		JButton extractButton = new JButton("随机抽取");
		extractButton.addActionListener(a -> {
			new Extract().go();
		});
		
		JButton seatButton = new JButton("一键排座位");
		seatButton.addActionListener(a -> {
			new Seat().go();
		});
		
		panel.add(extractButton);
		panel.add(seatButton);
		
		JButton aboutButton = new JButton("关于");
		aboutButton.addActionListener(a -> {
			JFrame msgbox = new JFrame("关于");
			JPanel mPanel = new JPanel();
			JButton ok = new JButton("OK");
			ok.addActionListener(a1 -> {
				msgbox.dispose();
			});
			mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.Y_AXIS));
			JPanel panel1 = new JPanel();
			JPanel panel2 = new JPanel();
			JPanel panel3 = new JPanel();
			JPanel panel4 = new JPanel();
			panel1.add(new JLabel("Class 15 Utils"));
			panel2.add(new JLabel("v1.0.0 on 2025.8.20"));
			panel3.add(new JLabel("by IlyaYezelovsky"));
			panel4.add(ok);
			mPanel.add(panel1);
			mPanel.add(panel2);
			mPanel.add(panel3);
			mPanel.add(panel4);
			msgbox.getContentPane().add(mPanel);
			msgbox.setSize(150, 150);
			msgbox.setVisible(true);
		});
		panel.add(aboutButton);
		
		JButton exitButton = new JButton("退出程序");
		exitButton.addActionListener(a -> {
			System.exit(0);
		});
		panel.add(exitButton);
		
		frame.getContentPane().add(panel);
		frame.setSize(150, 180);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Main().go();
	}

}
