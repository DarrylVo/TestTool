package model.QuestionBank;

/** Class for Written Response Questions and the various methods and variable
 *  that manages a Written Response Question..
 *  This is a subclass of Question.
 */
public class WrittenResponseQuestion extends Question{

	/** Answer submitted for the Code Question */
	private ShortLongAnswer SLanswer = new ShortLongAnswer();

	/** Keywords to look for to grade the Code Question */
	private String keywords[];

	/** Enum to determine the question type */
	private enum type {fillin, shortAnswer, longAnswer};

	/**
    * Constructor for a question
    */
   public WrittenResponseQuestion(){
      this.setType("Written Response");
   }

	/**
	*	Sets the answer to the supplied question.
	*
	*	@param answer New answer to set the question's answer to.
	*/
	public void SetActualAnswer(String answer){
		this.SLanswer.SetCorrectAnswer(answer);
	}

	/**
	*	Gets the answer of the question.
	*
	*	@return Answer of the question.
	*/
	public ShortLongAnswer GetActualAnswer(){
		return SLanswer;
	}

	/**
	*	Sets the student's answer to the supplied question.
	*
	*	@param answer New student's answer to set the question's student answer to.
	*/
	public void SetStudentAnswer(String answer){
		this.SLanswer.SetStudentAnswer(answer);
	}


	public void setAnswerObject(ShortLongAnswer a) {
		SLanswer = a;
	}
	public ShortLongAnswer getAnswer() {
		return SLanswer;
	}
}
