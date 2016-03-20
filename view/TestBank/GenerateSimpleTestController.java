package view.TestBank;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.Main;
import model.TestBank.TestBank;
import model.TestBank.Course;
import model.TestBank.Test;
import model.Statistics.Time;
import model.TestToolUser.Teacher;
/** Class GenerateSimpleTestController is a controller for viewing the generate simple
 * test menu
 * @author Darryl Vo (dvo03@calpoly.edu)
 *
 */
public class GenerateSimpleTestController {
	/** holds a reference to the main so it can call other viewing functions
	 * 
	 */
	private Main main;
	/** links to the fxml text field where the user inputs 
	 * the name of the test
	 */
	private Teacher teach;
	
	@FXML
	private TextField name;
	/** links to the fxml text field where the user inputs
	 * the number of questions
	 */
	@FXML
	private TextField numquestion;
	/** links to the fxml choicebox where the user picks the course
	 * 
	 */
	@FXML
	private Label courseLabel;
	@FXML
	/** links to the the choicebox where the user picks the duration
	 * 
	 */
	private ChoiceBox<String> duration;
	@FXML
	/** links to the choicebox where the user picks the difficulty
	 * 
	 */
	private ChoiceBox<Double> diff;
	
	private String course;
	
	/** sets the main reference
	 * 
	 * @param main Main to set reference to
	 */
	public void setMain(Main main) {
        this.main = main;
        teach = (Teacher)main.getUser();
		
    }
	public void setCourse(String course) {
		this.course = course;
		courseLabel.setText(this.course);
	
	}
	/** initializes the choiceboxes in the gui */
	public void initialize() {
		duration.getItems().addAll("Short","Medium","Long");
		diff.getItems().addAll(1.0,2.0,3.0,4.0,5.0);
		
		
		
		
	
	}
	/** generates the test if all the inputs are valid */
	public void handleGenerate() {
		 
		if (diff.getValue() == null){}
			//System.out.println("pick a difficulty value");
		else
			System.out.println("difficulty:"+(double)diff.getValue());
		
		String dur = (String)duration.getValue();
		
		if(dur == null){}
			//System.out.println("pick a duration");
		else
			System.out.println("duration:"+dur);
		
		
		
		String nme = name.getText();
		
		if (nme.equals("")||nme.equals(" ")){}
			//System.out.println("type a name");
		else
			System.out.println("name"+nme);
			
		int nq = 0;
		try {
			nq = Integer.parseInt( (String) numquestion.getText());
			
			System.out.println("num question:"+nq);
		}
		catch( NumberFormatException e) {
			//System.out.println("invalid number or no number exists");
		}
		
		
		if(diff.getValue() == null  ) {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("Pick a difficulty Value");
	        alert.setHeaderText("Difficulty value missing");
	        alert.setContentText("Please pick a difficulty value.");
	        alert.showAndWait();
		}
		else if(dur == null) {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("No Duration");
	        alert.setHeaderText("No Duration Selected");
	        alert.setContentText("Please select a duration.");
	        alert.showAndWait();
		}
		else if(nme.equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("No Test Name");
	        alert.setHeaderText("No Test Name");
	        alert.setContentText("Please type a test name.");
	        alert.showAndWait();
		}
		else if(nq==0) {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("No Valid Number of Questions");
	        alert.setHeaderText("No Valid Number of Questions");
	        alert.setContentText("Please type a valid number of questions.");
	        alert.showAndWait();
		}
		else {
			teach.generateSimpleTest(nme, teach.getCourse(course), nq, dur, (double)diff.getValue());
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("Success");
	        alert.setHeaderText("Simple Test has been generated");
	        alert.setContentText("You will now be taken to the test bank");
	        alert.showAndWait();
			main.showTestBankOverview(course);
		}
			
		
		/*
		if(diff.getValue() != null&&dur != null&&(!nme.equals("")&&!nme.equals(" "))&&nq!=0)
			teach.generateSimpleTest(nme, teach.getCourse(course), nq, dur, (double)diff.getValue());
			main.showTestBankOverview(course);
		*/
		
		
		
	}
	
	
	
}
