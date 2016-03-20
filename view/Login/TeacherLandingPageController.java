package view.Login;
import java.sql.Date;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import model.Main;
import model.QuestionBank.MultipleChoiceQuestion;
import model.QuestionBank.Question;
import model.QuestionBank.WrittenResponseQuestion;
import model.TestBank.Course;
import model.TestBank.Test;
import model.TestToolUser.Student;
import model.TestToolUser.Teacher;

public class TeacherLandingPageController {
	
   @FXML
   private Button QuestionBank;
   @FXML
   private Button GradeTests;
   @FXML
   private Button TestBank;
   @FXML
   private Button ViewStats;
   @FXML
   private Button loginStudent;
   @FXML
   private Button addStudent;
   @FXML
   private Button addCourse;
   @FXML
   ChoiceBox<String> course;
   
   @FXML
	TableView<Student> studentTable;
	
	TableColumn<Student, String> nameCol;
	TableColumn<Student, String> emailCol;
   
   @FXML
   Label teacherName;
   
   private Main main;
   
   private Teacher teach;
   
//   private static Student student;
   
   public void setMain(Main main) {
       this.main = main;
       teach = (Teacher)main.getUser();
       teacherName.setText(teach.getName());
       course.getItems().clear();
       ArrayList<Course> courses = teach.getCourseArrayList();
       if(courses!=null) {
    	   for(int i =0; i <courses.size();i++) {
   			course.getItems().addAll(courses.get(i).getCourseName());
   		}   
       }
       
       //Sample Tests
       /*
       student = new Student();
       Course myCourse = new Course();
       Course myCourse2 = new Course();
       Question question3 = new Question();
       Question question4 = new Question();
       Question question5 = new Question();
       Question question6 = new Question();
       Question question7 = new Question();
       Question question8 = new Question();
       Question question9 = new Question();
       Question question10 = new Question();
       question3.setQuestion("Which of these sorting algorithms");
       question4.setQuestion("Write code that will use a ");
       question5.setQuestion("How long does it take for");
       question6.setQuestion("True or False: Code that");
       question7.setQuestion("Which number would the number");
       question8.setQuestion("Write code that creates a ");
       question9.setQuestion("Write code that will recursively");
       question10.setQuestion("If a hash table is size 10");
       Test test = new Test();
       Date date = Date.valueOf("2015-5-6");
       test.setNumQuestion(10);
       test.setName("Midterm 1");
       test.setAssigned(date);
       test.setDue(date);
       MultipleChoiceQuestion question = new MultipleChoiceQuestion();
       question.setQuestion("Which of these data structures allows you to access items the quickest?");
       question.SetActualAnswer("Array");
       question.addPossibleAnswer("Heap");
       question.addPossibleAnswer("BST");
       question.addPossibleAnswer("Linked List");
       question.SetDifficulty(1);
       question.setPointValue(5);
       question.setTopic("Which of these data structures allows you to access items the quickest?");
       WrittenResponseQuestion question2 = new WrittenResponseQuestion();
       question2.setQuestion("What is the name of the data structure that uses first in, first out?");
       question2.SetActualAnswer("Queue");
       question2.SetDifficulty(4);
       question2.setPointValue(2);
       question2.setTopic("What is the name of the data structure that uses first in, first out?");
       test.addQuestion(question);
       test.addQuestion(question2);
       test.addQuestion(question3);
       test.addQuestion(question4);
       test.addQuestion(question5);
       test.addQuestion(question6);
       test.addQuestion(question7);
       test.addQuestion(question8);
       test.addQuestion(question9);
       test.addQuestion(question10);
       Test test2 = new Test();
       Date date2 = Date.valueOf("2015-5-1");
       test2.setNumQuestion(5);
       test2.setName("Quiz 2");
       test2.setAssigned(date2);
       test2.setDue(date);
       myCourse.addStudent(student);
       student.addTest(test);
       myCourse.setCourseName("CPE 103");
       student.addTest(test2);
       myCourse2.setCourseName("CSC 225");
       myCourse.addTest(test);
       myCourse2.addTest(test2);
       test.setCourse(myCourse);
       test2.setCourse(myCourse2);
       test.SetCourseName(test.getCourseName());
       test2.SetCourseName(test2.getCourseName());
       student.addCourse(myCourse);
       student.addCourse(myCourse2);
       System.out.println("teacher landing course: " + myCourse.getCourseName());
       System.out.println("teacher landing: " + student.getCourseArrayList().get(0).getCourseName());
*/
   }
   /*public static void setStudent(Student student1) {
	   student = student1;
   }*/
  
   
   public boolean courseSelected() {
	   if( (String)course.getValue() == null) {
		   Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("No Course");
	        alert.setHeaderText("No Course Selected");
	        alert.setContentText("Please select a course.");
	        alert.showAndWait();
	        return false;
	   }
	   return true;
   }
  
   
   @FXML
   private void initialize(){
	   
	   course.valueProperty().addListener(new ChangeListener<String>() {
	        @Override public void changed(ObservableValue ov, String t, String t1) {
	            showStudents();
	          }    
	      });
	   
	   QuestionBank.setOnAction((event) -> {
			if(courseSelected()){
         	main.showQuestionBankOverview(teach.getCourse((String)course.getValue()));
			}
       });
	   GradeTests.setOnAction((event) -> {
		   if(courseSelected())
         	main.showGradeTestOverview(teach.getCourse(course.getValue()));	   
       });
	   TestBank.setOnAction((event) -> {
		   if(courseSelected())
         	main.showTestBankOverview((String)course.getValue());	   
       });
       ViewStats.setOnAction((event) -> {
    	   if(courseSelected())
         	main.showCourseStatsOverview(teach.getCourse(course.getValue()));// change later	   
       });
       /*
       loginStudent.setOnAction((event) -> {
    	   StudentLandingPageController.setStudent(student);
            main.showStudentLandingPageOverview();  
       });*/
       addStudent.setOnAction((event) -> {
    	   if(courseSelected())
               main.showAddStudent(teach.getCourse((String)course.getValue()));	   
          });
       addCourse.setOnAction((event) -> {
      		
              main.showAddCourse(teach);	   
          });
   }
   @FXML
   public void showStudents() {
	   
		   Course cor = teach.getCourse(course.getValue());
		   ArrayList<Student> students = cor.GetStudentList();
		   ObservableList<Student> data = FXCollections.observableArrayList(students);
		   nameCol = new TableColumn<Student,String>("Name");
		   nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		   emailCol = new TableColumn<Student,String>("Email");
		   emailCol.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		   studentTable.setItems(data);
		   studentTable.getColumns().setAll(nameCol,emailCol);
	   
   }
}
