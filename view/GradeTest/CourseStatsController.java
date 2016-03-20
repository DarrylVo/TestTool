package view.GradeTest;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Main;
import model.GradingTest.Grader;
import model.Statistics.CourseStatistics;
import model.TestBank.Course;
import model.TestBank.Test;

public class CourseStatsController {

	
	
	public Main main;
	
	public Course course;
	
	public CourseStatistics stats;
	
	@FXML
	private Label numStudents;
	@FXML
	private Label numTests;
	@FXML
	private Label avgGrade;
	@FXML
	private Label suggestedCurve;
	
	@FXML
	private void initialize() {
		numStudents.setText(Integer.toString(stats.GetNumberTests()));
		numTests.setText(Integer.toString(stats.GetNumberStudents()));
		avgGrade.setText(Double.toString(stats.GetAvgCourseScore()));
		suggestedCurve.setText(Double.toString(stats.GetSuggestedCurve()));
	}
	
	public void setMain(Main main, Course course) {
		this.main = main;
		this.course = course;
		stats.AddTest(course.getAllTestsTaken());
		stats.SetNumberStudents(course.GetStudentList().size());
		
	}
	
}
