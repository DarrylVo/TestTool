package view.QuestionBank;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import model.Main;

public class QuestionBankSearchController {
	
    @FXML
    private ComboBox course;
    @FXML
    private ComboBox topic;
    @FXML
    private ComboBox type;
    @FXML
    private ComboBox date;
    @FXML
    private Button QBEdit;
    @FXML
    private Button QBDelete;
    @FXML
    private Button QBSearch;
    
   private Main main;
   
   public void setMain(Main main) {
       this.main = main;
   }
   
   @FXML
   private void initialize(){
	   QBEdit.setOnAction((event) -> {
		   main.showQuestionEditHandlerOverview();
		   });
	   QBDelete.setOnAction((event) -> {
		   main.showDeleteQuestion();
		   });
	   QBSearch.setOnAction((event) -> {
		   main.showDeleteQuestion();
		   });
	   
   }
   
}
