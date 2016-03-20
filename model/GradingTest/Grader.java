package model.GradingTest;


import model.QuestionBank.Question;
import model.TestBank.Comment;
import model.TestBank.Test;
import model.TestBank.Test.State;
import model.TestBank.TestsTaken;

/** 
 *  Class Grader is a parent class to more specific Grader classes: Its function is to perform all of the functions required for grading a test. 
 *  Class Grader provides methods to check question, get the score, edit a score and complete grading.
 *  Has a method which searches through tests to find the given test using a for loop.
 *  Has a data structute of type TestsTaken which contain a collection of Test to be graded.
 */
public class Grader {

	/**double that represents the percent of tests that have been marked as graded*/
    private double percentGraded = 0;
    /**boolean that represents whether the grades have been sent to the students*/
    private boolean gradesSent = false;
    /**a instance of the class TestsTaken that holds all the tests ready for grading for a certain test*/
    private TestsTaken tests;
	
	/**GradesSent returns the boolean instance variable gradesSent
	 *                                     <pre>
	  post:
	    //
	    return == gradesSent';
	 */
    public boolean gradesSent() {
    	return gradesSent;
    }
    
    /**GradesSentString returns the boolean instance variable as a String value with "YES" being returned if true and "NO" being returned if false*/
    public String GradesSentString() {
    	return gradesSent ? "YES" : "NO";
    }

	/** This method returns the student's final score on the exam. returns -1 if email doesn't match a test */
	public int GetFinalScore(String email) {
		Test temp = FindTest(email);
		if (temp == null) {
			return -1;
		}
		return temp.getStudentGrade();
	}
	/** This method returns the student's final percentage score. returns -1 if email doesn't match a test 
	 *                                               <pre>
	  pre:
	    // String email is not null and has length greater than zero
        (email != null) && (email.length() > 0);
	  post:
	    // The returned value is between 0 and 100 inclusive
	    (return >= 0) && (return <= 100);
	 */
	public double getPercent(String email) {
		Test temp = FindTest(email);
		if (temp == null) {
			return -1;
		}
		return temp.getStudentGrade()/(double)(temp.getTotalPoints()) * 100;
	}
	
	/**This method updates the percentGraded instance variable then returns it as a double
	 *                                                                   <pre>
	  post:
	    // The percentGraded value returned is between 0 and 100 inclusive
	    (return >= 0) && (return <= 100);
	 *
	 */
	public double getPercentGraded() {
		updatePercentGraded();
		return percentGraded;
	}
	
	/**This method updates the percentGraded instance variable then returns it as a String value representing its percentage*/
	public String GetPercentGradedString() {
		updatePercentGraded();
		return Double.toString(percentGraded * 100) + "%";
	}
	
	/**This method updates the percentGraded instance variable by going through the tests in TestsTaken class and seeing which percentage are marked as "graded"*/
	private void updatePercentGraded() {
		int numGraded = 0;
		Test[] arr = tests.getTests().toArray(new Test[0]);
		for (int i = 0; i < arr.length; i++) {
        	if (arr[i].getState().equals(State.valueOf("graded")))
        		numGraded++;
        }
		if (arr.length != 0)
			percentGraded = numGraded / (double)(arr.length);
		else
			percentGraded = 0;
	}
	
	/** This method sets the test variable to be the provided Test. */
	public void SetTestTaken(TestsTaken testtaken) {
		tests = testtaken;
	}
	
	/**This method returns the instance variable tests of the value TestsTaken*/
	public TestsTaken GetTestsTaken() {
		return tests;
	}
	
	/** This method allows the administrator to create a comment while grading.
	 *                                                                         
	  //pre:
	    // parameter Test t is not null and is not contained within the TestsTaken array
        //forall(TestsTaken tt_arr.GetTests(); tt_arr.equals(t)) && !t.equals(null);
	 *
	 */
	public void addTest(Test t) {
		tests.addTest(t);
	}
	
	/**
	 * This method creates a comment for the test given and returns the test with the comment created.
	 * If questionNumber is 0, the comment is for the test, else it is for the question.
	 */
	public void CreateComment(String email, String message, int questionNumber) {
		Test temp = FindTest(email);
		Comment cm = new Comment(message);
		if (questionNumber == 0)
			temp.setQuestionComment(cm, questionNumber);
		else 
			temp.setComment(cm);
	}
	
	/**This method edits the grade for a question given the student's email, the question to edit and the newscore*/
	public void EditQuestionGrade(String email, int newscore, int questionNumber) {
		// check to make sure changing returned value changes value in the collection;
		Test temp = FindTest(email);
		Question q = temp.getQuestion(questionNumber);
		q.setPointsEarned(newscore);
	}
	
	/**This method sends all the grades for a set of tests to be seen by the students who took them and returns the TestsTaken
	 *                                                
	  //post:
        // All tests values are non null  
	    //forall(tests.GetTests() tt_arr; !tt_arr.equals(null));
	 *
	 */
	public TestsTaken SubmitGrade() {
		System.out.println("submitted grades");
		gradesSent = true;
		percentGraded = 1;
		tests.SendGrades();
		return tests;
	}
	
	/**marks a test as complete given the name of the student*/
	public void GradeComplete(String email) {
		Test temp = FindTest(email);
		temp.setState(State.valueOf("graded"));
	}

	
	/**This method finds the test in the collection of tests inside the TestsTaken variable based on the student email*/
	private Test FindTest(String email) {
		Test[] arr = tests.getTests().toArray(new Test[0]);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].getStudent().getEmail().equals(email))
				return arr[i];
		}
		return null;
	}
	

}
