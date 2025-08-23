package ilya.classutils;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.*;

public class Student implements Serializable, Comparable<Student> {
	
	private static final long serialVersionUID = 2198637827847803716L;
	private String name;
	private boolean sex;//true for male, false for female
	private int num;
	private int score;
	private ArrayList<ScoreChange> scoreChangeList;
	
	public static TreeSet<Student> LIST;
	
	public static void initializeList() {
/*
 * "01♀宋奕妍", "02♂黄文林", "03♂李楚誉", "04♂苏浦", "05♂梁键", "06♂刘浩然", 
 * "07♂王张向阳", "08♀周淑艺", "09♂陶云良", "10♂陈陆", "11♂吴金桐", "12♂周谢予", 
 * "13♂莫之瑜", "14♂黎泳麟", "15♀麦珊", "16♀陈杨慧", "17♀林世凤", "18♀郑好", 
 * "19♂刘朝奕", "20♂陈禹州", "21♂黄天银", "22♀钟丹彤", "23♂杨振梁", "24♂梁钦瑜", 
 * "25♂田槟榕", "26♂朱伟余", "27♀周花吉", "28♀陈昱彤", "29♀潘思宇"
 */
		List<Student> temp = List.of(
				new Student(1, "宋奕妍", false),
				new Student(2, "黄文林", true),
				new Student(3, "李楚誉", true),
				new Student(4, "苏浦", true),
				new Student(5, "梁键", true),
				new Student(6, "刘浩然", true),
				new Student(7, "王张向阳", true),
				new Student(8, "周淑艺", false),
				new Student(9, "陶云良", true),
				new Student(10, "陈陆", true),
				new Student(11, "吴金桐", true),
				new Student(12, "周谢予", true),
				new Student(13, "莫之瑜", true),
				new Student(14, "黎泳麟", true),
				new Student(15, "麦珊", false),
				new Student(16, "陈杨慧", false),
				new Student(17, "林世凤", false),
				new Student(18, "郑好", false),
				new Student(19, "刘朝奕", true),
				new Student(20, "陈禹州", true),
				new Student(21, "黄天银", true),
				new Student(22, "钟丹彤", false),
				new Student(23, "杨振梁", true),
				new Student(24, "梁钦瑜", true),
				new Student(25, "田槟榕", true),
				new Student(26, "朱伟余", true),
				new Student(27, "周花吉", false),
				new Student(28, "陈昱彤", false),
				new Student(29, "潘思宇", false)
				);
		LIST = new TreeSet<Student>(temp);
	}
	
	@Override
	public int compareTo(Student s) {
		Integer n = num;
		return n.compareTo(s.num);
	}
	
	public Student(int no, String n, boolean s) {
		name = n;
		sex = s;
		num = no;
		score = 0;
		scoreChangeList = new ArrayList<ScoreChange>();
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getSex() {
		return sex;
	}
	
	public int getNum() {
		return num;
	}
	
	public int getScore() {
		return score;
	}
	
	//You should not use this when in general use
	void modifyScore(int s) {
		score += s;
	}
	
	public void changeScore(ZonedDateTime t, String r, int p) {
		scoreChangeList.add(new ScoreChange(this, t, r, p));
	}
	
	public void changeScore(String r, int p) {
		scoreChangeList.add(new ScoreChange(this, r, p));
	}
	
	public String getAllRecord() {
		StringBuffer sb = new StringBuffer();
		for (ScoreChange s:scoreChangeList) {
			sb.append(s.toString() + "\n");
		}
	}
	
	@Override
	public String toString() {
		return name;
	}

}
