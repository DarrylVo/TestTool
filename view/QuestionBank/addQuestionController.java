package view.QuestionBank;

import java.io.Console;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import model.Main;
import model.GradingTest.Grader;
import model.QuestionBank.Question;
import model.Statistics.Time;
import model.TestBank.Course;
import model.TestBank.Test;
import model.QuestionBank.MultipleChoiceQuestion;
import model.QuestionBank.WrittenResponseQuestion;



public class addQuestionController {

	private Course course;
    /**
     * Variable that holds the answerShort JavaFX item.
     */
    @FXML
    private Tab answerShort;
    /**
     * Variable that holds the anserMC JavaFX item.
     */
    @FXML
    private Tab answerMC;
    /**
     * Variable that holds the answerGraphics JavaFX item.
     */
    @FXML
    private Tab answerGraphics;
   /**
    * Variable that holds the MC JavaFX item.
    */
	@FXML
	private RadioButton MC;
	/**
    * Variable that holds the TF JavaFX item.
    */
	@FXML
	private RadioButton TF;
	/**
    * Variable that holds the fill JavaFX item.
    */
	@FXML
	private RadioButton fill;
	/**
    * Variable that holds the shortAns JavaFX item.
    */
	@FXML
	private RadioButton shortAns;
	/**
    * Variable that holds the longAns JavaFX item.
    */
	@FXML
	private RadioButton longAns;
	/**
    * Variable that holds the next JavaFX item.
    */
	@FXML
	private Button next;

	/**
    * Variable that holds the cancel JavaFX item.
    */
	@FXML
	private Button cancel;

	/**
    * Variable that holds the courseName JavaFX item.
    */
	@FXML
	private Label courseName;

	/**
    * Variable that holds the topic JavaFX item.
    */
	@FXML
	private TextField topic;



	/**
    * Variable that holds the title JavaFX item.
    */

	/**
    * Variable that holds the difficulty JavaFX item.
    */
	@FXML
	private TextField difficulty;

	/**
    * Variable that holds the tim JavaFX item.
    */
	@FXML
	private TextField time;

	/**
    * Variable that holds the instructions JavaFX item.
    */
	@FXML
	private TextArea instructions;

	/**
	 * Variable that holds the questionText JavaFX item.
	 */
	@FXML
	private TextArea questionText;

	/**
	 * Main variable that holds the main instance of a program.
	 */
	private Main main;

	/**
	 * Variable that holds the question that is being added or edited.
	 */
	private Question question;

	/**
	 * Previous screen that the view came from.
	 */
	private String prevScreen;
	/**
	 * Test that the question is being edited/added to.
	 */
	private Test test;

	/**
	 * Course that the question is being edited/added to.
	 */
	private String prevCourse; // Is null unless add question comes from testbank

