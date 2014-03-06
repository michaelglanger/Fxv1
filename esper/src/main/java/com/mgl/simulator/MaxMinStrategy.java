package com.mgl.simulator;

import java.util.List;

import com.mgl.account.Account;
import com.mgl.account.Positions;
import com.mgl.account.exception.AccountOperationException;
import com.mgl.fx.CandlePoint;
import com.mgl.reader.Loader;
import com.mgl.simulator.MaxMinRegion.Tuple;

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
		
		List<CandlePoint> list = data.getList();
		System.out.println("Start");
		for (int i = regionLength; i < list.size(); i++) {
			CandlePoint cp = list.get(i);
			Tuple t = mmRegion.step(cp);
			
			double close = cp.getClose();
			
			if (close > t.max && !Positions.LONG.equals(account.getCurrentPosition())) {
				try {
					account.buyLong(close);
					System.out.println("Buy " + account.getCurrentValue().toPlainString());
				} catch (AccountOperationException e) {
					e.printStackTrace();
				}
			} else if (close < t.min && !Positions.SHORT.equals(account.getCurrentPosition())) {
				try {
					account.buyShort(close);
					System.out.println("Sell " + account.getCurrentValue().toPlainString());
				} catch (AccountOperationException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

}
