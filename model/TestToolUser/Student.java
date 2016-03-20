package model.TestToolUser;

import java.util.ArrayList;

import model.QuestionBank.Question;
import model.TestBank.Course;
import model.TestBank.Test;

/**
 * The Student class extends the User class and defines Student specific methods.
 * The majority of these methods deal with the Test Taking process. The student can have courses assigned 
 * to him/her, can take a test and select answers, and can submit a completed test for grading. 
 */
public class Student extends User implements java.io.Serializable{
	/** Used for keeping track of what course the student is viewing */
	Course currentCourse;
	/** Contains the tests that have been assigned to a student */
	ArrayList<Test> assignedTests;
	
	public Student(){
	   assignedTests = new ArrayList<Test>();
	   courses = new ArrayList<Course>();
	}
	/**
	 * Constructor to make a new student
	 * @param name Name to set the student to
	 */
	public Student(String name){
	   super.setName(name);
	   assignedTests = new ArrayList<Test>();
	   courses = new ArrayList<Course>();
	}
	
	/** Returns the course the student is currently using */
	public Course getCurrentCourse() {
		return currentCourse;
	}
	
	/** Sets the course the student is currently using */
	public void setCurrentCourse(Course currentCourse) {
		this.currentCourse = currentCourse;
	}
	
	/** Adds a test to be assigned to a student */
	public void addTest(Test test) {
		assignedTests.add(test);
	}
	
	/** Gets the tests assigned to the student */
	public ArrayList<Test> getTests() {
		return assignedTests;
	}
	
	/**
 	* addCourse adds a course to the collection of courses the user has access to
   * (use in concurrence with Course.addStudent())
 	*/
	public void addCourse(String name){}
	
	/**
 	* removeCourse removes a course from the collection of courses the user has access to
	* (use in concurrence with Course.removeStudent())
 	*/
	public void removeCourse(String name){}

	/**
 	* takeTest initializes a test using methods found in the TestTaking package and transitions
	* TestTool into a Test Taking state 
 	*/	
	public void takeTest(Test test){}

	/**
 	* selectAnswer sets the student's answer for a question in a test
 	*/	
	public void selectAnswer(String answer){}

	/**
 	* submitTest checks the current test for unanswered questions
	* if the Test submission is a success, it closes the current test and returns (1) for success
	* if the Test submission is not a success, it returns (0) for submission failure
 	*/	
	public int submitTest(Test test){return 0;}

	/**
 	* viewTest previews the test to the student
 	*/	
	public void viewTest(Test test){}

}
