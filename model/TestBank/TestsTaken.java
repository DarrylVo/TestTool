package model.TestBank;

import java.util.ArrayList;
import java.util.Collection;
/** Class TestsTaken stores the tests after they are taken by the students. It has a collection 
 * of tests and has methods to get/add/remove tests to that collection. Each TestsTaken object 
 *  will hold one specific test. 
 *  */
public class TestsTaken implements java.io.Serializable {
    /**  Name of the test that is being stored  
     * */
	private String testname;
	/** A collection of tests that has all the taken tests 
	 * */
	private Collection<Test> tests = new ArrayList<Test>();
	
	/**boolean to indicate whether tests have been graded yet*/
	private boolean gradesSent = false;
	/** Sets the testname field 
	 * @param testname String to set the name of the test to*/
	public void SetTestName(String testname){
		this.testname = testname;
	}
	/** gets the name of the test 
	 * @return Test name in  a string*/
	public String GetTestName(){
		return testname;
	}
	/** Adds a test to the collection after being taken by a student
	 * 
	 * @param t test to be added 
	 */
	public void addTest(Test t){
		tests.add(t);
	}
	/** Removes a test from the collection 
	 * 
	 * @param t test to be removed
	 */
	public void removeTest(Test t){
		
	}
	/** Returns the collection of taken tests
	 * 
	 * @return tests A collection of all the taken tests
	 */
	public Collection<Test> getTests() {
		return tests;
	}
	/** Finds  test taken by a specific student
	 * 
	 * @param studentname looks for tests belonging to this student
	 * @return Test that was taken by this student
	 */
	public Test GetTestTaken(String studentname) {
		Test[] temp = tests.toArray(new Test[0]);
		for (int i = 0; i < tests.size(); i++) {
			if (temp[i].getStudent().getName() == studentname)
				return temp[i];
		}
		return null;
	}
	
	/**used to set gradeSent to true to indicate to the student UI that the test is available for review*/
	public void SendGrades() {
		gradesSent = true;
	}
	
	/**used to set gradeSent to true to indicate to the student UI that the test isn't available for review yet*/
	public void RetractGrades() {
		gradesSent = false;
	}

}
