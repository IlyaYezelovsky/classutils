package ilya.classutils;

import javax.swing.*;

public class StudentGUI {
	
	private JFrame frame;
	private JPanel panel;
	private JList<Student> list;
	
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
		
		JPanel rightPanel = new JPanel();
		JButton scoreButton = new JButton("计分");
		JLabel saveLabel = new JLabel("添加新计分项后请及时保存，否则关闭程序后将会丢失！");
		JButton saveButton = new JButton("保存");
		JButton loadButton = new JButton("加载");
		JPanel panel1 = new JPanel();
		panel1.add(scoreButton);
		JPanel panel2 = new JPanel();
		panel2.add(saveLabel);
		JPanel panel3 = new JPanel();
		panel3.add(scoreButton);
		JPanel panel4 = new JPanel();
		panel4.add(loadButton);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.add(panel1);
		rightPanel.add(panel2);
		rightPanel.add(panel3);
		rightPanel.add(panel4);
		panel.add(rightPanel);
		
		frame.getContentPane().add(panel);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}

}
