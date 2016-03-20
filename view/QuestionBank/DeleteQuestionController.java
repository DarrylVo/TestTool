package view.QuestionBank;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Main;

public class DeleteQuestionController {
	
   @FXML
   private Button QBDeleteYes;
   @FXML
   private Button QBDeleteNo;
   
   private Main main;
   
   public void setMain(Main main) {
       this.main = main;
   }
   
   @FXML
   private void initialize(){
	   QBDeleteYes.setOnAction((event) -> {
		   main.showDeleteQuestionYes();
		   });
	   QBDeleteNo.setOnAction((event) -> {
		   main.showQuestionBankOverview();
		   });
   }
   
}
