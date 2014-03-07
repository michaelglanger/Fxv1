package com.mgl.simulator;

import java.util.List;

import com.mgl.account.Account;
import com.mgl.account.Positions;
import com.mgl.account.exception.AccountOperationException;
import com.mgl.fx.CandlePoint;
import com.mgl.reader.Loader;
import com.mgl.simulator.MaxMinRegion.Tuple;
import com.mgl.ti.MA;

public class MaxMinStrategy extends Strategy {

	private int regionLength = 70;
	
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
		
		List<CandlePoint> list = data.getList();
		List<CandlePoint> initData = list.subList(0, regionLength);
		
		MaxMinRegion mmRegion = new MaxMinRegion(initData);
		
		System.out.println("Start");
		for (int i = regionLength; i < list.size(); i++) {
			
			Tuple t = mmRegion.step(list.get(i-1));
			CandlePoint cp = list.get(i);
			double close = cp.getClose();
			
			List<CandlePoint> maData = list.subList(i-regionLength, i);
			double sma = MA.sma(maData);
			double accSma = MA.accSma(maData);
			
			if ( close > t.max 
				 && Positions.NONE.equals(account.getCurrentPosition())
				 && close > sma	) 
			{
				try {
					account.buyLong(close);
					System.out.println("Buy " + account.getCurrentValue().toPlainString());
				} catch (AccountOperationException e) {
					e.printStackTrace();
				}
			} else 
			if ( (Positions.LONG.equals(account.getCurrentPosition()) && accSma < 0)
				 || (Positions.SHORT.equals(account.getCurrentPosition()) && accSma > 0) ) 
			{
				try {
					account.closePosition(close);
				} catch (AccountOperationException e) {
					e.printStackTrace();
				}
			} else
			if ( close < t.min 
				 && Positions.NONE.equals(account.getCurrentPosition())
				 && close < sma ) 
			{
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
