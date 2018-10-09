package com.cg.payroll.client;


import com.cg.payroll.exception.AssociateDetailsNotFoundException;
import com.cg.payroll.exception.PayrollServicesDownException;
import com.cg.payroll.services.PayrollServices;
import com.cg.payroll.services.PayrollServicesImpl;

public class MainClass {
	public static void main(String[] args) {
		PayrollServices payrollServices=new PayrollServicesImpl();
		try {
			payrollServices.acceptAssociateDetails( 100000,"Atul", "Anand", "NFS", "SR. Analyst", "CXP14568X", "atul@gmail.com", 17000, 12000, 12000, 500178412, "SBI", "SBI0001C");
		} catch (PayrollServicesDownException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*		try {
			System.out.println("Net Salary:"+payrollServices.calculateNetSalary(101));
		} catch (AssociateDetailsNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PayrollServicesDownException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	*/
		/*associate.getSalary().setHra((30*associate.getSalary().getBasicSalary())/100);
		associate.getSalary().setConveyenceAllowance((5*associate.getSalary().getBasicSalary())/100);
		associate.getSalary().setOtherAllowance((20*associate.getSalary().getBasicSalary())/100);
		associate.getSalary().setPersonalAllowance((25*associate.getSalary().getBasicSalary())/100);
		associate.getSalary().setGrossSalary(associate.getSalary().getBasicSalary()+associate.getSalary().getHra()+associate.getSalary().getConveyenceAllowance()+associate.getSalary().getOtherAllowance()+associate.getSalary().getPersonalAllowance());
		associate.getSalary().setMonthlyTax(((20*((associate.getSalary().getGrossSalary())*12))/100)/12);
		associate.getSalary().setNetSalary(associate.getSalary().getGrossSalary()-associate.getSalary().getMonthlyTax());
		
		System.out.println(associate.getSalary());*/	
	}
}
