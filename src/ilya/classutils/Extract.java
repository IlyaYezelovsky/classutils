package ilya.classutils;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Extract {

	private class InputListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				showResult(getSex(), Integer.parseInt(inputField.getText()), repeatAllowed());
			} catch (Exception e1) {
				Utils.showMsgbox("不正确的输入", "错误", 80, 100);
			}
		}

	}

	public final static int ALL_SEX = 0;
	public final static int BOY_ONLY = 1;
	public final static int GIRL_ONLY = 2;
	private JFrame frame;
	private JPanel panel;
	private JRadioButton allRadio;
	private JRadioButton boyRadio;
	private JRadioButton girlRadio;
	private JCheckBox repeatBox;
	private JTextField inputField;
	private JTextArea outputArea;
	private StringBuffer output;
	private int sex = 0;
	private Random rd;

	private String getMode() {
		if (boyRadio.isSelected()) {
			return "仅限男生";
		} else if (girlRadio.isSelected()) {
			return "仅限女生";
		} else if (allRadio.isSelected()) {
			return "不限性别";
		} else {
			throw new IllegalStateException("Exception happened when getting random mode");
		}
	}

	public Student getRandom(int type) {
		return switch (type) {
			case 0 -> getRandomStudent();
			case 1 -> getRandomBoy();
			case 2 -> getRandomGirl();
			default -> throw new IllegalArgumentException();
		};
	}

	public Student[] getRandom(int type, int num) {
		return getRandom(type, num, false);
	}

	public Student[] getRandom(int type, int num, boolean allowRepeat) {
		return switch (type) {
			case 0 -> getRandomStudent(num, allowRepeat);
			case 1 -> getRandomBoy(num, allowRepeat);
			case 2 -> getRandomGirl(num, allowRepeat);
			default -> throw new IllegalArgumentException();
		};
	}

	public Student getRandomBoy() {
		refresh();
		Student s = getRandomStudent();
		while (!s.getSex()) {
			s = getRandomStudent();
		}
		return s;
	}

	public Student[] getRandomBoy(int num) {
		return getRandomBoy(num, false);
	}

	public Student[] getRandomBoy(int num, boolean allowRepeat) {
		if (!allowRepeat) {
			ArrayList<Student> list = new ArrayList<>();
			if ((num < 1) || (num > 19)) {
				throw new IllegalArgumentException("Integer from 1 to 19 is expected but " + num + " is found");
			}
			while (list.size() < num) {
				Student i = getRandomBoy();
				if (!list.contains(i)) {
					list.add(i);
				}
			}
			return list.toArray(new Student[num]);
		} else {
			if (num < 1) {
				throw new IllegalArgumentException("Positive integer is expected but " + num + " is found");
			}
			Student[] result = new Student[num];
			for (int i = 0; i < num; i++) {
				result[i] = getRandomBoy();
			}
			return result;
		}
	}

	public Student getRandomGirl() {
		refresh();
		Student s = getRandomStudent();
		while (s.getSex()) {
			s = getRandomStudent();
		}
		return s;
	}

	public Student[] getRandomGirl(int num) {
		return getRandomGirl(num, false);
	}

	public Student[] getRandomGirl(int num, boolean allowRepeat) {
		if (!allowRepeat) {
			ArrayList<Student> list = new ArrayList<>();
			if ((num < 1) || (num > 9)) {
				throw new IllegalArgumentException("Integer from 1 to 9 is expected but " + num + " is found");
			}
			while (list.size() < num) {
				Student i = getRandomGirl();
				if (!list.contains(i)) {
					list.add(i);
				}
			}
			return list.toArray(new Student[num]);
		} else {
			if (num < 1) {
				throw new IllegalArgumentException("Positive integer is expected but " + num + " is found");
			}
			Student[] result = new Student[num];
			for (int i = 0; i < num; i++) {
				result[i] = getRandomGirl();
			}
			return result;
		}
	}

	public Student getRandomStudent() {
		refresh();
		return Student.LIST.toArray(new Student[28])[rd.nextInt(28)];
	}

	public Student[] getRandomStudent(int num) {
		return getRandomStudent(num, false);
	}

	public Student[] getRandomStudent(int num, boolean allowRepeat) {
		if (!allowRepeat) {
			ArrayList<Student> list = new ArrayList<>();
			if ((num < 1) || (num > 29)) {
				throw new IllegalArgumentException("Integer from 1 to 29 is expected but " + num + " is found");
			}
			while (list.size() < num) {
				Student i = getRandomStudent();
				if (!list.contains(i)) {
					list.add(i);
				}
			}
			return list.toArray(new Student[num]);
		} else {
			if (num < 1) {
				throw new IllegalArgumentException("Positive integer is expected but " + num + " is found");
			}
			Student[] result = new Student[num];
			for (int i = 0; i < num; i++) {
				result[i] = getRandomStudent();
			}
			return result;
		}
	}

	private String getRepeat() {
		if (repeatAllowed()) {
			return "允许重复";
		} else {
			return "不允许重复";
		}
	}

	private int getSex() {
		if (boyRadio.isSelected()) {
			return 1;
		} else if (girlRadio.isSelected()) {
			return 2;
		} else {
			return 0;
		}
	}

	public void go() {
		frame = new JFrame("随机抽取");
		panel = new JPanel();
		panel.setLayout(new FlowLayout());

		JPanel selectPanel = new JPanel();
		selectPanel.setLayout(new BoxLayout(selectPanel, BoxLayout.Y_AXIS));
		ButtonGroup group = new ButtonGroup();
		allRadio = new JRadioButton("不限性别");
		boyRadio = new JRadioButton("仅限男生");
		girlRadio = new JRadioButton("仅限女生");
		group.add(allRadio);
		group.add(boyRadio);
		group.add(girlRadio);
		selectPanel.add(allRadio);
		selectPanel.add(boyRadio);
		selectPanel.add(girlRadio);
		allRadio.setSelected(true);
		repeatBox = new JCheckBox("允许重复");
		selectPanel.add(repeatBox);
		panel.add(selectPanel);

		JPanel quickPanel = new JPanel();
		JButton button1 = new JButton("1");
		JButton button2 = new JButton("2");
		JButton button3 = new JButton("3");
		JButton button4 = new JButton("4");
		JButton button5 = new JButton("5");
		button1.addActionListener(a -> {
			showResult(getSex(), 1, repeatAllowed());
		});
		button2.addActionListener(a -> {
			showResult(getSex(), 2, repeatAllowed());
		});
		button3.addActionListener(a -> {
			showResult(getSex(), 3, repeatAllowed());
		});
		button4.addActionListener(a -> {
			showResult(getSex(), 4, repeatAllowed());
		});
		button5.addActionListener(a -> {
			showResult(getSex(), 5, repeatAllowed());
		});
		quickPanel.add(button1);
		quickPanel.add(button2);
		quickPanel.add(button3);
		quickPanel.add(button4);
		quickPanel.add(button5);

		JPanel inputPanel = new JPanel();
		inputField = new JTextField(5);
		JButton okButton = new JButton("抽取");
		JButton clearButton = new JButton("清屏");
		JButton copyButton = new JButton("复制");

		clearButton.addActionListener(a -> {
			output.delete(0, output.length());
			outputArea.setText("");
		});
		JButton exitButton = new JButton("关闭");
		exitButton.addActionListener(a -> {
			output.delete(0, output.length());
			outputArea.setText("");
			frame.dispose();
		});
		copyButton.addActionListener(a -> {
			Utils.copy(output.toString());
		});
		okButton.addActionListener(new InputListener());

		inputPanel.setLayout(new FlowLayout());
		inputPanel.add(inputField);
		inputPanel.add(okButton);
		inputPanel.add(clearButton);
		inputPanel.add(copyButton);
		inputPanel.add(exitButton);
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
		middlePanel.add(quickPanel);
		middlePanel.add(inputPanel);
		panel.add(middlePanel);

		output = new StringBuffer();

		outputArea = new JTextArea(12, 40);
		outputArea.setEditable(false);
		outputArea.setLineWrap(true);
		JScrollPane scroller = new JScrollPane(outputArea);
		scroller.setAutoscrolls(true);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel rightPanel = new JPanel();
		rightPanel.add(scroller);
		panel.add(rightPanel);

		frame.setContentPane(panel);
		frame.setSize(850, 275);
		frame.setVisible(true);
	}

	private void refresh() {
		rd.setSeed((long) (Math.random() * Long.MAX_VALUE));
	}

	public boolean repeatAllowed() {
		return repeatBox.isSelected();
	}

	private void showResult(int type, int num, boolean allowRepeat) {
		Student[] result = getRandom(type, num, allowRepeat);
		StringBuffer sb = new StringBuffer();
		sb.append(Utils.getTime() + " ");
		sb.append(getMode() + " " + getRepeat() + num + "个\n");
		for (int i = 0; i < result.length; i++) {
			sb.append("[" + (i + 1) + "] " + result[i] + "\n");
		}
		sb.append("----" + "\n");
		output.append(sb.toString());
		outputArea.setText(output.toString());
	}

}
