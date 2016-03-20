package model.QuestionBank;

import java.util.ArrayList;
import java.util.Scanner;

/** Class for Code Answer and the various methods and variable
 *  that manages a Code Question.
 *  This is a subclass of Answer
 */
public class CodingAnswer extends Answer implements java.io.Serializable{
	
	/** A String for Student's Answer for the Code Question */
	private String studentAnswer;
	
	/** String of Keywords to look for to grade the Code Question */
	private ArrayList<String> keywords;
	
	public CodingAnswer(ArrayList<String> keywords, String studentAnswer) {
		this.keywords = keywords;
		this.studentAnswer = studentAnswer;
	}
	
	public CodingAnswer(String keyword, String studentAnswer) {
		this.keywords = new ArrayList<String>();
		keywords.add(keyword);
		this.studentAnswer = studentAnswer;
	}
	
	/**
 	* gradeAnswer grades the answer of the student that depends on the correct answer
 	*/	
	public int gradeAnswer(int points) {
		Scanner scanner = new Scanner(studentAnswer);
		String[] usedKeywords = new String[keywords.size()];
		int numKeywordsUsed = 0;
		int keyMatches = 0;
		while (scanner.hasNext()) {
			String word = scanner.next();
			if (ContainsKeyword(keywords, word)) {
				keyMatches++;
				usedKeywords[numKeywordsUsed++] = word;
			}
		}
		return (int) (keyMatches / (double) keywords.size()) * points;
	}
	
	/**
	 * Returns true if the String value passed in is contained within the given array of Strings.
	 * @param words array of Strings to look through for the value str
	 * @param str String value to find
	 * @return true if str value is found in the words array, otherwise false
	 */
	private boolean ContainsKeyword(ArrayList<String> words, String str) {
		for (int i=0; i < words.size(); i++) {
			if (words.get(i).equals(str)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	*	Sets the Correct answer to the supplied question.
	*
	*	@param answer New answer to set the question's answer to.
	*/
	public void SetCorrectAnswer(String correct){
		keywords.add(correct);
	}
	
	/**
	*	Gets the Correct answer to the supplied question.
	*
	*	@return String[] Correct Answer
	*/
	public String[] GetCorrectAnswer(){return keywords.toArray(new String[0]);}
	
	/**
	*	Sets the Student's answer to the supplied question.
	*
	*	@param answer Student answer to set the question's answer to.
	*/
	public void SetStudentAnswer(String answer){
		studentAnswer = answer;
	}
	
	/**
	*	Gets the student's answer of the question.
	*
	*	@return Student's answer of the question.
	*/
	public String GetStudentAnswer(){return studentAnswer;}
	
	/**
	*	Compiles the code
	*
	*	@return the compilation of code
	*/
	public String Compile(){return null;}
}
