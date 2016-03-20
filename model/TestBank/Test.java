package model.TestBank;

import model.QuestionBank.Question;
import model.TestToolUser.Student;

import java.util.ArrayList;
import java.util.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Statistics.Time;
/** The Test class contains all the information that a test would have.
 *  The methods in this class edit and create Tests.
 */
public class Test implements java.io.Serializable {
        /** This Collection of questions stores all the data for each individual
        * question on the test
         */
	private ArrayList<Question> questions = new ArrayList<>();
	/**
	 * The questionLength variable represents the number of the questions.
	 */
	private transient SimpleIntegerProperty numQuestion = new SimpleIntegerProperty();
	/**
	 * Variable that hols how many questions are in a test
	 */
	private int numQuestionstore;
	/**
	 * The courseName variable represents the name of the course.
	 */
	private String courseName;
        /** This Collection of comments stores all the comments on the test created by the teacher when grading 
        */
	private Comment comments;
        /** this double contains how long the student will have to take the test */
	private Time duration;
        /** This double contains the difficulty level of the test */
	private transient SimpleDoubleProperty difficulty = new SimpleDoubleProperty();
	/**
	 * Variable that stores the difficulty of a test.
	 */
	private double difficultystore;
        /** This string contains the name of the test */
	private transient StringProperty name = new SimpleStringProperty();
	/**
	 * Variable that stores the name of a test.
	 */
	private String namestore;
        /** This variable contains what course this test is a part of */
	private Course course;
        /** Int of the total points possible on this test*/
	private int totalPoints;
         /** stores the student's grade on this test after the teacher has graded it*/
	private int studentGrade;
        /** Date the test is assigned to the student */
	private Date assigned;
        /** Date by which the student has to take the test */
	private Date due;
		/** Date in which the student completed the test */
	private Date completed;
        /** Enum containing the current state of this test (generated, published, taken, graded) */
	public enum State {generated, published, taken, graded};
	/**
	 * Variable that represents the current state of the Test.
	 */
	private State state = State.generated;
    /** stores whose test this is */
	private Student student;
	
    /** Adds a question to this test 
	 *
     * @param Sets the question length to a given length
     */
	
	public void setNumQuestion(int numq) {
		numQuestion.set(numq);
		numQuestionstore=numq;
	}
	/** Gets the question length variable
   *
    * @return The question length of a question.
    */
	public int getNumQuestion() {
		return questions.size();
	}
	/**
	 * Gets the number of questions that are in a test
	 * @return Number of questions in a test.
	 */
	public SimpleIntegerProperty numQuestionProperty() {
		return numQuestion;
	}
	/** Gets the course name of a course
   *
    * @return The course name of a course.
    */
	public String getCourseName() {
		return courseName;
	}
	/** Sets the course name of a course. 
   *
    * @param
    */
	public void SetCourseName(String name) {
		course.setCourseName(name);
	}
	
	/** Sets the name of this test
	 * @param name The string to set the name of the test to.
	 */
	public void setName(String name) {
		this.name.set(name);
		namestore = name;
	}
	/** Gets the name of this test
	 * @return The method returns the name of the test.
	 */
	public String getName() {
		return name.get();
	}
	/** Gets the date that the test was completed by the student */
	public Date getCompleted() {
		return completed;
	}
	/** Sets the date that the test was completed by the student */
	public void setCompleted(Date completed) {
		this.completed = completed;
	}
	
