package com.jpmorgan;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import com.jpmorgan.beans.BaseInstruction;

public class SettledReportTest {

	SettledReport settledReport = new SettledReport();

	@Test
	public void testWithOutMiddleEastData() {
		try {
			String userDir = System.getProperty("user.dir");
			settledReport.processInstructions(userDir + "/src/test/resources/testData.txt");

			List<BaseInstruction> lOfIncoming = settledReport.getLOfIncoming();
			assertEquals(5, lOfIncoming.size());

			BaseInstruction instruction = lOfIncoming.get(0);
			assertEquals("foo1", instruction.getEntity());
			assertEquals(10025.0, instruction.getTradeAmount(), 0.0);
			Calendar cal = Calendar.getInstance();
			cal.setTime(instruction.getSettlementDate());
			assertEquals(6, cal.get(Calendar.DAY_OF_MONTH));
			assertEquals(8, cal.get(Calendar.MONTH));
			assertEquals(2018, cal.get(Calendar.YEAR));

			instruction = lOfIncoming.get(1);
			assertEquals("foo2", instruction.getEntity());
			assertEquals(2777.5, instruction.getTradeAmount(), 0.0);
			cal.setTime(instruction.getSettlementDate());
			assertEquals(6, cal.get(Calendar.DAY_OF_MONTH));
			assertEquals(8, cal.get(Calendar.MONTH));
			assertEquals(2018, cal.get(Calendar.YEAR));
			
			instruction = lOfIncoming.get(2);
			assertEquals("foo3", instruction.getEntity());
			assertEquals(1881.25, instruction.getTradeAmount(), 0.0);
			cal.setTime(instruction.getSettlementDate());
			assertEquals(6, cal.get(Calendar.DAY_OF_MONTH));
			assertEquals(8, cal.get(Calendar.MONTH));
			assertEquals(2018, cal.get(Calendar.YEAR));
			
			instruction = lOfIncoming.get(3);
			assertEquals("foo4", instruction.getEntity());
			assertEquals(1881.25, instruction.getTradeAmount(), 0.0);
			cal.setTime(instruction.getSettlementDate());
			assertEquals("The day should have changed from saturday to Monday", 3, cal.get(Calendar.DAY_OF_MONTH));
			assertEquals(8, cal.get(Calendar.MONTH));
			assertEquals(2018, cal.get(Calendar.YEAR));
			
			instruction = lOfIncoming.get(4);
			assertEquals("foo5", instruction.getEntity());
			assertEquals(1881.25, instruction.getTradeAmount(), 0.0);
			cal.setTime(instruction.getSettlementDate());
			assertEquals("The day should have changed from Sunday to Monday",3, cal.get(Calendar.DAY_OF_MONTH));
			assertEquals(8, cal.get(Calendar.MONTH));
			assertEquals(2018, cal.get(Calendar.YEAR));

			List<BaseInstruction> lOfOutgoing = settledReport.getLOfOutgoing();
			assertEquals(4, lOfOutgoing.size());
			
			instruction = lOfOutgoing.get(0);
			assertEquals("foo6", instruction.getEntity());
			assertEquals(5643.75, instruction.getTradeAmount(), 0.0);
			cal = Calendar.getInstance();
			cal.setTime(instruction.getSettlementDate());
			assertEquals(6, cal.get(Calendar.DAY_OF_MONTH));
			assertEquals(8, cal.get(Calendar.MONTH));
			assertEquals(2018, cal.get(Calendar.YEAR));
			
			instruction = lOfOutgoing.get(1);
			assertEquals("foo8", instruction.getEntity());
			assertEquals(5062.5, instruction.getTradeAmount(), 0.0);
			cal = Calendar.getInstance();
			cal.setTime(instruction.getSettlementDate());
			assertEquals(6, cal.get(Calendar.DAY_OF_MONTH));
			assertEquals(8, cal.get(Calendar.MONTH));
			assertEquals(2018, cal.get(Calendar.YEAR));
			
			instruction = lOfOutgoing.get(2);
			assertEquals("foo7", instruction.getEntity());
			assertEquals(1277.5, instruction.getTradeAmount(), 0.0);
			cal = Calendar.getInstance();
			cal.setTime(instruction.getSettlementDate());
			assertEquals(6, cal.get(Calendar.DAY_OF_MONTH));
			assertEquals(8, cal.get(Calendar.MONTH));
			assertEquals(2018, cal.get(Calendar.YEAR));
			
			instruction = lOfOutgoing.get(3);
			assertEquals("foo9", instruction.getEntity());
			assertEquals(525.0, instruction.getTradeAmount(), 0.0);
			cal = Calendar.getInstance();
			cal.setTime(instruction.getSettlementDate());
			assertEquals("The day should have changed from saturday to Monday", 3, cal.get(Calendar.DAY_OF_MONTH));
			assertEquals(8, cal.get(Calendar.MONTH));
			assertEquals(2018, cal.get(Calendar.YEAR));

		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	@Test
	public void testWithMiddleEastData() {
		try {
			String userDir = System.getProperty("user.dir");
			settledReport.processInstructions(userDir + "/src/test/resources/testDataMiddleEast.txt");

			List<BaseInstruction> lOfIncoming = settledReport.getLOfIncoming();
			assertEquals(5, lOfIncoming.size());

			BaseInstruction instruction = lOfIncoming.get(0);
			assertEquals("foo1", instruction.getEntity());
			assertEquals(10025.0, instruction.getTradeAmount(), 0.0);
			Calendar cal = Calendar.getInstance();
			cal.setTime(instruction.getSettlementDate());
			assertEquals(6, cal.get(Calendar.DAY_OF_MONTH));
			assertEquals(8, cal.get(Calendar.MONTH));
			assertEquals(2018, cal.get(Calendar.YEAR));

			instruction = lOfIncoming.get(1);
			assertEquals("foo2", instruction.getEntity());
			assertEquals(2777.5, instruction.getTradeAmount(), 0.0);
			cal.setTime(instruction.getSettlementDate());
			assertEquals(6, cal.get(Calendar.DAY_OF_MONTH));
			assertEquals(8, cal.get(Calendar.MONTH));
			assertEquals(2018, cal.get(Calendar.YEAR));
			
			instruction = lOfIncoming.get(2);
			assertEquals("foo3", instruction.getEntity());
			assertEquals(1881.25, instruction.getTradeAmount(), 0.0);
			cal.setTime(instruction.getSettlementDate());
			assertEquals(6, cal.get(Calendar.DAY_OF_MONTH));
			assertEquals(8, cal.get(Calendar.MONTH));
			assertEquals(2018, cal.get(Calendar.YEAR));
			
			instruction = lOfIncoming.get(3);
			assertEquals("foo4", instruction.getEntity());
			assertEquals(1881.25, instruction.getTradeAmount(), 0.0);
			cal.setTime(instruction.getSettlementDate());
			assertEquals("The day should have changed from Friday to Sunday", 2, cal.get(Calendar.DAY_OF_MONTH));
			assertEquals(8, cal.get(Calendar.MONTH));
			assertEquals(2018, cal.get(Calendar.YEAR));
			
			instruction = lOfIncoming.get(4);
			assertEquals("foo5", instruction.getEntity());
			assertEquals(1881.25, instruction.getTradeAmount(), 0.0);
			cal.setTime(instruction.getSettlementDate());
			assertEquals("The day should have changed from Saturday to Sunday",2, cal.get(Calendar.DAY_OF_MONTH));
			assertEquals(8, cal.get(Calendar.MONTH));
			assertEquals(2018, cal.get(Calendar.YEAR));

			List<BaseInstruction> lOfOutgoing = settledReport.getLOfOutgoing();
			assertEquals(4, lOfOutgoing.size());
			
			instruction = lOfOutgoing.get(0);
			assertEquals("foo6", instruction.getEntity());
			assertEquals(5643.75, instruction.getTradeAmount(), 0.0);
			cal = Calendar.getInstance();
			cal.setTime(instruction.getSettlementDate());
			assertEquals(6, cal.get(Calendar.DAY_OF_MONTH));
			assertEquals(8, cal.get(Calendar.MONTH));
			assertEquals(2018, cal.get(Calendar.YEAR));
			
			instruction = lOfOutgoing.get(1);
			assertEquals("foo8", instruction.getEntity());
			assertEquals(5062.5, instruction.getTradeAmount(), 0.0);
			cal = Calendar.getInstance();
			cal.setTime(instruction.getSettlementDate());
			assertEquals(6, cal.get(Calendar.DAY_OF_MONTH));
			assertEquals(8, cal.get(Calendar.MONTH));
			assertEquals(2018, cal.get(Calendar.YEAR));
			
			instruction = lOfOutgoing.get(2);
			assertEquals("foo7", instruction.getEntity());
			assertEquals(1277.5, instruction.getTradeAmount(), 0.0);
			cal = Calendar.getInstance();
			cal.setTime(instruction.getSettlementDate());
			assertEquals(6, cal.get(Calendar.DAY_OF_MONTH));
			assertEquals(8, cal.get(Calendar.MONTH));
			assertEquals(2018, cal.get(Calendar.YEAR));
			
			instruction = lOfOutgoing.get(3);
			assertEquals("foo9", instruction.getEntity());
			assertEquals(525.0, instruction.getTradeAmount(), 0.0);
			cal = Calendar.getInstance();
			cal.setTime(instruction.getSettlementDate());
			assertEquals("The day should have changed from Friday to Sunday", 2, cal.get(Calendar.DAY_OF_MONTH));
			assertEquals(8, cal.get(Calendar.MONTH));
			assertEquals(2018, cal.get(Calendar.YEAR));

		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}

}
