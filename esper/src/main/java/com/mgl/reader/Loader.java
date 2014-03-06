package com.mgl.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import com.mgl.fx.CandlePoint;

public class Loader {
	
	private static List<CandlePoint> list;
	private static Loader instance;
	
	private Loader() {
		list = getList("D:/Users/axjyb/git/Fxv1/esper/data/data.ser");
	}
	
	public static Loader getInstance() {
		if (instance == null) {
			instance = new Loader();
		}
		return instance;
	}
	
	public List<CandlePoint> getList() {
		return list;
	}
	
	private List<CandlePoint> getList(String file) {
		List<CandlePoint> list = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
	        list = (List<CandlePoint>) ois.readObject();
	        ois.close();
	        
//	        for (CandlePoint cp : list) {
//	        	System.out.println(cp.toString());
//	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

}
