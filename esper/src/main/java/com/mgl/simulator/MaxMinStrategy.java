package com.mgl.simulator;

import java.util.List;

import com.mgl.account.Account;
import com.mgl.fx.CandlePoint;
import com.mgl.reader.Loader;

public class MaxMinStrategy extends Strategy {

	private int regionLength = 20;
	
	public MaxMinStrategy(Account ac) {
		super(ac);
	}

	@Override
	public void execute() {
		Loader data = Loader.getInstance();
		if (data.getList() == null || data.getList().isEmpty()) {
			System.err.println("No data");
			return;
		}
		
		List<CandlePoint> initData = data.getList().subList(0, regionLength);
		
		MaxMinRegion mmRegion = new MaxMinRegion(initData);
		
		
	}

}
