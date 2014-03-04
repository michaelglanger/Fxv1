package com.mgl.cep.esper;


import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Configuration config = new Configuration();
    	config.addEventTypeAutoName("com.mgl.cep.esper");
    	final EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(config);
    	String expression = "select avg(price) from OrderEvent.win:time(30 sec)";
    	EPStatement statement = epService.getEPAdministrator().createEPL(expression);
    	
    	String expression2 = "select price from OrderEvent";
    	EPStatement statement2 = epService.getEPAdministrator().createEPL(expression2);
    	statement2.addListener(new MyListener2());
    	
    	MyListener listener = new MyListener();
    	statement.addListener(listener);
    	
    	new Runnable() {
			
			public void run() {
				for (int i = 0; i < 1000; i++){
					double r = 100*Math.random();
		    		OrderEvent event = new OrderEvent("shirt", r);
		    		epService.getEPRuntime().sendEvent(event);
		    		try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		    	}
				
			}
		}.run();
    	
    }
}
