package com.mgl.simulator;

import com.mgl.account.Account;
import com.mgl.account.AccountHandler;
import com.mgl.account.exception.AccountAlreadyExists;
import com.mgl.reader.Loader;

public class Simulator {

	public static void main(String[] args) {
		
		try {
			Account ac = AccountHandler.getInstance().createAccount("main");
			
			MaxMinStrategy str = new MaxMinStrategy(ac);
			str.execute();
			
		} catch (AccountAlreadyExists e) {
			e.printStackTrace();
		}
		
	}

}
