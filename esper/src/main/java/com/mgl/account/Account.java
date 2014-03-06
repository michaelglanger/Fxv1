package com.mgl.account;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import com.mgl.account.exception.AccountOperationException;

public class Account {
	
	private String name;
	private BigDecimal initValue;
	private BigDecimal currentValue;
	private List<Operation> historicOperations;
	private Positions currentPosition = Positions.NONE;
	private long sizePosition;
	private double pricePosition;
	
	public Account(String name, BigDecimal init) {
		this.name = name;
		this.initValue = init;
		this.currentValue = new BigDecimal(init.doubleValue());
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
	
	public void buyLong(double value) throws AccountOperationException {
		if (Positions.SHORT.equals(currentPosition)) {
			sellShort(value);
		}
		
		sizePosition = (long) (currentValue.doubleValue() / value);
		pricePosition = value;
		currentPosition = Positions.LONG;
	}
	
	public void sellLong(double value) throws AccountOperationException {
		if (Positions.LONG.equals(currentPosition)) {
			double newValue = value * sizePosition;
			double result = newValue - (pricePosition * sizePosition);
			currentValue = currentValue.add(new BigDecimal(result));
			sizePosition = 0;
			pricePosition = 0d;
			currentPosition = Positions.NONE;
		} else {
			throw new AccountOperationException("Unable to sell long without a long position");
		}
		
	}
	
	public void buyShort(double value) throws AccountOperationException {
		if (Positions.LONG.equals(currentPosition)) {
			// TODO catch and re throw.
			sellLong(value);
		}
		
		sizePosition = (long) (currentValue.doubleValue() / value);
		pricePosition = value;
		currentPosition = Positions.SHORT;
	}
	
	public void sellShort(double value) throws AccountOperationException {
		if (Positions.SHORT.equals(currentPosition)) {
			double newValue = value * sizePosition;
			double result = (pricePosition * sizePosition) - newValue;
			currentValue = currentValue.add(new BigDecimal(result));
			sizePosition = 0;
			pricePosition = 0d;
			currentPosition = Positions.NONE;
		} else {
			throw new AccountOperationException("Unable to sell short without a short position");
		}
	}
	
	public void closePosition(double value) throws AccountOperationException {
		if (Positions.LONG.equals(currentPosition)) {
			sellLong(value);
		} else if (Positions.SHORT.equals(currentPosition)) {
			sellShort(value);
		}
	}
	
}
