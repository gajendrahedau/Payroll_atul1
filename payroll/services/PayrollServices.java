package com.cg.payroll.services;
import java.util.ArrayList;
import com.cg.payroll.beans.Associate;
import com.cg.payroll.exception.AssociateDetailsNotFoundException;
import com.cg.payroll.exception.PayrollServicesDownException;

public interface PayrollServices {
	int acceptAssociateDetails(int yearlyInvestmentUnder80C,String firstName,String lastName,
			String department,String designation,String panCard ,String emailId,
			int basicSalary, int epf, int companyPf,
			int accountNumber, 
			String bankName, String ifscCode)throws PayrollServicesDownException;
	
	int calculateNetSalary(int associateId)throws AssociateDetailsNotFoundException,
	PayrollServicesDownException;
	
	Associate getAssociateDetails(int associateId)throws PayrollServicesDownException, AssociateDetailsNotFoundException;
	
	ArrayList<Associate> getAllAssociateDetails() throws PayrollServicesDownException;
}
