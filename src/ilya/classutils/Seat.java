package ilya.classutils;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Seat {
	
	private JFrame frame;
	private JPanel panel;
	private JTextArea outputArea;
	private StringBuffer output;
	private final static Extract ext = new Extract();
	
	public void go() {
		frame = new JFrame("一键排座位");
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		JButton generateButton = new JButton("生成");
		generateButton.addActionListener(a -> {
			output.append(generate());
			outputArea.setText(output.toString());
		});
		JButton clearButton = new JButton("清屏");
		clearButton.addActionListener(a -> {
			output.delete(0, output.length());
			outputArea.setText("");
		});
		JButton exitButton = new JButton("返回");
		exitButton.addActionListener(a -> {
			output.delete(0, output.length());
			outputArea.setText("");
			frame.dispose();
		});
		JButton copyButton = new JButton("复制");
		copyButton.addActionListener(a -> {
			Utils.copy(output.toString());
		});
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		panel1.add(generateButton);
		panel2.add(clearButton);
		panel3.add(copyButton);
		panel4.add(exitButton);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(panel1);
		leftPanel.add(panel2);
		leftPanel.add(panel3);
		leftPanel.add(panel4);
		panel.add(leftPanel);
		
		outputArea = new JTextArea(12, 40);
		outputArea.setEditable(false);
		output = new StringBuffer();
		JScrollPane scroller = new JScrollPane(outputArea);
		scroller.setAutoscrolls(true);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel rightPanel = new JPanel();
		rightPanel.add(scroller);
		panel.add(rightPanel);
		
		frame.getContentPane().add(panel);
		frame.setSize(500, 250);
		frame.setVisible(true);
	}
	
	public String generate() {
		String[] origin = ext.getRandom(0, 29);
		StringBuffer sb = new StringBuffer();
		sb.append(Utils.getTime() + " 座位自动编排\n");
		for (int i = 0; i < 29; i++) {
			sb.append("(" + (int)((i + 1) / 5 + 1) + "," + ((i + 1) % 5 + 1) + ")" + origin[i] + "\n");
		}
		sb.append("----\n");
		return sb.toString();
	}

}
