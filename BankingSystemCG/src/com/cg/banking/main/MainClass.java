package com.cg.banking.main;

import java.util.List;

import com.cg.banking.beans.Account;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.services.BankingServices;
import com.cg.banking.services.BankingServicesImpl;

public class MainClass {
	public static void main(String[] args) {
		BankingServices bservices=new BankingServicesImpl();
		try {
			bservices.openAccount("Saving", 1000);
			List<Account>list=bservices.getAllAccountDetails();
			for (Account account : list) {
				System.out.println(account);
			}
			System.out.println("Single details\n\n\n");
			Account ac=bservices.getAccountDetails(10040);
			System.out.println(ac);
			
			 float amount=bservices.depositAmount(10044,300);
			System.out.println("Completed");
		} catch (InvalidAmountException e) {
			e.printStackTrace();
		} catch (InvalidAccountTypeException e) {
			e.printStackTrace();
		} catch (BankingServicesDownException e) {
			e.printStackTrace();
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		} catch (AccountBlockedException e) {
			e.printStackTrace();
		}  finally{
			
		}
	}
}
