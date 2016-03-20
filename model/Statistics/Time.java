package model.Statistics;
/**
 * Class Time is used to keep track of time for tests and measure how long a test took
 * Class Time has the methods set time and get time;
 * Class Time has the instance variables for the hour, minute and second of time
 */
public class Time implements java.io.Serializable {

	/**the hour unit of time*/
	private int hour;
	/**the minute unit of time*/
	private int minute;
	/**the second unit of time*/
	private int second;
	
	public Time() {
		
	}
	
	public Time(int hour, int minute, int second) {
		setTime(hour,minute,second);
	}
	
	/**sets time based on the passed in variables*/
	public void setTime(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	public void setTime(int second) {
		int remainder = second % 60;
		this.minute = second / 60;
		this.second = remainder;
		if(this.minute > 60) {
			this.hour = minute/60;
			this.minute = minute % 60;
		}
	}
	
	/**returns the time*/
	public Time GetTime() {
		return this;
	}
	
	/**return the hour*/
	public int GetHour() {
		return hour;
	}
	
	/** sets the hour */
	public void SetHour(int hour) {
		this.hour = hour;
	}
	
	/**return the minute*/
	public int GetMinute() {
		return minute;
	}
	
	/** sets the minute */
	public void SetMinute(int minute) {
		if(minute>=60) {
			int mod = minute%60/10;
			setTime(mod,minute-60*mod,0);
		}
		else
			this.minute = minute;
	}
	
	/**return the second*/
	public int GetSecond() {
		return second;
	}

	/** sets the second */
	public void SetSecond(int second) {
		this.second = second;
	}
	/*
	public double getTotalMinutes() {
		return (hour*60)+minute+((double)second/60);
	}*/
	
	public String toString() {
		String s = "hour:"+hour+" minute:"+minute+" second:"+second;
		return s;
	}
	
	public int getTotalSeconds() {
		return ((hour * 60 * 60) + (minute * 60) + second);
	}

}
