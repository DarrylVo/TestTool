package view.Login;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import model.Main;
import model.TestBank.Course;
import model.TestToolUser.Student;
/** controller class for add student screen*/
public class AddStudentController {
	
	
	@FXML
	TableView<Student> studentTable;
	
	TableColumn<Student, String> nameCol;
	TableColumn<Student, String> emailCol;
   
   /** holds reference to main executable */
   private Main main;
   
   private Course course;
   
   /** initializes method to go to UI */
   @FXML
   private void initialize(){
	   

   }
   

   
   /** sets main reference */
   public void setMain(Main main) {
       this.main = main;

      
   }
   
   public void setCourse(Course c) {
	   this.course = c;
	   System.out.println(this.course.getCourseName());
	   ArrayList<Student> studs = main.getDB().getStudents();
	   System.out.println(studs.size());
	   ObservableList<Student> data = FXCollections.observableArrayList(studs);
	   nameCol = new TableColumn<Student,String>("Name");
	   nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
	   emailCol = new TableColumn<Student,String>("Email");
	   emailCol.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
	   studentTable.setItems(data);
	   studentTable.getColumns().setAll(nameCol,emailCol);
   }
   @FXML
   public void selected() {
	   Student s = (Student)studentTable.getSelectionModel().getSelectedItem();
	   if(course.GetStudentList().contains(s)) {
		   Alert alert = new Alert(AlertType.WARNING);
	       alert.initOwner(main.getSecondaryStage());
	       alert.setTitle("Failure");
	       alert.setHeaderText("Student is already in the course");
	       alert.setContentText("Pick again");
	       alert.showAndWait();
	   }
	   else {
		   course.addStudent(s);
		   s.addCourse(course);
		   Alert alert = new Alert(AlertType.INFORMATION);
	       alert.initOwner(main.getSecondaryStage());
	       alert.setTitle("Success");
	       alert.setHeaderText("Student has been added to the course");
	       alert.setContentText("You will now be taken back to the home page");
	       alert.showAndWait();
		   main.getSecondaryStage().close();
		   main.showTeacherLandingPageOverview(); 
	   }
	   
	   
   }
   @FXML
   public void cancel() {
	   main.getSecondaryStage().close();
	   main.showTeacherLandingPageOverview(); 
   }
   
}
