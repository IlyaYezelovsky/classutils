package ilya.classutils;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class Main {
	
	private JFrame frame;
	private JPanel panel;
	private static boolean test;
	
	public void go() {
		frame = new JFrame("Class 15 Utilities v2.1.1");
		panel = new JPanel();
		
		JButton studentButton = new JButton("计分管理");
		studentButton.addActionListener(a -> {
			new StudentGUI().go();
		});
		
		JButton extractButton = new JButton("随机抽取");
		extractButton.addActionListener(a -> {
			new Extract().go();
		});
		
		JButton seatButton = new JButton("一键排座位");
		seatButton.addActionListener(a -> {
			new Seat().go();
		});
		
		panel.add(studentButton);
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
			panel1.add(new JLabel("Class 15 Utilities"));
			panel2.add(new JLabel("v2.1.1 on 2025.8.30"));
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
		frame.setSize(150, 185);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		setWindows();
		test = false;
		if (args.length != 0 || test) {
			ArrayList<String> argList = new ArrayList<String>(Arrays.asList(args));
			if (argList.contains("--initialize") || test) {
				Student.initializeList();
			} else {
				Student.loadAll();
			}
		} else {
			Student.loadAll();
		}
		launch();
	}
	
	private static void launch() {
		try {
//			throw new Exception("Test exception");
			new Main().go();
		} catch (Exception e) {
			Utils.showErrorMsgbox(e);
		}
	}
	
	public static void setWindows() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			Utils.showErrorMsgbox(e);
		}
	}

}
