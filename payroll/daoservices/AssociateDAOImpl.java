package com.cg.payroll.daoservices;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.beans.BankDetails;
import com.cg.payroll.beans.Salary;
import com.cg.payroll.util.ConnectionProvider;
public class AssociateDAOImpl implements AssociateDAO{
		private Connection conn=ConnectionProvider.getDBConnection();
	@Override
	public Associate save(Associate associate) throws SQLException{
		try {
			conn.setAutoCommit(false);
			PreparedStatement pstmt1=conn.prepareStatement("insert into Associate(associateId,yearlyInvestmentUnder80C,firstName,lastName,department,designation,panCard ,emailId) values(?,?,?,?,?,?,?)");
			pstmt1.setInt(1, associate.getYearlyInvestmentUnder80C());
			pstmt1.setString(2, associate.getFirstName());
			pstmt1.setString(3, associate.getLastName());
			pstmt1.setString(4, associate.getDepartment());
			pstmt1.setString(5, associate.getDesignation());
			pstmt1.setString(6, associate.getPanCard());
			pstmt1.setString(7, associate.getEmailId());
			pstmt1.executeUpdate();
			
			PreparedStatement pstmt2=conn.prepareStatement("select max(associateId) from Associate");
			
			ResultSet rs=pstmt2.executeQuery();
			rs.next();
			int associateId=rs.getInt(1);
			
			PreparedStatement pstmt3=conn.prepareStatement("insert into BankDetails (associateId,accountNumber,bankName,ifscCode) values(?,?,?,?)");
			pstmt3.setInt(1, associateId);
			pstmt3.setInt(2, associate.getBankdetails().getAccountNumber());
			pstmt3.setString(3, associate.getBankdetails().getBankName());
			pstmt3.setString(4, associate.getBankdetails().getIfscCode());
			pstmt3.executeUpdate();
			
			PreparedStatement pstmt4=conn.prepareStatement("insert into Salary (associateId,basicSalary,epf,companyPf) values(?,?,?,?)");
			pstmt4.setInt(1, associateId);
			pstmt4.setInt(2, associate.getSalary().getBasicSalary());
			pstmt4.setInt(3, associate.getSalary().getEpf());
			pstmt4.setInt(4, associate.getSalary().getCompanyPf());
			pstmt4.executeUpdate();
			conn.commit();
			associate.setAssociateId(associateId);
			return associate;	
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		}
		finally{
			conn.setAutoCommit(true);
		}
	}
	@Override
	public Associate findOne(int associateId) throws SQLException {
		PreparedStatement pstmt1=conn.prepareStatement("Select * from Associate where associateId="+associateId);
		ResultSet associateRS=pstmt1.executeQuery();
		if(associateRS.next()){
			String firstName=associateRS.getString("firstName");
			String lastName=associateRS.getString("lastName");
			String department=associateRS.getString("department");
			String designation=associateRS.getString("designation");
			String panCard=associateRS.getString("panCard");
			String emailId=associateRS.getString("emailId");
			int yearlyInvestmentUnder80C=associateRS.getInt("yearlyInvestmentUnder80C");
			Associate associate=new Associate(associateId, yearlyInvestmentUnder80C, firstName, lastName, department, designation, panCard, emailId, null, null);
			
			PreparedStatement pstmt2=conn.prepareStatement("Select * from BankDetails where associateId="+associateId);
			ResultSet bankDetailsRs=pstmt2.executeQuery();
			bankDetailsRs.next();
			
			int accountNumber=bankDetailsRs.getInt("accountNumber");
			String bankName=bankDetailsRs.getString("bankName");
			String ifscCode=bankDetailsRs.getString("ifscCode");
			associate.setBankdetails(new BankDetails(accountNumber, bankName, ifscCode));
			
			PreparedStatement pstmt3=conn.prepareStatement("Select * from Salary where associateId="+associateId);
			
			ResultSet salaryRs=pstmt3.executeQuery();
			salaryRs.next();
			associate.setSalary(new Salary(salaryRs.getInt("basicSalary"),salaryRs.getInt("hra"),salaryRs.getInt("conveyenceAllowance"),salaryRs.getInt("otherAllowance"),salaryRs.getInt("personalAllowance"),salaryRs.getInt("monthlyTax"),salaryRs.getInt("epf"),salaryRs.getInt("companyPf"),salaryRs.getInt("grossSalary"),salaryRs.getInt("netSalary")));
			
			return associate;		
		}
		return null;
	}
	@Override
	public ArrayList<Associate> findAll() throws SQLException{
		PreparedStatement pstmt1=conn.prepareStatement("Select * from Associate");
		ResultSet associateRS=pstmt1.executeQuery();
		ArrayList<Associate> associates = new ArrayList<>();
		while(associateRS.next()){
			int associateId= associateRS.getInt("associate");
			String firstName=associateRS.getString("firstName");
			String lastName=associateRS.getString("lastName");
			String department=associateRS.getString("department");
			String designation=associateRS.getString("designation");
			String panCard=associateRS.getString("panCard");
			String emailId=associateRS.getString("emailId");
			int yearlyInvestmentUnder80C=associateRS.getInt("yearlyInvestmentUnder80C");
			Associate associate=new Associate(associateId, yearlyInvestmentUnder80C, firstName, lastName, department, designation, panCard, emailId, null, null);
			
			PreparedStatement pstmt2=conn.prepareStatement("Select * from BankDetails where associateId="+associateId);
			ResultSet bankDetailsRs=pstmt2.executeQuery();
			bankDetailsRs.next();
			
			int accountNumber=bankDetailsRs.getInt("accountNumber");
			String bankName=bankDetailsRs.getString("bankName");
			String ifscCode=bankDetailsRs.getString("ifscCode");
			associate.setBankdetails(new BankDetails(accountNumber, bankName, ifscCode));
			
			PreparedStatement pstmt3=conn.prepareStatement("Select * from Salary where associateId="+associateId);
			
			ResultSet salaryRs=pstmt3.executeQuery();
			salaryRs.next();
			associate.setSalary(new Salary(salaryRs.getInt("basicSalary"),salaryRs.getInt("hra"),salaryRs.getInt("conveyenceAllowance"),salaryRs.getInt("otherAllowance"),salaryRs.getInt("personalAllowance"),salaryRs.getInt("monthlyTax"),salaryRs.getInt("epf"),salaryRs.getInt("companyPf"),salaryRs.getInt("grossSalary"),salaryRs.getInt("netSalary")));
			associates.add(associate);
				
		}
		return associates;	
		
	}
	@Override
	public boolean update(Associate associate) {
			return false;
	}
}
