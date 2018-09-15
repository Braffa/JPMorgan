package com.jpmorgan;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SettledReport {

	private static String BUY_FLAG = "B";
	protected List<Instruction> lOfIncoming;
	protected List<Instruction> lOfOutgoing;

	/*
	 * Function to return a calendar object from a String
	 */

	CalendarFromString getCalendar = (String date) -> {
		SimpleDateFormat format1 = new SimpleDateFormat("dd MMM yyyy");
		Date fDate = format1.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fDate);
		return calendar;
	};

	/*
	 * function to determine if the settlement date falls on a weekend then reset it
	 * to the next working day depending on the currency.
	 */
	CalculateSettlementDate settlementDate = (Calendar cal, String currency) -> {
		int day = cal.get(Calendar.DAY_OF_WEEK);
		if (CurrencyEnum.isMiddleEastcurrency(currency)) {
			if (day == Calendar.FRIDAY) {
				cal.add(Calendar.DATE, 2);
			} else if (day == Calendar.SATURDAY) {
				cal.add(Calendar.DATE, 1);
			}
		} else {
			if (day == Calendar.SATURDAY) {
				cal.add(Calendar.DATE, 2);
			} else if (day == Calendar.SUNDAY) {
				cal.add(Calendar.DATE, 1);
			}
		}
		return cal.getTime();
	};

	public void processInstructions(String filePath) throws FileNotFoundException {
		lOfIncoming = new ArrayList<Instruction>();
		lOfOutgoing = new ArrayList<Instruction>();
		Scanner in = new Scanner(new FileReader(filePath));
		while (in.hasNextLine()) {
			String line = in.nextLine().trim();
			Instruction instruction = parseLine(line);
			if (instruction != null) {
				if (instruction.getBuySell().equals(BUY_FLAG)) {
					lOfIncoming.add(instruction);
				} else {
					lOfOutgoing.add(instruction);
				}
			}
		}
		in.close();
		System.out.println("Incoming");
		Collections.sort(lOfIncoming, new TradeAmountComparator().reversed());
		lOfIncoming.forEach(System.out::println);

		System.out.println("Outgoing");
		Collections.sort(lOfOutgoing, new TradeAmountComparator().reversed());
		lOfOutgoing.forEach(System.out::println);
	}

	private Instruction parseLine(String line) {
		String[] fields = getfields(line);
		Instruction instruction = new Instruction();
		try {
			instruction.setEntity(fields[0]);
			instruction.setBuySell(fields[1]);
			instruction.setAgreedFX(Double.parseDouble(fields[2]));
			instruction.setCurrency(fields[3]);
			instruction.setInstructionDate(getCalendar.getCalendarFromString(fields[4]).getTime());
			Calendar calendar = getCalendar.getCalendarFromString(fields[5]);
			instruction.setSettlementDate(settlementDate.setSettlementDate(calendar, fields[3]));
			instruction.setUnits(Integer.parseInt(fields[6]));
			instruction.setPricePerUnit(Double.parseDouble(fields[7]));
			instruction.setTradeAmount();
		} catch (Exception e) {
			System.out.println("Invalid Instruction");
			e.printStackTrace();
			return null;
		}
		return instruction;
	}

	private String[] getfields(String line) {
		String[] fields = new String[8];
		Scanner scanner = new Scanner(line);
		Scanner scan = scanner.useDelimiter(",");
		int index = 0;
		while (scan.hasNext()) {
			String field = scan.next();
			fields[index] = field.trim();
			index++;
		}
		scan.close();
		scanner.close();
		return fields;
	}

}
