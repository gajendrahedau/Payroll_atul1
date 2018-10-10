package com.cg.banking.daoservices;

import java.util.ArrayList;
import java.util.HashMap;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;




public class AccountDAO {
	private static HashMap<Long, Account> accounts=new HashMap<>();
	/*private static HashMap<Long, Transaction> transactions=new HashMap<>();*/
	
	private static long ACCOUNT_NO_COUNTER=5001101;
	private static int PIN_NUMBER=0001;
	public Account save(Account account) {
		account.setAccountNo(ACCOUNT_NO_COUNTER++);
		if(PIN_NUMBER!=9999)
			account.setPinNumber(PIN_NUMBER++);
		else
			account.setPinNumber(0001);
		accounts.put(account.getAccountNo(), account);
		return account;
	}
	
	public Account findOne(long accountNo) {
		return accounts.get(accountNo);
	}
	
	public ArrayList<Account> findAll() {
		return new ArrayList<>(accounts.values());
	}
	
	/*public ArrayList<Transaction> findTransaction() {
		return new ArrayList<>(transactions.values());
	}*/


}
