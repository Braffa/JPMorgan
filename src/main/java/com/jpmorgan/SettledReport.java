package com.jpmorgan;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.jpmorgan.beans.BaseInstruction;
import com.jpmorgan.beans.Instruction;
import com.jpmorgan.beans.MiddleEastInstruction;

public class SettledReport {

	private static String BUY_FLAG = "B";
	private List<BaseInstruction> lOfIncoming;
	private List<BaseInstruction> lOfOutgoing;

	public void processInstructions(String filePath) throws FileNotFoundException {
		lOfIncoming = new ArrayList<BaseInstruction>();
		lOfOutgoing = new ArrayList<BaseInstruction>();
		Scanner in = new Scanner(new FileReader(filePath));
		while (in.hasNextLine()) {
			String line = in.nextLine().trim();
			BaseInstruction instruction = parseLine(line);
			if (instruction.getBuySell().equals(BUY_FLAG)) {
				lOfIncoming.add(instruction);
			} else {
				lOfOutgoing.add(instruction);
			}
		}
		in.close();
		printReport(lOfIncoming, "Incoming");
		printReport(lOfOutgoing, "Outgoing");

	}

	private void printReport(List<BaseInstruction> lOfInstructions, String action) {

		Collections.sort(lOfInstructions, new TradeAmountComparator().reversed());

		for (BaseInstruction bi : lOfInstructions) {
			System.out.println(action + " " + bi.getEntity() + " " + bi.getTradeAmount());
		}
		System.out.println();
	}

	private BaseInstruction parseLine(String line) {
		String[] fields = getfields(line);
		String currency = fields[3];
		BaseInstruction instruction;

		if (CurrencyEnum.isMiddleEastcurrency(currency)) {
			instruction = new MiddleEastInstruction();
		} else {
			instruction = new Instruction();
		}

		instruction.setEntity(fields[0]);
		instruction.setBuySell(fields[1]);
		instruction.setAgreedFX(Double.parseDouble(fields[2]));
		instruction.setCurrency(fields[3]);
		instruction.setInstructionDate(getDate(fields[4]).getTime());
		instruction.setSettlementDate(getDate(fields[5]));
		instruction.setUnits(Integer.parseInt(fields[6]));
		instruction.setPricePerUnit(Double.parseDouble(fields[7]));
		instruction.setTradeAmount();
		return instruction;
	}

	private String[] getfields(String line) {
		String[] fields = new String[8];
		Scanner scan = new Scanner(line).useDelimiter(",");
		int index = 0;
		while (scan.hasNext()) {
			String field = scan.next();
			fields[index] = field.trim();
			index++;
		}
		scan.close();
		return fields;
	}

	private Calendar getDate(String date) {
		try {
			SimpleDateFormat format1 = new SimpleDateFormat("dd MMM yyyy");
			Date fDate = format1.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fDate);
			return calendar;
		} catch (ParseException pe) {
			return Calendar.getInstance();
		}
	}

	public List<BaseInstruction> getLOfIncoming() {
		return lOfIncoming;
	}

	public List<BaseInstruction> getLOfOutgoing() {
		return lOfOutgoing;
	}
}
