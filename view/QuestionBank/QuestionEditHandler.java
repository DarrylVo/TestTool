package view.QuestionBank;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import model.Main;
import model.GradingTest.Grader;
import model.QuestionBank.Question;
import model.TestBank.Test;


public class QuestionEditHandler {

	@FXML
	private RadioButton multipleChoice;

	@FXML
	private RadioButton trueFalse;

	@FXML
	private RadioButton matching;

	@FXML
	private RadioButton fillIn;

	@FXML
	private RadioButton shortAnswer;

	@FXML
	private RadioButton longAnswer;

	@FXML
	private RadioButton graphic;

	@FXML
	private RadioButton coding;

	@FXML
	private Button skipToReview;

	@FXML
	private Button next;

	@FXML
	private Button cancel;

	@FXML
	private ComboBox courseName;

	@FXML
	private ComboBox topic;

	@FXML
	private TextField title;

	@FXML
	private TextField approxTime;

	@FXML
	private TextArea instructions;

	@FXML
	private TextArea questionText;

	@FXML
	private Slider difficulty;

	private Main main;

	private Question question = new Question();

	@FXML
	private void initialize(){
		skipToReview.setOnAction((event) -> {
			question.verifyQuestion();
			main.initRootLayout();
			main.showGradeTestOverview(); // change to MCAnswerOverview
	      });

		next.setOnAction((event) -> {
			question.verifyQuestion();
			main.initRootLayout();
			main.showMCAnswerHandlerOverview(); // change to MCAnswerOverview
	      });

		cancel.setOnAction((event) -> {
			 main.initRootLayout();
	         main.showGradeTestOverview(); // change to Question Bank
	      });
	}

	public void setMain(Main main) {
        this.main = main;
	}

}
