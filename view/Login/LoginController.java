package view.Login;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Main;
import model.TestToolUser.Student;
import model.TestToolUser.Teacher;
import model.TestToolUser.User;
/** controller class for login screen*/
public class LoginController {
   /** button */
   @FXML
   private Button LoginButton;
   /** holds refence to main executable */
   private Main main;
   /** textfield box */
   @FXML
   TextField username;
   /** text box for password */
   @FXML
   PasswordField password;
   
   
   /** initializes */
   @FXML
   private void initialize() {
      // Handle Button event.
      LoginButton.setOnAction((event) -> {
    	User s = login();
    	
    	if(s!=null) {
    		s.restoreObservable();
    		main.setUser(s);
    		 main.initRootLayout();
   		  if(s instanceof Teacher)
   		  main.showTeacherLandingPageOverview();
   		  else if(s instanceof Student)
   			  main.showStudentLandingPageOverview();
           		  
    	}
    	else {
    		Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("Error");
	        alert.setHeaderText("Login Error");
	        alert.setContentText("Incorrect Login credentials. Please try again");
	        alert.showAndWait();
    	}
    	 
    	  
    	  
    	 
      });
      
   }
   
   @FXML
   private User login() {
	   return main.getDB().login(username.getText(),password.getText());
   }
   /** sets main reference */
   public void setMain(Main main) {
       this.main = main;

       // Add observable list data to the table
       //personTable.setItems(mainApp.getPersonData());
   }
   
   @FXML
   public void register() {
	   main.showRegister();
   }
}
