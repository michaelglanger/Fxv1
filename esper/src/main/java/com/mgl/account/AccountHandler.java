package com.mgl.account;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.mgl.account.exception.AccountUnknown;
import com.mgl.account.exception.AccountAlreadyExists;

public class AccountHandler {

	private static AccountHandler instance;
	private static Map<String, Account> accounts;
	private static int defaultInitValue;
	
	private AccountHandler() {
		accounts = new HashMap<String, Account>();
		defaultInitValue = 10000;
	}
	
	public static AccountHandler getInstance() {
		if (instance == null) {
			instance = new AccountHandler();
		}
		
		return instance;
	}
	
	public void setDefaultInitValue(int init) {
		defaultInitValue = init;
	}
	
	public int getDefaultInitValue() {
		return defaultInitValue;
	}
	
	public Account createAccount(String accountName) throws AccountAlreadyExists {
		if (existAccount(accountName)) {
			throw new AccountAlreadyExists(accountName);
		}
		Account ac = new Account(accountName, new BigDecimal(defaultInitValue));
		accounts.put(accountName, ac);
		return ac;
	}
	
	public boolean existAccount(String accountName) {
		return accounts.containsKey(accountName);
	}
	
	public Account getAccount(String accountName) throws AccountUnknown {
		Account account = accounts.get(accountName);
		if (account != null) {
			return account;
		} else {
			throw new AccountUnknown("Account unknown: " + accountName);
		}
	}
}
