package model.QuestionBank;

import java.util.ArrayList;

/** Class for Multiple Choice Questions and the various methods and variable
 *  that manages a Multiple Choice Question..
 *  This is a subclass of Question.
 */
public class MultipleChoiceQuestion extends Question{

	/** MC answer variable */
	private MultipleChoiceAnswer MCAnswer = new MultipleChoiceAnswer();

	/** Enum to determine the question type */
	private enum type {matching, multipleChoice, truefalse};

	/**
	 * Constructor for a question
	 */
	public MultipleChoiceQuestion(){
	   this.setType("Multiple Choice");
	}

	public ArrayList<String> getPossibleAnswers() {
		return MCAnswer.getPossibleAnswers();
	}
	
	public void setAnswerObject(MultipleChoiceAnswer a) {
		MCAnswer = a;
	}
	public MultipleChoiceAnswer getAnswer() {
		return MCAnswer;
	}

	/**
	*	Sets the answer to the supplied question. Actual answer will be the index of
	*	the correct response in the ArrayList.
	*
	*	@param answer New answer to set the question's answer to.
	*/
	public void SetActualAnswer(String answer){
		this.MCAnswer.SetCorrectAnswer(answer);
	};

	/**
	*	Gets the answer of the question.
	*
	*	@return Answer of the question.
	*/
	public String GetActualAnswer(){
		return this.MCAnswer.GetCorrectAnswer();
	};

	/**
	*	Sets the Student answer using the selected radio button as an index.
	*
	*	@param answer New student's answer to set the question's student answer to.
	*/
	public void SetStudentAnswer(String answer){
		this.MCAnswer.SetStudentAnswer(answer);
	};

	/**
	*	Gets the student's answer of the question.
	*
	*	@return Student's answer of the question.
	*/
	public String GetStudentAnswer(){
		return this.MCAnswer.GetStudentAnswer();
	};


	/**
	*	Adds another choice to the multiple choice question.
	*
	*	@param choice New possible choice for the answer in the multiple choice question.
	*
	*	pre: possibleAnswers[].count < 8
	*	post: none
	*/
	public void addPossibleAnswer(String choice){
		 MCAnswer.addPossibleAnswer(choice);
	};

	/**
	*	Removes a choice from the multiple choice question.
	*
	*	pre: possibleAnswers[].count > 0
	*	post: none
	*
	*/
	public void removePossibleAnswer(int index){
		MCAnswer.removePossibleAnswer(index);

	};

}
