package ilya.classutils;

import java.time.*;
import java.time.format.*;
import javax.swing.*;

public class StudentGUI {

	public static class ScoreEditGUI {

		public static ZonedDateTime parseTime(String dateTimeStr) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
					.withZone(ZoneId.of("Asia/Irkutsk"));
			return ZonedDateTime.parse(dateTimeStr, formatter).withNano(0);
		}

		private JFrame frame;
		private JPanel panel;
		private StudentGUI outer;

		private ScoreEditGUI(StudentGUI outer) {
			// Only in this type you can create ScoreEditGUI instances.
			this.outer = outer;
		}

		private void go(ScoreChange s) {
			frame = new JFrame("编辑计分项");
			panel = new JPanel();
			JLabel scoreLabel = new JLabel("分数");
			JLabel reasonLabel = new JLabel("原因");
			JLabel timeLabel = new JLabel("时间");
			JTextField scoreField = new JTextField(20);
			JTextField reasonField = new JTextField(20);
			JTextField timeField = new JTextField(20);
			JButton okButton = new JButton("确定");
			okButton.addActionListener(a -> {
				try {
					s.setTime(parseTime(timeField.getText()));
					s.setScore(Integer.parseInt(scoreField.getText()));
					s.setReason(reasonField.getText());
					outer.refresh();
				} catch (Exception e) {
					Utils.showErrorMsgbox(e);
				}
			});
			JButton cancelButton = new JButton("取消");
			cancelButton.addActionListener(a -> {
				frame.dispose();
			});
			JPanel scorePanel = new JPanel();
			scorePanel.add(scoreLabel);
			scorePanel.add(scoreField);
			JPanel reasonPanel = new JPanel();
			reasonPanel.add(reasonLabel);
			reasonPanel.add(reasonField);
			JPanel timePanel = new JPanel();
			timePanel.add(timeLabel);
			timePanel.add(timeField);
			JPanel buttonPanel = new JPanel();
			buttonPanel.add(okButton);
			buttonPanel.add(cancelButton);
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			panel.add(scorePanel);
			panel.add(reasonPanel);
			panel.add(timePanel);
			panel.add(buttonPanel);
			frame.setContentPane(panel);
			frame.setSize(250, 175);
			frame.setVisible(true);
		}

	}

	private JFrame frame;
	private JPanel panel;
	private JList<Student> list;
	private JList<ScoreChange> scoreList;
	private JLabel leftLabel;

	public StudentGUI() {
		list = new JList<>(Student.LIST.toArray(new Student[28]));
	}

	public void go() {

		frame = new JFrame("计分管理");
		panel = new JPanel();

		JScrollPane scroller = new JScrollPane(list);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		list.setVisibleRowCount(10);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(l -> {
			refresh();
		});
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(scroller);
		leftLabel = new JLabel("当前分数：0");
		leftPanel.add(leftLabel);
		panel.add(leftPanel);

		scoreList = new JList<>();
		scoreList.setFixedCellWidth(350);
		JScrollPane scrollPane = new JScrollPane(scoreList);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scoreList.setVisibleRowCount(10);
		scoreList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(scrollPane);

		JPanel rightPanel = new JPanel();
		JLabel scoreLabel = new JLabel("分数");
		JLabel reasonLabel = new JLabel("原因");
		JTextField scoreField = new JTextField(20);
		JTextField reasonField = new JTextField(20);
		JButton scoreButton = new JButton("计分");
		JButton deleteButton = new JButton("删除");
		JButton editButton = new JButton("编辑");
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
				Student.saveAll();
				refresh();
			}
		});
		deleteButton.addActionListener(a -> {
			try {
				if ((list.getSelectedValue() != null) && (scoreList.getSelectedValue() != null)) {
					list.getSelectedValue().removeRecord(scoreList.getSelectedValue());
				} else {
					Utils.showMsgbox("你还没有选中", "错误");
				}
			} catch (Exception e) {
				Utils.showErrorMsgbox(e);
			} finally {
				Student.saveAll();
				refresh();
			}
		});
		editButton.addActionListener(a -> {
			if (scoreList.getSelectedValue() != null) {
				new ScoreEditGUI(this).go(scoreList.getSelectedValue());
			}
		});
		JLabel saveLabel = new JLabel("数据将会自动保存在默认文件中");
		JButton saveButton = new JButton("另存为");
		saveButton.addActionListener(a -> {
			JFileChooser fc = new JFileChooser();
			fc.showSaveDialog(frame);
			if (fc.getSelectedFile() != null) {
				Student.saveAll(fc.getSelectedFile());
				refresh();
				Utils.showMsgbox("保存成功", "提示");
			}
		});
		JButton loadButton = new JButton("加载");
		loadButton.addActionListener(a -> {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(frame);
			if (fc.getSelectedFile() != null) {
				Student.loadAll(fc.getSelectedFile());
				list.setSelectedValue(null, true);
				leftLabel.setText("当前分数：0");
				scoreList.setListData(new ScoreChange[0]);
				list.setListData(Student.LIST.toArray(new Student[28]));
				refresh();
				Utils.showMsgbox("加载成功", "提示");
			}
		});
		JButton exitButton = new JButton("返回");
		exitButton.addActionListener(a -> {
			Student.saveAll();
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
		panel2.add(deleteButton);
		panel2.add(editButton);
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

		refresh();
		frame.setContentPane(panel);
		frame.setSize(600, 480);
		frame.setVisible(true);
	}

	public void refresh() {
		if (list.getSelectedValue() != null) {
			scoreList.setListData(list.getSelectedValue().getAllRecord());
			leftLabel.setText("当前分数：" + list.getSelectedValue().getScore());
		}
	}

}
