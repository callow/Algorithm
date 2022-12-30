package com.algo.util.csv;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import com.algo.util.csv.model.Tick;

@SuppressWarnings("deprecation")
public class CSVUtil {
	
	
	public static final String[] HEADERS = {"close", "high", "low", "open","symbol","timestamp","volume"};
	
	public static List<Tick> readCSV(String filePath) {
		List<Tick> ticks = new ArrayList<>();
		try(Reader in = new FileReader(filePath)) {
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS).withFirstRecordAsHeader().parse(in);
			for (CSVRecord record : records) {
				Tick tick = new Tick();
				tick.setClose(new BigDecimal(record.get("close")));
				tick.setHigh(new BigDecimal(record.get("high")));
				tick.setLow(new BigDecimal(record.get("low")));
				tick.setOpen(new BigDecimal(record.get("open")));
				tick.setSymbol(record.get("symbol"));
				tick.setTimestamp(Long.valueOf(record.get("timestamp")));
				tick.setVolume(Integer.valueOf(record.get("volume")));
				ticks.add(tick);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ticks;
	}
	
	public static void writeCSV(List<Tick> ticks , String name)  {
	    try (FileWriter out = new FileWriter(name);CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS))) {
	    	ticks.forEach(i -> {
	    		try {
					printer.printRecord(i.getClose(), i.getHigh(),i.getLow(),i.getOpen(),i.getSymbol(),i.getTimestamp(),i.getVolume());
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	});
	    } catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		writeCSV(readCSV(""),"test.csv");
	}
}
