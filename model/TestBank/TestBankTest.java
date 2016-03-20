package model.TestBank;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import javafx.collections.ObservableList;
import model.QuestionBank.Question;
import model.QuestionBank.QuestionPattern;
import model.Statistics.Time;
import model.TestToolUser.Teacher;

public class TestBankTest {
	public Teacher user;
	public ArrayList<QuestionPattern> patterns;
	public Course c3;
	public model.TestBank.Test t3;
	public model.TestBank.Test t4;
	public model.TestBank.Test t5;
	

	@Before
	public void setUp() throws Exception {
		user = new Teacher("Sample");
		Course c1 = new Course("CSC 101");
		c1.addTopic("Strings");
		c1.addTopic("Arrays");
		c1.addTopic("Truth Table");
		Question mc1 = new Question();
	    mc1.setQuestion("what is the meaning of life");
	    mc1.setDuration(new Time(0,2,0));
	    mc1.SetDifficulty(3.0);
		Question mc2 = new Question();
		mc2.setQuestion("how fat is your mom");
		mc2.setDuration(new Time(0,2,0));
		mc2.SetDifficulty(3.0);
		c1.addQuestion(mc1);
		c1.addQuestion(mc2);
		
		Course c2 = new Course("CSC 102");
		c2.addTopic("Nodes");
		c2.addTopic("ArrayList");
		c2.addTopic("Linked List");
		c3 = new Course("CSC 103");
		c3.addTopic("topic 1");
		c3.addTopic("topic 2");
		c3.addTopic("topic 3");
		String s = "a";
		for(int i = 0; i < 10; i ++) {
			Question q = new Question();
			q.setQuestion(s);
			q.SetDifficulty(3.0);
			q.setDuration(new Time(0,1,0));
			q.setTopic("topic 1");
			q.setType("Coding Question");
			c3.addQuestion(q);
			s=s.concat("b");
		}
		s="b";
		for(int i = 0; i < 10; i ++) {
			Question q = new Question();
			q.setQuestion(s);
			q.setDuration(new Time(0,1,0));
			q.SetDifficulty(3.0);
			q.setType("Multiple Choice");
			q.setTopic("topic 2");
			c3.addQuestion(q);
			s=s.concat("b");
		}
		s="c";
		for(int i = 0; i < 10; i ++) {
			Question q = new Question();
			q.setQuestion(s);
			q.setDuration(new Time(0,1,0));
			q.SetDifficulty(3.0);
			q.setTopic("topic 3");
			q.setType("Short Answer");
			c3.addQuestion(q);
			s=s.concat("b");
		}
		s="d";
		for(int i = 0; i < 10; i ++) {
			Question q = new Question();
			q.setQuestion(s);
			q.setDuration(new Time(0,1,0));
			q.SetDifficulty(3.0);
			q.setType("Coding Question");
			c3.addQuestion(q);
			s=s.concat("b");
		}
		
		Course c4 = new Course("CPE 357");
		user.addCourse(c1);
		user.addCourse(c2);
		user.addCourse(c3);
		user.addCourse(c4);
		
		patterns = new ArrayList<QuestionPattern>();
		patterns.add(new QuestionPattern("topic 1",user.getCourse("CSC 103"),"Coding Question",3.0,new Time(0,10,0),12));
		patterns.add(new QuestionPattern("topic 2",user.getCourse("CSC 103"),"Multiple Choice",3.0,new Time(0,10,0),15));
		patterns.add(new QuestionPattern("topic 3",user.getCourse("CSC 103"),"Short Answer",3.0,new Time(0,10,0),25));

		
	}

	@Test
	public void testGenerateSimpleTest() {
	model.TestBank.Test	newtest=user.generateSimpleTest("test 1", user.getCourse("CSC 101"), 1, "Medium", 3.0);
		
		assertEquals("test 1", newtest.getName());
		assertEquals(user.getCourse("CSC 101"),newtest.getCourse());
		assertEquals(1,newtest.getNumQuestion());
		assertEquals(3,newtest.getDifficulty(),0);
		
		model.TestBank.Test t2 = user.generateSimpleTest("test 2", user.getCourse("CSC 102"), 10, "Medium", 3.0);
		assertEquals("test 2",t2.getName());
		assertEquals(user.getCourse("CSC 102"),t2.getCourse());
		assertEquals(3,t2.getDifficulty(),0);
		
		t3 = user.generateSimpleTest("test 3", user.getCourse("CSC 103"), 40, "Short", 3.0);
		assertEquals("test 3",t3.getName());
		assertEquals(user.getCourse("CSC 103"),t3.getCourse());
		assertEquals(3,t3.getDifficulty(),0);
		assertEquals(new Time(0,30,0).getTotalSeconds(),t3.getDuration().getTotalSeconds());
		
		t4 = user.generateSimpleTest("test 4", user.getCourse("CSC 103"), 25, "Long", 3.0);
		assertEquals("test 4", t4.getName() );
		assertEquals(user.getCourse("CSC 103"),t4.getCourse());
		assertEquals(3,t4.getDifficulty(),0);
		assertEquals(25,t4.getNumQuestion());
	}

	@Test
	public void testGenerateComplexTest() {
		Course c = user.getCourse("CSC 103");
		t5 = c.generateComplexTest("test 5", c, 50, patterns, new Time(3,0,0));
		assertEquals("test 5", t5.getName() );
		assertEquals(user.getCourse("CSC 103"),t5.getCourse());
	}
	
	@Test
	public void testAddTest() {
		t3 = user.generateSimpleTest("test 3", user.getCourse("CSC 103"), 40, "Short", 3.0);
		t4 = user.generateSimpleTest("test 4", user.getCourse("CSC 103"), 25, "Long", 3.0);
		
		ObservableList<model.TestBank.Test> testList = c3.getTestBank();
		
		assertTrue(testList.contains(t3));
		assertTrue(testList.contains(t4));
		
	}
	
	@Test public void testDeleteTest() {
		Course c = user.getCourse("CSC 103");
		t3 = user.generateSimpleTest("test 3", user.getCourse("CSC 103"), 40, "Short", 3.0);
		t4 = user.generateSimpleTest("test 4", user.getCourse("CSC 103"), 25, "Long", 3.0);
		
		c3.deleteTest(t4);
		c3.deleteTest(t5);
		assertTrue(c3.searchTest(t3));
		assertFalse(c3.searchTest(t4));
		assertFalse(c3.searchTest(t5));
	}

}
