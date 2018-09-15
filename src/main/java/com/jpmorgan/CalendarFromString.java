package com.jpmorgan;

import java.text.ParseException;
import java.util.Calendar;

@FunctionalInterface
public interface CalendarFromString {
	
	Calendar getCalendarFromString(String Date)  throws ParseException ;

}
