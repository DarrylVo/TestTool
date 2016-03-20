package view.TestBank;
import model.QuestionBank.QuestionPattern;
import model.Statistics.Time;
import model.TestBank.Course;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import model.Main;
import java.util.ArrayList;
import model.TestToolUser.Teacher;
/** Class GenerateComplexTestController is the controller class
 * for the generate complex test gui page
 * @author Darryl Vo(dvo03@calpoly.edu)
 *
 */

public class GenerateComplexTestController {
	/** reference to the main */
	private Main main;
	/** reference to main teacher */
	private Teacher teach;
	/** string holding the current selected course */
	private String curCourse = "";
	/** used to create new rows for gridpane */
	int i =1;
	/** grid pane used to create question pattern */
	@FXML
	GridPane gpane;
	/** choicebox to choose which course to pull questions from */
	@FXML
	Label courseLabel;
	/** textfield to type in name of test */
	@FXML
	TextField name;
	/**text field to type in number of questions. will only accept ints */
	@FXML
	TextField numquestion;
	/** text field to get how many hours the duration of the test should be*/ 
	@FXML
	TextField hour;
	/** text field to get how many minutes the duration of the test should be*/ 
	@FXML
	TextField minute;
	/** holds all of the newly created buttons when you add a question pattern row
	 * in the gridpane
	 */
	private ArrayList<Button> button = new ArrayList<Button>();
	/** holds all of the newly created subject combo boxes when you add a question pattern row
	 * in the gridpane
	 */
	private ArrayList<ComboBox<String>> subject = new ArrayList<ComboBox<String>>();
	/** holds all of the newly created question type combo boxes when you add a question pattern row
	 * in the gridpane
	 */
	private ArrayList<ComboBox<String>> questiontype = new ArrayList<ComboBox<String>>();
	/** holds all of the newly created difficutly combo boxes when you add a question pattern row
	 * in the gridpane
	 */
	private ArrayList<ComboBox<Double>> difficulty = new ArrayList<ComboBox<Double>>();
	/** holds all of the newly created duration combo boxes when you add a question pattern row
	 * in the gridpane
	 */
	private ArrayList<TextField> duration = new ArrayList<TextField>();
	/** holds all of the newly created percent text fields when you add a question pattern row
	 * in the gridpane
	 */
	private ArrayList<TextField> percent = new ArrayList<TextField>();
	

	/** holds the topic list for the selected course */
	private ArrayList<String> topiclist;
	
	private String course;
	
	/** sets the main reference */
	public void setMain(Main main) {
        this.main = main;
        
        teach = (Teacher)main.getUser();
    }
	
