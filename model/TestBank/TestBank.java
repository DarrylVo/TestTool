package model.TestBank;

import java.util.ArrayList;
import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Statistics.Time;	
import model.QuestionBank.QuestionPattern;
import model.TestToolUser.Teacher;
import model.TestToolUser.User;
import model.TestToolUser.Student;
import model.QuestionBank.Question;
import model.QuestionBank.QuestionBank;
/**Class TestBank provides methods to store and create tests. It stores tests in a Collection of tests
 * @author Darryl Vo (dvo03@calpoly.edu) 
 * */
public class TestBank implements java.io.Serializable {

   /** 
    * A Collection of all the Tests the user has generated
    */
	private transient ObservableList<Test> testBank = FXCollections.observableArrayList();
	/**
	 * A variable that holds a copy of the test bank.
	 */
	private ArrayList<Test> tbcopy = new ArrayList<Test>();

	
	
	/** Generates a simple test
	 * @param testName String consisting of the test name
	 * @param course What course the test belongs to
	 * @param numQuestions The number of questions it should have
	 * @param duration The length of the test  
	 * @param difficulty the difficulty level of the test
	 * @return A Test Object
	 *   <pre>
	 pre:
	 !exists(Test other; testBank.contains(other); other.equals(return)); 
	 
	 post:
	 testBank.contains(return);
	
	 * */
	 public Test generateSimpleTest(String testName, Course course, int numQuestions, String duration, double difficulty) {
		Time testDur;
		double maxtime=0;
		double buildingtime=0;
		int totalq = 0;
		
		ArrayList<String> usedQuestions = new ArrayList<String>();
		
		if(duration.equals("Short"))
			testDur = new Time(0,30,0);
		else if(duration.equals("Medium"))
			testDur = new Time(1,0,0);
		else
			testDur = new Time(1,30,0);
		 /*
		System.out.println("in TestBank, generating simple test");
		System.out.println("name:"+testName);
		System.out.println("Course:"+course.getCourseName());
		System.out.println("num q:"+numQuestions);
		System.out.println("duration:"+duration);
		System.out.println("difficulty:"+difficulty);*/
		Test newtest = new Test();
		newtest.setName(testName);
		newtest.setCourse(course);
		newtest.setNumQuestion(numQuestions);
		newtest.setDifficulty(difficulty);
		newtest.setDuration(testDur);
		
		maxtime = testDur.getTotalSeconds();
		int timeout = 0;
		while(buildingtime< maxtime&&totalq<course.getTotalQuestions(difficulty)&&totalq<numQuestions) {
			Question q = course.getRandomQuestion(difficulty);
			//System.out.println(q.getDuration().getTotalSeconds());
			if(!usedQuestions.contains(q.getQuestion())&& buildingtime+q.getDuration().getTotalSeconds()<=maxtime) {
				usedQuestions.add(q.getQuestion());
				newtest.addQuestion(q.getCopy());
				buildingtime+=q.getDuration().getTotalSeconds();
				totalq++;
				//System.out.println("added");
			}
			timeout++;
			if(timeout>400) {
				//System.out.println("SIMPLE ALGORITHM HAS TIMED OUT");
				break;
			}
				
		}
		// later check if we cant add the number of questions the teacher wants. 
		newtest.setNumQuestion(totalq);
		Time time = new Time();
		time.setTime((int)buildingtime);
		newtest.setDuration(time);
		testBank.add(newtest);
		tbcopy.add(newtest);
		return newtest;
	 }
	/** Generates a complex test 
	 *@param testName String consisting of the test name
	 *@param course What course the test belongs to
	 *@param numQuestions The number of questions it should have
	 *@param pattern The collection of question patterns to use to create the complex test
	 *@return ATest Object
	 *
	 * pre:
	 !exists(Test other; testBank.contains(other); other.equals(return)); 
	 
	 post:
	 testBank.contains(return);
	*/
	public Test generateComplexTest(String testName, Course course, int numQuestions, ArrayList<QuestionPattern> pattern, Time duration) {
		int buildingtime = 0;
		int buildingquestion = 0;
		/*
		System.out.println("in TestBank, generating complex test");		
		System.out.println("Name:"+testName);
		System.out.println("Course:"+course.getCourseName());
		System.out.println("numQuestions:"+numQuestions);
		System.out.println("total minutes:"+duration.getTotalSeconds());
		/*
		System.out.println("Question Patterns:");
		for (int i = 0; i<pattern.size();i++) {
			System.out.println("pattern:"+i);
			System.out.println("Topic:"+pattern.get(i).getTopic());
			System.out.println("Question Type:"+pattern.get(i).getQuestionType());
			System.out.println("difficulty:"+pattern.get(i).getDifficulty());
			System.out.println("Total Minutes Duration:"+pattern.get(i).getDuration().getTotalSeconds());
			System.out.println("percent:"+pattern.get(i).getPercent());
		}*/
		//get difficulty by averaging question pattern durations
		Test newtest = new Test();
		newtest.setName(testName);
		newtest.setCourse(course);
		newtest.setNumQuestion(numQuestions);
		newtest.setDuration(duration);
		
		ArrayList<String> usedQuestions = new ArrayList<String>();
		int timeout = 0;
		
		for (int i = 0; i<pattern.size();i++) {
			QuestionPattern qp = pattern.get(i);
			/*
			System.out.println("pattern:"+i);
			System.out.println("Topic:"+pattern.get(i).getTopic());
			System.out.println("Question Type:"+pattern.get(i).getQuestionType());
			System.out.println("difficulty:"+pattern.get(i).getDifficulty());
			System.out.println("Total Minutes Duration:"+pattern.get(i).getDuration().getTotalSeconds());
			System.out.println("percent:"+pattern.get(i).getPercent());
			*/
			int pbuildingtime = 0;
			int pbuildingquestion = 0;
			double timemax = duration.getTotalSeconds();
			boolean done = false;
			int pnumquestionmax = (int)(qp.getPercent()/100 * (double)numQuestions);
			double ptimemax = qp.getDuration().getTotalSeconds();
			//System.out.println("pnumquestionmax"+pnumquestionmax);
			while(pbuildingtime<qp.getDuration().getTotalSeconds()&&pbuildingquestion<pnumquestionmax&&pbuildingquestion<course.getTotalQuestions(qp.getDifficulty())) {
				Question q = course.getRandomQuestion(qp.getDifficulty());
				//System.out.println(q.toString());
				/*
				if(buildingtime+q.getDuration().getTotalSeconds()>timemax||buildingquestion>=numQuestions) {
					done = true;
					
					break;
					
				}*/
				if(!usedQuestions.contains(q.getQuestion())&& pbuildingtime+q.getDuration().getTotalSeconds()<=ptimemax&&pbuildingquestion<pnumquestionmax) {
					//System.out.println("added qqq");
					usedQuestions.add(q.getQuestion());
					newtest.addQuestion(q.getCopy());
					pbuildingtime+=q.getDuration().getTotalSeconds();
					buildingtime+=q.getDuration().getTotalSeconds();
					pbuildingquestion++;
					buildingquestion++;
					//System.out.println("added");
				}
				timeout++;
				if(timeout>400) {
					//System.out.println("complex ALGORITHM HAS TIMED OUT");
					break;
				}
				
			}/*
			if(done)
				break;*/
		}
		// later check if we cant add the number of questions the teacher wants/have the same time. 
				newtest.setNumQuestion(buildingquestion);
				Time time = new Time();
				time.setTime(buildingtime);
				newtest.setDuration(time);
		testBank.add(newtest);
		tbcopy.add(newtest);
		return newtest;
	}
	/** Deletes a test from the Collection
	 * @param test Test to be deleted from the test bank
	 *  <pre>
	 pre:
	 testBank.contains(test);
	 
	 post:
	 forall (Test other ;
           testBank.contains(other) iff
       !other.equals(test) && testBank.contains(other));
	 * */
	public void deleteTest(Test test) {
		if (test!= null)
			tbcopy.remove(test);
	}
	/** Search for a test within the Collection
	 * @param test Test to be searched for
	 * 
	 * pre: test!= null && testBank!=null &&testBank.size>0;
	 * post: testBank.size() == testBank'.size();
	 * */
	public boolean searchTest(Test test) {
		if(tbcopy.contains(test))
			return true;
		else
			return false;
	
	}
	/** Add a test to the testbank 
	 * @param test Test to be added
	 * 
	 * pre: test!= null && forall(Test t; testBank.contains(t); !t.equals(test));
	 * */
	public void AddTest(Test test) {
		testBank.add(test);
		tbcopy.add(test);
	}
	/**
	 * Clears the observable list to a clean Observable array list
	 */
	public void restoreObservable() {
		
		for(int i = 0; i <tbcopy.size();i++) {
			tbcopy.get(i).restoreObservable();
		}
		testBank = FXCollections.observableArrayList(tbcopy);
	}

	/**
	 * Gets the observable list of a testbank.
	 * @return The observable list of a testbank.
	 */
	public ObservableList<Test> getTestBank() {
		return testBank;
	}


}
