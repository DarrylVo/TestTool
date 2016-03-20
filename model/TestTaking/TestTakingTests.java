package model.TestTaking;

import org.junit.Assert;
import org.junit.Test;

import model.QuestionBank.Question;
import model.Statistics.Time;

public class TestTakingTests {

	@Test
	public void testSetInterval() {
		Timer timer = new Timer();
		timer.setInterval(180);
		Time time = new Time();
		time.setTime(0, 3, 0);
		timer.SetTimeRemaining(time);
		Assert.assertEquals(timer.SetInterval(), 179);
	}

	@Test
	public void testTimerStop() {
		Timer timer = new Timer();
		timer.setInterval(1);
		Time time = new Time();
		time.setTime(0, 0, 1);
		timer.SetTimeRemaining(time);
		Time time2 = new Time();
		time2.setTime(0, 0, 1);
		timer.setTimeBegin(time2);
		Assert.assertEquals(timer.GetTimeRemaining().GetMinute(), timer.getTimeBegin().GetMinute());
		Assert.assertEquals(timer.GetTimeRemaining().GetHour(), timer.getTimeBegin().GetHour());
		Assert.assertEquals(timer.GetTimeRemaining().GetSecond(), timer.getTimeBegin().GetSecond());
		timer.start();
		Assert.assertNotEquals(timer.GetTimeRemaining(), timer.getTimeBegin());
	}
	
	@Test
	public void testGetQuestions() {
		Viewing viewing = new Viewing();
		Question question = new Question();
		model.TestBank.Test test = new model.TestBank.Test();
		test.addQuestion(question);
		viewing.SetTest(test);
		Assert.assertEquals(viewing.GetQuestionsRemaining(), 1);
		test.getQuestions().get(0).setAnswered();
		Assert.assertEquals(viewing.GetQuestionsRemaining(), 0);
	}
}
