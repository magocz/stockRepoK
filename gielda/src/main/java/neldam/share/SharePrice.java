package neldam.share;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SharePrice {
	// key - date , value - price in that date
	private Map<Date, Double> sharePrices;

	public SharePrice() {
		sharePrices = new HashMap<Date, Double>();
	}

	private Date string2date(String sDate) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		try {
			return formater.parse(sDate);
		} catch (ParseException e) {
			return null;
		}
	}

	public void put(String date, Double price) {
		if(string2date(date) != null){
			this.sharePrices.put(string2date(date), price);
		}
	}
	
	public Map<Date, Double> getSharePrices() {
		return this.sharePrices;
	}

	public Double get(Date date) {
		return this.sharePrices.get(date);
	}

	public void put(Date currentDay, double price) {
		this.sharePrices.put(currentDay, price);
	}
}
