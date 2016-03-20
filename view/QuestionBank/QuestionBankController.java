package view.QuestionBank;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Main;
import model.QuestionBank.Question;
import model.QuestionBank.QuestionBank;
import model.TestBank.Course;
import model.TestToolUser.Student;

public class QuestionBankController {
	
   @FXML
   private Button QBAdd;
   @FXML
   private Button QBDelete;
   @FXML
   private Button QBEdit;
   @FXML
   private Button QBAdvancedSearch;
   @FXML
   private Button QBSearch;
   @FXML
   TableView<Question> questionTable;
   
   private TableColumn<Question,String> descriptionCol;
   private TableColumn<Question,String> courseCol;
   private TableColumn<Question,String> topicCol;
   private TableColumn<Question,String> typeCol;
   private TableColumn<Question,Number> difficultyCol;
   
   private Main main;
   
   private Course course;
   
  
      
   public QuestionBankController() {
		
   }
   
   public void setCourse(Course c) {
	   this.course = c;
	   ObservableList<Question> data = FXCollections.observableArrayList(c.getQuestionBank().getQuestions());
	   Question q = new Question();
	   q.setQuestion("Question 1");
	   q.setCourse(c);
	   q.setTopic("fun");
	   q.setType("stuff");
	   q.SetDifficulty(69.0);
	   data.add(q);
	   if(data != null) {
		   descriptionCol = new TableColumn<Question,String>("Description");
		   descriptionCol.setCellValueFactory(cellData -> cellData.getValue().questionProperty());
		   courseCol = new TableColumn<Question,String>("Course");
		   courseCol.setCellValueFactory(cellData -> cellData.getValue().courseProperty());
		   topicCol = new TableColumn<Question,String>("Topic");
		   topicCol.setCellValueFactory(cellData -> cellData.getValue().topicProperty());
		   typeCol = new TableColumn<Question,String>("Type");
		   typeCol.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
		   difficultyCol = new TableColumn<Question,Number>("Difficulty");
		   difficultyCol.setCellValueFactory(cellData -> cellData.getValue().difficultyProperty());
		   
		   questionTable.setItems(data);
		   questionTable.getColumns().setAll(descriptionCol,courseCol,topicCol,typeCol,difficultyCol);
	   }
	   
   }
   
   public void setMain(Main main) {
       this.main = main;
   }
   
   @FXML
   private void initialize(){
	   QBAdd.setOnAction((event) -> {
		   main.initRootLayout();
		   main.showQuestionAddHandlerOverview("Question Bank", course.getCourseName(), null, null);
		   });
	   QBDelete.setOnAction((event) -> {
		   main.initRootLayout();
		   main.showDeleteQuestion();
		   });
	   QBEdit.setOnAction((event) -> {
		   main.initRootLayout();
		   main.showQuestionEditHandlerOverview();
		   });
	   QBAdvancedSearch.setOnAction((event) -> {
		   main.initRootLayout();
		   main.showQuestionBankSearch();
		   });
	   QBSearch.setOnAction((event) -> {
		   main.initRootLayout();
		   //main.showQuestionBankSearch();
		   });
   }
   
}
