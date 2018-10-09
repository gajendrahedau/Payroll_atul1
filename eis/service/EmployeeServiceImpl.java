package com.cg.eis.service;

import com.cg.eis.bean.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	Employee emp;
	@Override
	public void acceptEmployeeDetails(int id, String name, String designation,
			int salary) {
		emp =new Employee(id, salary, name, designation);
		
	}
	@Override
	public void findScheme(){
		String desg=emp.getDesignation();
		int sal=emp.getSalary();
		if(desg=="System Associate" && sal>=5000 && sal<20000)
			emp.setInsuranceScheme("Scheme C");
		else if(desg=="Programmer" && sal>=20000 && sal<40000)
			emp.setInsuranceScheme("Scheme B");
		else if(desg=="Manager" && sal>=40000)
			emp.setInsuranceScheme("Scheme A");
		else if(desg=="Clerk" && sal<5000)
			emp.setInsuranceScheme("No Scheme");		
	}
	@Override
	public String display() {
		return emp.getInsuranceScheme();
	}


}
