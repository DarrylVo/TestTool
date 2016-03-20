package view.TakeTest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Main;
import model.QuestionBank.MultipleChoiceQuestion;
import model.QuestionBank.WrittenResponseQuestion;
import model.TestBank.*;
import model.TestTaking.*;
import model.TestToolUser.Student;

import java.sql.Date;

public class ViewCompletedTestsController {
	private Main main;

	@FXML
	private TableView<Test> testCompleteTable;
	@FXML
	private TableColumn<Test, String> testName;
	@FXML
	private TableColumn<Test, String> dateCompleted;
	@FXML
	private TableColumn<Test, String> className;
	@FXML
	private TableColumn<Test, String> dateAssigned;
	@FXML
	private TableColumn<Test, Integer> grade;
	private Test testToView;
	private Viewing viewTest = new Viewing();
	private static Student student;
	@FXML
	private Button button;

	private ObservableList<Test> testList;

	public static void setStudent(Student student1) {
		student = student1;
	}
	
	public ViewCompletedTestsController() {
	
	}

	public void setMain(Main main) {
		this.main = main;
	}

	@FXML
	private void initialize() {
		Test test = new Test();
		Date date = Date.valueOf("2015-10-6");
		test.setNumQuestion(20);
		test.setName("Midterm 1");
		test.setAssigned(date);
		test.setDue(date);
		Course course = new Course();
		course.setCourseName("CSC 225");
		course.addStudent(student);
		test.setCourse(course);
		MultipleChoiceQuestion question = new MultipleChoiceQuestion();
		question.setQuestion("Here is a test question?");
		question.SetActualAnswer("Yup");
		question.addPossibleAnswer("choice");
		question.addPossibleAnswer("and another");
		question.SetDifficulty(1);
		question.setPointValue(5);
		question.setTopic("Strings");
		WrittenResponseQuestion question2 = new WrittenResponseQuestion();
		question2.setQuestion("Here is yet another test question?");
		question2.SetActualAnswer("No");
		question2.SetDifficulty(4);
		question2.setPointValue(2);
		question2.setTopic("Arrays");
		test.addQuestion(question);
		test.addQuestion(question2);
		test.setStudentGrade(55);
		test.setCompleted(Date.valueOf("2015-05-30"));
		Test test2 = new Test();
		Date date2 = Date.valueOf("2015-6-1");
		Course course2 = new Course();
		course2.setCourseName("CPE 102");
		test2.setCourse(course2);
		course2.addStudent(student);
		test2.setNumQuestion(12);
		test2.setName("Midterm 2");
		test2.setAssigned(date2);
		test2.setDue(date2);
		test2.setStudentGrade(96);
		test2.setCompleted(Date.valueOf("2015-6-1"));
		testList = FXCollections.observableArrayList(test, test2);
		testName.setCellValueFactory(new PropertyValueFactory<Test, String>("name"));
		dateCompleted.setCellValueFactory(new PropertyValueFactory<Test, String>("completed"));
		className.setCellValueFactory(new PropertyValueFactory<Test, String>("courseName"));
		dateAssigned.setCellValueFactory(new PropertyValueFactory<Test, String>("assigned"));
		grade.setCellValueFactory(new PropertyValueFactory<Test, Integer>("studentGrade"));
		testCompleteTable.setItems(testList);
		testCompleteTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> handleClickedTest(newValue));
	}

	@FXML
	private void handleViewTest() {
		if (testToView != null) {
			viewTest.SetTest(testToView);
			TakeTestController.setViewing(viewTest);
			main.initRootLayout();
			main.viewTestCompleted();
		}
	}

	private void handleClickedTest(Test test) {
		testToView = test;
		System.out.println(testToView.getName());
	}

}
