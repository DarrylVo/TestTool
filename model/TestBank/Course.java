package model.TestBank;

import java.util.ArrayList;
import java.util.Collection;

import javafx.collections.ObservableList;
import model.QuestionBank.Question;
import model.QuestionBank.QuestionBank;
import model.QuestionBank.QuestionPattern;
import model.Statistics.Time;
import model.TestToolUser.Student;

/** This class represents a class course 
* The methods found in this class edit and assign courses to a test.
*/ 
public class Course implements java.io.Serializable {
        /** The name of the course*/
	private String name;
        /** A Collection of all the students enrolled in this course */
	private ArrayList<Student> students = new ArrayList<Student>();
        /** A Collection of all the tests in this course */
	private TestBank testbank = new TestBank();
	/** Collection of tests that have been taken */
	private Collection<TestsTaken> tests = new ArrayList<TestsTaken>();
	/** Collection of strings that represents topics in a course */
	private ArrayList<String> topics = new ArrayList<String>();
	/**
	 * Variable that represents the questionBank that is associated to a Course.
	 */
	private QuestionBank questionBank = new QuestionBank();
	
	/**
	 * Constructor for a course object
	 */
	public Course() {
		
	}
	/**
    * Constructor for a course object
    * 
    * @param name A name to set the course name to.
    */
	public Course(String name) {
		this.name = name;
	}
	
	public boolean searchTest(Test test) {
		return testbank.searchTest(test);
	}
	
	
	/** Add a test to the TestTaken collection
	* @param t Test to add to the TestsTaken collection.
	*/
	public void addTestTaken(TestsTaken t) {
		tests.add(t);
	}
	/** Adds a student to this course 
	* @param s Student to add to this course.
	*/
	public void addStudent(Student s) {
		students.add(s);
	}
	/** Removes a student from this course.
	* @param s Student to remove from the course
	*/
	public void removeStudent(Student s) {
		students.remove(s);
	}
    /** Gets the course name 
	* @return Name of the course.
    */
	public String getCourseName() {
		return name;
	}
	/** Sets the course name
	* @param name The name of the course to set to.
	*/
	public void setCourseName(String name) {
		this.name = name;
	}
	/** Adds a test to this course
	* @param t Test to add to a course
	*/
	public void addTest(Test t) {
		this.testbank.AddTest(t);
	}
	/** Removes a test from this course
	* @param t Test to remove from course
	*/
	public void removeTest(Test t) {
		this.testbank.deleteTest(t);
	}
	/**
	 * Generates a simple version of a test.
	 * 
	 * @param testName Test name to set the test to.
	 * @param numQuestions Number of questions to have in the test.
	 * @param duration Duration of time to have the question.
	 * @param difficulty Difficulty to have the questions of a test to.
	 * @return A generated test based on the given inputs.
	 */
	public Test generateSimpleTest(String testName, int numQuestions, String duration, double difficulty) {
		return testbank.generateSimpleTest(testName, this, numQuestions, duration, difficulty);
	 }
	
	/**
	 * Generates a more complex version of a test. 
	 * This means there is more features to customize.
	 * 
	 * @param testName Test name to set the test to.
	 * @param course Course that the test is related to.
	 * @param numQuestions Number of questions that will be in the test that is generated.
	 * @param pattern Similar questions that the generated test questions should be like.
	 * @param duration Esimated duration of time that the test will take to complete.
	 * @return A generated test based on the given inputs.
	 */
	public Test generateComplexTest(String testName,Course course, int numQuestions, ArrayList<QuestionPattern> pattern, Time duration) {
		return testbank.generateComplexTest(testName,course, numQuestions, pattern,duration);
	}
	
	/** Get the testsTaken for a given testname
	* @param testname Test to add to the TestsTaken collection
	* @return TestTaken collection
	* pre: testname != null && !testname.isEmpty()
	*/
	public TestsTaken getTestsTaken(String testname) {
		TestsTaken[] temp = tests.toArray(new TestsTaken[0]);
		for (int i = 0; i < tests.size(); i++) {
			if (temp[i].GetTestName() == testname)
				return temp[i];
		}
		return null;
	}
	/** Gets all of the tests that have been taken
	* @return Collection of TestsTaken
	*/
	public Collection<TestsTaken> getAllTestsTaken() {
		return tests;
	}
	/**
	 * Gets a random question from the question bank
	 * @return Random question to return.
	 */
	public Question getRandomQuestion(double a) {
		return questionBank.getRandomQuestion(a);
	}
	/**
	 * Gets the number of total questions.
	 * @return A number that represents the total number of questions in the question bank.
	 */
	public int getTotalQuestions() {
		return questionBank.getTotalQuestions();
	}
	
	public int getTotalQuestions(double diff) {
		return questionBank.getTotalQuestions(diff);
	}
	/**
	 * Adds a question to the questionBank of a course.
	 * @param question Question to add to the question bank.
	 */
	public void addQuestion(Question question){
		this.questionBank.addQuestion(question);
	}
	/** 
	 * Gets the ArrayList that holds all the topics in a course.
	 * @return ArrayList that holds all the topics in a course.
	 */
	public ArrayList<String> getTopicArrayList() {
		return topics;
	}
	/**
	 * Adds a topic to the ArrayList of all the topics in a course.
	 * @param topic Topic to add to the ArrayList of topics in a course.
	 */
	public void addTopic(String topic) {
		topics.add(topic);
	}
	
	/** Gets all of the tests that have been taken
	* @return Collection of TestsTaken
	*/
	public ArrayList<Student> GetStudentList() {
		return students;
	}
	
	/**
	 * Gets the TestBank in the form of an ObservableList
	 * @return The Test Bank in the form of an ObservableList
	 */
	public ObservableList<Test> getTestBank() {
		return testbank.getTestBank();
	}
	
	/**
	 * Gets the question bank that is associated to a course.
	 * @return QuestionBank that is associated to a course.
	 */
	public QuestionBank getQuestionBank(){
	   return this.questionBank;
	}
	/**
	 * Clears the Observable testbank to a clean Observable collection
	 */
	public void restoreObservable() {
		testbank.restoreObservable();
	}
	public void deleteTest(Test newtest) {
		testbank.deleteTest(newtest);
		
	}

}
