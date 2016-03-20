package model.Statistics;

import java.util.Collection;

import model.TestBank.Course;
import model.TestBank.Test;
import model.TestBank.TestsTaken;

/**
 * Class CourseStatistics has the purpose of compiling statistics for the overall course.
 * Class CourseStatistics has the methods to get average score, get number of students, get number of tests, and get suggested curve
 * Class Couse Statistics has a data structure that is a collection of Test classes which are used to compile the course statistics.
 */
public class CourseStatistics {

	/** the course that the statistics are generated for */
	private Course course;
	/** static variable to hold average score for all students in a course */ 
	private double avgCourseScore = 0;
	/** number of students in course */ 
	private int numberStudents = 0;
	/** number of tests assigned */ 
	private int numberTests = 0;
	/** collection of graded tests used to accumulate stats */ 
	private Collection<TestsTaken> tests; 
	
	/**
 	* GetCourse returns the course type 
 	*/ 
	public Course GetCourse(){
		return course;
	}

	/**
 	* SetCourse sets the course instance variable
 	*/ 
	public void SetCourse(Course course){
		this.course = course;
	}
	
	/**
 	* GetAvgCourseScore returns the average grade for the course out of 100 as a double
 	*/ 
	public double GetAvgCourseScore(){
		int sum = 0;
		int total = 0;
		TestsTaken[] arr = tests.toArray(new TestsTaken[0]);
		for (int i=0; i < arr.length; i++) {
			Test[] testList = arr[i].getTests().toArray(new Test[0]);
			for (int k=0; k < arr.length; k++) {
				sum += testList[k].getStudentGrade();
				total += testList[k].getTotalPoints();
			}
		}
		avgCourseScore = (double)(sum)/total;
		return avgCourseScore;
	}

	/**
 	* addTest takes in a Test and adds it to the Collection of tests
 	*/ 
	public void AddTestsTaken(TestsTaken taken){
		tests.add(taken);
		this.IncrementNumberTests();
		numberStudents = taken.getTests().size();
	}
	
	/**
 	* addTest takes in a Collection of Tests and adds them to the Collection of tests
 	*/ 
	public void AddTest(Collection<TestsTaken> taken){
		tests.addAll(taken);
		numberTests += taken.size();
	}

	/**
 	* GetNumberStudents returns the number of students in the course 
 	*/ 
	public int GetNumberStudents(){
		return numberStudents;
	}
	
	/**
 	* GetNumberStudents returns the number of students in the course 
 	*/ 
	public void SetNumberStudents(int num){
		numberStudents = num;
	}
	
	/**
 	* IncrementNumberStudents adds one to the number of students in the course
 	*/ 
	public void IncrementNumberStudents() {
		numberStudents++;
	}
	
	/**
 	* GetNumberTests returns the number of tests in the course
 	*/ 
	public int GetNumberTests(){
		return numberTests;
	}
	
	/**
 	* IncrementNumberTests adds one to the number of students in the course
 	*/ 
	public void IncrementNumberTests(){
		numberTests++;
	}
	
	/**
 	* GetSuggestedCurve returns the suggest amount of points to add to each student's grade for the course
 	*/ 
	public double GetSuggestedCurve(){
		System.out.println("course stats get suggested curve");
		return -1;
	}
}
