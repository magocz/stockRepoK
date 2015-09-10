package neldam.strategy.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import neldam.share.Share;
import neldam.strategy.Strategy;

public class RandomStrategy implements Strategy {

	private List<String> intrestetShares;

	public RandomStrategy() {
		intrestetShares = new ArrayList<String>(Arrays.asList("PKOBP", "TPSA"));
	}

	public List<String> think2sale(Map<String, Share> shareMap) {
		Random rand = new Random();
		List<String> share2sale = new ArrayList<String>();
		for (Entry<String, Share> entry : shareMap.entrySet()) {
			if(rand.nextBoolean()){
				share2sale.add(entry.getKey());
			}
		}
		return share2sale;
	}

	public List<String> think2buy() {
		Random rand = new Random();
		List<String> share2buy = new ArrayList<String>();
		for(String sharename : intrestetShares){
			if(rand.nextBoolean()){
				share2buy.add(sharename);
			}
		}
		return share2buy;
	}

}
