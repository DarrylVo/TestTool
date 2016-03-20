package view.QuestionBank;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Main;

public class CodeAnswerController {

    @FXML
    private TextField title;
    @FXML
    private TextField questionType;
    @FXML
    private TextArea questionText;
    @FXML
    private TextField pointValue;
    @FXML
    private Button back;
    @FXML
    private Button next;
    @FXML
    private Button cancel;
    @FXML
    private TextArea keywords;
    @FXML
    private Button uploadScript;
    @FXML
    private TextField filePath;
    @FXML
    private RadioButton partial;
    @FXML
    private RadioButton full;

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
