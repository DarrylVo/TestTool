package view.Menu;

import javafx.fxml.FXML;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Main;
import model.TestBank.Course;
import model.TestToolUser.Student;
import model.TestToolUser.Teacher;
import model.TestToolUser.User;

/** controller class for the Menu*/
public class TreeController {
          /** has reference to main executable */
	 private Main main;
	 private Teacher teach;
	 private Student stud;
	/** treeview to be shown as menu*/
	@FXML
	private TreeView data_treeview;

	/** intializes tree menu */
	public void initialize() {
		
        
		
		
	}
	
	public void setStudent(Student stud) {
		this.stud = stud;
TreeItem<String> treeItemRoot = new TreeItem<> ("Root");
  
        TreeItem<String> nodeItemA = new TreeItem<>("Home");
        
        treeItemRoot.getChildren().addAll(nodeItemA);
        data_treeview.setRoot(treeItemRoot);
        data_treeview.setShowRoot(false);
        data_treeview.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {            
                
                    TreeItem<String> item =(TreeItem) data_treeview.getSelectionModel().getSelectedItem();
                    if( item != null) {
	                    System.out.println("Selected Text : " + item.getValue());
	                    if(((String)item.getValue()).contains("Home")) {
	                    	main.showStudentLandingPageOverview();
	                    }
	                    int a = data_treeview.getSelectionModel().getSelectedIndex();
	                    data_treeview.getSelectionModel().clearSelection(a);
                    
                    }
                
            }
        });
	}
	
	public void setMain(Main main) {
        this.main = main;
    }
	public void setTeacher(Teacher teach) {
		this.teach = teach;
		TreeItem<String> treeItemRoot = new TreeItem<> ("Root");
        
        TreeItem<String> nodeItemA = new TreeItem<>("Home");
        TreeItem<String> nodeItemB = new TreeItem<>("Question Bank");
        
  
        TreeItem<String> nodeItemD = new TreeItem<>("Test Bank");
        treeItemRoot.getChildren().addAll(nodeItemA,nodeItemD ,nodeItemB);
         
        ArrayList<Course> courses = teach.getCourseArrayList();
        if(courses!=null) {
        	 for(int i = 0; i < courses.size();i++) {
             	TreeItem<String> c = new TreeItem<>(courses.get(i).getCourseName());
             	nodeItemD.getChildren().add(c);
             	TreeItem<String> c1 = new TreeItem<>(courses.get(i).getCourseName()+"\n");
             	nodeItemB.getChildren().add(c1);
             }
      
       
        
      
    
      
        
        data_treeview.setRoot(treeItemRoot);
        data_treeview.setShowRoot(false);
        data_treeview.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {            
                
                    TreeItem<String> item =(TreeItem) data_treeview.getSelectionModel().getSelectedItem();
                    if( item != null) {
	                    System.out.println("Selected Text : " + item.getValue());
	                    if(((String)item.getValue()).contains("Home")) {
	                    	main.showTeacherLandingPageOverview();
	                    }
	                    else if(item.getValue().contains("\n")) {
	                    	String s = item.getValue().substring(0, item.getValue().length()-1);
	                    	main.showQuestionBankOverview(teach.getCourse(s));
	                    	
	                    }
	                    	
	                    else if(courses.contains(teach.getCourse(item.getValue()))) {
	                    	main.showTestBankOverview(item.getValue());
	                    }
	                    int a = data_treeview.getSelectionModel().getSelectedIndex();
	                    data_treeview.getSelectionModel().clearSelection(a);
                    
                    }
                
            }
        });

	}

}}