	/** Gets the questions of this test
    * @return The method returns the questions of the test.
    */
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	/** Adds a question to this test 
	 *
     *  @param q Question to be added to the test
      pre: q != null
     */
	public void addQuestion(Question q) {
		this.questions.add(q);
		//System.out.println("Test.AddQuestion() invoked.");
	}
	/** Removes a question from this test
	 *
     *  @param q Question to be removed from the test
	  pre: q != null && this.questions!=null
	 */
	public void removeQuestion(Question q){
		questions.remove(q);
	}
	/** Gets a question from this test
     * @param n The question number to retrieve from the questions collection. 
     * @return Question that is retrieved from the questions collection. Returns null if not found.
     * pre: n >= 0
	 */
	public Question getQuestion(int n){
		Question[] arr = questions.toArray(new Question[0]);
		for (int i=0; i < arr.length; i++) {
			if (arr[i].getQuestionNumber() == n)
				return arr[i];
		}
		return null;
	}
	/** Sets the duration of this test
	 * @param duration The duration to set the test to.
	 */
	public void setDuration(Time duration){
		this.duration = duration;
	}
	/** Gets the duration of this test
	 * @return The method returns the duration of the test.
	 */
	public Time getDuration(){
		return this.duration;
	}
	/** Sets the difficulty of this test
	 * @param difficulty The difficulty to set the test to.
	 */
	public void setDifficulty(double difficulty){
		this.difficulty.set(difficulty);
		difficultystore = difficulty;
	}
	/** Gets the difficult of this test
	 * @return The method returns the difficulty of the test.
	 */
	public double getDifficulty(){
		return this.difficulty.get();
	}
	/**
	 * Gets the difficulty of a test.
	 * @return The difficulty of a test
	 */
	public DoubleProperty difficultyProperty() {
		return difficulty;
	}
	/** Sets the date this test is to be assigned 
	* @param assigned The date the test is assigned on.
	*/
	public void setAssigned(Date assigned){
		this.assigned = assigned;
	}
	/** Gets the date at which this test is assigned 
	* @return The method returns the date the test is assigned on.
	*/
	public Date getAssigned(){
		return this.assigned;
	}
	/** Set the date this test is due.
	* @param due Date to set the due date of the Test to.
	*/
	public void setDue(Date due){
		this.due = due;
	}
	/** Gets the due date 
	* @return The method returns the due date of the Test.
	*/
	public Date getDue(){
		return this.due;
	}
	
	/** Sets the course of this test 
	* @param c The course to set the Test to.
	*/
	public void setCourse(Course c){
		course = c;
		courseName = c.getCourseName();
	}
	/** Gets the course of this test 
	* @return The course that the test is associated with.
	*/
	public Course getCourse(){
		return course;
	}
	/** Sets the total points for this test 
	* @param points The total points the test should be set to.
	*/
	public void setTotalPoints(int points){
		totalPoints = points;
	}
	/** Gets the total points for this test
	 * @return The method returns the total points of the test.
	 */
	public int getTotalPoints(){
		return totalPoints;
	}
	
	/** Sets the state of this test (generated, published, taken, graded)
	* @param enm The State to set the test to.
	*/ 
	public void setState(State enm){
		state = enm;
	}
	/** Gets the state of this test (generated, published, taken, graded)
	* @return The method returns the State of the test.
	*/ 
	public State getState(){
		return state;
	}
	/** Sets the student's grade of this test 
	* @param grade The grade to set the Test to.
	*/
	public void setStudentGrade(int grade){
		studentGrade = grade;
	}
	/** Gets the student's grade of this test
	 * @return The method returns the Student's grade of the Test.
	 */
	public int getStudentGrade(){
		return studentGrade;
	}
	/** Sets the student assigned to the test
	* @param student The student to set the test to.
	*/
	public void setStudent(Student student){
		this.student = student;
	}
	/** Gets the student assigned to the test
	* @return Student that is assigned to the test.
	*/
	public Student getStudent(){
		return student;
	}
	/** Sets the comment of the Test
	* @param Comment The comment to set the test to.
	*/
	public void setComment(Comment comment){
		comments = comment;
	}
	
	/**
	 * Sets the given comment for the specified question
	 * @param comment Class Comment to set as the comment in the Question Class.
	 * @param questionNum the number question to set the comment for.
	 */
	public void setQuestionComment(Comment comment, int questionNum){
		Question q = getQuestion(questionNum);
		q.setComment(comment);
	}
	/** Gets the comment of the Test
	* @return The method returns the comment of the test.
	* pre: this.comment != null
	*/
	public Comment getComments(){
		return comments;
	}
	/** Saves the changes made while editing a test.
	*
	*/
	public void saveChanges(){
		System.out.println("SaveChanges() invoked.");
	}
	
	/**
	 * Checks if question is in the test
	 * @param q The question to check if the question is in the test
	 * pre: q != null
	 */
	public String isInTest(Question q){
	   if (q != null) {
	      for (int i = 0; i < this.questions.size(); i++) {
	         if (q.getQuestion() != null &&
	               q.getQuestion() == this.questions.get(i).getQuestion()) {
	            return "Yes";
	         }
	      }
	   }
	   return "No";
	}
	/**
	 * Gets the name of a test
	 * @return The name of a test
	 */
	public StringProperty nameProperty() {
		return name;
	}
	/**
	 * Clears the Observable array lists of number of question, difficulty, and name
	 */
	public void restoreObservable() {
		name = new SimpleStringProperty();
		numQuestion = new SimpleIntegerProperty();
		difficulty = new SimpleDoubleProperty();
		name.set(namestore);
		numQuestion.set(numQuestionstore);
		difficulty.set(difficultystore);
	}
}
