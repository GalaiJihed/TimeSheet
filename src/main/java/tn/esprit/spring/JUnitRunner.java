package tn.esprit.spring;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import tn.esprit.spring.services.EmployeServiceImplTest;
public class JUnitRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(EmployeServiceImplTest.class);
	    for (Failure failure : result.getFailures()) {
	      System.out.println(failure.toString());
	    }

	}

}
