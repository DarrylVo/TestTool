package model.QuestionBank;
/**
 *  Class Answer is a parent class to more specific Answer classes like Coding, MultipleChoice,
 *  ShortLong, and Written Response Answers.
 *  Class Answer provides methods to grade a question based on the answer that was given
 *  Set Correct answer, and Set the Students Answer.
 */
public abstract class Answer implements java.io.Serializable {

	/**
 	* gradeAnswer grades the answer of the student that depends on the correct answer
 	*
 	* gradeAnswer is different for each Answer type !!! point to grade test code? !!!
 	*/
	abstract int gradeAnswer(int points);

	/**
 	* SetCorrectAnswer set the correct answer to the question
 	*/
	abstract void SetCorrectAnswer(String answer);

	/**
 	* SetStudentAnswer set the Student's answer to the question
 	*/
	abstract void SetStudentAnswer(String answer);


}