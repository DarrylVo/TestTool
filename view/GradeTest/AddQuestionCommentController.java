package view.GradeTest;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import model.Main;
import model.QuestionBank.Question;
import model.TestBank.Comment;

public class AddQuestionCommentController {

	@FXML
	private TextArea comment;
	@FXML
	private Button create;
	@FXML
	private Button cancel;
	
    /**the main class for creating screens*/
	private Main main;
	private Question question;
	private SimpleStringProperty commentProp;
	
	/**method to react to ui events*/
	@FXML
	private void initialize() {
		create.setOnAction((event) -> {
			CreateComment(comment.getText());
			main.getSecondaryStage().close();
	      });
		
		cancel.setOnAction((event) -> {
			main.getSecondaryStage().close();
	      });
	}


	private void CreateComment(String msg) {
		if (comment == null || comment.equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("Can't Create Comment");
	        alert.setHeaderText("No Text");
	        alert.setContentText("Please type in text before creating the comment");

	        alert.showAndWait();
		}
		else {
			question.setComment(new Comment(msg));
			commentProp.setValue(msg);
		}
		
	}
	
	public void setMain(Main main, Question question, SimpleStringProperty commentProp) {
		this.main = main;
		this.question = question;
		this.commentProp = commentProp;
	}
}
