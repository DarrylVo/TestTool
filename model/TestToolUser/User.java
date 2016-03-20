package model.TestToolUser;

import java.util.ArrayList;
import java.util.Collection;

import javafx.beans.property.SimpleStringProperty;
import model.TestBank.Course;
import model.TestTaking.*;
import model.TestBank.Test;

/**
 * The User class defines the basic fields of a user and universal user
 * actions in TestTool. It is a parent to Student and Teacher. Users have
 *  a name, password, email, and applicable courses, as well as getters and 
 *  setters for those fields. 
 */
public abstract class User implements java.io.Serializable {

	/** name (First and Last) of the user */
	private String name;
	private transient SimpleStringProperty nme;
	/** password associated with the user that allows access into TestTool */
	private String password;


	/** the email address of the user, where they can be contacted about tests and statuses; 
	* also used for logging into TestTool */
	private String email;
	private transient SimpleStringProperty eml;
	/** collection of courses that the user can access */
	protected ArrayList<Course> courses;
	
	public SimpleStringProperty nameProperty() {
		nme = new SimpleStringProperty(name);
		return nme;
	}
	
	public SimpleStringProperty emailProperty() {
		eml = new SimpleStringProperty(email);
		return eml;
	}

	/**
 	* addCourse adds a course to the collection of courses the user has access to
   * (use in concurrence with Course.removeStudent())
   *
      pre: course != null 
   */
	public void addCourse(Course course) {
		courses.add(course);
	}
	
	/**
 	* removeCourse removes a course from the collection of courses the user has access to
	* (use in concurrence with Course.addStudent())
   *
      pre: course != null && this.courses!=null
 	*/
	public void removeCourse(String course) {
		
	}
	
	/**
 	* takeTest initializes a test using methods found in the TestTaking package and transitions
	* TestTool into a Test Taking state 
   *
      pre: test != null
 	*/
	public void takeTest(Test test) {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Course getCourse(String coursename) {
		for(int i=0; i<courses.size();i++) {
			if (courses.get(i).getCourseName().equals(coursename))
				return courses.get(i);
		}
		System.out.println("ERROR ERROR couldn't find course");
		return null;
	}
	
	public ArrayList<Course> getCourseArrayList() {
		initArrayList();
		return courses;
	}
	
	public void initArrayList() {
		if(courses==null) 
			courses= new ArrayList<Course>();
	}
	
	public void restoreObservable() {
		for(int i = 0; i <courses.size();i++) {
			courses.get(i).restoreObservable();
		}
	}
	
	
}
