package model.QuestionBank;

/** Class for Code Questions and the various methods and variable
 *  that manages a Code Question.
 *  This is a subclass of Question
 */
public class CodingQuestion extends Question implements java.io.Serializable{

	/** Answer submitted for the Code Question */
	private CodingAnswer answer;

	/** Keywords to look for to grade the Code Question */
	private String keywords[];

	/** Filename to save code to in order compile and run the code */
	private String filename;
	
	/**actual question text*/
	private String question;

	/**
	*	Sets the question to the supplied question.
	*
	*	@param question New question to set the question to.
	*/
	public void SetQuestion(String question){
		this.question = question;
	}

	/**
	*	Gets the question of the question.
	*
	*	@return Question of the CodingQuestion.
	*/
	public String GetQuestion(){
		return this.question;
	};

	/**
	*	Sets the answer to the supplied question.
	*
	*	@param answer New answer to set the question's answer to.
	*/
	public void SetActualAnswer(String answer){
		this.answer.SetCorrectAnswer(answer);
	}

	/**
	*	Gets the answer of the question.
	*
	*	@return Answer of the CodingQuestion.
	*/
	public CodingAnswer GetActualAnswer(){
		return this.answer;
	};

	/**
	*	Sets the student's answer to the supplied question.
	*
	*	@param answer New student's answer to set the question's student answer to.
	*/
	public void SetStudentAnswer(String answer){
		this.answer.SetStudentAnswer(answer);
	}

	/**
	*	Gets the student's answer of the question.
	*
	*	@return Student's answer of the CodingQuestion.
	*/
	public String GetStudentAnswer(){
		return this.answer.GetStudentAnswer();
	};


}
