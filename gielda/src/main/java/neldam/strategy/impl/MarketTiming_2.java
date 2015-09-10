package neldam.strategy.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import neldam.broker.Broker;
import neldam.share.Share;
import neldam.stock.Stock;
import neldam.strategy.Strategy;

public class MarketTiming_2 implements Strategy {
	private Stock stock;// = new Stock();
	private Broker broker;// = new Broker();

	private List<String> intrestetShares;

	public MarketTiming_2() {
		stock = Stock.getStockInstance();
		broker = new Broker();
		intrestetShares = new ArrayList<String>(Arrays.asList("PKOBP", "TPSA", "PGNIG"));
	}

	public List<String> think2buy() {
		List<String> share2buy = new ArrayList<String>();
		for (String shareName : intrestetShares) {
			if (stock.isGrowing(shareName)) {
				share2buy.add(shareName);
			}
		}
		return share2buy;
	}

	public List<String> think2sale(Map<String, Share> shareMap) {
		List<String> share2sale = new ArrayList<String>();
		for (Entry<String, Share> entry : shareMap.entrySet()) {
			if (broker.isProfit(entry.getKey(), entry.getValue().getPrice())) {
				share2sale.add(entry.getKey());
			}
		}
		return share2sale;
	}

}
