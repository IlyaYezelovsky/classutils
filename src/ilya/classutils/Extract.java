package ilya.classutils;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Extract {
	
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
	private final static Random rd = new Random();
	
	public String getRandomStudent() {
		return Utils.LIST_ALL[rd.nextInt(29)];
	}
	
	public String getRandomBoy() {
		return Utils.LIST_BOY[rd.nextInt(19)];
	}
	
	public String getRandomGirl() {
		return Utils.LIST_GIRL[rd.nextInt(10)];
	}
	
	public String[] getRandomStudent(int num, boolean allowRepeat) {
		if (!allowRepeat) {
			HashSet<String> set = new HashSet<String>();
			if (num < 1 || num > 29) {
				throw new IllegalArgumentException("必须输入[1, 29]的整数，但却发现了「" + num + "」");
			}
			while (set.size() < num) {
				set.add(getRandomStudent());
			}
			return set.toArray(new String[num]);
		} else {
			if (num < 1) {
				throw new IllegalArgumentException("必须输入正整数，但却发现了「" + num + "」");
			}
			String[] result = new String[num];
			for (int i = 0; i < num; i++) {
				result[i] = getRandomStudent();
			}
			return result;
		}
	}
	
	public String[] getRandomBoy(int num, boolean allowRepeat) {
		if (!allowRepeat) {
			HashSet<String> set = new HashSet<String>();
			if (num < 1 || num > 19) {
				throw new IllegalArgumentException("必须输入[1, 19]的整数，但却发现了「" + num + "」");
			}
			while (set.size() < num) {
				set.add(getRandomBoy());
			}
			return set.toArray(new String[num]);
		} else {
			if (num < 1) {
				throw new IllegalArgumentException("必须输入正整数，但却发现了「" + num + "」");
			}
			String[] result = new String[num];
			for (int i = 0; i < num; i++) {
				result[i] = getRandomBoy();
			}
			return result;
		}
	}
	
	public String[] getRandomGirl(int num, boolean allowRepeat) {
		if (!allowRepeat) {
			HashSet<String> set = new HashSet<String>();
			if (num < 1 || num > 10) {
				throw new IllegalArgumentException("必须输入[1, 10]的整数，但却发现了「" + num + "」");
			}
			while (set.size() < num) {
				set.add(getRandomGirl());
			}
			return set.toArray(new String[num]);
		} else {
			if (num < 1) {
				throw new IllegalArgumentException("必须输入正整数，但却发现了「" + num + "」");
			}
			String[] result = new String[num];
			for (int i = 0; i < num; i++) {
				result[i] = getRandomGirl();
			}
			return result;
		}
	}
	
	public String[] getRandomStudent(int num) {
		return getRandomStudent(num, false);
	}
	
	public String[] getRandomBoy(int num) {
		return getRandomBoy(num, false);
	}
	
	public String[] getRandomGirl(int num) {
		return getRandomGirl(num, false);
	}
	
	public String getRandom(int type) {
		switch (type) {
		case 0: return getRandomStudent();
		case 1: return getRandomBoy();
		case 2: return getRandomGirl();
		default: throw new IllegalArgumentException();
		}
	}
	
	public String[] getRandom(int type, int num, boolean allowRepeat) {
		switch (type) {
		case 0: return getRandomStudent(num, allowRepeat);
		case 1: return getRandomBoy(num, allowRepeat);
		case 2: return getRandomGirl(num, allowRepeat);
		default: throw new IllegalArgumentException();
		}
	}
	
	public String[] getRandom(int type, int num) {
		return getRandom(type, num, false);
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
		inputField = new JTextField(25);
		JButton okButton = new JButton("抽取");
		JButton clearButton = new JButton("清屏");
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
		okButton.addActionListener(new InputListener());
		inputPanel.setLayout(new FlowLayout());
		inputPanel.add(inputField);
		inputPanel.add(okButton);
		inputPanel.add(clearButton);
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
		
		frame.getContentPane().add(panel);
		frame.setSize(1000, 250);
		frame.setVisible(true);
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
	
	public final static int ALL_SEX = 0;
	public final static int BOY_ONLY = 1;
	public final static int GIRL_ONLY = 2;
	
	private boolean repeatAllowed() {
		return repeatBox.isSelected();
	}
	
	private void showResult(int type, int num, boolean allowRepeat) {
		String[] result = getRandom(type, num, allowRepeat);
		StringBuffer sb = new StringBuffer();
		sb.append(Utils.getTime() +  " ");
		sb.append(getMode() + " " + getRepeat() + num + "个\n");
		for (int i = 0; i < result.length; i++) {
			sb.append("[" + (i + 1) + "] " + result[i] + "\n");
		}
		sb.append("----" + "\n");
		output.append(sb.toString());
		outputArea.setText(output.toString());
	}
	
	private class InputListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				showResult(getSex(), Integer.parseInt(inputField.getText()), repeatAllowed());
			} catch (Exception e1) {
				JFrame msgbox = new JFrame("Error");
				JPanel mPanel = new JPanel();
				JButton ok = new JButton("OK");
				ok.addActionListener(a -> {
					msgbox.dispose();
				});
				mPanel.add(new JLabel("不正确的输入"));
				mPanel.add(ok);
				msgbox.getContentPane().add(mPanel);
				msgbox.setSize(100, 100);
				msgbox.setVisible(true);
			}
		}
	}
	
	private String getMode() {
		if (boyRadio.isSelected()) {
			return "仅限男生";
		} else if (girlRadio.isSelected()) {
			return "仅限女生";
		} else {
			return "不限性别";
		}
	}
	
	private String getRepeat() {
		if (repeatAllowed()) {
			return "允许重复";
		} else {
			return "不允许重复";
		}
	}

}
