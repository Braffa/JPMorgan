package com.jpmorgan;

import java.util.Comparator;

public class TradeAmountComparator implements Comparator<Instruction> {

	public int compare(Instruction a, Instruction b) {

		return Double.compare(a.getTradeAmount(), b.getTradeAmount());

	}
}
