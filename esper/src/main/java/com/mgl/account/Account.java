package com.mgl.account;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class Account {
	
	private String name;
	private BigDecimal initValue;
	private BigDecimal currentValue;
	private List<Operation> historicOperations;
	private Positions currentPosition;
	private long sizePosition;
	private double pricePosition;
	
	public Account(String name, BigDecimal init) {
		this.name = name;
		this.initValue = init;
		historicOperations = new LinkedList<Operation>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setCurrentValue(BigDecimal value) {
		currentValue = value;
	}
	
	public BigDecimal getCurrentValue() {
		return currentValue;
	}
	
	public BigDecimal getInitValue() {
		return initValue;
	}
	
	public List<Operation> getHistoricOperations() {
		return historicOperations;
	}
	
	public void addOperation(Operation operation) {
		historicOperations.add(operation);
	}
	
	public Positions getCurrentPosition() {
		return currentPosition;
	}
	
	public void buyLong(double value) {
		if (Positions.SHORT.equals(currentPosition)) {
			sellShort(value);
		}
		
		sizePosition = (long) (currentValue.doubleValue() / value);
		pricePosition = sizePosition * value;
		
		currentPosition = Positions.LONG;
	}
	
	public void sellLong(double value) {
		if (Positions.LONG.equals(currentPosition)) {
			double newValue = value * sizePosition;
			double result = newValue - pricePosition;
			currentValue = currentValue.add(new BigDecimal(result));
			sizePosition = 0;
			pricePosition = 0d;
			currentPosition = Positions.NONE;
		} else {
			// cannot sell if there is no previous position.
		}
		
	}
	
	public void buyShort(double value) {
		
		currentPosition = Positions.SHORT;
	}
	
	public void sellShort(double value) {
		
		currentPosition = Positions.NONE;
	}
	
	public void closePosition(double value) {
		if (Positions.LONG.equals(currentPosition)) {
			sellLong(value);
		} else if (Positions.SHORT.equals(currentPosition)) {
			sellShort(value);
		}
	}
	
}
