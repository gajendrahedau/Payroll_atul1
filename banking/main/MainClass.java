package com.cg.banking.main;

import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.services.BankingServices;
import com.cg.banking.services.BankingServicesImpl;

public class MainClass {

	public static void main(String[] args) {
		BankingServices bankingServices=new BankingServicesImpl();
		try {
			bankingServices.openAccount("Savings", 10000);
		} catch (InvalidAmountException | InvalidAccountTypeException
				| BankingServicesDownException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
