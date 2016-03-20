package model.QuestionBank;

import java.util.ArrayList;
import java.util.Scanner;

/** Class for ShortLongAnswer (aka Free Response) and the various methods and variable
 *  that manages a Code Question.
 *  This is a subclass of Answer
 */
public class ShortLongAnswer extends Answer{

	/** Keywords to look for to grade the Code Question */
	private ArrayList<String> keywords;

	/** String to hold Student Answer **/
	private String studentAnswer;

	public ShortLongAnswer() {
		keywords = new ArrayList<String>();
		studentAnswer = "";
	}
	
	public ShortLongAnswer(ArrayList<String> keywords, String studentAnswer) {
		this.keywords = keywords;
		this.studentAnswer = studentAnswer;
	}
	
	public ShortLongAnswer(String keyword, String studentAnswer) {
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
	*	For Short/Long answer questions, the correct answer is a set of keywords
	*
	*	@param the correct answer string
	*/
	public void SetCorrectAnswer(String correct){
		keywords.add(correct);
	}

	/**
	*	Gets the Correct answer to the supplied question.
	*
	*	@return String[] Correct Answer
	*/
	public String[] GetCorrectAnswer(){
		return keywords.toArray(new String[0]);
	}

	/**
	*	Sets the Student's answer to the supplied question.
	*
	*	@param answer Student answer to set the question's answer to.
	*/
	public void SetStudentAnswer(String answer){
		studentAnswer = answer;
	}

	/**
	*	Gets the Correct answer to the supplied question.
	*
	*	@return int Correct Answer
	*/
	public String GetStudentAnswer(){
		return studentAnswer;
	}

}