	public void setCourse(Course c) {
		this.course = c;
		courseName.setText("course:"+c.getCourseName());



	}
	/**
	 * Initialized the button controllers of the view.
	 */
	@FXML
	private void initialize(){


	   next.setOnAction((event) -> {
	      if (!questionText.equals(null)) {
	         question.setQuestion(questionText.getText());
	         question.setTopic(topic.getText());
	         question.SetInstructions(instructions.getText());
	         question.SetDifficulty(Double.parseDouble(difficulty.getText()));
	         question.setDuration(new Time(0,Integer.parseInt(time.getText()),0));
	      }


	      if(this.question.verifyQuestion() == 1){
          	MultipleChoiceQuestion mcq;
          	WrittenResponseQuestion wrq;

	         switch (question.getType()){
	            case "MC":
	            	mcq = new MultipleChoiceQuestion();
	            	mcq.setQuestion(question.getQuestion());
	            	mcq.setTopic(question.getTopic());
	            	mcq.SetInstructions(question.getInstructions());
	            	mcq.SetDifficulty(question.getDifficulty());
	            	mcq.setDuration(question.getDuration());

	               main.showMCAnswerHandlerOverview(mcq,course); // change to MCAnswerOverview
	               break;
	            case "TF":
	            	mcq = new MultipleChoiceQuestion();
	            	mcq.setQuestion(question.getQuestion());
	            	mcq.setTopic(question.getTopic());
	            	mcq.SetInstructions(question.getInstructions());
	            	mcq.SetDifficulty(question.getDifficulty());
	            	mcq.setDuration(question.getDuration());

	               main.showMCAnswerHandlerOverview(mcq,course); // change to MCAnswerOverview
	               break;
	            case "match":
	               break;
	            case "fill":
	            	wrq = new WrittenResponseQuestion();
	            	wrq.setQuestion(question.getQuestion());
	            	wrq.setTopic(question.getTopic());
	            	wrq.SetInstructions(question.getInstructions());
	            	wrq.SetDifficulty(question.getDifficulty());
	            	wrq.setDuration(question.getDuration());


	               main.showResponseAnswerOverview(wrq,course); // change to MCAnswerOverview
	               break;
	            case "shortAns":
	            	wrq = new WrittenResponseQuestion();
	            	wrq.setQuestion(question.getQuestion());
	            	wrq.setTopic(question.getTopic());
	            	wrq.SetInstructions(question.getInstructions());
	            	wrq.SetDifficulty(question.getDifficulty());
	            	wrq.setDuration(question.getDuration());

	               main.showResponseAnswerOverview(wrq,course); // change to MCAnswerOverview
	               break;
	            case "longAns":
	            	wrq = new WrittenResponseQuestion();
	            	wrq.setQuestion(question.getQuestion());
	            	wrq.setTopic(question.getTopic());
	            	wrq.SetInstructions(question.getInstructions());
	            	wrq.SetDifficulty(question.getDifficulty());
	            	wrq.setDuration(question.getDuration());

	               main.showResponseAnswerOverview(wrq,course); // change to MCAnswerOverview
	               break;
	            case "graphic":
	               break;
	            case "coding":
	               break;
	            default:
	               break;
	         }
	      }
	      });

		cancel.setOnAction((event) -> {
			if (prevScreen.equals("Question Bank")){
			   main.showQuestionBankOverview(this.course); // change to Question Bank
			}
			else{
			   main.showEditTest(this.prevCourse, this.test);   // change to Test edit
			}
	      });
		MC.setOnAction((event) -> {
		   question.setType("MC");
		   clearRadioExcept("MC");
         });
		TF.setOnAction((event) -> {
         question.setType("TF");
         clearRadioExcept("TF");
         });
		fill.setOnAction((event) -> {
         question.setType("fill");
         clearRadioExcept("fill");
         });
		shortAns.setOnAction((event) -> {
         question.setType("shortAns");
         clearRadioExcept("shortAns");
         });
		longAns.setOnAction((event) -> {
         question.setType("longAns");
         clearRadioExcept("longAns");
         });
	}

	/**
	 * Sets the main of the controller.
	 * @param main Main to set to.
	 * @param prev Previous screen that the view came from.
	 * @param course Course of the question that is being added to.
	 * @param q Question that is being edited.
	 * @param t Test that the question is being added to.
	 */
	public void setMain(Main main, String prev, String course, Question q, Test t) {
		System.out.println(this.course.getCourseName());
        this.main = main;
        this.prevScreen = prev;
        this.prevCourse = course; // Set if editing test
        this.question = q; // Set if editing test
        if (question == null) {   // If adding a new question
           question = new Question();
        }
        this.test = t; // Set if editing test
        if (this.question != null) {
           if (question.getType() != null && !question.getType().isEmpty()) {
              clearRadioExcept(this.question.getType());
           }
           if (question.getDuration() != null){
              time.setText(String.valueOf(question.getDuration().GetMinute()));
           }
           if (question.getQuestion() != null && !question.getQuestion().isEmpty()){
              questionText.setText(question.getQuestion());
           }
           if (question.getInstructions() != null &&
                 !question.getInstructions().isEmpty()){
              instructions.setText(question.getInstructions());
           }
           if (question.getTopic() != null && !question.getTopic().isEmpty()){
              topic.setText(question.getTopic());
           }/*
           if (this.prevCourse != null && !this.prevCourse.isEmpty()){
              topic.setText(question.getTopic());
           }*/
        }
	}
	/**
	 * Unsets all the radio buttons except for the one specified
	 * @param radio Name of radio button to leave selected
	 */
	public void clearRadioExcept(String radio){
	   MC.setSelected(false);
	   TF.setSelected(false);
	   fill.setSelected(false);
	   shortAns.setSelected(false);
	   longAns.setSelected(false);

	   switch (radio){
	      case "MC":
	         MC.setSelected(true);
	         break;
	      case "TF":
	         TF.setSelected(true);
	         break;
	      case "fill":
	         fill.setSelected(true);
	         break;
	      case "shortAns":
	         shortAns.setSelected(true);
	         break;
	      case "longAns":
	         longAns.setSelected(true);
	         break;

	      default:
	         break;
	   }
	}

}
