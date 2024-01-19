package com.algo.util.csv;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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
	
	public static String COMMA = ",";
	public static final String[] HEADERS = {"close", "high", "low", "open","symbol","timestamp","volume"};
	
	static String inputPath = "src/com/algo/util/csv/tb_chart_W1.csv";
	static String outputPath = "src/com/algo/util/csv/tb_chart_W1.sql";
	public static void main(String[] args) {
		try {
			int total = count(inputPath);
			System.out.println("total count:" + total);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		readCSV(inputPath);
//		writeCSV(readCSV("src/com/algo/util/csv/tb_chart_D1.csv"),"src/com/algo/util/csv/ticks-out.csv");
	}
	
	/**
	 * 
		����csv�ļ��� ��д����sql�ļ�
	 */
	public static List<Tick> readCSV(String filePath) {
		List<Tick> ticks = new ArrayList<>();
		
		
		try(Reader in = new FileReader(filePath)) {
			int success = 1; // header
			int fail = 0;
			FileWriter writer  = new FileWriter(outputPath);
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS).withFirstRecordAsHeader().parse(in);
			for (CSVRecord record : records) {
				Tick tick = new Tick();
				tick.setClose(new BigDecimal(record.get("close")));
				tick.setHigh(new BigDecimal(record.get("high")));
				tick.setLow(new BigDecimal(record.get("low")));
				tick.setOpen(new BigDecimal(record.get("open")));
				tick.setSymbol(record.get("symbol"));
				tick.setTimestamp(Long.valueOf(record.get("timestamp")) * 1000 - 7200000 + 86400000);
				tick.setVolume(Long.valueOf(record.get("volume")));
				if (tick.getVolume() < Integer.MAX_VALUE) {
					StringBuilder builder = new StringBuilder();
					builder.append("replace into _1week_kline(symbol,timestamp,open,high,low,close,volume) values (");
					builder.append("'").append(tick.getSymbol()).append("'");
			        builder.append(tick.getTimestamp()).append(COMMA);
			        builder.append(tick.getOpen()).append(COMMA);
			        builder.append(tick.getHigh()).append(COMMA);
			        builder.append(tick.getLow()).append(COMMA);
			        builder.append(tick.getClose()).append(COMMA);
			        builder.append(tick.getVolume()).append(");");
			        writer.write(builder + System.lineSeparator());
			        success++;
				} else {
					fail++;
				}
			}
			int total = success+ fail;
			System.out.println("success : " + success  + " fail: " + fail + " total:" + total);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ticks;
	}
	
	/**
	 * count how many of rows in csv
	 */
	public static int count(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	    byte[] c = new byte[1024];
	    int count = 0;
	    int readChars = 0;
	    boolean empty = true;
	    while ((readChars = is.read(c)) != -1) {
	        empty = false;
	        for (int i = 0; i < readChars; ++i) {
	            if (c[i] == '\n') {
	                ++count;
	            }
	        }
	    }
	    return (count == 0 && !empty) ? 1 : count;
	    } finally {
	    is.close();
	   }
	}
	
	public static void writeCSV(List<Tick> ticks , String name)  {
	    try (FileWriter out = new FileWriter(name);CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS))) {
	    	ticks.forEach(i -> {
	    		try {
					printer.printRecord(i.getClose(), i.getHigh(),i.getLow(),i.getOpen(),i.getSymbol(),i.getTimestamp() * 1000 - 7200000,i.getVolume());
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	});
	    } catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	

}
