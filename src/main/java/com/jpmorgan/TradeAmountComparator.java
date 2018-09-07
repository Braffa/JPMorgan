package com.jpmorgan;

import java.util.Comparator;

import com.jpmorgan.beans.BaseInstruction;

public class TradeAmountComparator implements Comparator<BaseInstruction> {

	public int compare(BaseInstruction a, BaseInstruction b) {

		return Double.compare(a.getTradeAmount(), b.getTradeAmount());

	}
}
