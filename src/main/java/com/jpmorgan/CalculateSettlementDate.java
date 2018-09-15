package com.jpmorgan;

import java.util.Calendar;
import java.util.Date;

@FunctionalInterface
public interface CalculateSettlementDate {

	Date setSettlementDate(Calendar calendar, String currency);
}
