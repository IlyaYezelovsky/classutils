package ilya.classutils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class Main {

	public class DutyManager {

		private static final LocalDate FIRST_DAY = LocalDate.of(2025, 8, 20);
		private static final String[][] DUTY_PATTERN = {
				{
						"A", "C"
				}, {
						"B", "D"
				}, {
						"C", "A"
				}, {
						"D", "B"
				}
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
			// ZonedDateTime now = ZonedDateTime.of(2025, 9, 5, 11, 45, 14, 0,
			// ZoneId.of("Asia/Irkutsk"));
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
			// throw new RuntimeException("Test exception");
			new Main().go();
		} catch (Exception e) {
			Utils.showErrorMsgbox(e);
		}
	}

	public static void main(String[] args) {
		setWindows();
		test = false;
		if ((args.length != 0) || test) {
			List<String> argList = new ArrayList<>(Arrays.asList(args));
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
		frame = new JFrame("Class 15 Utilities v2.1.5");
		panel = new JPanel();

		JButton studentButton = new JButton("计分管理");
		studentButton.addActionListener(a -> {
			new StudentGUI().go();
		});
		JPanel studentPanel = new JPanel();
		studentPanel.add(studentButton);

		JButton extractButton = new JButton("随机抽取");
		extractButton.addActionListener(a -> {
			new Extract().go();
		});
		JPanel extractPanel = new JPanel();
		extractPanel.add(extractButton);

		JButton seatButton = new JButton("一键排座位");
		seatButton.addActionListener(a -> {
			new Seat().go();
		});
		JPanel seatPanel = new JPanel();
		seatPanel.add(seatButton);

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
			panel2.add(new JLabel("v2.1.5 on 2025-09-07"));
			panel3.add(new JLabel("by IlyaYezelovsky"));
			panel4.add(ok);
			mPanel.add(panel1);
			mPanel.add(panel2);
			mPanel.add(panel3);
			mPanel.add(panel4);
			msgbox.getContentPane().add(mPanel);
			msgbox.setSize(210, 150);
			msgbox.setVisible(true);
		});
		JPanel aboutPanel = new JPanel();
		aboutPanel.add(aboutButton);

		JButton exitButton = new JButton("退出程序");
		exitButton.addActionListener(a -> {
			System.exit(0);
		});
		JPanel exitPanel = new JPanel();
		exitPanel.add(exitButton);

		duty = new JLabel(DutyManager.getTodayDuty());
		JPanel dutyPanel = new JPanel();
		dutyPanel.add(duty);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(studentPanel);
		panel.add(extractPanel);
		panel.add(seatPanel);
		panel.add(aboutPanel);
		panel.add(exitPanel);
		panel.add(dutyPanel);

		frame.setContentPane(panel);
		frame.setSize(320, 225);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
