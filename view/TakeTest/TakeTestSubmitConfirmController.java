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
import model.QuestionBank.CodingQuestion;
import model.QuestionBank.MultipleChoiceQuestion;
import model.QuestionBank.Question;
import model.QuestionBank.WrittenResponseQuestion;
import model.Statistics.Time;
import model.TestTaking.Viewing;

public class TakeTestSubmitConfirmController {

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
	private Button ok;

	private static Viewing viewTest;

	private final ObservableList<Question> questionList;

	public TakeTestSubmitConfirmController() {
		questionList = FXCollections.observableArrayList(viewTest.getTest().getQuestions());
		System.out.println("plz");
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public static void setViewing(Viewing viewing) {
		viewTest = viewing;
	}

	@FXML
	private void initialize() {
		System.out.println("hello?");
		questions.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
		questionTable.setItems(questionList);

		Time time = viewTest.GetTimeRemaining();
		if (time.GetHour() != 0)
			timeRemaining.setText("0" + time.GetHour() + ":" + time.GetMinute() + ":" + time.GetSecond() + "0");
		else
			timeRemaining.setText(time.GetMinute() + ":" + time.GetSecond() + "0");
		System.out.println("initialize over");
	}

	@FXML
	private void handleOK() {
		System.out.println("guess this is good");
		TakeTestSubmitController.setViewing(viewTest);
		System.out.println("main: " + main);
		main.takeTestSubmit();
		System.out.println("uhh");
	}

	@FXML
	private void handleCancel() {
		if (viewTest.getCurrentQuestion() instanceof MultipleChoiceQuestion) {
			TakeTestMCController.setViewing(viewTest);
			main.takeTestMC();
		} else if (viewTest.getCurrentQuestion() instanceof WrittenResponseQuestion) {
			TakeTestWrittenController.setViewing(viewTest);
			main.takeTestWritten();
		} else if (viewTest.getCurrentQuestion() instanceof CodingQuestion) {
			TakeTestCodingController.setViewing(viewTest);
			main.takeTestCoding();
		}
	}
}
