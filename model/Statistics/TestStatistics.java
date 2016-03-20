package model.Statistics;

import java.util.Collection;

import model.TestBank.Test;

/**
 * Class TestStatistics has the purpose of generating statistics for an individual test based on student performance on the test.
 * Class TestStatistics has the methods get average score, get percent score, get average duration, change average score, edit average score.
 * Class TestStatistics has the static variables for average score, average duration, and times taken.
 */
public class TestStatistics implements java.io.Serializable {

	/** average score a student recieved on the test */
	private double avgScore = 0;
	/** average time taken by a student on the test */
	private Time avgDuration = new Time(0,0,0);
	/** number of times the test has been taken overall */
	private int timesTaken = 0;
	/** overall points the Test is worth */
	private int pointsWorth;
	
    /**
 	 * getPercentCorrect returns the the average score in terms of percentage
 	 */
	public double getPercentCorrect(){
		return avgScore; // change later
	}
	
	/**
 	 * getPointsWorth returns the points the Test is worth
 	 */
	public double getPointsWorth(){
		return pointsWorth; 
	}
	
	/**
 	 * GetPercentCorrect returns the the average score in terms of percentage
 	 */
	public void setPointsWorth(int pointsWorth){
		pointsWorth = pointsWorth; 
	}
	
    /**
 	 * GetAvgScore returns the instance variable avgScore
 	 */
	public double getAvgScore(){
		return avgScore;
	}
	
    /**
 	 * GetAvgDuration returns the instance variable avgDuration
 	 */
	public Time getAvgDuration(){
		return avgDuration;
	}
	
    /**
 	 * GetTimesTaken returns the instance variable avgDuration timesTaken
 	 */
	public int getTimesTaken(){
		return timesTaken;
	}
	
    /**
 	 * IncrementTimesTaken adds one to the number of times the test has been taken
 	 */
	public void incrementTimesTaken(){
		timesTaken++;
	}
	
	/**
	 * Takes a Test value to update the static variable avgScore and avgDuration
	 * @param test Test class to update static variables with
	 */
	public void updateScoreDuration(Test test) {
		updateAvgScore(test);
		updateAvgDuration(test);
		incrementTimesTaken();
	}
	
	/**
	 * Takes a collection of tests to update the static variable avgScore and avgDuration
	 * @param tests Collection of tests to update static variables with
	 */
	public void updateScoreDuration(Collection<Test> tests) {
		Test[] arr = tests.toArray(new Test[0]);
		for (int i=0; i < arr.length; i++) {
			updateAvgScore(arr[i]);
			updateAvgDuration(arr[i]);
			incrementTimesTaken();
		}
			
	}
	
    /**
 	 * Takes a test and uses its score to add to the instance variable avgScore. It also increments the number of tests taken.
 	 * @return The updated average test score
 	 */
	private double updateAvgScore(Test test){
		avgScore = (avgScore * timesTaken + test.getTotalPoints()) /(double)(timesTaken + 1);
		return avgScore;
	}
	
	/**
 	 * Takes a test and changes the instance variable avgDuration
 	 */
	private Time updateAvgDuration(Test test){
		Time newDuration = test.getDuration();
		int totalHours = avgDuration.GetHour() * timesTaken + newDuration.GetHour();
		int totalMinutes = avgDuration.GetMinute() * timesTaken + newDuration.GetMinute();
		int totalSeconds = avgDuration.GetSecond() * timesTaken + newDuration.GetSecond();
		
		totalSeconds = totalHours * 3600 + totalMinutes * 60 + totalSeconds;
		
		totalMinutes = (int) ((totalSeconds / (timesTaken + 1)) / 60);
		int avgSeconds = (int) ((totalSeconds / (timesTaken + 1)) % 60);
		
		int avgHours = (int) (totalMinutes / 60);
		int avgMinutes = (int) (totalMinutes % 60);
		
		avgDuration.SetHour(avgHours);
		avgDuration.SetMinute(avgMinutes);
		avgDuration.SetSecond(avgSeconds);
		
		return avgDuration;
	}
	
    /**
 	 * GetSuggestedCurve returns the suggested amount of points to add to each student's score on the test
 	 */
	public double getSuggestedCurve(double highGrade, double lowGrade){
		System.out.println("get suggested curve in test stats");
		return -1;
	}

}
