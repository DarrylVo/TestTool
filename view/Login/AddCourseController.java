package view.Login;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Main;
import model.TestBank.Course;
import model.TestToolUser.Teacher;

public class AddCourseController {
	private Main main;
	private Teacher teach;
	@FXML
	TextField courseName;
	
	public void setMain(Main main) {
        this.main = main;
       
		
    }
	
	public void setTeacher(Teacher teach) {
		this.teach = teach;
	}
	@FXML
	private void addCourse() {
		if(courseName.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getSecondaryStage());
	        alert.setTitle("Error");
	        alert.setHeaderText("Error");
	        alert.setContentText("Please input a course name");
	        alert.showAndWait();
		}
		else {
			
		    Course c = new Course(courseName.getText());
		    teach.initArrayList();
		    teach.addCourse(c);
		    Alert alert = new Alert(AlertType.INFORMATION);
	        alert.initOwner(main.getSecondaryStage());
	        alert.setTitle("Sucess!");
	        alert.setHeaderText("Course has been added");
	        alert.setContentText("You will now be taken back to the home page");
	        alert.showAndWait();
			main.getSecondaryStage().close();
			main.showTeacherLandingPageOverview();
		}
	}
}
