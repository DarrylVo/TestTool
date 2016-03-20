package view.GradeTest;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import model.Main;
import model.GradingTest.Grader;
import model.QuestionBank.*;
import model.TestBank.Course;
import model.TestBank.Test;
import model.TestBank.TestsTaken;
import model.TestBank.Test.State;

/** Class GradeTestStudentMCController is a controller class which allows the user to edit individual grades for multiple choice questions
 *  Class GradeTestStudentMCController has the methods SetUpTest, ShowQuestionValue, and changeGradeValue.
 *  Class GradeTestStudentMCController invokes many button clicks and uses the Grader, Test, and Question class to get its data.
 */
public class GradeTestStudentMCController {
	/**Button to submit grades to students*/
	@FXML
	private Button submitGrades;
	/**Button to save changes made to a grade*/
	@FXML
	private Button save;
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
	@FXML
	private Label studentName;
	/**Label representing the student's grade*/
	@FXML
	private Label studentGrade;
	private Label questionNumber;
	/**Text area holding the question text*/
	@FXML
	private TextArea questionText;
	/**text area holding the student answer*/
	@FXML 
	private TextArea studentAnswer;
	/**text area holding the correct answer*/
	@FXML 
	private TextArea correctAnswer;
	/**combo box holding the possible grades to give*/
	@FXML 
	private ComboBox<String> editGrade = new ComboBox<String>();
	/**text area for the teacher comments*/
	@FXML 
	private TextArea commentText;
	/**first possible multiple choice answer*/
	@FXML
	private RadioButton firstMC;
	/**second possible multiple choice answer*/
	@FXML
	private RadioButton secondMC;
	/**third possible multiple choice answer*/
	@FXML
	private RadioButton thirdMC;
	/**fourth possible multiple choice answer*/
	@FXML
	private RadioButton fourthMC;
	/**fifth possible multiple choice answer*/
	@FXML
	private RadioButton fifthMC;
	/**correct answer*/
	@FXML
	private RadioButton correctMC;
	
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
	private static Test test;
	
	/**list of Tests to put in the TableView variable*/
	private ObservableList<Test> testList;
	
	/**String property for comments to use for binding to comment text area*/
	private SimpleStringProperty commentProp = new SimpleStringProperty("");
	/**String property for the grade text area to use for binding*/
	private SimpleStringProperty gradeProp = new SimpleStringProperty("");
	
	/**static variable to indicate what the current selected question number is*/
	private static int questIndex;
	
	/**method to react to ui events*/
	@FXML
	private void initialize() {
		courseName.setText(testName);
		
		submitGrades.setOnAction((event) -> {
			sendGrades();
	      });
		
		
		questionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getQuestionNumber())));
		questionTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newQuestion) -> showQuestionDetails(newQuestion));
		
		
		studentGrade.textProperty().bind(gradeProp);
		
		
		gradingComplete.setOnAction((event) -> {
			 gradeComplete();
	      });
		
		gradingHome.setOnAction((event) -> {
			 main.initRootLayout();
	         main.showGradeTestStudentOverview(testName, course, grader);
	      });
		
		
	}
	
	/**sets up the ui for the question and gets the question answer*/
	private void SetUpTest() {
		MultipleChoiceQuestion newQuestion = (MultipleChoiceQuestion) questionTable.getItems().get(questIndex);
		
		questionNumber.setText(Integer.toString(newQuestion.getQuestionNumber()));
		questionText.setText(newQuestion.getQuestion());
		if (newQuestion.getMessage() == null)
			newQuestion.setComment("");
		commentProp.set(newQuestion.getMessage().GetMessage());
		comment.setOnAction((event) -> {
			main.showAddQuestionComment(newQuestion, commentProp);
			
	      });
		commentText.textProperty().bind(commentProp);
		
		SetMCAnswers(newQuestion);
		
		editGrade.getItems().clear();
		for (int i=0; i <= newQuestion.getPointValue(); i++) {
			editGrade.getItems().add(i + "/" + newQuestion.getPointValue());
		}
		editGrade.getSelectionModel().select(newQuestion.getPointsEarned());
		editGrade.getSelectionModel().selectedItemProperty().addListener(
			(observable, oldValue, newStringValue) -> changeGradeValue(newStringValue, test, newQuestion.getQuestionNumber()));
	
	
	
	}
	
	/** fills in the question details*/
	private void showQuestionDetails(Question newQuestion) {
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
	
	/**sets up the multiple choice possible answers*/
	private void SetMCAnswers(MultipleChoiceQuestion question) {
		MultipleChoiceAnswer mc = question.getAnswer();
		String[] possibleAnswers = mc.getPossibleAnswers().toArray(new String[0]);
		
		if (possibleAnswers.length > 0) {
			if (mc.GetStudentIndex() == 0) 
				firstMC.setSelected(true);
			firstMC.setText(possibleAnswers[0]);
		}
		if (possibleAnswers.length > 1) {
			if (mc.GetStudentIndex() == 1)
				secondMC.setSelected(true);
			secondMC.setText(possibleAnswers[1]);
		}
		if (possibleAnswers.length > 2) {
			if (mc.GetStudentIndex() == 2)
				thirdMC.setSelected(true);
			thirdMC.setText(possibleAnswers[2]);
		}
		if (possibleAnswers.length > 3) {
			if (mc.GetStudentIndex() == 3)
				fourthMC.setSelected(true);
			fourthMC.setText(possibleAnswers[3]);
		}
		if (possibleAnswers.length > 4) {
			if (mc.GetStudentIndex() == 4)
				fifthMC.setSelected(true);
			fifthMC.setText(possibleAnswers[4]);
		}
		correctMC.setText(mc.GetCorrectAnswer());
		
	}

	/**edits the student's question grade to the value in the combo box*/
	private void changeGradeValue(String newValue, Test test, int number) {
		int newGrade = Integer.valueOf(newValue.split("/")[0]);
		grader.EditQuestionGrade(test.getStudent().getEmail(), newGrade, number);
		gradeProp.setValue("Grade: " + grader.GetFinalScore(test.getStudent().getEmail()) + "/" 
				+ test.getTotalPoints() + " " + grader.getPercent(test.getStudent().getEmail()) + "%");
	}
	
	/**method to set up  the main and course instance variable while also setting up the TableView*/
	public void setMain(Main main, String testname, Course course, Grader grader) {
		
		this.testName = testname;
		this.main = main;
        this.course = course;
        this.grader = grader;
        questionTable.setItems(FXCollections.observableArrayList(test.getQuestions()));
		SetUpTest();
		gradeProp.setValue("Grade: " + grader.GetFinalScore(test.getStudent().getEmail()) + "/" 
				+ test.getTotalPoints() + " " + grader.getPercent(test.getStudent().getEmail()) + "%");
		studentName.setText(test.getStudent().getName());
	}
	
	public static void SetIndeces(Test t, int questNum) {
		questIndex = questNum;
		test = t;
	}
	
	/**method to mark a Test as having been graded by invoking the SetState method in the Test class*/
	private void gradeComplete() {
		test.setState(State.valueOf("graded"));
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
