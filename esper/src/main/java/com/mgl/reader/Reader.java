package com.mgl.reader;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mgl.fx.CandlePoint;

public class Reader {

	public static void main(String[] args) {
		BufferedReader br = null;
		 
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader("D:/Users/axjyb/git/Fxv1/esper/data/data.csv"));
			sCurrentLine = br.readLine();
			System.out.println(sCurrentLine);
			// first line removed.
			
			System.out.println("Loading data");
			List<CandlePoint> list = new ArrayList<CandlePoint>();
			while ((sCurrentLine = br.readLine()) != null) {
				sCurrentLine = br.readLine();
				String[] strss = sCurrentLine.split(",");			
//				for (String s : strss) {
//					System.out.println(s);
//				}
								
				String[] dateTime = strss[0].split(" ");
				String[] date = dateTime[0].replace(".", "-").split("-");
				String[] time = dateTime[1].split(":");
				
				int year = Integer.parseInt(date[2]) - 1900;
				int month = Integer.parseInt(date[1]) - 1;
				int day = Integer.parseInt(date[0]);
				int hour = Integer.parseInt(time[0]);
				int minute = Integer.parseInt(time[1]);
				
				Timestamp ts = new Timestamp(year, month, day, hour, minute, 0, 0);
				
				CandlePoint candle = new CandlePoint(Double.parseDouble(strss[1]), Double.parseDouble(strss[2]), Double.parseDouble(strss[3]), Double.parseDouble(strss[4]), ts.getTime());
				
				list.add(candle);
				
			}
			FileOutputStream fos = new FileOutputStream("D:/Users/axjyb/git/Fxv1/esper/data/data.ser");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(list);
	        oos.close();
			System.out.println("End");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

}
