package view.TestBank;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Main;
import model.TestBank.TestBank;
import model.TestBank.Course;
import model.TestBank.Test;
import model.Statistics.Time;
import model.TestToolUser.*;
import javafx.collections.FXCollections;
/** Class TestBankController is the controller class for the test bank
 * landing page
 * @author Darryl Vo(dvo03@calpoly.edu)*/
public class TestBankController {
	
	private Teacher teach;
	/** holds reference to main program */
	private Main main;
/** holds a testbank to show */
	private TestBank testbank;
	private String course;
	
	@FXML
	TableView<Test> tableView;
	

	TableColumn<Test, String> name = new TableColumn<Test,String>("Test Name");
	
	@FXML
	Label courseLabel;
	
	
	TableColumn<Test,Number> numquestion=new TableColumn<Test, Number>("# of Questions");
	
	TableColumn<Test,Number> difficulty = new TableColumn<Test,Number>("Difficulty");
	
	//ObservableList<Test> data = FXCollections.observableArrayList()

	
	public TestBankController() {
		
		//testbank = new TestBank();
	}
	
	public void setCourse(String c) {
		course = c;
		courseLabel.setText("Course:"+c);
	}
	/** sets main reference*/
	public void setMain(Main main) {
        this.main = main;
        teach = (Teacher) main.getUser();
        tableView.setItems(teach.getCourse(course).getTestBank());
        name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        name.setMinWidth(150);
        numquestion.setMinWidth(150);
        difficulty.setMinWidth(150);
        numquestion.setCellValueFactory(cellData -> cellData.getValue().numQuestionProperty());
        difficulty.setCellValueFactory(cellData -> cellData.getValue().difficultyProperty());
        tableView.getColumns().addAll(name,numquestion,difficulty);
        
    }
	/** initializes */
	private void initialize(){
		
	}
        /** handles the button to generate simple test **/
	@FXML
	private void handleGenerate(){
		main.viewGenerate(course);
		
	}
     /** handles the delete button action */
	@FXML
	private void handleDeleteTest() {
	   if (tableView.getSelectionModel().getSelectedItem() != null) {
	      teach.getCourse(course).removeTest(tableView.getSelectionModel().getSelectedItem());
	      teach.getCourse(course).restoreObservable();
		   main.showTestBankOverview(this.course);
	   }
	}
/** handles button to edit test */
	@FXML
	private void handleEditTest() { 
	   if (tableView.getSelectionModel().getSelectedItem() != null) {
	      main.showEditTest(this.course, tableView.getSelectionModel().getSelectedItem());
	   }
	}

}
