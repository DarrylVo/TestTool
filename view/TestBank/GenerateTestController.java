package view.TestBank;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
/** Class GenerateTestController is the controller for the initial generate test screen

 * @author Darryl Vo(dvo03@calpoly.edu)
 */
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert.AlertType;
import model.Main;
import model.TestBank.TestBank;
import model.TestBank.Course;
import model.TestBank.Test;
import model.Statistics.Time;
public class GenerateTestController {
	/** holds a reference to the main */
	private Main main;
	@FXML
	/** choicebox to handle which type of generation to do */
	private ChoiceBox<String> choicebox;
	private String course;
	/** sets the main reference */
	public void setMain(Main main) {
        this.main = main;
    }
	public void setCourse(String course) {
		this.course = course;
	}
	/** intialized the combobox in the gui */
	public void initialize() {
		choicebox.getItems().addAll("Simple","Complex");
	}
	/** once what type of generation is selected, this will handle going to the next screen */
	@FXML 
	public void handleGo() {
		
		String selected = (String)choicebox.getValue();
		if(selected==null) {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("No Course");
	        alert.setHeaderText("No Course Selected");
	        alert.setContentText("Please select a course.");
	        alert.showAndWait();
		}
			
		else if(selected.equals("Simple"))
			main.viewSimpleGenerate(course);
		else if(selected.equals("Complex")) {
			main.viewComplexGenerate(course);
		}
		
	
	}
}
