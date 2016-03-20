package model.TestTaking;

import model.QuestionBank.Question;
import model.Statistics.Time;
import model.TestBank.Test;

/**
 * The purpose of this class is to hold the necessary data for a user to 
 * take a test. It will be used for any test-taking period. It contains 
 * the test itself, as well as a timer for the test. Additionally, 
 * it keeps track of which questions have been answered, and makes sure 
 * the format of the test is correct.
 */
    public class Viewing {

	/** Shows the student taking the test how many questions remains */
	private int questionsRemaining;
	
	/** The timer that is shown when the students takes the test */
	private Timer timer;
	
	/** Test of the information a test would have */
	private Test test;

	/** Lets program know which question the user is currently working with */
	private Question currentQuestion;
	/** Allows the question currently being looked at to be set */
	public void setCurrentQuestion(Question question) {
		currentQuestion = question;
	}
	/** Returns the question currently being looked at */
	public Question getCurrentQuestion() {
		return currentQuestion;
	}
	
	/** Sets the test for this test-taking period to be the given test. */
	public void SetTest(Test test) {
		this.test = test;
	}
	/** Returns the test being viewed */
	public Test getTest() {
		return test;
	}
	/** Gets the time remaining in the test */
	public Time GetTimeRemaining() {
		return timer.GetTimeRemaining();
	}
	/** Gets the message that shows the student how much time is left */
	public String GetTimeMessage() {
		return "You have two minutes remaining to take this test.";
	}
	/** Gets the total amount of questions */
	public int getNumQuestion() {
		return test.getNumQuestion();
	}
		
	/** Gets the number of questions remaining for this test
	 * pre: <pre>
	 * 	test.getQuestions() != null &&
	 * 	test.getQuestions().size() > 0;
	 * 	
	 * post:
	 * 	forall(int questionsAnswered && int i; questionsAnswered == 0 && i == 0; if (test.getQuestions().get(i).getAnswered()) (questionsAnswered += 1);
	 * 	'questionsRemaining == test.getQuestions().size() - questionsAnswered;
	 * 	return 'questionsRemaining;
	 * */
	public int GetQuestionsRemaining() {
		questionsRemaining = 0;
		for (int i = 0; i < test.getQuestions().size(); i++) {
			if (!test.getQuestions().get(i).getAnswered())
				questionsRemaining++;
		}
		return questionsRemaining;
	}
	
	/** Gets the test name */
	public String GetTestName(){
		return test.getName();
	}
	/** Sets the timer to be the provided Timer object.
	 * 									<pre>
	 	pre:
	 		timer != null;
	 	post: 
	 		this.timer.equals(timer);
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	/** Returns the timer for this test.*/
	public Timer GetTimer() {
		return timer;
	}
	
	/** Starts the timer to count down 
	 * <pre>
	  pre: 
	  test != null;
    
      post: 
      timer.getRemainingTime() != null;
     **/	
	public void startTime() {
		timer.start();
		System.out.println("Starting timer.");
	}
}
