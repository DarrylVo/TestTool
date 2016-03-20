package model.Statistics;

import java.util.Collection;

import model.TestBank.Course;
import model.TestBank.Test;

/**
 * Class StudentStatistics generates statistics for the individual student using the CourseStatistics and the TestStatistics classes.
 * Class StudentStatistics has methods to generate test stats and course stats for a given course or test.
 * Class StudentStatistics has two data structures which are collections of Course Statistics and TestStatistics classes.
 */
public class StudentStatistics implements java.io.Serializable {

	/** a collection of course statistics for all the courses the student is in */
	private Collection<CourseStatistics> courseStats;
	/** a collection of all tests taken statistics for an individual test */
	private Collection<TestStatistics> testStats;
	
	/**
 	* addTest adds a Test to the collection of tests
 	*/ 
	public void AddCourseStats(CourseStatistics stats){
		courseStats.add(stats);
	}
	
	/**
 	* addTest adds a Test to the collection of tests
 	*/ 
	public void AddTestStats(TestStatistics stats){
		testStats.add(stats);
	}
	
	/**
 	* generateTestStats creates the statistics for the specified test and returns it as a TestStatistics variable
 	*/ 
	public TestStatistics GenerateTestStats(String testname){
		System.out.println("generate test stats in student stats");
		return null;
	}
	
	/**
 	* generateCourseStats creates the statistics for the specified course and returns it as a CourseStatistics variable
 	*/ 
	public CourseStatistics GenerateCourseStats(String coursename){
		System.out.println("generate course stats in student stats");
		return null;
	}

}
