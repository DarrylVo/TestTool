package view.TakeTest;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Arc;
import javafx.scene.text.Text;
import model.Main;
import model.QuestionBank.CodingQuestion;
import model.QuestionBank.MultipleChoiceQuestion;
import model.QuestionBank.Question;
import model.QuestionBank.WrittenResponseQuestion;
import model.Statistics.Time;
import model.TestBank.Test.State;
import model.TestTaking.Viewing;
import view.TakeTest.TakeTestWrittenController;
import view.TakeTest.TakeTestCodingController;

public class TakeTestMCController {

	@FXML
	Main main;
	@FXML
	private TableView<Question> questionTable;
	@FXML
	private TableColumn<Question, String> questions;
	@FXML
	Arc timerArc;
	@FXML
	private GridPane questionView;
	@FXML
	private final ObservableList<Question> questionList;
	@FXML
	private static Viewing viewTest;
	@FXML
	private Text timeRemaining;
	@FXML
	private RadioButton radioOne;
	@FXML
	private RadioButton radioTwo;
	@FXML
	private RadioButton radioThree;
	@FXML
	private RadioButton radioFour;
	@FXML
	private RadioButton radioFive;
	@FXML
	private RadioButton radioSix;
	@FXML
	private ProgressBar progressBar;

	private RadioButton[] buttons = new RadioButton[6];

	@FXML
	private Button submit;

	@FXML
	private Button nextQuestion;

	public TakeTestMCController() {
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
		RadioButton[] radioButtons = { radioOne, radioTwo, radioThree, radioFour, radioFive, radioSix };
		System.out.println(viewTest.getTest().getState());
		//if (viewTest.getTest().getState() != State.taken && viewTest.getTest().getState() != State.graded) {
			new AnimationTimer() {
				@Override
				public void handle(long now) {
					Time time = viewTest.GetTimer().GetTimeRemaining();
					if (time.GetHour() != 0) {
						if (time.GetSecond() != 0)
							timeRemaining.setText(time.GetHour() + ":" + time.GetMinute() + ":" + time.GetSecond());
						else
							timeRemaining
									.setText(time.GetHour() + ":" + time.GetMinute() + ":" + time.GetSecond() + "0");
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
		//}
		/*else if (viewTest.getTest().getState() == State.graded) {
			displayGradedQuestion();
		}*/
		
		questions.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
		questionTable.setItems(questionList);
		questionTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> handleClickedQuestion(newValue));
		((Text) questionView.getChildren().get(0)).setText(viewTest.getCurrentQuestion().getQuestion());
		for (int i = 0; i < ((MultipleChoiceQuestion) viewTest.getCurrentQuestion()).getPossibleAnswers().size(); i++) {
			radioButtons[i].setVisible(true);
			radioButtons[i].setText(((MultipleChoiceQuestion) viewTest.getCurrentQuestion()).getPossibleAnswers().get(i));
		}
		buttons = radioButtons;
	}

	public void displayGradedQuestion() {
		
	}
	
	@FXML
	private void handleNextQuestion() {
		if (viewTest.getTest().getState() != State.graded && viewTest.getTest().getState() != State.taken) {
			int j = 0;
			while (j < buttons.length && !buttons[j].isSelected() && buttons[j].isVisible())
				j++;
			if (j < buttons.length && buttons[j].isSelected()) {
				((MultipleChoiceQuestion) viewTest.getCurrentQuestion()).SetStudentAnswer(buttons[j].getText());
				viewTest.getCurrentQuestion().setAnswered();
			}
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
		if (viewTest.getTest().getState() != State.graded && viewTest.getTest().getState() != State.taken) {
			int j = 0;
			while (j < buttons.length && !buttons[j].isSelected() && buttons[j].isVisible())
				j++;
			if (j < buttons.length && buttons[j].isSelected()) {
				((MultipleChoiceQuestion) viewTest.getCurrentQuestion()).SetStudentAnswer(buttons[j].getText());
				viewTest.getCurrentQuestion().setAnswered();
			}
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
