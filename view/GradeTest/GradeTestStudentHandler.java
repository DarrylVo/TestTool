package view.GradeTest;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.Main;
import model.GradingTest.Grader;
import model.QuestionBank.CodingQuestion;
import model.QuestionBank.MultipleChoiceQuestion;
import model.QuestionBank.Question;
import model.QuestionBank.WrittenResponseQuestion;
import model.TestBank.Comment;
import model.TestBank.Course;
import model.TestBank.Test;
import model.TestBank.TestsTaken;
import model.TestBank.Test.State;

/** Class GradeTestStudentHandler is a controller class which connects the Grade Tests Landing Page to the backend.
 *  Class GradeTestStudentHandler has the methods show test details and grade complete.
 *  Class GradeTestStudentHandler invokes many button clicks and uses the Test class to get its data.
 */
public class GradeTestStudentHandler {

	/**Button to submit grades to students*/
	@FXML
	private Button submitGrades;
	/**Button to save changes made to a grade*/
	@FXML
	private Button viewStudent;
	/**Button to mark student's test as graded*/
	@FXML
	private Button gradingComplete;
	/**Button to take the admin back to the Grade Test Landing Page*/
	@FXML
	private Button gradingHome;
	/**Button to add a comment*/
	@FXML
	private Button comment;
	/**Label representing the coursename*/
	@FXML
	private Label courseName;
	/**String representing the testname*/
	private String testName;
	/**TableView of all the tests to be graded*/
	@FXML
    private TableView<Test> studentTable;
	/**column containing Test value*/
    @FXML
    private TableColumn<Test, String> studentNameColumn;
    /**column containing Test value*/
    @FXML
    private TableColumn<Test, String> gradeColumn;
    /**column containing Test value*/
    @FXML
    private TableColumn<Test, String> gradeCompleteColumn;


	
    /**the main class for creating screens*/
	private Main main;
	
	/**Grader class to grade and edit the test scores*/
	private Grader grader;
	
	/**Course class for getting student tests*/
	private Course course;
	
	/**list of tests that have been taken and need to be graded*/
	private TestsTaken taken;
	
	/**list of Tests to put in the TableView variable*/
	private ObservableList<Test> testList;
	
	/**String property for comments to use for binding to comment text area*/
	private SimpleStringProperty commentProp = new SimpleStringProperty("");
	/**String property for the grade text area to use for binding*/
	private SimpleStringProperty gradeProp = new SimpleStringProperty("");
	
	/**method to react to ui events*/
	@FXML
	private void initialize() {
		
		
		studentNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudent().getName()));
		gradeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(Double.toString(grader.getPercent(cellData.getValue().getStudent().getEmail()))));
		gradeCompleteColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getState().toString()));

		
		submitGrades.setOnAction((event) -> {
			sendGrades();
	      });
		
		viewStudent.setOnAction((event) -> {
			showGrades();
	      });
		
		gradingComplete.setOnAction((event) -> {
			 gradeComplete();
	      });
		
		gradingHome.setOnAction((event) -> {
			 main.initRootLayout();
	         main.showGradeTestOverview(course);
	      });
		
		
	}
	

	/**method which reacts to viewGrades button being pressed by going to the grade students screen*/
	private void showGrades() {
		int selectedIndex = studentTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex < 0) {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Test Selected");
	        alert.setContentText("Please select a test in the table.");

	        alert.showAndWait();
		}
		else {
			Test temp = studentTable.getItems().get(selectedIndex);
			main.initRootLayout();
			main.showGradeStudentTestMainOverview(testName, course, grader, temp);
			
		}
	}
	
	
	/**method to set up  the main and course instance variable while also setting up the TableView*/
	public void setMain(Main main, String testname, Course course, Grader grader) {
		
		this.testName = testname;
		this.main = main;
        this.course = course;
        this.grader = grader;
        courseName.setText(testName);
        
        // get tests taken with name testname
        taken = this.course.getTestsTaken(testName);
        // get array of tests to go through and add each test to the Table View
        testList = FXCollections.observableArrayList(taken.getTests());
        studentTable.setItems(testList);
        
	}
	
	/**method to mark a Test as having been graded by invoking the SetState method in the Test class*/
	private void gradeComplete() {
		int selectedIndex = studentTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex < 0) {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Test Selected");
	        alert.setContentText("Please select a test in the table.");

	        alert.showAndWait();
		}
		else {
			testList.get(selectedIndex).setState(State.valueOf("graded"));
		}
	}
	
	/**method which reacts to sendGrades button being pressed by invoking the SubmitGrade method in the Grader class*/
	private void sendGrades() {
		if (!grader.GetPercentGradedString().equals("100.0%")) {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("Can't Submit");
	        alert.setHeaderText("Tests Not Fully Graded");
	        alert.setContentText("Please complete grading for this test before submitting.");

	        alert.showAndWait();
		}
		else {
			grader.SubmitGrade();
		}
	}
	
}
