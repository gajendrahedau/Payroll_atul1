package com.cg.banking.daoservices;
import java.sql.SQLException;
import java.util.List;

import com.cg.banking.beans.Account;
public interface AccountDAO {
	Account save(Account account) throws SQLException;
	Account creditAmount(long accountNo, float amount);
	Account extractAmount(long accountNo, float amount);
	Account findOne(long accountNo);
	List<Account> findAll();
}