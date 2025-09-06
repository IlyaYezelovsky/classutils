package ilya.classutils;

import java.io.*;
import java.time.*;
import java.util.*;

public class Student implements Serializable, Comparable<Student> {

	private static final long serialVersionUID = 2198637827847803716L;
	static TreeSet<Student> LIST;

	public static void initializeList() {
		List<Student> temp = List.of(new Student(2, "黄文林", true), new Student(3, "李楚誉", true),
				new Student(4, "苏浦", true), new Student(5, "梁键", true), new Student(6, "刘浩然", true),
				new Student(7, "王张向阳", true), new Student(8, "周淑艺", false), new Student(9, "陶云良", true),
				new Student(10, "陈陆", true), new Student(11, "吴金桐", true), new Student(12, "周谢予", true),
				new Student(13, "莫之瑜", true), new Student(14, "黎泳麟", true), new Student(15, "麦珊", false),
				new Student(16, "陈杨慧", false), new Student(17, "林世凤", false), new Student(18, "郑好", false),
				new Student(19, "刘朝奕", true), new Student(20, "陈禹州", true), new Student(21, "黄天银", true),
				new Student(22, "钟丹彤", false), new Student(23, "杨振梁", true), new Student(24, "梁钦瑜", true),
				new Student(25, "田槟榕", true), new Student(26, "朱伟余", true), new Student(27, "周花吉", false),
				new Student(28, "陈昱彤", false), new Student(29, "潘思宇", false));
		LIST = new TreeSet<>(temp);
	}

	public static void loadAll() {
		loadAll(new File("score.ser"));
	}

	public static void loadAll(File file) {
		try (ObjectInputStream os = new ObjectInputStream(new FileInputStream(file))) {
			Object obj = os.readObject();
			if (obj instanceof TreeSet) {
				TreeSet<?> set = (TreeSet<?>) obj;
				if (set.getFirst() instanceof Student) {
					LIST = (TreeSet<Student>) set;
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			Utils.showErrorMsgbox(Utils.withSuppressed(e));
		}
	}

	public static void saveAll() {
		saveAll(new File("score.ser"));
	}

	public static void saveAll(File file) {
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
			os.writeObject(LIST);
		} catch (IOException e) {
			Utils.showErrorMsgbox(Utils.withSuppressed(e));
		}
	}

	private String name;
	private boolean sex;// true for male, false for female
	private int num;
	private ArrayList<ScoreChange> scoreChangeList;

	public Student(int num, String name, boolean sex) {
		this.name = name;
		this.sex = sex;
		this.num = num;
		scoreChangeList = new ArrayList<>();
	}

	public void changeScore(String reason, int score) {
		scoreChangeList.add(new ScoreChange(this, reason, score));
	}

	public void changeScore(ZonedDateTime time, String reason, int score) {
		scoreChangeList.add(new ScoreChange(this, time, reason, score));
	}

	@Override
	public int compareTo(Student other) {
		Integer n = num;
		return n.compareTo(other.num);
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Student) && o.toString().equals(toString()));
	}

	public ScoreChange[] getAllRecord() {
		return scoreChangeList.toArray(new ScoreChange[scoreChangeList.size()]);
	}

	public String getName() {
		return name;
	}

	public int getNum() {
		return num;
	}

	public int getScore() {
		int x = 0;
		for (ScoreChange s : scoreChangeList) {
			x += s.getPoint();
		}
		return x;
	}

	public boolean getSex() {
		return sex;
	}

	public char getSexSymbol() {
		return sex ? '♂' : '♀';
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	public void removeRecord(ScoreChange record) {
		scoreChangeList.remove(record);
	}

	@Override
	public String toString() {
		return num + Character.toString(getSexSymbol()) + name;
	}

}
