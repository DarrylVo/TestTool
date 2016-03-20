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
import model.TestToolUser.Student;

public class TakeTest2MinutesController {

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

	public TakeTest2MinutesController() {
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

		Time time = viewTest.GetTimer().GetTimeRemaining();
		if (time.GetHour() != 0)
			timeRemaining.setText("0" + time.GetHour() + ":" + time.GetMinute() + ":" + time.GetSecond() + "0");
		else
			timeRemaining.setText(time.GetMinute() + ":" + time.GetSecond() + "0");
	}

	@FXML
	private void handleOK() {
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
