package com.cg.banking.services;
import java.sql.SQLException;
import java.util.List;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.daoservices.AccountDAO;
import com.cg.banking.daoservices.AccountDAOImpl;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.InsufficientAmountException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.exceptions.InvalidPinNumberException;

public class BankingServicesImpl implements BankingServices {
	AccountDAO dao=new AccountDAOImpl();
	@Override
	public long openAccount(String accountType, float initBalance)
			throws InvalidAmountException, InvalidAccountTypeException, BankingServicesDownException {
		Account account=null;
		try {
			account=new Account(accountType,initBalance);
			account=dao.save(account);
		} catch (SQLException e) {
			System.out.println("From services openAccount");
			e.printStackTrace();
		}
		return account.getAccountNo();
	}

	@Override
	public float depositAmount(long accountNo, float amount)
			throws AccountNotFoundException, BankingServicesDownException, AccountBlockedException {
			Account account=getAccountDetails(accountNo);
			if(accountStatus(accountNo).equals("Blocked")) throw new AccountBlockedException("Account is Blocked From Deposit");			
			account.setAccountBalance(account.getAccountBalance()+amount);
			account=dao.creditAmount(accountNo,amount);
		return account.getAccountBalance();
	}

	@Override
	public float withdrawAmount(long accountNo, float amount, int pinNumber) throws InsufficientAmountException,
			AccountNotFoundException, InvalidPinNumberException, BankingServicesDownException, AccountBlockedException {
			Account account=getAccountDetails(accountNo);
			if(accountStatus(accountNo).equals("Blocked")) throw new AccountBlockedException("Account is Blocked From Withdraw");
			if(account.getPinNumber()!=pinNumber) throw new InvalidPinNumberException("Pin is Incorrect from Withdraw");
			if((account.getAccountBalance()-1000)<amount) throw new InsufficientAmountException("Balance is low.");
			account.setAccountBalance(account.getAccountBalance()-amount);
			account=dao.extractAmount(accountNo,amount);
		return account.getAccountBalance();
	}

	@Override
	public boolean fundTransfer(long accountNoTo, long accountNoFrom, float transferAmount, int pinNumber)
			throws InsufficientAmountException, AccountNotFoundException, InvalidPinNumberException,
			BankingServicesDownException, AccountBlockedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account getAccountDetails(long accountNo) throws AccountNotFoundException, BankingServicesDownException {
		Account account=dao.findOne(accountNo);
		if(account==null) throw new AccountNotFoundException("Account Not Exist from getAccountDetails");
		return account;
	}

	@Override
	public List<Account> getAllAccountDetails() throws BankingServicesDownException {
		return dao.findAll();
	}
	@Override
	public List<Transaction> getAccountAllTransaction(long accountNo)
			throws BankingServicesDownException, AccountNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String accountStatus(long accountNo)
			throws BankingServicesDownException, AccountNotFoundException, AccountBlockedException {
		Account account=getAccountDetails(accountNo);
		return account.getStatus();
	}
}
