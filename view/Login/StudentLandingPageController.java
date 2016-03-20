package view.Login;
import java.util.ArrayList;
import java.sql.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import model.Main;
import model.QuestionBank.MultipleChoiceQuestion;
import model.QuestionBank.Question;
import model.QuestionBank.WrittenResponseQuestion;
import model.TestBank.Course;
import model.TestBank.Test;
import model.TestToolUser.Student;
import view.TakeTest.ViewIncompleteTestsController;

public class StudentLandingPageController {
	
   @FXML
   private ChoiceBox<String> course;
   @FXML
   private Button takeTest;
   @FXML
   private Button completedTests;
    
   private Main main;
      
   private static Student student;
   
   ArrayList<String> courseNames;
   
   ObservableList<String> studentCourses;
   
   public void setMain(Main main) {
       this.main = main;
   }
   
   @FXML
   private void initialize(){
	   //Sample Tests
       
      
	   course.setItems(studentCourses);
	   course.setOnAction((event) -> {
		   	student.setCurrentCourse(student.getCourseArrayList().get(courseNames.indexOf(course.getValue())));
		   });
	   takeTest.setOnAction((event) -> {
		   	main.initRootLayout();
		   	ViewIncompleteTestsController.setStudent(student);
			main.viewAssignedTests();
		   });
	   completedTests.setOnAction((event) -> {
		   main.initRootLayout();
			main.viewCompletedTests(); 
		   });
   }
   
   public static void setStudent(Student student1) {
	  student = student1;
   }
   
   public StudentLandingPageController() {
	  courseNames = new ArrayList<>();
	   for (int i = 0; i < student.getCourseArrayList().size(); i++)
		   courseNames.add(student.getCourseArrayList().get(i).getCourseName());
	   studentCourses = FXCollections.observableArrayList(courseNames);
	   
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
       
   }
}