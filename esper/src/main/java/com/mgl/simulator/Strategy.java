package com.mgl.simulator;

import com.mgl.account.Account;

public abstract class Strategy {
	
	protected Account account;
	
	public Strategy(Account ac) {
		this.account = ac;
	}
	
	abstract public void execute();

}
