package model.GradingTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class GradingTestRunner {
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(GradingTest.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
  }
}