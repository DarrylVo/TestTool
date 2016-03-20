package model.TestToolUser;

import model.QuestionBank.QuestionBank;
import model.Statistics.Time;
import model.TestBank.TestBank;
import model.TestBank.Test;
import model.QuestionBank.Question;

import java.util.ArrayList;

import model.GradingTest.*;
import model.TestBank.Course;
/**
 * The Teacher class extends the User class and defines Teacher specific fields and methods.
 * The majority of these methods deal with Test and Question generation and editing. The teacher
 * holds the TestBank and QuestionBank, and can add and remove courses, take a test they have created, 
 * edit tests and questions, delete tests and questions, search for tests and questions, add tests 
 * and questions, as well as generate tests.
 */
public class Teacher  extends User implements java.io.Serializable {
	
	/** the set of questions that a teacher has access to */
	//private QuestionBank questionBank = new QuestionBank();
	
	public Teacher() {
		courses = new ArrayList<Course>();
	}
	
	public Teacher(String name) {
		
		
		super.setName(name);
		courses = new ArrayList<Course>();
	}
	
	/**
 	* editTest brings up the edit Test menu found in TestBank 
 	*/	
	public void editTest(Test test){}
	
	/**
 	* editQuestion brings up the edit Question menu found in QuestionBank 
 	*/	
	public void editQuestion(Question question){}
	
	/**
 	* deleteTest invokes TestBank's deleteTest method to delete a test  
 	*/	
	public void deleteTest(Test test){}
	
	/**
 	* gradeTest invokes the methods in GradingTest to grade a test
 	*/	
	public void gradeTest(Test test){}
	
	/**
 	* addQuestion invokes QuestionBank's addQuestion method to add a question
 	*/	
	public void addQuestion(Question question){}
	
	/**
 	* deleteQuestion invokes QuestionBank's deleteQuestion method to delete a question 
 	*/	
	public void deleteQuestion(Question question){}
	
	/**
 	* submitTest checks the current test for unanswered questions
	* if the Test submission is a success, it closes the current test and returns (1) for success
	* if the Test submission is not a success, it returns (0) for submission failure
 	*/	
	public int submitTest(Test test){return 0;}
	
	/**
 	* generateSimpleTest invokes TestBank's generateSimpleTest method to generate a simple test
 	*/	
	public Test generateSimpleTest(String testName, Course course, int numQuestions, String duration, double difficulty){
		return course.generateSimpleTest(testName, numQuestions, duration, difficulty);
	}
	
	/**
 	* generateComplexTestinvokes TestBank's generateComplexTest method to generate a complex test
 	*/	
	public Test generateComplexTest(){return null;}
	
	/**
 	* searchQuestion invokes QuestionBank's searchQuestion method to search for a question 
 	*/	
	public Question searchQuestion(Question question){return null;}
	
	/**
 	* searchTest invokes TestBank's searchTest method to search for a test 
 	*/	
	public void searchTest(Test test){}

	


}
