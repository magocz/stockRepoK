package neldam.stock;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import neldam.share.SharePrice;

public class StockDataBase {

	public static Map<String, SharePrice> getDataFrom(String filePath) throws IOException {
		Map<String, SharePrice> shareMap = new HashMap<String, SharePrice>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length == 3) {
					Double price = null;
					try {
						price = Double.parseDouble(data[2]);
					} catch (NumberFormatException exception) {
						continue;
					}
					if (shareMap.containsKey(data[0])) {
						shareMap.get(data[0]).put(data[1], price);
						continue;
					}
					shareMap.put(data[0], new SharePrice());
					shareMap.get(data[0]).put(data[1], Double.parseDouble(data[2]));

				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shareMap;
	}

	public static List<Date> getDays() {
		List<Date> days = new LinkedList<Date>();
		try {
			BufferedReader bufferedReader = new BufferedReader(
					new FileReader("C:/Users/magocz/javaKurse/stockRepo/gielda/src/main/resources/dane.csv"));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length == 3) {
					Date date = string2date(data[1]);
					if (days.isEmpty() || !days.contains(date)) {
						days.add(date);
					}
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return days;
	}

	private static Date string2date(String sDate) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		try {
			return formater.parse(sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
