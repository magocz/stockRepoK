package neldam.time;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import neldam.stock.StockDataBase;

public class Time {
	private static List<Date> days;
	private Integer currentDay = 0;

	private static Time instance;

	private Time() {
		days = StockDataBase.getDays();
		Collections.sort(days); // sortujemy dni rosnaco xD
	}

	public static Time getTime() {
		if (instance == null) {
			instance = new Time();
		}
		return instance;
	}

	public List<Date> getDays() {
		return days;
	}

	public Date getCurrentDay() {
		return days.get(currentDay);
	}

	public void incrementDate() {
		currentDay++;
	}

	public void decrementDate() {
		currentDay--;
	}

}
