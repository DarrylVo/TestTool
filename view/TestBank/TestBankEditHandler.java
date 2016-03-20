package view.TestBank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Main;
import model.QuestionBank.MultipleChoiceQuestion;
import model.QuestionBank.Question;
import model.QuestionBank.QuestionBank;
import model.QuestionBank.WrittenResponseQuestion;
import model.TestBank.*;
import model.TestToolUser.Student;
import model.TestToolUser.Teacher;
/**
* Class that handles the various events for the Test Bank Edit view.
* 
* The methods in this class are to handle the various button clicks and interaction in the view.
*/
public class TestBankEditHandler {
   /**
    * Variable that represents the questions on a test.
    */
   @FXML
   private TableView<Question> questionTable; 
   /**
    * Variable that represents a Question object's question string.
    */
   @FXML
   private TableColumn<Question, String> questionColumn;
   /**
    * Variable that represents a Question object's topic.
    */
   @FXML
   private TableColumn<Question, String> topicColumn;
   /**
    * Variable that represents a Question object's question type.
    */
   @FXML
   private TableColumn<Question, String> typeColumn;
   /**
    * Variable that represents a Test's point value.
    */
   @FXML
   private TableColumn<Question, Integer> pointsColumn;
   
   /**
   * Variable that represents the CreateQuestionButton in the fxml file.
   */
   @FXML
   private Button CreateQuestionButton;
   /**
   * Variable that represents the EditQuestionButton in the fxml file.
   */
   @FXML
   private Button EditQuestionButton;
   /**
   * Variable that represents the DeleteQuestionButton in the fxml file.
   */
   @FXML
   private Button DeleteQuestionButton;
   /**
   * Variable that represents the AddExistingButton in the fxml file.
   */
   @FXML
   private Button AddExistingButton;
   /**
    * Variable that represents the AssignButton in the fxml file.
    */
   @FXML
   private Button AssignButton;
   /**
   * Variable that represents the CancelButton in the fxml file.
   */
   @FXML
   private Button CancelButton;
   /**
    * Variable that references the Main application.
    */
   private Main main;
   /**
    * Variable that represents the test that is being edited.
    */
   private Test test;
   /**
    * Variable that represents the course that the test that is being edited is in.
    */
   private String course;
   /**
    * Variable that represents the teacher that the test that is associated with.
    */
   private Teacher teacher;
   /**
    * Variable that represents the course that the test that is being edited is in.
    */
   private QuestionBank questionBank;
   /**
    * Variable that represents the students that the test is associated to.
    */
   private ArrayList<Student> students;
   /**
    * The data as an observable list of Questions.
    */
   private ObservableList<Question> questionData = FXCollections.observableArrayList();
   /**
    * The constructor for the EditBank Controller.
    */
   public TestBankEditHandler() {
   }
   /**
   * Initialize function is called when the scene for TestBankEdit view is first put in place.
   */
   @FXML
   private void initialize() {    
   
      // Initialize the question table 
      questionColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getQuestion()));
      topicColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTopic()));
      typeColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getType()));
      pointsColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getPointValue()));
      
      // Handle Create Question Button event.
      CreateQuestionButton.setOnAction((event) -> {
         main.showQuestionAddHandlerOverview("Test Bank", this.course, null, this.test);
      });
      
      // Handle Edit Question Button event.
      EditQuestionButton.setOnAction((event) -> {
         if (questionTable.getSelectionModel().getSelectedItem() != null){   
            main.showQuestionAddHandlerOverview("Test Bank", this.course,
                  questionTable.getSelectionModel().getSelectedItem(), this.test);
         }
      });
      
      // Handle Delete Question Button event.
      DeleteQuestionButton.setOnAction((event) -> {
         if(questionTable.getSelectionModel().getSelectedItem() != null){
            this.test.removeQuestion(questionTable.getSelectionModel().getSelectedItem());
            main.showEditTest(this.course, this.test);
         }
      });
      
      // Handle Add Existing Question Button event.
      AddExistingButton.setOnAction((event) -> {
         if(questionTable.getSelectionModel().getSelectedItem() != null){
            this.questionBank.addQuestion(questionTable.getSelectionModel().getSelectedItem());
         }
      });
      
      
      // Handle Cancel Button event.
      CancelButton.setOnAction((event) -> {
         main.showTestBankOverview(this.course);
      });
   }
   /** Sets the course variable to a new course
    * @param c Course to set the course variable to.
    */
   public void setCourse(String c) {
      this.course = c;
   }
   /** Sets the test variable to a new test
    * @param t Test to set the test variable to.
    */
   public void setTest(Test t) {
      this.test = t;
      
      ArrayList<Question> questionList = this.test.getQuestions();
      // Add all questions to table
      for (int i = 0; i < questionList.size(); i++) {
         questionData.add(questionList.get(i));
         questionTable.setItems(questionData);
      }
   }
   /** Sets the main variable to a new main
    * @param main Main to set the main variable to.
    */
    public void setMain(Main main) {
	   this.main = main;
	   this.teacher = (Teacher) main.getUser();
	   this.questionBank = teacher.getCourse(course).getQuestionBank();
	}
    /** 
     * Assigns tests to all students in a course.
     */
    @FXML
    public void assignTests(){
       if(this.test != null){
          this.students = (ArrayList<Student>) teacher.getCourse(course).GetStudentList();
          for (int i = 0; i < students.size(); i++){
             this.students.get(i).addTest(test);
          }
       }
    }
}
