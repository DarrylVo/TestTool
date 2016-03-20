package view.QuestionBank;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.Main;
import model.QuestionBank.MultipleChoiceQuestion;
import model.QuestionBank.Question;
import model.TestBank.Course;
import model.QuestionBank.MultipleChoiceAnswer;
public class MCAnswerHandler {



	   @FXML
	   private Button back;

	   @FXML
	   TextArea textA;
	   @FXML
	   RadioButton rbA;
	   @FXML
	   TextArea textB;
	   @FXML
	   RadioButton rbB;
	   @FXML
	   TextArea textC;
	   @FXML
	   RadioButton rbC;
	   @FXML
	   TextArea textD;
	   @FXML
	   RadioButton rbD;

	   @FXML
	   TextArea questionText;

	   @FXML
	   TextField points;

	   @FXML
	   private Button next;

	   @FXML
	   private Button cancel;

	   private Main main;

	   private Course course;

	   private MultipleChoiceQuestion question;

		@FXML
		private void initialize(){
			ToggleGroup group = new ToggleGroup();
			rbA.setToggleGroup(group);
			rbB.setToggleGroup(group);
			rbC.setToggleGroup(group);
			rbD.setToggleGroup(group);

			back.setOnAction((event) -> {
				main.initRootLayout();
				main.showQuestionEditHandlerOverview(); // change to QuestionOverview
		      });

			next.setOnAction((event) -> {
				main.initRootLayout();
				MultipleChoiceAnswer mca = new MultipleChoiceAnswer();
				mca.addPossibleAnswer(textA.getText());
				mca.addPossibleAnswer(textB.getText());
				mca.addPossibleAnswer(textC.getText());
				mca.addPossibleAnswer(textD.getText());
				if(rbA.isSelected()) {
					mca.SetCorrectAnswer(textA.getText());
					mca.SetCorrectIndex(0);
				}
				if(rbB.isSelected()) {
					mca.SetCorrectAnswer(textB.getText());
					mca.SetCorrectIndex(1);
				}
				if(rbC.isSelected()) {
					mca.SetCorrectAnswer(textC.getText());
					mca.SetCorrectIndex(2);
				}
				if(rbD.isSelected()) {
					mca.SetCorrectAnswer(textD.getText());
					mca.SetCorrectIndex(3);
				}
				question.setCourse(course);
				question.setAnswerObject(mca);
				question.setPointValue(Integer.parseInt(points.getText()));
				course.addQuestion(question);
		         main.showQuestionBankOverview(course); // change to Question Bank
		      });

			cancel.setOnAction((event) -> {
				 main.initRootLayout();
				 main.showQuestionBankOverview(course); // change to Question Bank
		      });
		}

	public void setMain(Main main) {
        this.main = main;
	}

	public void setCourse(Course c) {
		this.course = c;
	}
	public void setQuestion(MultipleChoiceQuestion q) {
		this.question = (MultipleChoiceQuestion) q;
		questionText.setText(question.getQuestion());
	}

}
