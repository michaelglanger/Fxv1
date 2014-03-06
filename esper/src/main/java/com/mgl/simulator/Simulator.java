package com.mgl.simulator;

import com.mgl.account.Account;
import com.mgl.account.AccountHandler;
import com.mgl.account.exception.AccountAlreadyExists;
import com.mgl.reader.Loader;

public class Simulator {

	public static void main(String[] args) {
		int status = 0;
		double account = 10000;
		double shares = 0;
		
		Loader data = Loader.getInstance();
		if (data.getList() == null || data.getList().isEmpty()) {
			System.err.println("No data");
			return;
		}
				
		try {
			Account ac = AccountHandler.getInstance().createAccount("main");
		} catch (AccountAlreadyExists e) {
			e.printStackTrace();
		}
		
		int initMin = 20;
		for (int i = initMin; i < data.getList().size(); i++) {
			double localHigh = data.getList().get(i-initMin).getClose();
			double localLow = data.getList().get(i-initMin).getClose();
			for (int j = 1; j < initMin; j++) {
				if (data.getList().get(i-j).getClose() > localHigh) {
					localHigh = data.getList().get(i-j).getClose();
				} else if (data.getList().get(i-j).getClose() < localLow) {
					localLow = data.getList().get(i-j).getClose();
				}
			}
			
			if (data.getList().get(i).getClose() > localHigh) {
				// buy
				if (status < 1) {
					System.err.println("Buy " + data.getList().get(i).getClose() + " Account: " + account);
					if (status != 0) {
						double gain = account - (shares * data.getList().get(i).getClose());
						account += gain;
						double shr = account / data.getList().get(i).getClose();
						shares = shr;
					} else {
						double shr = account / data.getList().get(i).getClose();
						shares = shr;
					}
					status = 1;
				} 				
			} else if (data.getList().get(i).getClose() < localLow) {
				// sell
				if (status > 0) {
					System.err.println("Sell " + data.getList().get(i).getClose() + " Account: " + account);
					double gain = (shares * data.getList().get(i).getClose()) - account;
					account += gain;
					double shr = account / data.getList().get(i).getClose();
					shares = shr;
					status = -1;
				} 	
			}
			
		}
		
		
		
		
	}

}
