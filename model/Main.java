package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.util.ArrayList;

import view.Menu.TreeController;
import view.QuestionBank.DeleteQuestionController;
import view.QuestionBank.DeleteQuestionYesController;
import view.QuestionBank.MCAnswerHandler;
import view.QuestionBank.QuestionBankController;
import view.QuestionBank.QuestionBankSearchController;
import view.QuestionBank.QuestionEditHandler;
import view.QuestionBank.addQuestionController;
import view.QuestionBank.ResponseAnswerOverviewController;
import view.QuestionBank.GraphicAnswerController;
import view.QuestionBank.CodeAnswerController;
import view.QuestionBank.MatchingAnswerController;

import view.TakeTest.TakeTest2MinutesController;
import view.TakeTest.TakeTestCodingController;
import view.TakeTest.TakeTestWrittenController;
import view.TakeTest.ViewCompletedTestsController;
import view.TakeTest.TakeTestController;
import view.TakeTest.TakeTestMCController;
import view.TakeTest.TakeTestSubmitConfirmController;
import view.TakeTest.TakeTestSubmitController;
import view.TakeTest.TakeTestTimeUpController;
import view.TakeTest.ViewIncompleteTestsController;
import view.TakeTest.ViewTestCompletedController;
import view.TestBank.TestBankController;
import view.TestBank.GenerateTestController;
import view.TestBank.GenerateSimpleTestController;
import view.TestBank.GenerateComplexTestController;
import view.TestBank.TestBankEditHandler;

import view.GradeTest.AddQuestionCommentController;
//import view.GradeTest.AddQuestionCommentController;
import view.GradeTest.CourseStatsController;
import view.GradeTest.GradeStudentTestMain;
import view.GradeTest.GradeTestController;
import view.GradeTest.GradeTestStudentCodingController;
import view.GradeTest.GradeTestStudentHandler;
import view.GradeTest.GradeTestStudentMCController;
import view.GradeTest.GradeTestStudentWrittenController;
import view.GradeTest.GradeViewStatsController;

