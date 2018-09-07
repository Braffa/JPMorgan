package com.jpmorgan;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CurrencyEnumTest {
	
	@Test
	public void testSAR() {
		assertTrue(CurrencyEnum.isMiddleEastcurrency("AED"));
	}

	@Test
	public void testAED() {
		assertTrue(CurrencyEnum.isMiddleEastcurrency("SAR"));
	}
	
	@Test
	public void testUSD() {
		assertFalse(CurrencyEnum.isMiddleEastcurrency("USD"));
	}
}
