package model.QuestionBank;
import java.util.ArrayList;
import java.util.Random;

/**
 *  Class for QuestionBank. This holds a collection of questions and the
 *  various methods to alter the collection.
 */
public class QuestionBank implements java.io.Serializable {

	/** Collection that holds the various questions */
	private ArrayList<Question> questionBank = new ArrayList<Question>();
	private ArrayList<Question> qb1 = new ArrayList<Question>();
	private ArrayList<Question> qb2 = new ArrayList<Question>();
	private ArrayList<Question> qb3 = new ArrayList<Question>();
	private ArrayList<Question> qb4 = new ArrayList<Question>();
	private ArrayList<Question> qb5 = new ArrayList<Question>();
	
	public ArrayList<Question> getQuestions() {
		return questionBank;
	}
	
	/** 
	*	Adds a new question to add to the question bank.
	*	
	*	@param question New question to add to the question bank.
    *   
	*/
	public int getTotalQuestions() {
		return questionBank.size();
	}
	
	public void addQuestion(Question question) {
		questionBank.add(question);
		if(question.getDifficulty()==1)
			qb1.add(question);
		else if(question.getDifficulty()==2)
			qb2.add(question);
		else if(question.getDifficulty()==3)
			qb3.add(question);
		else if(question.getDifficulty()==4)
			qb4.add(question);
		else if(question.getDifficulty() == 5)
			qb5.add(question);
      //  System.out.println ("QuestionBank.AddQuestion invoked.");
    }
	
	/** 
	*	Deletes the question from the question bank.
	*	
	*	@param question Question to delete from the question bank.
    *   pre: questionBank.contains(question);
    *   post: !questionBank.equals(return);
	*/
	public void deleteQuestion(Question question){
		questionBank.remove(question);
        System.out.println("QuestionBank.deleteQuestion invoked.");
    }
	
	/** 
	*	Edits the question from the question bank.
	*	
	*	@param question Question to edit from the question bank.
    *   pre: questionBank.contains(question);
    *   post: !questionBank.equals(return);
	*/
	public void editQuestion(Question question){
        System.out.println("QuestionBank.editQuestion invoked.");
    }
	
	/** 
	*	Searches the question bank for the question.
	*	
	*	@param question Question to search for in the question bank.
	*   @return Question that was retrieved in search.
	*   pre: questionBank.contains(question);
    *   post: !questionBank.equals(return);
	*/
	public Question searchQuestion(int question){return null;}
	
    /**
     *	Searches the question bank for the question using a keyword.
     *
     *	@param question Question to search for in the question bank.
     *   @return Question that was retrieved in search.
     */
	public ArrayList<Question> SimpleSearch(String keyword){return null;}
	
    /**
     *	Searches the question bank for the question using an advanced search.
     *
     *	@param question Question to search for in the question bank.
     *   @return Question that was retrieved in search.
     */
	public ArrayList<Question> AdvancedSearch(int difficulty, String coursename, String type, String topic){return null;}
	
    /**
     *  Creates Multiple Choice Question to add to the Question Bank
     */
	public Question createMultipleChoiceQuestion(){return null;}
	
    /**
     *  Creates Coding Question to add to the Question Bank
     */
	public Question createCodingQuestion(){return null;}
	
    /**
     *  Creates Written Response Question to add to the Question Bank
     */
	public Question createWrittenResponseQuestion(){return null;}
	
	public Question getRandomQuestion(double a) {
		int a1 = (int)a;
		Random rand = new Random();
		int r=0; 
		switch(a1) {
			case 1:
				r = 0 + rand.nextInt( (qb1.size()-1) - 0 + 1);
				return qb1.get(r);
			case 2:
				r = 0 + rand.nextInt( (qb2.size()-1) - 0 + 1);
				return qb2.get(r);
			case 3:
				r = 0 + rand.nextInt( (qb3.size()-1) - 0 + 1);
				return qb3.get(r);
			case 4: 
				r = 0 + rand.nextInt( (qb4.size()-1) - 0 + 1);
				return qb4.get(r);
			case 5: 
				r = 0 + rand.nextInt( (qb5.size()-1) - 0 + 1);
				return qb5.get(r);
		}
		return questionBank.get(r);
	}
	public int getTotalQuestions(double diff) {
		int d = (int) diff;
		switch(d) {
		   case 1: return qb1.size();
		   case 2: return qb2.size();
		   case 3: return qb3.size();
		   case 4: return qb4.size();
		   case 5: return qb5.size();
		}
		return 0;
	}
	

}
