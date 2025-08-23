package ilya.classutils;

import java.io.*;
import javax.swing.*;

public class StudentGUI {
	
	private JFrame frame;
	private JPanel panel;
	private JList<Student> list;
	private JTextArea scoreArea;
	
	public StudentGUI(Student[] array) {
		list = new JList<Student>(array);
	}
	
	public void go() {
		frame = new JFrame("学生列表");
		panel = new JPanel();
		
		JScrollPane scroller = new JScrollPane(list);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		list.setVisibleRowCount(10);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(scroller);
		
		scoreArea = new JTextArea(20, 25);
		scoreArea.setEditable(false);
		list.addListSelectionListener(a -> {
			if (!a.getValueIsAdjusting()) {
				refresh();
			}
		});
		JScrollPane scrollPane = new JScrollPane(scoreArea);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPane);
		
		JPanel rightPanel = new JPanel();
		JLabel scoreLabel = new JLabel("分数");
		JLabel reasonLabel = new JLabel("原因");
		JTextField scoreField = new JTextField(20);
		JTextField reasonField = new JTextField(20);
		JButton scoreButton = new JButton("计分");
		scoreButton.addActionListener(a -> {
			try {
				if (list.getSelectedValue() != null) {
					list.getSelectedValue().changeScore(reasonField.getText(), Integer.parseInt(scoreField.getText()));
				} else {
					Utils.showMsgbox("你还没有选中", "错误");
				}
			} catch (NumberFormatException e) {
				Utils.showMsgbox("请输入正确的数字", "错误");
			} finally {
				try {
					Student.saveAll(new File("score.ini"));
				} catch (IOException e) {
					Utils.showErrorMsgbox(e);
				}
				refresh();
			}
		});
		JLabel saveLabel = new JLabel("数据将会自动保存在默认文件中");
		JButton saveButton = new JButton("另存为");
		saveButton.addActionListener(a -> {
			JFileChooser fc = new JFileChooser();
			fc.showSaveDialog(frame);
			if (fc.getSelectedFile() != null) {
				try {
					Student.saveAll(fc.getSelectedFile());
				} catch (IOException e) {
					Utils.showErrorMsgbox(e);
				}
			}
		});
		JButton loadButton = new JButton("加载");
		loadButton.addActionListener(a -> {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(frame);
			if (fc.getSelectedFile() != null) {
				try {
					Student.loadAll(fc.getSelectedFile());
				} catch (Exception e) {
					Utils.showErrorMsgbox(e);
				}
			}
		});
		JButton exitButton = new JButton("返回");
		exitButton.addActionListener(a -> {
			frame.dispose();
		});
		JPanel panelA = new JPanel();
		panelA.add(scoreLabel);
		panelA.add(scoreField);
		JPanel panelB = new JPanel();
		panelB.add(reasonLabel);
		panelB.add(reasonField);
		JPanel panel1 = new JPanel();
		panel1.add(saveLabel);
		JPanel panel2 = new JPanel();
		panel2.add(scoreButton);
		JPanel panel3 = new JPanel();
		panel3.add(saveButton);
		JPanel panel4 = new JPanel();
		panel4.add(loadButton);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		JPanel panel5 = new JPanel();
		panel5.add(exitButton);
		rightPanel.add(panelA);
		rightPanel.add(panelB);
		rightPanel.add(panel1);
		rightPanel.add(panel2);
		rightPanel.add(panel3);
		rightPanel.add(panel4);
		rightPanel.add(panel5);
		panel.add(rightPanel);
		
		frame.getContentPane().add(panel);
		frame.setSize(600, 400);
		frame.setVisible(true);
	}
	
	private void refresh() {
		if (list.getSelectedValue() != null) {
			scoreArea.setText(list.getSelectedValue().getAllRecord());
		}
	}

}
