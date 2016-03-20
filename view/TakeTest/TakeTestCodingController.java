package view.TakeTest;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Arc;
import javafx.scene.text.Text;
import model.Main;
import model.QuestionBank.CodingQuestion;
import model.QuestionBank.MultipleChoiceQuestion;
import model.QuestionBank.Question;
import model.QuestionBank.WrittenResponseQuestion;
import model.Statistics.Time;
import model.TestTaking.Viewing;

public class TakeTestCodingController {

	@FXML
	Main main;
	@FXML
	private TableView<Question> questionTable;
	@FXML
	private TableColumn<Question, String> questions;
	@FXML
	private final ObservableList<Question> questionList;
	@FXML
	private static Viewing viewTest;
	@FXML
	private Text timeRemaining;
	@FXML
	private TextArea codingAnswer;
	@FXML
	private Button nextQuestion;
	@FXML
	private Button submit;
	@FXML
	Arc timerArc;
	@FXML
	private ProgressBar progressBar;

	public TakeTestCodingController() {
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
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				Time time = viewTest.GetTimer().GetTimeRemaining();
				if (time.GetHour() != 0) {
					if (time.GetSecond() != 0)
						timeRemaining.setText(time.GetHour() + ":" + time.GetMinute() + ":" + time.GetSecond());
					else
						timeRemaining.setText(time.GetHour() + ":" + time.GetMinute() + ":" + time.GetSecond() + "0");
				} else {
					if (time.GetSecond() != 0)
						timeRemaining.setText(time.GetMinute() + ":" + time.GetSecond());
					else
						timeRemaining.setText(time.GetMinute() + ":" + time.GetSecond() + "0");
				}
				timerArc.setLength((viewTest.GetTimeRemaining().getTotalSeconds() * 360)
						/ viewTest.GetTimer().getTimeBegin().getTotalSeconds());
				progressBar.setProgress(
						1 - ((double) (viewTest.GetQuestionsRemaining()) / (double) viewTest.getNumQuestion()));
				if (viewTest.GetTimeRemaining().getTotalSeconds() == 120) {
					TakeTest2MinutesController.setViewing(viewTest);
					main.takeTest2Minutes();
				}
			}
		}.start();

		questions.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
		questionTable.setItems(questionList);

		questionTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> handleClickedQuestion(newValue));
	}

	@FXML
	private void handleNextQuestion() {
		if (codingAnswer.getText() != null && !codingAnswer.getText().isEmpty()) {
			((CodingQuestion) viewTest.getCurrentQuestion()).SetStudentAnswer(codingAnswer.getText());
			viewTest.getCurrentQuestion().setAnswered();
		}
		viewTest.setCurrentQuestion(viewTest.getTest().getQuestions()
				.get(viewTest.getTest().getQuestions().indexOf(viewTest.getCurrentQuestion()) + 1));
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

	private void handleClickedQuestion(Question question) {
		if (codingAnswer.getText() != null && !codingAnswer.getText().isEmpty()) {
			((CodingQuestion) viewTest.getCurrentQuestion()).SetStudentAnswer(codingAnswer.getText());
			viewTest.getCurrentQuestion().setAnswered();
		}
		viewTest.setCurrentQuestion(question);
		if (question instanceof MultipleChoiceQuestion) {
			TakeTestMCController.setViewing(viewTest);
			main.takeTestMC();
		} else if (question instanceof WrittenResponseQuestion) {
			TakeTestWrittenController.setViewing(viewTest);
			main.takeTestWritten();
		} else if (question instanceof CodingQuestion) {
			TakeTestCodingController.setViewing(viewTest);
			main.takeTestCoding();
		}
	}

	@FXML
	private void handleSubmit() {
		if (viewTest.GetQuestionsRemaining() > 0) {
			TakeTestSubmitConfirmController.setViewing(viewTest);
			main.takeTestSubmitConfirm();
		} else {
			TakeTestSubmitController.setViewing(viewTest);
			main.takeTestSubmit();
		}
	}
}
