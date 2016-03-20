package model.TestTaking;

import model.Statistics.Time;
import java.util.TimerTask;
/** 
 * The purpose of this class is to act as a timer for a student taking a test. 
 * The professor will decide how much time the student has to take the test, 
 * and timer will run once the test begins. The student can also see the timer,
 *  therefore knowing how much time they have left. */
public class Timer {
	/** Contains the time remaining to take a particular test */
	private Time timeRemaining;
	/** Contains the original time that the timer was set to begin at */
	private Time timeBegin;
	/** Holds the number of seconds left on the timer. This variable is used by java's timer class. */
	private static int interval;
	/** This timer allows this Timer class to count down using a java timer. */
	private static java.util.Timer timer; 
	/** Contains a message for the student to see regarding how much time is left */
	private String message;
	/** This boolean will tell the UI whether it is time to print a message to the screen, letting the user know there are 2 minutes left. */
	private boolean sendMessage;
	
	/** Sets the time remaining */
	public void SetTimeRemaining(Time time) {
		timeRemaining = time;
		System.out.println("Time remaining on test has been set to " + time);
	}
	/** Gets the time remaining */
	public Time GetTimeRemaining() {
		return timeRemaining;
	}
	/** Sets the message the student sees */
	public void SetMessage(String message){
		this.message = message;
	}
	public void setInterval(int interval1) {
		interval = interval1;
	}
	public void setTimeBegin(Time timeBegin) {
		this.timeBegin = timeBegin;
	}
	public Time getTimeBegin() {
		return timeBegin;
	}
	
	/** Gets the message the student sees */
	public String GetMessage(){
		return message;
	}
	
	/** Tells class using this timer that a certain time constraint
	 *  has been reached, and the user should be alerted.
	 * */
	public boolean GetSendMessage() {
		return sendMessage;
	}
	/** This method starts the timer. 
	 * 									<pre>
	 pre: 
		timeRemaining.hour != 0
		&& timeRemaining.minute != 0 
		&& timeRemaining.second != 0;
	 post: 
	 	 interval == (timeRemaining.second + (timeRemaining.minute * 60) + (timeRemaining.hour * 60 * 60));
	 */
	public void start() {
		int seconds = timeRemaining.GetSecond() + (timeRemaining.GetMinute() * 60) + (timeRemaining.GetHour() * 60 * 60);
	    int delay = 1000;
	    int period = 1000;
	    timer = new java.util.Timer();
	    interval = seconds;
	    timer.scheduleAtFixedRate(new TimerTask() {
	        public void run() {
	            SetInterval();
	        }
	    }, delay, period);
		System.out.println("Starting timer.");
	}

	/** pre: <pre>
	 * interval >= 1;
	 * 
	 * post: 
	 * interval' == interval - 1;
	 * 
	 * 
	 * This helper method counts down the timer every second. 
	 * It contains logic for both the internal, java timer, as well as
	 *  the timer that the user will see.*/
	public int SetInterval() {
	    if (interval == 1) {
	        this.Stop();
	    }
	    else if (interval == 120) {
	    	sendMessage = true;
	    }
	    if (timeRemaining.GetSecond() == 0) {
	    	if (timeRemaining.GetMinute() == 0) {
	    		if (timeRemaining.GetHour() != 0) {
	    			timeRemaining.SetHour(timeRemaining.GetHour() - 1);
	    			timeRemaining.SetMinute(59);
	    			timeRemaining.SetSecond(59);
	    		}
	    	}
	    	else {
	    		timeRemaining.SetMinute(timeRemaining.GetMinute() - 1);
	    		timeRemaining.SetSecond(59);
	    	}
	    }
	    else {
	    	timeRemaining.SetSecond(timeRemaining.GetSecond() - 1);
	    }
	    return --interval;
	}
	/**
	 * pre: <pre>
	 * 	timeRemaining >= timeBegin;
	 */
	/** Stops the timer */
	public void Stop(){
		timer.cancel();
	}
	/** Alarm to go off when time is up */
	public void Alarm(){}
}
