package com.mgl.ti;

import java.util.List;

import com.mgl.fx.CandlePoint;

public class MA {
	
	public static double sma(List<CandlePoint> caList) {
		double result = 0d;
		
		double sum = 0;
		for (CandlePoint cp : caList) {
			sum += cp.getClose();
		}
		result = sum / caList.size();
		
		return result;
	}
	
	public static double accSma(List<CandlePoint> caList) {
		double previous = sma(caList.subList(0, caList.size()-2));
		double current = sma(caList);
		return Math.atan(1 / (current - previous));
	}

}
