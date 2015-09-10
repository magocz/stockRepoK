package neldam.stock;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import neldam.share.SharePrice;
import neldam.time.Time;

public class Stock {
	// key - name of share
	private Map<String, SharePrice> shareMap;
	private Time time;

	private static Stock instance;
	
	private Stock() {
		try {
			shareMap = StockDataBase.getDataFrom("../gielda/target/classes/dane.csv");
			time = Time.getTime();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Stock getStockInstance(){
		if(instance == null){
			instance = new Stock();
		}
		return instance;
	}

	public Double getSharePrice(String shareName) {
		return shareMap.get(shareName).get(time.getCurrentDay());
	}

	private Date decrementDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -1);
		return c.getTime();
	}

	public boolean isGrowing(String shareName) {
		if (shareMap.get(shareName).get(decrementDate(time.getCurrentDay())) != null) {
			if (shareMap.get(shareName).get(time.getCurrentDay()) < shareMap.get(shareName)
					.get(decrementDate(time.getCurrentDay()))) {
				return true;
			}
		}
		return false;
	}
}
