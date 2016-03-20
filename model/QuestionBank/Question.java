package model.QuestionBank;

import java.awt.Image;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import model.Statistics.Time;
import model.TestBank.Comment;
import model.TestBank.Course;

/** Class for test Questions and the various methods and variable
 *  that manages a Question.
*/
public class Question implements java.io.Serializable {

	/** Question of the test question. */
	private String question;
	//private SimpleStringProperty questionP;
	
	/** Unique integer ID of question */
	private int ID;

	/** Difficulty of the test question */
	private double difficulty;
	//private SimpleDoubleProperty difficultyP;

	/** Expected time that it will take the student to complete the question */
	private Time expectedTime;

	/** Number of possible points to be earned for a question */
	private int pointValue;

	/** Number of points earned for a question */
	private int pointsEarned;

	/** Question number within a test bank */
	private int questionNumber;

	/** Topic of a test question */
	private String topic;
	//private SimpleStringProperty topicP;

	/** Variable that holds the question's type. */
   private String type;
   //private SimpleStringProperty typeP;

	/** Correct answer of the test question. */
	private Answer actualAnswer;

	/** Student's answer of the test question. */
	private Answer studentAnswer;

	/** Flag to see if the student has provided an answer */
	private boolean answered;

	/** Comment that an Instructor has given for this question. */
	private Comment comment;

	/** Additional instructions that Instructors may choose to include with the question */
	private String instructions;

	/** Image that is displayed with the question during test taking */
	private Image img;

	/** Time that taken by the student on this question */
	private Time duration;
	
	private Course course;
	//private SimpleStringProperty courseStringP;
	
	public SimpleStringProperty courseProperty() {
		return new SimpleStringProperty(course.getCourseName());
		
	}
	public SimpleStringProperty typeProperty() {
		return new SimpleStringProperty(type);
		
	}
	public SimpleStringProperty topicProperty() {
		return new SimpleStringProperty(topic);
	}
	public SimpleDoubleProperty difficultyProperty() {
		return new SimpleDoubleProperty(difficulty);
	}
	public SimpleStringProperty questionProperty() {
		return new SimpleStringProperty(question);
	}
	
	public void setCourse(Course c) {
		this.course = c;
	}
	public Course getCourse() {
		return this.course;
	}
	/**
	*	Sets the question to the supplied question.
	*
	*	@param question New question to set the question to.
	*/
	 public void setQuestion(String question){
		 this.question = question;
	 }

	/**
	*	Gets the question of the question.
	*
	*	@return Question of the question.
	*/
	 public String getQuestion(){
		 return question;
	 }
	/** Sets the question to be answered.
	 * 
	 */
	 public void setAnswered() {
		 answered = true;
	 }
	
	 /** Gets the status of whether the question has been answered.
	  */
	  public boolean getAnswered() {
		  return answered;
	  }
	 
	/**
	 * Sets time taken by the student on the question
	 *
	 * @param time	Total time on question
	 */
	public void setDuration(Time time) {
		this.duration = time;
	}

	/**
	 * Return the time the student took on this question
	 *
	 * @return 	Time taken on this question
	 */
	public Time getDuration() {
		return this.duration;
	}

	/**
	*	Sets the difficulty to the supplied question.
	*
	*	@param difficulty New difficulty to set the question to.
	*/
	public void SetDifficulty(double difficulty){
		this.difficulty = difficulty;
	}

	/**
	*	Gets the difficulty to the supplied question.
	*
	*	@return  Difficulty of the question.
	*/
	public double getDifficulty(){
		return this.difficulty;
	}

	/**
	*	Sets the expected time taken of a question.
	*
	*	@param expected Time expected on this question.
	*/
	public void SetExpectedTime(Time expected){
		this.expectedTime = expected;
	}

	/**
	*	Gets the expected time taken of the question.
	*
	*	@return  Difficulty to get the question to.
	*/
	public Time getExpectedTime(){
		return this.expectedTime;
	}

	/**
	*	Sets the topic to the supplied question.
	*
	*	@param topic New topic to set the question to.
	*/
	public void setTopic(String topic){
	   this.topic = topic;
	}

	/**
	*	Gets the topic of the question.
	*
	*	@return Topic of the question.
	*/
	 public String getTopic(){
		 return this.topic;
	 }



	/**
	*	Sets the answer to the supplied question by calling the answer
	*	method: SetCorrectAnswer
	*
	*	@param answer New answer to set the question's answer to.
	*/

	 public void SetActualAnswer(String answer){
		 this.actualAnswer.SetCorrectAnswer(answer);
	 }


	/**
	*	Gets the answer of the question.
	*
	*	@return Answer of the question.
	*/
	 public Answer getActualAnswer(){
		 return this.actualAnswer;
	 };

	/**
	*	Sets the student's answer to the supplied question.
	*
	*	@param answer New student's answer to set the question's student answer to.
	*/

