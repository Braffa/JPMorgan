package com.jpmorgan;

public enum CurrencyEnum {
	AED, SAR;
	
	public static boolean isMiddleEastcurrency (String currency) {
		for (CurrencyEnum value : CurrencyEnum.values()) {
		    if (currency.equals(value.toString())) {
		    		return true;
		    }
		}
		return false;
	}
}
