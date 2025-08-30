package ilya.classutils;

import java.io.*;
import java.time.*;
import java.util.*;

public class ScoreChange implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2777262877276329833L;
	private ZonedDateTime time;
	private String reason;
	private Student owner;
	private int point;
	
	public ScoreChange(Student o, ZonedDateTime t, String r, int p) {
		owner = o;
		time = t;
		reason = r;
		point = p;
	}
	
	public ScoreChange(Student o, String r, int p) {
		this(o, ZonedDateTime.now(ZoneId.of("Asia/Irkutsk")), r, p);
	}
	
	public Student getOwner() {
		return owner;
	}
	
	public ZonedDateTime getTime() {
		return time;
	}
	
	public String getReason() {
		return reason;
	}
	
	public int getPoint() {
		return point;
	}
	
	@Override
	public String toString() {
		return owner.getName() + "于" + Utils.getTime(getTime()) + "因" + getReason() + Utils.pm(getPoint()) + "分";
	}

}