import view.Login.AddCourseController;
import view.Login.AddStudentController;
//import view.Login.AddStudentController;
import view.Login.LoginController;
import view.Login.RegisterUserController;
import view.Login.StudentLandingPageController;
import view.Login.TeacherLandingPageController;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.GradingTest.Grader;
import model.TestBank.Course;
import model.TestBank.Test;
import model.TestBank.TestsTaken;
import model.TestToolUser.Student;
import model.TestToolUser.User;
import model.TestToolUser.UserDB;
import model.TestToolUser.Teacher;
import model.QuestionBank.MultipleChoiceQuestion;
import model.Statistics.Time;
import model.QuestionBank.Question;
import model.QuestionBank.WrittenResponseQuestion;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private AnchorPane login;
	private Stage secondaryStage;
	private UserDB userdb;
	private User user;
	private Student student;
	// remove later
	private Course course;

	// same

	public Main() {

		// change later
		// commented out because causing null pointers

		user = new Teacher("test");
		user.setPassword("test");
		user.setEmail("test");
		Course c1 = new Course("CSC 101");
		c1.addTopic("Strings");
		c1.addTopic("Arrays");
		c1.addTopic("Truth Table");
		Question mc1 = new Question();
		mc1.setQuestion("Program variables are stored in what part of the computer?");
		mc1.setTopic("Chapter 1");
		mc1.setPointValue(11);
		mc1.setDuration(new Time(0, 2, 0));
		mc1.SetDifficulty(2.0);
		mc1.setType("Multiple Choice");
		Question mc2 = new Question();
		mc2.setType("Multiple Choice");
		mc2.setQuestion("Which type of algorithm is Bubble Sort?");
		mc2.setDuration(new Time(0, 2, 0));
		mc2.SetDifficulty(2.0);
		mc2.setTopic("Chapter 2");
		mc2.setPointValue(9);
		c1.addQuestion(mc1);
		c1.addQuestion(mc2);
		Test t = new Test();
		t.addQuestion(mc1);
		t.addQuestion(mc2);
		t.setName("test 1");
		t.setNumQuestion(2);
		t.setDifficulty(1.0);
		c1.addTest(t);
		Test t2 = new Test();
		t2.setName("test 2");
		t2.setNumQuestion(0);
		t2.setDifficulty(0);
		c1.addTest(t2);

		Course c2 = new Course("CSC 102");
		c2.addTopic("Nodes");
		c2.addTopic("ArrayList");
		c2.addTopic("Linked List");
		Course c3 = new Course("CSC 103");
		Course c4 = new Course("CPE 357");
		user.addCourse(c1);
		user.addCourse(c2);
		user.addCourse(c3);
		user.addCourse(c4);

		student = new Student();
		student.setEmail("blahblah@gmail.com");
		student.setName("John Doe");
		student.setPassword("random");
		// Sample Tests
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
		myCourse.setCourseName("CPE 103");
		myCourse2.setCourseName("CSC 225");
		myCourse.addTest(test);
		myCourse2.addTest(test2);
		test.setCourse(myCourse);
		test2.setCourse(myCourse2);
		test.SetCourseName(test.getCourseName());
		test2.SetCourseName(test2.getCourseName());
		student.addCourse(myCourse);
		student.addCourse(myCourse2);
		user.addCourse(myCourse2);
		user.addCourse(myCourse);
		/*
		 * Test test = new Test(); test.setName("Test 1");
		 * test.setAssigned(Date.valueOf("2015-5-6")); Test test2 = new Test();
		 * test2.setName("Test 2"); test2.setAssigned(Date.valueOf("2015-7-9"));
		 *
		 * course = new Course(); course.setCourseName("CPE 103"); TestsTaken
		 * taken = new TestsTaken(); Student student = new Student();
		 * student.setName("John Smith"); student.setEmail("jsmith1@gmail.com");
		 * Student student2 = new Student(); student2.setName("Ashley Thompson"
		 * ); student2.setEmail("athompson1@gmail.com");
		 * test.setStudent(student); test2.setStudent(student2);
		 * taken.addTest(test); taken.addTest(test2); taken.SetTestName("Test 1"
		 * );
		 *
		 * course.AddTestTaken(taken);
		 *
		 * course.addTestTaken(taken);
		 */
	}

	@Override
	public void start(Stage primaryStage) {
		loadDB();
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("TestTool");

		initLoginScreen();
		// initRootLayout();

		// showTestBankOverview();
	}

	/** loads the login screen */
	public void initLoginScreen() {

		try {
			primaryStage.setOnHiding(new EventHandler<WindowEvent>() {

				public void handle(WindowEvent event) {

					save();

				}
			});
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LoginController.class.getResource("Login.fxml"));
			login = (AnchorPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(login);
			primaryStage.setScene(scene);
			primaryStage.show();
			LoginController controller = loader.getController();
			controller.setMain(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showRegister() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(RegisterUserController.class.getResource("RegisterUser.fxml"));
			AnchorPane reg = (AnchorPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(reg);
			primaryStage.setScene(scene);
			primaryStage.show();
			RegisterUserController controller = loader.getController();
			controller.setMain(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {

			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(TreeController.class.getResource("RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			TreeController tc = loader.getController();
			if (user instanceof Teacher)
				tc.setTeacher((Teacher) user);
			else
				tc.setStudent((Student) user);
			tc.setMain(this);
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			// TreeController controller = loader.getController();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showTestBankOverview(String course) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(TestBankController.class.getResource("TestBankOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);
			TestBankController controller = loader.getController();
			controller.setCourse(course);
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void viewGenerate(String course) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GenerateTestController.class.getResource("GenerateTest.fxml"));
			AnchorPane edittest = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(edittest);
			GenerateTestController controller = loader.getController();
			controller.setMain(this);
			controller.setCourse(course);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** displays the simple generate screen */
	public void viewSimpleGenerate(String course) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GenerateSimpleTestController.class.getResource("GenerateSimpleTest.fxml"));
			AnchorPane edittest = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(edittest);
			GenerateSimpleTestController controller = loader.getController();
			controller.setMain(this);
			controller.setCourse(course);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void viewComplexGenerate(String course) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GenerateComplexTestController.class.getResource("GenerateComplexTest.fxml"));
			AnchorPane edittest = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(edittest);
			GenerateComplexTestController controller = loader.getController();
			controller.setMain(this);
			controller.setCourse(course);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** displays the main grade test screen */
	public void showGradeTestOverview(Course course) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GradeTestController.class.getResource("GradeTestLanding.fxml"));
			BorderPane gradeOverview = (BorderPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(gradeOverview);
			GradeTestController controller = loader.getController();
			controller.setMain(this, course);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** displays the comment adding */
	/*
	 * public void showAddQuestionComment(Question question) { try { // Load
	 * person overview. FXMLLoader loader = new FXMLLoader();
	 * loader.setLocation(AddQuestionCommentController.class.getResource(
	 * "AddQuestionComment.fxml")); AnchorPane addComment = (AnchorPane)
	 * loader.load();
	 *
	 * AddQuestionCommentController controller = loader.getController();
	 * controller.setMain(this, question);
	 *
	 * Scene scene = new Scene(addComment); secondaryStage = new Stage();
	 * secondaryStage.setScene(scene); secondaryStage.setAlwaysOnTop(true);
	 * secondaryStage.show(); } catch (IOException e) { e.printStackTrace(); } }
	 */

	 /**displays the comment adding*/
    public void showAddQuestionComment(Question question, SimpleStringProperty commentProp) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AddQuestionCommentController.class.getResource("AddQuestionComment.fxml"));
            AnchorPane addComment = (AnchorPane) loader.load();

            AddQuestionCommentController controller = loader.getController();
            controller.setMain(this, question, commentProp);

            Scene scene = new Scene(addComment);
            secondaryStage = new Stage();
            secondaryStage.setScene(scene);
            secondaryStage.setAlwaysOnTop(true);
            secondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * displays the screen showing all the student test questions with the ability to
	 * edit the grades
	 */
	public void showGradeTestStudentOverview(String testname, Course course, Grader grader) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GradeTestStudentHandler.class.getResource("GradeTestStudent.fxml"));
			BorderPane gradeOverview = (BorderPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(gradeOverview);
			GradeTestStudentHandler controller = loader.getController();
			controller.setMain(this, testname, course, grader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * displays the screen showing all the student tests with the ability to
	 * edit the grades
	 */
	public void showGradeStudentTestMainOverview(String testname, Course course, Grader grader, Test test) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GradeStudentTestMain.class.getResource("GradeStudentTestMain.fxml"));
			BorderPane gradeOverview = (BorderPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(gradeOverview);
			GradeStudentTestMain controller = loader.getController();
			controller.setMain(this, testname, course, grader, test);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**displays the screen showing all the student test questions multiple choice with the ability to edit the grades*/
    public void showGradeTestStudentMCOverview(String testname, Course course, Grader grader) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GradeTestStudentMCController.class.getResource("GradeTestStudentMC.fxml"));
            BorderPane gradeOverview = (BorderPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(gradeOverview);
            GradeTestStudentMCController controller = loader.getController();
            controller.setMain(this, testname, course, grader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**displays the screen showing all the student test questions written answer with the ability to edit the grades*/
    public void showGradeTestStudentWrittenOverview(String testname, Course course, Grader grader) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GradeTestStudentWrittenController.class.getResource("GradeTestStudentWritten.fxml"));
            BorderPane gradeOverview = (BorderPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(gradeOverview);
            GradeTestStudentWrittenController controller = loader.getController();
            controller.setMain(this, testname, course, grader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**displays the screen showing all the student test questions coding with the ability to edit the grades*/
    public void showGradeTestStudentCodingOverview(String testname, Course course, Grader grader) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GradeTestStudentCodingController.class.getResource("GradeTestStudentCoding.fxml"));
            BorderPane gradeOverview = (BorderPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(gradeOverview);
            GradeTestStudentCodingController controller = loader.getController();
            controller.setMain(this, testname, course, grader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/** displays the screen with all the question and test statistics */
	public void showGradeTestStatsOverview(String testname, Course course, Grader grader) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GradeViewStatsController.class.getResource("ViewTestStats.fxml"));
			BorderPane gradeOverview = (BorderPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(gradeOverview);
			GradeViewStatsController controller = loader.getController();
			controller.setMain(this, testname, course, grader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** displays the screen with all the question and test statistics */
	public void showCourseStatsOverview(Course course) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CourseStatsController.class.getResource("CourseStats.fxml"));
			AnchorPane gradeOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(gradeOverview);
			CourseStatsController controller = loader.getController();
			controller.setMain(this, course);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** displays the screen for creating a question */
	public void showEditTestCreateQuestion() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(QuestionEditHandler.class.getResource("QuestionOverview.fxml"));
			AnchorPane qbOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(qbOverview);
			QuestionEditHandler controller = loader.getController();
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** displays the screen used for editing a test */
	public void showEditTest(String course, Test test) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(TestBankEditHandler.class.getResource("TestBankEdit.fxml"));
			AnchorPane edittest = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(edittest);
			TestBankEditHandler controller = loader.getController();
			controller.setCourse(course);
			controller.setMain(this);

			controller.setTest(test);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** displays the question bank landing page */
	public void showQuestionBankOverview(Course c) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(QuestionBankController.class.getResource("QuestionBank.fxml"));
			AnchorPane qbOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(qbOverview);
			QuestionBankController controller = loader.getController();
			controller.setMain(this);
			controller.setCourse(c);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** displays actual page to delete Question */
	public void showDeleteQuestion() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(DeleteQuestionController.class.getResource("DeleteQuestion.fxml"));
			AnchorPane deleteYesNo = (AnchorPane) loader.load();
			// Set person overview into the center of root layout.
			rootLayout.setCenter(deleteYesNo);
			DeleteQuestionController controller = loader.getController();
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** displays the question bank landing page */
	public void showQuestionBankSearch() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(QuestionBankController.class.getResource("QuestionBankSearch.fxml"));
			AnchorPane qbSearch = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(qbSearch);
			QuestionBankSearchController controller = loader.getController();
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** displays screen if yes is pressed after delete question */
	public void showDeleteQuestionYes() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(DeleteQuestionYesController.class.getResource("DeleteQuestionYes.fxml"));
			AnchorPane yesDelete = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(yesDelete);
			DeleteQuestionYesController controller = loader.getController();
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** displays the screen for adding a question */
	public void showQuestionAddHandlerOverview(String prev, String course, Question q, Test test) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(addQuestionController.class.getResource("QuestionOverview.fxml"));
			AnchorPane addOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(addOverview);

			addQuestionController controller = loader.getController();
			if(user.getCourse(course) !=null)
				controller.setCourse(user.getCourse(course));
			controller.setMain(this, prev, course, q, test);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** displays the screen for editing a question */
	public void showQuestionEditHandlerOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(QuestionEditHandler.class.getResource("QuestionOverview.fxml"));
			AnchorPane qbOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(qbOverview);
			QuestionEditHandler controller = loader.getController();
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** displays the screen for a multiple choice answer to a question */
	public void showMCAnswerHandlerOverview(MultipleChoiceQuestion q, Course c) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MCAnswerHandler.class.getResource("MCAnswerOverview.fxml"));
			AnchorPane qbOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(qbOverview);
			MCAnswerHandler controller = loader.getController();
			controller.setMain(this);
			controller.setCourse(c);
			controller.setQuestion(q);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** displays the screen for a multiple choice answer to a question */
	public void showResponseAnswerOverview(WrittenResponseQuestion q, Course c) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ResponseAnswerOverviewController.class.getResource("ResponseAnswerOverview.fxml"));
			AnchorPane qbOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(qbOverview);
			ResponseAnswerOverviewController controller = loader.getController();
			controller.setMain(this);
			controller.setCourse(c);
			controller.setQuestion(q);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// coding question
	/*public void showResponseAnswerOverview(WrittenResponseQuestion q, Course c) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ResponseAnswerOverviewController.class.getResource("ResponseAnswerOverview.fxml"));
			AnchorPane qbOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(qbOverview);
			ResponseAnswerOverviewController controller = loader.getController();
			controller.setMain(this);
			controller.setCourse(c);
			controller.setQuestion(q);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// graphic question
	public void showResponseAnswerOverview(WrittenResponseQuestion q, Course c) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ResponseAnswerOverviewController.class.getResource("ResponseAnswerOverview.fxml"));
			AnchorPane qbOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(qbOverview);
			ResponseAnswerOverviewController controller = loader.getController();
			controller.setMain(this);
			controller.setCourse(c);
			controller.setQuestion(q);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// matching question
	public void showResponseAnswerOverview(WrittenResponseQuestion q, Course c) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ResponseAnswerOverviewController.class.getResource("ResponseAnswerOverview.fxml"));
			AnchorPane qbOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(qbOverview);
			ResponseAnswerOverviewController controller = loader.getController();
			controller.setMain(this);
			controller.setCourse(c);
			controller.setQuestion(q);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	/** displays the admin landing page */
	public void showTeacherLandingPageOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(TeacherLandingPageController.class.getResource("TeacherLandingPage.fxml"));
			AnchorPane landingOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(landingOverview);
			TeacherLandingPageController controller = loader.getController();
			//controller.setStudent(student);
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** displays the student landing page */
	public void showStudentLandingPageOverview() {
		try {
			StudentLandingPageController.setStudent((Student) user);
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(StudentLandingPageController.class.getResource("StudentLandingPage.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);
			StudentLandingPageController controller = loader.getController();
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** displays the addStudent */
	public void showAddStudent(Course c) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AddStudentController.class.getResource("AddStudent.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			AddStudentController controller = loader.getController();
			controller.setMain(this);
			controller.setCourse(c);
			Scene scene = new Scene(personOverview);
			secondaryStage = new Stage();
			secondaryStage.setScene(scene);
			secondaryStage.setAlwaysOnTop(true);
			secondaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** displays the student page for viewing tests to take */
	public void viewAssignedTests() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ViewIncompleteTestsController.class.getResource("ViewIncompleteTestsOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);
			ViewIncompleteTestsController controller = loader.getController();
			controller.setStudent((Student)user);
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void viewCompletedTests() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ViewCompletedTestsController.class.getResource("ViewCompletedTests.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);
			ViewCompletedTestsController controller = loader.getController();
			controller.setStudent((Student)user);
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void viewTestCompleted() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ViewTestCompletedController.class.getResource("ViewTestCompleted.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);
			ViewTestCompletedController controller = loader.getController();
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** displays student page for taking a test */
	public void takeTestOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(TakeTestController.class.getResource("TakeTestBegin.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);
			TakeTestController controller = loader.getController();
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** displays actual page where student takes test */
	public void takeTestMC() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(TakeTestMCController.class.getResource("TakeTestMC.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);
			TakeTestMCController controller = loader.getController();
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void takeTestWritten() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(TakeTestWrittenController.class.getResource("TakeTestWritten.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);
			TakeTestWrittenController controller = loader.getController();
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void takeTestCoding() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(TakeTestCodingController.class.getResource("TakeTestCoding.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);
			TakeTestCodingController controller = loader.getController();
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void takeTest2Minutes() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(TakeTest2MinutesController.class.getResource("TakeTest2Minutes.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);
			TakeTest2MinutesController controller = loader.getController();
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void takeTestTimeUp() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(TakeTestTimeUpController.class.getResource("TakeTestTimeUp.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);
			TakeTestTimeUpController controller = loader.getController();
			controller.setStudent((Student)user);
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void takeTestSubmitConfirm() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(TakeTestSubmitConfirmController.class.getResource("TakeTestSubmitConfirm.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);
			TakeTestSubmitConfirmController controller = loader.getController();
			System.out.println("error one");
			controller.setMain(this);
			System.out.println("error two");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void takeTestSubmit() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(TakeTestSubmitController.class.getResource("TakeTestTestSubmitted.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);
			TakeTestSubmitController controller = loader.getController();
			System.out.println("Error 3");
			controller.setStudent((Student)user);
			controller.setMain(this);
			System.out.println("Error 4");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("userdb");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(userdb);
			out.close();
			fileOut.close();

		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public User getUser() {
		System.out.println("WHATDDUUUPPP");
		for (int i = 0; i < user.getCourseArrayList().size(); i++) {
			System.out.println(user.getCourseArrayList().get(i).getCourseName());
		}
		return user;
	}

	public void setUser(User u) {
		this.user = u;
	}

	public UserDB getDB() {
		return this.userdb;
	}

	public void loadDB() {
		/*
		 * this.userdb = new UserDB(); Teacher t = new Teacher();
		 * t.setEmail("test"); t.setPassword("test"); t.setName("dvo");
		 * t.addCourse(new Course("CPE 101")); userdb.addTeacher(t);
		 */

		try {
			FileInputStream fileIn = new FileInputStream("userdb");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			this.userdb = (UserDB) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			this.userdb = new UserDB();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return;
		}

	}

	public void showAddCourse(Teacher teach) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AddCourseController.class.getResource("AddCourse.fxml"));
			AnchorPane edittest = (AnchorPane) loader.load();
			AddCourseController controller = loader.getController();
			controller.setMain(this);
			controller.setTeacher(teach);

			Scene scene = new Scene(edittest);
			secondaryStage = new Stage();
			secondaryStage.setScene(scene);
			secondaryStage.setAlwaysOnTop(true);
			secondaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 *
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public Stage getSecondaryStage() {
		return secondaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
