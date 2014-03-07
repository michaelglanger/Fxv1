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
package com.mgl.simulator;

import java.util.ArrayList;
import java.util.List;

import com.mgl.fx.CandlePoint;

public class MaxMinRegion {
	
	private double max;
	private double min;
	private List<CandlePoint> regionList = new ArrayList<CandlePoint>();
	
	public MaxMinRegion(List<CandlePoint> initRegion) {
		regionList.addAll(initRegion);
	}
	
	public Tuple step(CandlePoint cp) {
		Tuple result = new Tuple();
		
		regionList.remove(0);
		regionList.add(cp);

		max = regionList.get(0).getClose();
		min = max;
		
		for (CandlePoint c : regionList) {
			if (c.getClose() > max) { 
				max = c.getClose();
			} else if (c.getClose() < min) {
				min = c.getClose();
			}
		}
		result.max = max;
		result.min = min;
		return result;
	}

	public class Tuple {
		public double max;
		public double min;
	}
	
}
