package model.QuestionBank;

import java.util.ArrayList;

/** Class for Multiple Choice Answer and the various methods and variable
 *  that manages a Multiple Choice Question.
 *  This is a subclass of Answer
 */
public class MultipleChoiceAnswer extends Answer {
	/** number for the number of answers **/
	private int numberAnswers;

	/** Sting[] to hold all answers */
	private ArrayList<String> answers = new ArrayList<>();

	/** correct Answer string **/
	private String correctAnswer;

	/** integer for the correct Answer **/
	private int correctIndex;

	/** student Answer String **/
	private String studentAnswer;

	/** integer for the student Answer **/
	private int studentIndex;

	// add to this constructor: String param - T/F 2 numAnswer, MC 4 numAnswer
	public MultipleChoiceAnswer()
	{
		numberAnswers = 4;
	}

	public ArrayList<String> getPossibleAnswers() {
		return answers;
	}

	/**
	 * 	Grades the question according to the given correct answer and assigns points
	 * 	based on whether or not the Student's response was correct
	 *
	 * @param points	possible points
	 * @return			points earned (based on a percentage of possible points)
	 */
	public int gradeAnswer(int points){
		if(studentAnswer.equals(correctAnswer))
			return points;
		else
			return 0;
	}

	/**
	*	Sets the answer to the supplied question. Actual answer will be the index of
	*	the correct response in the ArrayList.
	*
	*	@param index  	index of the question's correct answer
	*/
	public void SetCorrectIndex(int index){
		this.correctIndex = index;
	}

	/**
	*	Sets the String answer to the supplied question.
	*
	*	@param answer New answer to set the question's answer to.
	*/
	public void SetCorrectAnswer(String correct){
		if (!answers.contains(correct))
			answers.add(correct);
		this.correctAnswer = correct;
		for (int i=0; i < answers.size(); i++) {
			if (correctAnswer.equals(answers.get(i)))
				correctIndex = i;
		}
	}

	/**
	*	Gets the answer of the question.
	*
	*	@return Answer of the question.
	*/
	public String GetCorrectAnswer(){
		return this.correctAnswer;
	}

	/**
	*	Gets the answer index of the question.
	*
	*	@return Answer index of the question.
	*/
	public int GetCorrectIndex(){
		return this.correctIndex;
	}

	/**
	*	Sets the student answer string to the supplied question.
	*
	*	@param answer New student's answer to set the question's student answer to.
	*/
	public void SetStudentAnswer(String answer){
		this.studentAnswer = answer;
		for (int i=0; i < answers.size(); i++) {
			if (studentAnswer.equals(answers.get(i)))
				studentIndex = i;
		}
	}

	/**
	*	Sets the Student answer using the selected radio button as an index.
	*
	*	@param index  	index of the question's correct answer
	*/
	public void SetStudentIndex(int index){
		this.studentIndex = index;
	}

	/**
	*	Gets the student's answer of the question.
	*
	*	@return Student's answer of the question.
	*/
	public String GetStudentAnswer(){
		return this.studentAnswer;
	}

	/**
	*	Gets the student's answer of the question.
	*
	*	@return Student's answer of the question.
	*/
	public int GetStudentIndex(){
		return this.studentIndex;
	}

	/**
	 * Edits the answer choice string at the location given by index
	 *
	 * @param index		index of the answer choice to be changed
	 * @param answer	String to be used as new answer choice
	 */
	public void EditAnswerChoices(int index, String answer){
		this.answers.set(index, answer);
	}


	/**
	*	Adds another choice to the multiple choice question.
	*
	*	@param choice New possible choice for the answer in the multiple choice question.
	*
	*	pre: possibleAnswers[].count < 8
	*	post: none
	*
	*/
	public void addPossibleAnswer(String answer){
		answers.add(answer);
	};

	/**
	*	Removes an answer choice from the multiple choice question.
	*
	*	pre: possibleAnswers[].count > 0
	*	post: none
	*
	*  @param index		the index of the answer choice to remove
	*
	*/
	public void removePossibleAnswer(int index){
		answers.remove(index);

	};
}