	public void setCourse(String course) {
		this.course = course; 
		courseLabel.setText(this.course);
		topiclist=teach.getCourse(course).getTopicArrayList();

	}
	/*
	@FXML
	public void handleTopic() {
		if ((String) course.getValue()==null)
			System.out.println("pick a course plz");
		else {
			String ctemp = (String) course.getValue();
			Course selectedcourse = teach.getCourse(ctemp);
		}
			
	}*/
	/** adds a question pattern row in the gui */
	@FXML
	public void addrow() {
		//gpane.addRow(i, new Text(""));
		
		Button b = new Button("Delete");
		b.setMinWidth(85);
		b.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	i--;
		    	int index = gpane.getRowIndex(b)-1;
		    	
		    	gpane.getChildren().remove(button.get(index));
		    	button.remove(index);
		    	//gpane.setConstraints(button.get(index-1),5 ,index);
		    	
		    	gpane.getChildren().remove(subject.get(index));
		    	subject.remove(index);
		    	//gpane.setConstraints(subject.get(index-1),0 ,index);
		    	
		    	gpane.getChildren().remove(questiontype.get(index));
		    	questiontype.remove(index);
		    	//gpane.setConstraints(questiontype.get(index-1),1 ,index);
		    	
		    	gpane.getChildren().remove(difficulty.get(index));
		    	difficulty.remove(index);
		    	//gpane.setConstraints(difficulty.get(index-1),2 ,index);
		    	
		    	gpane.getChildren().remove(duration.get(index));
		    	duration.remove(index);
		    	//gpane.setConstraints(duration.get(index-1),3 ,index);
		    	
		    	gpane.getChildren().remove(percent.get(index));
		    	percent.remove(index);
		    	//gpane.setConstraints(percent.get(index-1),4 ,index);
		    	for(int z=index; z<button.size();z++) {
		    		gpane.setRowIndex(button.get(z), gpane.getRowIndex(button.get(z))-1);
		    		gpane.setRowIndex(subject.get(z), gpane.getRowIndex(subject.get(z))-1);
		    		gpane.setRowIndex(questiontype.get(z), gpane.getRowIndex(questiontype.get(z))-1);
		    		gpane.setRowIndex(difficulty.get(z), gpane.getRowIndex(difficulty.get(z))-1);
		    		gpane.setRowIndex(duration.get(z), gpane.getRowIndex(duration.get(z))-1);
		    		gpane.setRowIndex(percent.get(z), gpane.getRowIndex(percent.get(z))-1);
		    		
		    	}
		    }
		});
		button.add(b);
		
		TextField tf = new TextField();
		percent.add(tf);
		
		ComboBox<String> qt = new ComboBox<String>();
		qt.getItems().addAll(   "Coding", "MC", "Short Answer", "Long Answer"   );
		qt.setMinWidth(102);
		questiontype.add(qt);
		
		TextField dur = new TextField();
		
		dur.setMinWidth(102.0);
		duration.add(dur);
		
		ComboBox<Double> d = new ComboBox<Double>();
		d.getItems().addAll(1.0,2.0,3.0,4.0,5.0);
		d.setMinWidth(102.0);
		difficulty.add(d);
		
		ComboBox<String> n = new ComboBox<String>();
		n.setMinWidth(102.0);
		
		for(int i = 0; i<topiclist.size();i++) {
			n.getItems().add(topiclist.get(i));
		}
		
		subject.add(n);
		
		
		/*
		gpane.setConstraints(n,0,i);
		gpane.setConstraints(d,2,i);
		gpane.setConstraints(dur,3,i);
		gpane.setConstraints(qt,1,i);
		gpane.setConstraints(tf,4,i);
		gpane.setConstraints(b,5,i);
		gpane.getChildren().addAll(n,d,dur,qt,tf,b);
		*/
		gpane.addRow(i, n,qt,d,dur,tf,b);
		//gpane.getRowConstraints().add(new RowConstraints(77));
		i++;
		for(int i = 0; i<button.size();i++) {
    		System.out.println("I:"+gpane.getRowIndex(button.get(i)));
    		
    	}
		System.out.println("");
	}
	@FXML
	public void handleDeleteRow() {
		
	}
	
	@FXML
	public void handleCreated() {
		
		ArrayList<QuestionPattern> questionpatterns = new ArrayList<QuestionPattern>();
		if(subject.size()==0) {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("Error");
	        alert.setHeaderText("No question patterns");
	        alert.setContentText("There are no question patterns. Click add question pattern!");
	        alert.showAndWait();
		}
		for(int z = 0; z <subject.size();z++) {
			//System.out.println("row:"+z+1);
			
			String subj = subject.get(z).getValue();
			if(subj!=null)
				System.out.println("Subject:"+subj);
			else
				System.out.println("pick a subject");
			
			String qt = questiontype.get(z).getValue();
			if(qt!=null)
				System.out.println("Question TYpe:"+qt);
			else
				System.out.println("pick a question type");
			
			if(difficulty.get(z).getValue()!=null)
				System.out.println("Difficulty:"+(double)difficulty.get(z).getValue());
			else
					System.out.println("pick a difficulty");
			double dur = 0;
			try {
				dur = Integer.parseInt(duration.get(z).getText());
				
				System.out.println("Duration:"+dur);
			}
			catch( NumberFormatException e) {
				System.out.println("invalid number or no number exists for Percent field");
			}
			double prcnt = 0;
			try {
				prcnt = Integer.parseInt(percent.get(z).getText());
				
				System.out.println("Percent:"+prcnt);
			}
			catch( NumberFormatException e) {
				System.out.println("invalid number or no number exists for Percent field");
			}
			if(subj!=null&&qt!=null&&difficulty.get(z).getValue()!=null&&prcnt!=0&&dur!=0) {
				questionpatterns.add(new QuestionPattern(subj,teach.getCourse(course),qt,difficulty.get(z).getValue(),new Time(0,(int)dur,0),prcnt));
			}
			
			else {
				Alert alert = new Alert(AlertType.WARNING);
		        alert.initOwner(main.getPrimaryStage());
		        alert.setTitle("Error");
		        alert.setHeaderText("Invalid Question Pattern(s)");
		        alert.setContentText("One or more of the question patterns are empty/invalid. P"
		        		+ "lease check over them.");
		        alert.showAndWait();
		        break;
			}
				
			
		}
		String nme = name.getText();
		if (nme.equals("")||nme.equals(" "))
			System.out.println("type a name");
		else
			System.out.println("name"+nme);
		//
		int nq = 0;
		try {
			nq = Integer.parseInt( (String) numquestion.getText());
			
			System.out.println("num question:"+nq);
		}
		catch( NumberFormatException e) {
			System.out.println("num question:invalid number or no number exists");
		}
		//
		int hoursz = 0;
		try {
			hoursz = Integer.parseInt( (String) hour.getText());
			
			System.out.println("hours:"+hoursz);
		}
		catch( NumberFormatException e) {
			System.out.println("hour: invalid number or no number exists");
		}
		
		//
		int minutesz = 0;
		try {
			minutesz = Integer.parseInt( (String) minute.getText());
			
			System.out.println("minutes:"+minutesz);
		}
		catch( NumberFormatException e) {
			System.out.println("minutes: invalid number or no number exists");
		}
		if (questionpatterns.size()==0)
			System.out.println("no valid questionpatterns");
		else {
			teach.getCourse(course).generateComplexTest(nme,teach.getCourse(course), nq, questionpatterns, new Time(hoursz,minutesz,0));
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("Success");
	        alert.setHeaderText("Your Complex test has been Generated");
	        alert.setContentText("You will now be taken back to the test bank overview");
	        alert.showAndWait();
	        main.showTestBankOverview(course);
		}
	}
	

}
