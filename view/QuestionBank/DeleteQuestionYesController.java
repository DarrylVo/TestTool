package view.QuestionBank;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Main;
import model.QuestionBank.QuestionBank;

/** controller class for DeleteQuestion if Yes screen*/
public class DeleteQuestionYesController {
    /** button */
    @FXML
    private Button Delete;
    
    /** holds reference to main executable */
    private Main main;
   
    /** initializes */
    @FXML
    private void initialize() {
      // Handle Button event.
    	Delete.setOnAction((event) -> {
            main.initRootLayout();
            main.showQuestionBankOverview();
        });
        
   }
   /** sets main reference */
   public void setMain(Main main) {
       this.main = main;
   }
   
}
