package model.TestToolUser;

import java.util.Collection;

/**
 * The Login class is used to add new users (teachers or students)  
 * to the TestTool Application, and to verify and allow access to
 * users that are registered in the TestTool system. It contains methods 
 * for comparing inputted email/password fields against a list of 
 * registered users, as well as methods to register users.
 */
public class Login {

	/** the group of student users registered in TestTool */
	private Collection<Student> students;

	/** the group of teacher users registered in TestTool */
	private Collection<Teacher> teachers;
	
	/**
 	* VerifyLoginInfo checks the inputted email and password against the collection 
	* of students and the collection of teachers and returns true (1) if there  
	* is a match or false (0) if not
 	*/
	public int VerifyLoginInfo(String email, String password){return 0;}
	
	/**
 	* addStudent adds a student, with their basic login info, to the
	* collection of students
 	*/
	public void addStudent(String name, String password, String email){}
	
	/**
 	* addTeacher adds a teacher, with their basic login info, to the
	* collection of students
 	*/
	public void addTeacher(String name, String password, String email){}
}
