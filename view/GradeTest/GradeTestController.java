package view.GradeTest;

import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Main;
import model.GradingTest.Grader;
import model.TestBank.Course;
import model.TestBank.Test;
import model.TestBank.Test.State;
import model.TestBank.TestsTaken;
import model.TestToolUser.Student;

/** Class GradeTestController is a controller class which connects the Grade Tests Landing Page to the backend.
 *  Class GradeTestController has the methods show grades, send grades and view stats.
 *  Class GradeTestController invokes many button clicks and uses the Grader class to get its data.
 */
public class GradeTestController {

	/**button to go to the grade student tests screen*/
	@FXML
	private Button viewGrades;
	/**button to go to the stats screen*/
	@FXML
	private Button viewStats;
	/**button to send grades to students*/
	@FXML
	private Button sendGrades;
	/**table of all the tests and their info*/
	@FXML
    private TableView<Grader> testTable;
	/**column in table of the testname*/
    @FXML
    private TableColumn<Grader, String> testNameColumn;
    /**column in table of the test date administered*/
    @FXML
    private TableColumn<Grader, String> dateColumn;
    /**column in the table of the percent of student tests graded*/
    @FXML
    private TableColumn<Grader, String> percentGradedColumn;
    /**column in the table of whether the test grades have been sent*/
    @FXML
    private TableColumn<Grader, String> gradesSentColumn;
    /**label representing the name of the course*/
    @FXML
    private Label coursename;
	
    /**the main class for creating screens*/
	private Main main;
	
	/**Course class for getting student tests*/
	private Course course;
	
	/**list of Graders to put in the TableView variable*/
	private ObservableList<Grader> graderList = FXCollections.observableArrayList();
	
	/**method to set up  the main and course instance variable while also setting up the TableView*/
	public void setMain(Main main, Course course) {
        this.main = main;
        this.course = course;
        // TAKE THIS OUT LATER!!!!!!!
        if (course == null) {
        	Date date = new Date();
        	this.course = new Course("course 1");
        	Test test = new Test();
        	test.setName("Test 1");
        	test.setAssigned(new Date());
        	Test test2 = new Test();
        	test2.setName("Test 2");
        	test2.setAssigned(new Date());
        	
        	this.course.setCourseName("CPE 103");
        	TestsTaken taken = new TestsTaken();
        	Student student = new Student();
        	student.setName("John Smith");
        	student.setEmail("jsmith1@gmail.com");
        	Student student2 = new Student();
        	student2.setName("Ashley Thompson");
        	student2.setEmail("athompson1@gmail.com");
        	test.setStudent(student);
        	test.setStudentGrade(75);
        	test2.setStudentGrade(80);
        	test.setTotalPoints(100);
        	test2.setTotalPoints(100);
        	test2.setStudent(student2);
        	test.setState(State.valueOf("taken"));
        	test2.setState(State.valueOf("taken"));
        	taken.addTest(test);
        	taken.addTest(test2);
        	taken.SetTestName("CPE 103");

        	this.course.addTestTaken(taken);
        }
        // REMOVE ABOVE LATER
        TestsTaken[] taken = this.course.getAllTestsTaken().toArray(new TestsTaken[0]);
        for (int i = 0; i < taken.length; i++) {
        	Grader grader = new Grader();
        	grader.SetTestTaken(taken[i]);
        	graderList.add(grader);
        }
        testTable.setItems(graderList);
        coursename.setText(this.course.getCourseName());
	}
	
	/**method to react to ui events*/
	@FXML
	private void initialize() {
		
		
		viewGrades.setOnAction((event) -> {
			showGrades(); 
	      });
		
		viewStats.setOnAction((event) -> {
			viewStats(); 
	      });
		
		sendGrades.setOnAction((event) -> {
			sendGrades(); 
	      });
		

		testNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GetTestsTaken().GetTestName()));
		dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GetTestsTaken().getTests().toArray(new Test[0])[0].getAssigned().toString())); // this is bad, make sure teststaken contain at least one test
		percentGradedColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GetPercentGradedString()));
		gradesSentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GradesSentString()));
		
	}
	
	/**method which reacts to viewGrades button being pressed by going to the grade students screen*/
	private void showGrades() {
		int selectedIndex = testTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex < 0) {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Test Selected");
	        alert.setContentText("Please select a test in the table.");

	        alert.showAndWait();
		}
		else {
			Grader temp = testTable.getItems().get(selectedIndex);
			System.out.println(selectedIndex);
			main.initRootLayout();
			main.showGradeTestStudentOverview(temp.GetTestsTaken().GetTestName(), course, temp);
		}
	}
	
	/**method which reacts to sendGrades button being pressed by invoking the SubmitGrade method in the Grader class*/
	private void sendGrades() {
		int selectedIndex = testTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex < 0) {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Test Selected");
	        alert.setContentText("Please select a test in the table.");

	        alert.showAndWait();
		}
		else if (!percentGradedColumn.getCellData(selectedIndex).equals("100.0%")) {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("Can't Submit");
	        alert.setHeaderText("Tests Not Fully Graded");
	        alert.setContentText("Please complete grading for this test before submitting.");

	        alert.showAndWait();
		}
		else {
			Grader temp = testTable.getItems().get(selectedIndex);
			temp.SubmitGrade();
		}
	}
	
	/**method which reacts to the viewStats button by going to the stats screen*/
	private void viewStats() {
		int selectedIndex = testTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex < 0) {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Test Selected");
	        alert.setContentText("Please select a test in the table.");

	        alert.showAndWait();
		}
		else {
			Grader temp = testTable.getItems().get(selectedIndex);
			System.out.println(selectedIndex);
			main.initRootLayout();
			main.showGradeTestStatsOverview(temp.GetTestsTaken().GetTestName(), course, temp);
		}
	}
	
}
