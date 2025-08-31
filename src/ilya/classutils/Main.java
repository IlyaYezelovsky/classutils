package ilya.classutils;

import java.time.*;
import java.time.temporal.*;
import java.util.*;

import javax.swing.*;

public class Main {

	public class DutyManager {

		private static final LocalDate FIRST_DAY = LocalDate.of(2025, 8, 25);
		private static final String[][] DUTY_PATTERN = { { "A", "C" }, // 第1天
				{ "B", "D" }, // 第2天
				{ "C", "A" }, // 第3天
				{ "D", "B" } // 第4天
		};

		private static long calculateWorkingDays(LocalDate startDate, LocalDate endDate) {
			long totalDays = ChronoUnit.DAYS.between(startDate, endDate);
			long workingDays = 0;

			for (long i = 0; i <= totalDays; i++) {
				LocalDate currentDate = startDate.plusDays(i);
				if (!isWeekend(currentDate)) {
					workingDays++;
				}
			}

			return workingDays;
		}

		public static String getTodayDuty() {

			ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Irkutsk"));
			LocalDate today = now.toLocalDate();

			long workingDays = calculateWorkingDays(FIRST_DAY, today);

			int patternIndex = (int) (workingDays % 4);
			String[] duty = DUTY_PATTERN[patternIndex];

			return String.format("值日生：教室%s/包干区%s", duty[0], duty[1]);
		}

		private static boolean isWeekend(LocalDate date) {
			return date.getDayOfWeek().getValue() >= 6;
		}

	}
	private static boolean test;
	private static void launch() {
		try {
			//			throw new Exception("Test exception");
			new Main().go();
		} catch (Exception e) {
			Utils.showErrorMsgbox(e);
		}
	}
	public static void main(String[] args) {
		setWindows();
		test = false;
		if (args.length != 0 || test) {
			ArrayList<String> argList = new ArrayList<>(Arrays.asList(args));
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

	public static void setWindows() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			Utils.showErrorMsgbox(e);
		}
	}

	private JFrame frame;

	private JPanel panel;

	private JLabel duty;

	public void go() {
		frame = new JFrame("Class 15 Utilities v2.1.2");
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
			panel2.add(new JLabel("v2.1.2 on 2025.8.31"));
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

		duty = new JLabel(DutyManager.getTodayDuty());
		panel.add(duty);

		frame.getContentPane().add(panel);
		frame.setSize(150, 200);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
