package com.cg.eis.pl;

import com.cg.eis.service.EmployeeService;
import com.cg.eis.service.EmployeeServiceImpl;

public class MainClass {
	public static void main (String[] args){
		EmployeeService emp=new EmployeeServiceImpl();
		emp.acceptEmployeeDetails(101, "Atul", "System Associate", 10000);
		emp.findScheme();
		System.out.println("Scheme for the employee is : "+ emp.display());
	}
}