	 public void SetStudentAnswer(String answer){
		 this.studentAnswer.SetStudentAnswer(answer);
	 }

	/**
	*	Gets the student's answer of the question.
	*
	*	@return Student's answer of the question.
	*/
	 public Answer getStudentAnswer(){
		 return this.studentAnswer;
	 }

	/**
	*	Sets the question's possible point value.
	*
	*	@param value New value to set the question's possible point value.
	*/

	 public void setPointValue(int value){
		 this.pointValue = value;
	 }

	/**
	*	Gets the question's possible point value.
	*
	*	@return The question's possible point value.
	*/
	 public int getPointValue(){
		 return this.pointValue;
	 }

	/**
	*	Sets the question's earned point value.
	*
	*	@param value New value to set the question's earned point value.
	*/

	 public void setPointsEarned(int value){
		 this.pointsEarned = value;
	 }

	/**
	*	Gets the question's earned point value.
	*
	*	@return The question's earned point value.
	*/
	 public int getPointsEarned(){
		 return this.pointsEarned;
	 }

	/**
	*	Checks if the Student's answer is correct by calling Answer's "gradeAnswer"
	*
	*	@return Whether or not the Student's answer matches with the correct answer.
	*/
	public boolean checkAnswer(){
		return this.actualAnswer.gradeAnswer(this.pointValue) == this.pointValue;
	}

	/**
	 * 	Allows the Instructor to include further instructions with the question.
	 *  This field may be left empty.
	 *
	 * @param instructions	The additional information the Instructor has chosen
	 * 						to include with/for the question.
	 */
	public void SetInstructions(String instructions){
		this.instructions = instructions;
	}

	/**
	 * 	Gets the question's additional instructions.
	 *  If no further instructions were attached to the question, returns an empty string.
	 *
	 * @return	The question's instructions or an empty string.
	 */
	public String getInstructions(){
		return this.instructions;
	}

	/**
	 * Adds an image which is displayed with the question during test taking.
	 *
	 * @param img	The image to be displayed with the question.
	 */
	public void addImage(Image img){
		this.img = img;
	}

	/**
	 * Adds an image which is displayed with the question during test taking.
	 *
	 * @return img	The image to be displayed with the question.
	 */
	public Image getImage(Image img){
		return this.img;
	}

	/**
	 * This function checks whether all necessary fields have
	 * been entered into the Question.
	 *
	 * pre: none
	 * post: foreach(field in question; field != empty)
	 *
	 * @return	1 if successfully verified; 0 if not
	 */
	public int verifyQuestion(){
		if (this.question !=null && this.type != null
		      && !this.question.isEmpty() && !this.type.isEmpty()){
		   return 1;
		}
		else{
		   return 0;
		}
	}

	/**
	 * Sets the question number for the Question within a test
	 *
	 * @param number the question number
	 */
	public void setQuestionNumber(int number) {
		this.questionNumber = number;
	}

	/**
	 * Returns the questions number
	 *
	 * @return question number
	 */
	public int getQuestionNumber() {
		return this.questionNumber;
	}

	/**
	 * Returns the question's unique ID
	 *
	 * @return question ID
	 */
	public int getQuestionID() {
		return this.ID;
	}


	/**
	 * Sets the Comment value (pre-created comment)
	 *
	 * @param comment the Comment for the question to set
	 */
	public void setComment(Comment comment) {
		this.comment = comment;
	}

	/**
	 * Creates a new comment and set its message as the parameter
	 *
	 * @param comment the String value the Comment class will contain
	 */
	public void setComment(String comment) {
		this.comment = new Comment(comment);
	}

	/**
	 * Returns the Comment for this Question
	 *
	 * @return comment for the Question
	 */
	public Comment getMessage() {
		return this.comment;
	}

	/**
	   *  Sets the type of a question.
	   *
	   *  @param type Type of question to set the question to.
	   */
	public void setType(String type){
	   this.type = type;
	}
	/**
	 * Gets the type of the question.
	 *
	 * @return The type of the question.
	 */
	public String getType(){
	   return this.type;
	}
	
	public Question getCopy() {
		Question q = new Question();
		q.question = this.question;
		q.ID = this.ID;
		q.difficulty = this.difficulty;
		q.expectedTime = this.expectedTime;
		q.pointValue = this.pointValue;
		q.pointsEarned = this.pointsEarned;
		q.questionNumber = this.questionNumber;
		q.topic = this.topic; 
		q.type = this.type;
		q.actualAnswer = this.actualAnswer;
		q.studentAnswer = this.studentAnswer;
		q.answered = this.answered;
		q.comment = this.comment;
		q.instructions = this.instructions;
		q.img = this.img;
		q.duration = this.duration;
		return q;
	}
	public String toString() {
		String s = "question:"+question+" topic:"+topic+ " type:"+type+ "difficulty:" +difficulty;
		return s;
	}
}
