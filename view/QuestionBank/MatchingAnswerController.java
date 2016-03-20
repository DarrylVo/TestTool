package view.QuestionBank;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Main;

public class MatchingAnswerController {

    @FXML
    private TextField title;
    @FXML
    private TextArea questionText;
    @FXML
    private Button addNewAnswer;
    @FXML
    private Button removeAnswer;
    @FXML
    private TextField pointValue;
    @FXML
    private Button back;
    @FXML
    private Button next;
    @FXML
    private Button cancel;

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private void initialize(){
 	   back.setOnAction((event) -> {
            main.showQuestionEditHandlerOverview();
 		   });
 	   next.setOnAction((event) -> {
 		   main.showQuestionBankOverview();
           // needs to save answers and question to questionDB
 		   });
    }
}


