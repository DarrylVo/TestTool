package view.Login;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Main;
import model.TestBank.Course;
import model.TestToolUser.Student;
import model.TestToolUser.Teacher;

public class RegisterUserController {
	private Main main;
	   
	   
	   
	   @FXML
	   ChoiceBox<String> typeList;
	   @FXML
	   TextField name;
	   @FXML
	   TextField password;
	   @FXML
	   TextField email;
	   
	   public void setMain(Main main) {
	       this.main = main;
	    
	       
	   }
	   
	   public void handleRegister() {
		   String nme = name.getText();
		   String eml = email.getText();
		   String pass = password.getText();
		   String type = typeList.getValue();
		   
		   if(nme.equals("")) {
			   Alert alert = new Alert(AlertType.WARNING);
		        alert.initOwner(main.getPrimaryStage());
		        alert.setTitle("No Valid User Name");
		        alert.setHeaderText("No Valid User Name");
		        alert.setContentText("Please type a valid name.");
		        alert.showAndWait();
		   }
		   else if (pass.length()<4) {
			   Alert alert = new Alert(AlertType.WARNING);
		        alert.initOwner(main.getPrimaryStage());
		        alert.setTitle("No Valid Password");
		        alert.setHeaderText("No Valid Password");
		        alert.setContentText("Please type a valid Password. At least four characters");
		        alert.showAndWait();
		   }
		   else if (type.equals("")) {
			   Alert alert = new Alert(AlertType.WARNING);
		        alert.initOwner(main.getPrimaryStage());
		        alert.setTitle("Type is not selected");
		        alert.setHeaderText("Type is not selected");
		        alert.setContentText("Please select a user type.");
		        alert.showAndWait();
		   }
		   else if(eml.equals("")) {
			   Alert alert = new Alert(AlertType.WARNING);
		        alert.initOwner(main.getPrimaryStage());
		        alert.setTitle("No Valid Email");
		        alert.setHeaderText("No Valid Email");
		        alert.setContentText("Please type in a valid email address");
		        alert.showAndWait();
		   }
		   else {
			   if(main.getDB().checkExisting(eml)) {
				   Alert alert = new Alert(AlertType.WARNING);
			        alert.initOwner(main.getPrimaryStage());
			        alert.setTitle("REgister Error");
			        alert.setHeaderText("This email has already been registered");
			        alert.setContentText("Please type in a different email address");
			        alert.showAndWait();
			   }
			   else if(type.equals("Teacher")) {
				  Teacher teach = new Teacher();
				  teach.setEmail(eml);
				  teach.setName(nme);
				  teach.setPassword(pass);
				  main.getDB().addTeacher(teach);
				  Alert alert = new Alert(AlertType.INFORMATION);
			        alert.initOwner(main.getPrimaryStage());
			        alert.setTitle("Success");
			        alert.setHeaderText("User has been created!");
			        alert.setContentText("You will now be taken back to the Login Screen");
			        alert.showAndWait();
				   main.initLoginScreen();
			   }
			   else if(type.equals("Student")) {
				   Student stud = new Student();
				   stud.setEmail(eml);
				   stud.setName(nme);
				   stud.setPassword(pass);
				   main.getDB().addStudent(stud);
				   Alert alert = new Alert(AlertType.INFORMATION);
			        alert.initOwner(main.getPrimaryStage());
			        alert.setTitle("Success");
			        alert.setHeaderText("User has been created!");
			        alert.setContentText("You will now be taken back to the Login Screen");
			        alert.showAndWait();
				   main.initLoginScreen();
			   }
			   
		   }
	   }
	   
	   
	   public void initialize() {
		   typeList.getItems().addAll("Teacher","Student");
	   }
	   @FXML
	   public void handleCancel() {
		   main.initLoginScreen();
	   }

}
