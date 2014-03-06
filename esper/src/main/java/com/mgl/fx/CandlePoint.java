//
/////////////////////////////////////////////////////////////////
//                 C O P Y R I G H T  (c) 2014
//             A G F A - G E V A E R T  G R O U P
//                    All Rights Reserved
/////////////////////////////////////////////////////////////////
//
//       THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF
//                    Agfa-Gevaert Group
//      The copyright notice above does not evidence any
//     actual or intended publication of such source code.
//
/////////////////////////////////////////////////////////////////
//
//
package com.mgl.fx;

import java.io.Serializable;

public class CandlePoint implements Serializable{
		
	private double open;
	private double max;
	private double min;
	private double close;
	private long timestamp;
	
	public CandlePoint(){
	}
	
	public CandlePoint(double open, double max, double min, double close, long timestamp) {
		this.open = open;
		this.max = max;
		this.min = min;
		this.close = close;
		this.timestamp = timestamp;
	}
	
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public String toString() {
		return "Timestamp: " + timestamp + " Open: " + open + " Max: " + max + " Min: " + min + " Close: " + close;
		
	}
	
}
