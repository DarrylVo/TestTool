package view.TakeTest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Main;
import model.QuestionBank.Question;
import model.Statistics.Time;
import model.TestBank.TestsTaken;
import model.TestBank.Test.State;
import model.TestTaking.Viewing;
import model.TestToolUser.Student;

public class TakeTestTimeUpController {

	private Main main;
	@FXML
	private TableView<Question> questionTable;
	@FXML
	private TableColumn<Question, String> questions;
	@FXML
	private Button takeTest;
	@FXML
	private Button cancelTest;
	@FXML
	private Text timeRemaining;
	@FXML
	private Button viewCompleted;
	@FXML
	private Button home;

	private static Viewing viewTest;
	
	private static Student student;

	private final ObservableList<Question> questionList;

	public TakeTestTimeUpController() {
		questionList = FXCollections.observableArrayList(viewTest.getTest().getQuestions());
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public static void setViewing(Viewing viewing) {
		viewTest = viewing;
	}
	
	public static void setStudent(Student student1) { 
		student = student1;
	}

	@FXML
	private void initialize() {
		questions.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
		questionTable.setItems(questionList);

		viewTest.GetTimer().Stop();
		viewTest.getTest().setState(State.taken);

		Time time = viewTest.GetTimeRemaining();
		if (time.GetHour() != 0)
			timeRemaining.setText("0" + time.GetHour() + ":" + time.GetMinute() + ":" + time.GetSecond() + "0");
		else
			timeRemaining.setText(time.GetMinute() + ":" + time.GetSecond() + "0");
		
		TestsTaken testsTaken = new TestsTaken();
		testsTaken.addTest(viewTest.getTest());
		viewTest.getTest().getCourse().addTestTaken(testsTaken);
	}

	@FXML
	private void handleViewCompleted() {
		main.viewCompletedTests();
	}

	@FXML
	private void handleHome() {
		main.showStudentLandingPageOverview();
	}
}
