package model.GradingTest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.GradingTest.Grader;
import model.Statistics.TestStatistics;
import model.Statistics.Time;
import model.TestBank.TestsTaken;
import model.TestToolUser.Student;

public class GradingTest {
	
	public static void main(String[] args) {
		GradingTest test = new GradingTest();
		test.testGetPercent();
		test.testPercentGraded();
		test.testTestStatsUpdateDuration();
		
		test.testGetPercent();
		test.testPercentGraded();
		test.testTestStatsUpdateDuration();

	}
	
	
	private TestsTaken taken = new TestsTaken();
	private Grader grader = new Grader();
	
	public void SetUpTests(int grade, int numTests) {
		taken.getTests().clear();
		for (int i=0; i < numTests; i++) {
			model.TestBank.Test test = new model.TestBank.Test();
			Student student = new Student();
			student.setEmail("student" + i);
			test.SetCourseName("test");
			test.setTotalPoints(100);
			test.setStudentGrade(i+grade);
			test.setStudent(student);
			test.setDuration(new Time(i, i+10, 0));
			taken.addTest(test);
		}
		grader.SetTestTaken(taken);
	}
	
	
	
	@Test
	public void testGetPercent() {
		int grade = 71;
		int numTests = 30;
		SetUpTests(grade, numTests);
		for (int i=0; i < numTests; i++) {
			assertEquals(i + grade, grader.GetFinalScore("student" + i));
		}
		SetUpTests(grade=50, numTests=1);
		for (int i=0; i < numTests; i++) {
			assertEquals(i + grade, grader.GetFinalScore("student" + i));
		}
		SetUpTests(grade=0, numTests=10);
		for (int i=0; i < numTests; i++) {
			assertEquals(i + grade, grader.GetFinalScore("student" + i));
		}
		SetUpTests(grade=0, numTests=0);
		for (int i=0; i < numTests; i++) {
			assertEquals(i + grade, grader.GetFinalScore("student" + i));
		}
	}
	
	@Test
	public void testPercentGraded() {
		int grade = 71;
		int numTests = 30;
		SetUpTests(grade, numTests);
		assertEquals(0.0, grader.getPercentGraded(), 0.0000001);
		for (int i=0; i < numTests; i++) {
			grader.GradeComplete("student" + i);
			assertEquals((i + 1)/((double)numTests), grader.getPercentGraded(), 0.0000001);
		}
		SetUpTests(grade = 50, numTests = 10);
		assertEquals(0.0, grader.getPercentGraded(), 0.0000001);
		for (int i=0; i < numTests; i++) {
			grader.GradeComplete("student" + i);
			assertEquals((i + 1)/((double)numTests), grader.getPercentGraded(), 0.0000001);
		}
		SetUpTests(grade = 0, numTests = 1);
		assertEquals(0.0, grader.getPercentGraded(), 0.0000001);
		for (int i=0; i < numTests; i++) {
			grader.GradeComplete("student" + i);
			assertEquals((i + 1)/((double)numTests), grader.getPercentGraded(), 0.0000001);
		}
		SetUpTests(grade = 0, numTests = 0);
		assertEquals(0.0, grader.getPercentGraded(), 0.0000001);
		for (int i=0; i < numTests; i++) {
			grader.GradeComplete("student" + i);
			assertEquals((i + 1)/((double)numTests), grader.getPercentGraded(), 0.0000001);
		}
	}
	
	@Test
	public void testTestStatsUpdateDuration() {
		TestStatistics stats = new TestStatistics();
		int grade = 71;
		int numTests = 10;
		SetUpTests(grade, numTests);
		stats.updateScoreDuration(taken.getTests());
		assertEquals(4, stats.getAvgDuration().GetHour());
		assertEquals(44, stats.getAvgDuration().GetMinute());
		assertEquals(30, stats.getAvgDuration().GetSecond());
		
		stats = new TestStatistics();
		SetUpTests(grade=50, numTests=0);
		stats.updateScoreDuration(taken.getTests());
		assertEquals(0, stats.getAvgDuration().GetHour());
		assertEquals(0, stats.getAvgDuration().GetMinute());
		assertEquals(0, stats.getAvgDuration().GetSecond());
		
		stats = new TestStatistics();
		SetUpTests(grade=30, numTests=1);
		stats.updateScoreDuration(taken.getTests());
		assertEquals(0, stats.getAvgDuration().GetHour());
		assertEquals(10, stats.getAvgDuration().GetMinute());
		assertEquals(0, stats.getAvgDuration().GetSecond());
		
		stats = new TestStatistics();
		SetUpTests(grade=30, numTests=1);
		stats.updateScoreDuration(taken.getTests());
		assertEquals(3, stats.getAvgDuration().GetHour());
		assertEquals(13, stats.getAvgDuration().GetMinute());
		assertEquals(0, stats.getAvgDuration().GetSecond());
	}

}
