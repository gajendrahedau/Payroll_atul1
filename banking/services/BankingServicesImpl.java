package com.cg.banking.services;

import java.util.List;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.daoservices.AccountDAO;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.InsufficientAmountException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.exceptions.InvalidPinNumberException;

public class BankingServicesImpl implements BankingServices {
	
	
	private static int TR_ID=10100;	
	
	private AccountDAO accountDAO=new AccountDAO();
	@Override
	public long openAccount(String accountType, float initBalance)
			throws InvalidAmountException, InvalidAccountTypeException, BankingServicesDownException {
		Account account=new Account(accountType, initBalance);
		account=accountDAO.save(account);
		return account.getAccountNo();
	}

	@Override
	public float depositAmount(long accountNo, float amount)
			throws AccountNotFoundException, BankingServicesDownException, AccountBlockedException {
		
		
		
		Account account=accountDAO.findOne(accountNo);
		account.getTransaction().setAmount(amount);
		account.getTransaction().setTransactionType("Deposit");
		account.getTransaction().setTransactionId(TR_ID++);
				
		return account.getAccountBalance()+account.getTransaction().getAmount();
	}

	@Override
	public float withdrawAmount(long accountNo, float amount, int pinNumber) throws InsufficientAmountException,
			AccountNotFoundException, InvalidPinNumberException, BankingServicesDownException, AccountBlockedException {
		
		Account account=accountDAO.findOne(accountNo);
		float balance=account.getAccountBalance();
		if (account.getPinNumber()==pinNumber){
			account.getTransaction().setAmount(amount);
			account.getTransaction().setTransactionType("Withdrawal");
			account.getTransaction().setTransactionId(TR_ID++);
			balance=account.getAccountBalance()-account.getTransaction().getAmount();
		}
		return balance;
	}

	@Override
	public boolean fundTransfer(long accountNoTo, long accountNoFrom, float transferAmount, int pinNumber)
			throws InsufficientAmountException, AccountNotFoundException, InvalidPinNumberException,
			BankingServicesDownException, AccountBlockedException {
		Account accountOfReceiver=accountDAO.findOne(accountNoTo);
		Account accountOfSener=accountDAO.findOne(accountNoFrom);
		if (accountOfSener.getPinNumber()==pinNumber){
			accountOfSener.getTransaction().setAmount(transferAmount);
			accountOfSener.getTransaction().setTransactionType("Withdrawal");
			accountOfReceiver.getTransaction().setTransactionType("Withdrawal");
			accountOfSener.getTransaction().setTransactionId(TR_ID++);
			accountOfReceiver.getTransaction().setTransactionId(TR_ID++);
			
			accountOfSener.setAccountBalance(accountOfSener.getAccountBalance()-accountOfSener.getTransaction().getAmount());
			accountOfReceiver.setAccountBalance(accountOfReceiver.getAccountBalance()+accountOfReceiver.getTransaction().getAmount());
		}
		return false;
	}

	@Override
	public Account getAccountDetails(long accountNo) throws AccountNotFoundException, BankingServicesDownException {
		Account account=accountDAO.findOne(accountNo);
		return account;
	}

	@Override
	public List<Account> getAllAccountDetails() throws BankingServicesDownException {
		
		return accountDAO.findAll();
	}

	@Override
	public List<Transaction> getAccountAllTransaction(long accountNo)
			throws BankingServicesDownException, AccountNotFoundException {
		/*Account account=accountDAO.findOne(accountNo);
		Transaction transaction=account.getTransaction();
		return transaction;*/
		return null;
	}

	@Override
	public String accountStatus(long accountNo)
			throws BankingServicesDownException, AccountNotFoundException, AccountBlockedException {
		// TODO Auto-generated method stub
		return null;
	}

}