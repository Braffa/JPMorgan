package com.jpmorgan.beans;

import java.util.Calendar;

public class Instruction extends BaseInstruction {

	@Override
	public void setSettlementDate(Calendar cal) {
		int day = cal.get(Calendar.DAY_OF_WEEK);
		if (day == Calendar.SATURDAY) {
			cal.add(Calendar.DATE, 2);
		} else if (day == Calendar.SUNDAY) {
			cal.add(Calendar.DATE, 1);
		}
		this.settlementDate = cal.getTime();
	}

}
