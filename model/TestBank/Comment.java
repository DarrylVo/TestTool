package model.TestBank;
/** This class contains the comment a teacher attaches to a question or test when grading.
* The methods included in the class set and get the message that is in a comment.
*/
public class Comment implements java.io.Serializable {
	/** Contains the message of the comment.*/
	private String message;
	/**
	 * Constructor for a Comment object.
	 * @param msg Message to initialize the Comment's message to.
	 */
	public Comment(String msg) {
		message = msg;
	}
	/** Sets the comment to something.
	* @param msg Message to set the comment to.
	*/ 
	public void SetMessage(String msg){
		this.message = msg;
	}
	/** Gets the contents of the comment.
	* @return The message of the comment.
	*/
	public String GetMessage(){
		return message;
	}
}
