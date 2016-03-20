package view.GradeTest;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Main;
import model.GradingTest.Grader;
import model.QuestionBank.*;
import model.Statistics.QuestionStatistics;
import model.TestBank.Course;
import model.TestBank.Test;

/** Class GradeViewStatsController is a controller class which connects the Grade Tests Landing Page to the backend.
 *  Class GradeViewStatsController has the methods show stats details.
 *  Class GradeViewStatsController invokes many button clicks and uses the Question class to get its data.
 */
public class GradeViewStatsController {
	/**TableView holding all the question statistics*/
	@FXML
    private TableView<Question> statsTable;
	/**column in TableView holding all the Question values*/
    @FXML
    private TableColumn<Question, String> questionColumn;
    /**Button that connects the page back to the Grade Tests Landing Page*/
    @FXML
	private Button gradingHome;
    /**Label representing the name of the test*/
    @FXML
	private Label testName;
    @FXML
	private Label questionNum;
    @FXML
	private Label percentCorrect;
    @FXML
	private TextArea question;
    @FXML
	private TextArea commonAns;
    
    /**the main class for creating screens*/
    private Main main;
    
    /**Course class for getting student tests*/
    private Course course;
    
    /**string representing the name of the test*/
    private String testname;
    
    /**Grader class to derive the statistics from*/
    private Grader grader;
    
    /**list of Questions to set in the TableView*/
    private ObservableList<Question> questionList;

    /**method to set up  the main, grader, testname and course instance variable while also setting up the TableView*/
    public void setMain(Main main, String testname, Course course, Grader grader) {
    	this.testname = testname;
    	this.main = main;
    	this.course = course;
    	this.grader = grader;
    	questionList = FXCollections.observableArrayList(grader.GetTestsTaken().getTests().toArray(new Test[0])[0].getQuestions());
    	// REMOVE THESE QUESTIONS LATER
    	Question test = new Question();
    	test.setQuestion("Test Stats");
    	questionList.add(test);
    	MultipleChoiceQuestion q1 = new MultipleChoiceQuestion();
    	q1.setPointValue(5);
    	q1.setQuestion("Which of these data structures allows you to access items the quickest?");
    	q1.setQuestionNumber(1);
    	q1.SetStudentAnswer("Array");
    	q1.addPossibleAnswer("Linked List");
    	q1.addPossibleAnswer("Array");
    	q1.addPossibleAnswer("BST");
    	q1.addPossibleAnswer("Heap");
    	q1.SetActualAnswer("Array");
    	q1.setPointsEarned(5);
    	questionList.add(q1);
    	WrittenResponseQuestion q2 = new WrittenResponseQuestion();
    	q2.setPointValue(5);
    	q2.setQuestion("What is the name of the data structure we learned that uses first in last out?");
    	q2.setQuestionNumber(2);
    	q2.SetStudentAnswer("stack");
    	q2.SetActualAnswer("queue");
    	q2.setPointsEarned(5);
    	questionList.add(q2);
    	// REMOVE ABOVE
    	statsTable.setItems(questionList);
    }
    
    /**method to react to ui events*/
    @FXML
    private void initialize() {
    	testName.setText(testname);
    	testName.setText(course.getCourseName() + " Question Stats"); // remove later
    	
    	statsTable.getSelectionModel().selectFirst();
    	
    	questionColumn.setCellValueFactory(cellData -> new SimpleStringProperty("question " + Integer.toString(cellData.getValue().getQuestionNumber())));
    	
    	statsTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showStatsDetails(newValue));
    	
    	gradingHome.setOnAction((event) -> {
			 main.initRootLayout();
	         main.showGradeTestOverview(course);
	      });
    }

    /**method for when a question is selected showing the statistics*/
	private void showStatsDetails(Question newValue) {
		//ADD TO STATS LATER
		System.out.println("selected question: " + newValue.getQuestionNumber());
		if (newValue != null) {
			if (newValue.getQuestion().equals("Test Stats")) {
				
			}
			else {
				questionNum.setText("question " + Integer.toString(newValue.getQuestionNumber()));
				QuestionStatistics stats = new QuestionStatistics();
				//percentCorrect.setText("finish later");
				//commonAns.setText("finish later");
				question.setText(newValue.getQuestion());
			}
		}
	}
}
