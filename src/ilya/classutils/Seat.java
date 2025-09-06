package ilya.classutils;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class Seat {

	public class SeatExcel {

		private static void setCellValue(Cell cell, Object value) {
			if (value instanceof String) {
				cell.setCellValue((String) value);
			} else if (value instanceof Number) {
				cell.setCellValue(((Number) value).doubleValue());
			} else if (value instanceof Boolean) {
				cell.setCellValue((Boolean) value);
			} else if (value instanceof java.util.Date) {
				cell.setCellValue((java.util.Date) value);
			} else {
				cell.setCellValue(value.toString());
			}
		}

		public static void setCellValue(Sheet sheet, int rowIndex, int colIndex, Object value) {
			Row row = sheet.getRow(rowIndex);
			if (row == null) {
				row = sheet.createRow(rowIndex);
			}

			Cell cell = row.getCell(colIndex);
			if (cell == null) {
				cell = row.createCell(colIndex);
			}

			setCellValue(cell, value);
		}

		private Workbook makeExcel() {
			Workbook excel = new XSSFWorkbook();
			Sheet sheet = excel.createSheet("学生座位表");
			if (latest == null) {
				throw new IllegalStateException("你还没有抽取！");
			}
			String[] list = latest;
			int i = 0;
			for (int j = 4; j > 1; j--, i++) {
				setCellValue(sheet, j, 1, list[i]);
			}
			for (int j = 2; j < 7; j++) {
				for (int k = 6; k > 1; k--, i++) {
					setCellValue(sheet, k, j, list[i]);
				}
			}
			return excel;
		}

		private void save(Workbook excel, File file) {
			try (FileOutputStream outputStream = new FileOutputStream(file)) {
				excel.write(outputStream);
				Utils.showMsgbox("导出成功", "信息");
			} catch (Exception e) {
				Utils.showErrorMsgbox(Utils.withSuppressed(e));
			}

		}

		private boolean showSaveGUI(Workbook excel) {
			JFileChooser chooser = new JFileChooser();
			chooser.showSaveDialog(frame);
			if (chooser.getSelectedFile() != null) {
				save(excel, chooser.getSelectedFile());
				return true;
			} else {
				return false;
			}
		}

	}

	private final static Extract ext = new Extract();
	private JFrame frame;
	private JPanel panel;
	private JTextArea outputArea;
	private StringBuffer output;
	public String[] latest;

	public boolean export() {
		SeatExcel exporter = new SeatExcel();
		return exporter.showSaveGUI(exporter.makeExcel());
	}

	public void generateList() {
		Student[] temp = ext.getRandom(0, 28, false);
		latest = new String[28];
		for (int i = 0; i < 28; i++) {
			latest[i] = temp[i].toString();
		}
	}

	public String generateString() {
		generateList();
		StringBuffer sb = new StringBuffer();
		sb.append(Utils.getTime() + " 座位自动编排\n");
		for (int i = 0; i < 28; i++) {
			sb.append("(" + (((i + 2) / 5) + 1) + "," + (((i + 2) % 5) + 1) + ")" + latest[i] + "\n");
		}
		sb.append("----\n");
		return sb.toString();
	}

	public void go() {
		frame = new JFrame("一键排座位");
		panel = new JPanel();
		panel.setLayout(new FlowLayout());

		JButton generateButton = new JButton("生成");
		generateButton.addActionListener(a -> {
			output.append(generateString());
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
		JButton exportButton = new JButton("导出");
		exportButton.addActionListener(a -> {
			try {
				export();
			} catch (Exception e) {
				Utils.showErrorMsgbox(e);
			}
		});

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		panel1.add(generateButton);
		panel2.add(clearButton);
		panel3.add(copyButton);
		panel4.add(exportButton);
		panel5.add(exitButton);

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(panel1);
		leftPanel.add(panel2);
		leftPanel.add(panel3);
		leftPanel.add(panel4);
		leftPanel.add(panel5);
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

		frame.setContentPane(panel);
		frame.setSize(500, 275);
		frame.setVisible(true);
	}

}
