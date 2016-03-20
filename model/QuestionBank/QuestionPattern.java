package model.QuestionBank;

import model.TestBank.Course;
import model.Statistics.Time;

public class QuestionPattern implements java.io.Serializable {

	private Course course;
	private String questionType;
	private double difficulty;
	private Time duration;
	private double percentOfTest;
	private String topic;
	
	public QuestionPattern(String topic, Course course, String type, double difficulty, Time duration, double percent) {
		setCourse(course);
		setQuestionType(type);
		setDifficulty(difficulty);
		setDuration(duration);
		setPercent(percent);
		setTopic(topic);
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public String getTopic() {
		return this.topic;
	}

	public void setCourse(Course course) {
		this.course=course;
	}
	
	public Course getCourse() {
		 return this.course;
	}
	
	public void setQuestionType(String type) {
		this.questionType = type;
	}
	
	public String getQuestionType() {
		return this.questionType;
	}
	
	public void setDifficulty(double difficulty) {
		this.difficulty = difficulty;
	}
	
	public double getDifficulty() {
		return this.difficulty;
	}
	
	public void setDuration(Time duration) {
		this.duration = duration;
	}
	
	public Time getDuration() {
		return this.duration;
	}
	
	public void setPercent(double percent) {
		this.percentOfTest = percent;
	}
	
	public double getPercent() {
		return this.percentOfTest;
	}
	
}
