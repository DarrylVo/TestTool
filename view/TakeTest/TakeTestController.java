package view.TakeTest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.TestTaking.*;
import model.Main;
import model.QuestionBank.*;
import model.Statistics.Time;

public class TakeTestController {

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

	private static Viewing viewTest;

	private final ObservableList<Question> questionList;

	public TakeTestController() {
		questionList = FXCollections.observableArrayList(viewTest.getTest().getQuestions());
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public static void setViewing(Viewing viewing) {
		viewTest = viewing;
	}

	@FXML
	private void initialize() {
		questions.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
		questionTable.setItems(questionList);
		takeTest.setOnAction((event) -> {
			handleTestStart();
		});
		cancelTest.setOnAction((event) -> {
			handleCancel();
		});
		Time time = viewTest.GetTimer().GetTimeRemaining();
		if (time.GetHour() != 0)
			timeRemaining.setText("0" + time.GetHour() + ":" + time.GetMinute() + ":" + time.GetSecond() + "0");
		else
			timeRemaining.setText(time.GetMinute() + ":" + time.GetSecond() + "0");
		questionTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> handleTest(newValue));
	}

	private void handleTest(Question question) {

	}

	@FXML
	private void handleTestStart() {
		viewTest.setCurrentQuestion(viewTest.getTest().getQuestions().get(0));
		viewTest.startTime();
		if (viewTest.getTest().getQuestions().get(0) instanceof MultipleChoiceQuestion) {
			TakeTestMCController.setViewing(viewTest);
			main.takeTestMC();
		} else if (viewTest.getTest().getQuestions().get(0) instanceof WrittenResponseQuestion) {
			TakeTestWrittenController.setViewing(viewTest);
			main.takeTestWritten();
		} else if (viewTest.getTest().getQuestions().get(0) instanceof CodingQuestion) {
			TakeTestCodingController.setViewing(viewTest);
			main.takeTestCoding();
		}
	}

	@FXML
	private void handleCancel() {
		viewTest.SetTest(null);
		main.viewAssignedTests();
	}
}
