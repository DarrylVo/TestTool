package view.QuestionBank;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.Main;
import model.QuestionBank.WrittenResponseQuestion;
import model.QuestionBank.MultipleChoiceAnswer;
import model.QuestionBank.Question;
import model.TestBank.Course;
import model.QuestionBank.ShortLongAnswer;

public class ResponseAnswerOverviewController {

    @FXML
    private Button back;
    @FXML
    private Button next;
    @FXML
    private Button cancel;
    @FXML
    private TextField type;
    @FXML
    private TextArea questionText;
    @FXML
    private TextArea keyword;
    @FXML
    private TextField points;
    @FXML
    private RadioButton partial;
    @FXML
    private RadioButton full;

   private Main main;

   private Course course;

   private WrittenResponseQuestion question;

   public void setMain(Main main) {
       this.main = main;
   }

   @FXML
   private void initialize(){

	   ToggleGroup group = new ToggleGroup();
	   partial.setToggleGroup(group);
	   full.setToggleGroup(group);

		back.setOnAction((event) -> {
			main.initRootLayout();
			main.showQuestionEditHandlerOverview(); // change to QuestionOverview
	      });

		next.setOnAction((event) -> {
			main.initRootLayout();
			ShortLongAnswer sla = new ShortLongAnswer();
			String holder = keyword.getText();
			String[] subStr = holder.split(" ");
			for(String sub : subStr)
			{
				sla.SetCorrectAnswer(sub);
			}

			question.setCourse(course);
			question.setAnswerObject(sla);
			question.setPointValue(Integer.parseInt(points.getText()));
			course.addQuestion(question);
	         main.showQuestionBankOverview(course); // change to Question Bank
	      });



	   cancel.setOnAction((event) -> {
			 main.initRootLayout();
	         main.showQuestionBankOverview(course); // change to Question Bank
	      });
   }

	public void setCourse(Course c) {
		this.course = c;
	}
	public void setQuestion(WrittenResponseQuestion q) {
		this.question = (WrittenResponseQuestion) q;
		questionText.setText(question.getQuestion());
	}

}
