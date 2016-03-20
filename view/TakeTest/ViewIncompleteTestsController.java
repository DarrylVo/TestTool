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
import model.QuestionBank.Question;
import model.QuestionBank.WrittenResponseQuestion;
import model.Statistics.Time;
import model.TestBank.*;
import model.TestTaking.*;
import model.TestToolUser.Student;

import java.sql.Date;
import java.util.ArrayList;

public class ViewIncompleteTestsController {
	private Main main;

	@FXML
	private TableView<Test> testTable;
	@FXML
	private TableColumn<Test, String> testName;
	@FXML
	private TableColumn<Test, Integer> numberOfQuestions;
	@FXML
	private TableColumn<Test, String> className;
	@FXML
	private TableColumn<Test, String> dateAssigned;
	@FXML
	private TableColumn<Test, String> dateDue;
	@FXML
	private Button button;

	private Test testToTake;
	
	private Viewing viewTest = new Viewing();
	
	private static Student student;
	
	private final ObservableList<Test> testList;

	public ViewIncompleteTestsController() {
		ArrayList<Test> courseTests = new ArrayList<>();
		for (int i = 0; i < student.getTests().size(); i++) {
			if (student.getTests().get(i).getCourse().equals(student.getCurrentCourse())) {
				courseTests.add(student.getTests().get(i));
			}
		}
		testList = FXCollections.observableArrayList(courseTests);
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public static void setStudent(Student student1) { 
		student = student1;
	}
	
	@FXML
	private void initialize() {
		testName.setCellValueFactory(new PropertyValueFactory<Test, String>("name"));
		numberOfQuestions.setCellValueFactory(new PropertyValueFactory<Test, Integer>("numQuestion"));
		className.setCellValueFactory(new PropertyValueFactory<Test, String>("courseName"));
		dateAssigned.setCellValueFactory(new PropertyValueFactory<Test, String>("assigned"));
		dateDue.setCellValueFactory(new PropertyValueFactory<Test, String>("due"));

		testTable.setItems(testList);

		testTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> handleClickedTest(newValue));
	}

	@FXML
	private void handleTakeTest() {
		if (testToTake != null) {
			Timer timer = new Timer();
			Time time = new Time();
			Time time2 = new Time();
			time2.setTime(0, 15, 0);
			time.setTime(0, 15, 0);
			viewTest.setTimer(timer);
			viewTest.GetTimer().SetTimeRemaining(time);
			viewTest.GetTimer().setTimeBegin(time2);
			viewTest.SetTest(testToTake);
			TakeTestController.setViewing(viewTest);
			main.initRootLayout();
			main.takeTestOverview();
		}
	}

	private void handleClickedTest(Test test) {
		testToTake = test;
	}
}
