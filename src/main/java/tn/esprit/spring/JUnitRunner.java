package tn.esprit.spring;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import tn.esprit.spring.controller.RestControlEmployeTest;
import tn.esprit.spring.services.EmployeServiceImplTest;
public class JUnitRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(RestControlEmployeTest.class);
	    for (Failure failure : result.getFailures()) {
	      System.out.println(failure.toString());
	    }
	    System.out.println(result.wasSuccessful());

	}

}
