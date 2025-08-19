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
	private int sex = 0;
	private final static Random rd = new Random();
	
	public String getRandomStudent() {
		return List.ALL[rd.nextInt(29)];
	}
	
	public String getRandomBoy() {
		return List.BOY[rd.nextInt(19)];
	}
	
	public String getRandomGirl() {
		return List.GIRL[rd.nextInt(10)];
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
		allRadio = new JRadioButton("全部学生");
		boyRadio = new JRadioButton("仅男生");
		girlRadio = new JRadioButton("仅女生");
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
			showResult(getSex(), 1, repeat());
		});
		button2.addActionListener(a -> {
			showResult(getSex(), 2, repeat());
		});
		button3.addActionListener(a -> {
			showResult(getSex(), 3, repeat());
		});
		button4.addActionListener(a -> {
			showResult(getSex(), 4, repeat());
		});
		button5.addActionListener(a -> {
			showResult(getSex(), 5, repeat());
		});
		quickPanel.add(button1);
		quickPanel.add(button2);
		quickPanel.add(button3);
		quickPanel.add(button4);
		quickPanel.add(button5);

		JPanel inputPanel = new JPanel();
		inputField = new JTextField(25);
		JButton okButton = new JButton("确定");
		okButton.addActionListener(new InputListener());
		inputPanel.setLayout(new FlowLayout());
		inputPanel.add(inputField);
		inputPanel.add(okButton);
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.add(quickPanel);
		rightPanel.add(inputPanel);
		panel.add(rightPanel);
		
		frame.getContentPane().add(panel);
		frame.setSize(500, 150);
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
	
	private boolean repeat() {
		return repeatBox.isSelected();
	}
	
	private void showResult(int type, int num, boolean allowRepeat) {
		String[] result = getRandom(type, num, allowRepeat);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append("[" + (i + 1) + "] " + result[i] + "\n");
		}
		Msgbox.info(sb.toString(), "随机抽取结果");
	}
	
	private class InputListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				showResult(getSex(), Integer.parseInt(inputField.getText()), repeat());
			} catch (NumberFormatException ex) {
				Msgbox.error("不正确的输入");
			} catch (IllegalArgumentException ex) {
				Msgbox.error(ex);
			}
		}
	}

}
