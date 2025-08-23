package ilya.classutils;

import java.io.*;
import java.time.*;
import java.util.*;

public class ScoreRecord implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -40737904219312927L;
	private ArrayList<ZonedDateTime> timeList;
	private ArrayList<String> reasonList;
	private ArrayList<Integer> scoreList;
	private Student owner;
	private int score;
	
	public ScoreRecord(Student s) {
		owner = s;
		timeList = new ArrayList<ZonedDateTime>();
		reasonList = new ArrayList<String>();
		scoreList = new ArrayList<Integer>();
		score = 0;
	}
	
	public void addScore(int point, String reason) {
		timeList.add(ZonedDateTime.now(ZoneId.of("Asia/Irkutsk")));
		reasonList.add(reason);
		scoreList.add(point);
		owner.addScore(point);
	}

}
