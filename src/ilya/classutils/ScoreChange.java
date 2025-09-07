package ilya.classutils;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ScoreChange implements Serializable {

	private static final long serialVersionUID = 2777262877276329833L;
	private ZonedDateTime time;
	private String reason;
	private Student owner;
	private int score;

	public ScoreChange(Student owner, String reason, int score) {
		this(owner, ZonedDateTime.now(ZoneId.of("Asia/Irkutsk")), reason, score);
	}

	public ScoreChange(Student owner, ZonedDateTime time, String reason, int score) {
		this.owner = owner;
		this.time = time;
		this.reason = reason;
		this.score = score;
	}

	public Student getOwner() {
		return owner;
	}

	public int getPoint() {
		return score;
	}

	public String getReason() {
		return reason;
	}

	public ZonedDateTime getTime() {
		return time;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setTime(ZonedDateTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return owner.getName() + "于" + Utils.getTime(getTime()) + "因" + getReason() + Utils.pm(getPoint()) + "分";
	}

}
