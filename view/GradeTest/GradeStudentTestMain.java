package view.GradeTest;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import model.Main;
import model.GradingTest.Grader;
import model.QuestionBank.CodingAnswer;
import model.QuestionBank.CodingQuestion;
import model.QuestionBank.MultipleChoiceQuestion;
import model.QuestionBank.Question;
import model.QuestionBank.WrittenResponseQuestion;
import model.TestBank.Course;
import model.TestBank.Test;
import model.TestBank.Test.State;

/** Class GradeStudentTestMain is a controller class which connects the Grade Tests Select Student Page to the backend.
 *  Class GradeStudentTestMain has the method showQuestionDetails which finds out the question type and selects the proper ui.
 *  Class GradeStudentTestMain invokes many button clicks and uses the Grader class to get its data.
 */
public class GradeStudentTestMain {

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
    /**Label representing the student name*/
	@FXML
	private Label studentName;
	/**Label representing the student's grade*/
	@FXML
	private Label studentGrade;
	/**comment text area*/
	@FXML 
	private TextArea commentText;
	
	/**table containing the questions on the test*/
	@FXML
    private TableView<Question> questionTable;
	/**column containing Test value*/
    @FXML
    private TableColumn<Question, String> questionColumn;
	
    /**the main class for creating screens*/
	private Main main;
	
	/**Grader class to grade and edit the test scores*/
	private Grader grader;
	
	/**Course class for getting student tests*/
	private Course course;
	
	/**list of tests that have been taken and need to be graded*/
	private Test test;
	
	/**list of Tests to put in the TableView variable*/
	private ObservableList<Test> testList;
	
	/**static variable to indicate what the current selected question number is*/
	private static int questIndex;
	/**String property for the grade text area to use for binding*/
	private SimpleStringProperty gradeProp = new SimpleStringProperty("");
	
	/**method to react to ui events*/
	@FXML
	private void initialize() {
		courseName.setText(testName);
		
		questionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getQuestionNumber())));
		questionTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newQuestion) -> showQuestionDetails(newQuestion, test));
		
		
		studentGrade.textProperty().bind(gradeProp);
		
		gradingComplete.setOnAction((event) -> {
			 gradeComplete();
	      });
		
		gradingHome.setOnAction((event) -> {
			 main.initRootLayout();
	         main.showGradeTestStudentOverview(testName, course, grader);
	      });
		
		//save.setOnAction((event) -> {
			//grader.Save();
	      //});
		
	}
	/** fills in the question details*/
	private void showQuestionDetails(Question newQuestion, Test newValue) {
		int questIndex = questionTable.getSelectionModel().getSelectedIndex();
		if (newQuestion instanceof CodingQuestion) {
			GradeTestStudentCodingController.SetIndeces(test, questIndex);
			main.showGradeTestStudentCodingOverview(testName, course, grader);
		}
		if (newQuestion instanceof MultipleChoiceQuestion) {
			GradeTestStudentMCController.SetIndeces(test, questIndex);
			main.showGradeTestStudentMCOverview(testName, course, grader);
		}
		if (newQuestion instanceof WrittenResponseQuestion) {
			GradeTestStudentWrittenController.SetIndeces(test, questIndex);
			main.showGradeTestStudentWrittenOverview(testName, course, grader);
		}
	}
	
	/**method to set up  the main and course instance variable while also setting up the TableView*/
	public void setMain(Main main, String testname, Course course, Grader grader, Test test) {
		
		this.testName = testname;
		this.main = main;
        this.course = course;
        this.grader = grader;
        this.test = test;
        questionTable.setItems(FXCollections.observableArrayList(test.getQuestions()));
        gradeProp.setValue("Grade: " + grader.GetFinalScore(test.getStudent().getEmail()) + "/" 
				+ test.getTotalPoints() + " " + grader.getPercent(test.getStudent().getEmail()) + "%");
        studentName.setText(test.getStudent().getName());
	}
	
	/**method to mark a Test as having been graded by invoking the SetState method in the Test class*/
	private void gradeComplete() {
		test.setState(State.valueOf("graded"));
	}
	
}
