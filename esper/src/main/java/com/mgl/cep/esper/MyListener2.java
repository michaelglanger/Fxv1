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
package com.mgl.cep.esper;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class MyListener2 implements UpdateListener {

	public void update(EventBean[] arg0, EventBean[] arg1) {
		 EventBean event = arg0[0];
	     System.out.println("Price=" + event.get("price"));

	}

}
