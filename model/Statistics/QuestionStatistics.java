package model.Statistics;

import java.util.ArrayList;

import model.QuestionBank.Question;

/**
 * Class QuestionStatistics has the purpose of compiling statistics for each indiviudal question across all students and potentially
 * all tests, allowing the teacher to see how difficult a question really is.
 * Class QuestionStatistics has methods to get most common answer, get average score, and get number correct.
 * Class QuestionStatistcs has static variables for number of people who got the question correct, the most common answer, 
 * and times the question was attempted.
 */
public class QuestionStatistics implements java.io.Serializable {

	/** number of time questions has been answered correctly */
	private int numberCorrect; 
	/** the most common answer to the question, if applicable */
	private String mostCommon; 
	private ArrayList<Question> list = new ArrayList<Question>();
	
   /**
 	* GetPercentCorrect returns a double showing the percent of attempts that were answered correctly
 	*/ 
	public double getPercentCorrect(){
		return numberCorrect/(double)list.size();
	}
	
   /**
 	* GetMostCommon returns the instance variable mostCommon showing the most common answer given 
 	*/ 
	public String getMostCommon(){
		return mostCommon;
	}
	
   /**
 	* GetAvgScore returns the avg points recieved on the question given the points the question is worth
 	*/ 
	public double getAvgScore(int pointsWorth){
		return getPercentCorrect() * pointsWorth;
	}
	
   /**
 	* GetNumberCorrect returns the instance variable numberCorrect showing the number of students who got the question correct
 	*/ 
	public int GetNumberCorrect(){
		numberCorrect = 0;
		for (int i=0; i < list.size(); i++) {
			if (list.get(i).checkAnswer()) {
				incrementNumberCorrect();
			}
		}
		return numberCorrect;
	}
	
   /**
 	* GetTimesTaken returns the instance variable timesTaken showing the number of times the question was attempted
 	*/ 
	public int getTimesTaken(){
		return list.size();
	}
	
   /**
 	* IncrementNumberCorrect adds one to the instance variable numberCorrect
 	*/ 
	public void incrementNumberCorrect() {
		numberCorrect++;
	}

	
	/**
	 * SetMostCommon sets the instance variable most common to the given String values
	 */
	public void setMostCommon(String mostcommon) {
		mostCommon = mostcommon;
	}
	

}
