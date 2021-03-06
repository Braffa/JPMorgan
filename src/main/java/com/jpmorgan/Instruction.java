package com.jpmorgan;

import java.util.Date;

public class Instruction {

	private String entity;

	private String buySell;

	private double agreedFX;

	private String currency;

	private Date instructionDate;

	protected Date settlementDate;

	private int units;

	private double pricePerUnit;
	
	private double tradeAmount;

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getBuySell() {
		return buySell;
	}

	public void setBuySell(String buySell) {
		this.buySell = buySell;
	}

	public double getAgreedFX() {
		return agreedFX;
	}

	public void setAgreedFX(double agreedFX) {
		this.agreedFX = agreedFX;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}

	public Date getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public double getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount() {
		this.tradeAmount = pricePerUnit * units * agreedFX;
	}

	@Override
	public String toString() {
		return entity + " " + tradeAmount;
	}


}
