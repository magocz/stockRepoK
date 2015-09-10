package neldam.broker;

import neldam.share.BrokerShare;
import neldam.share.Share;
import neldam.stock.Stock;

public class Broker {

	private final static Double PROVISION = 0.005;
	private Double provisionValue;

	private Stock stock = Stock.getStockInstance();

	public Broker() {
		provisionValue = 0.0;
	}

	public BrokerShare sellShare_BrokerShare(String shareName, Share share) {
		Double currentSharePrice = stock.getSharePrice(shareName);
		if (currentSharePrice != null) {
			share.setPrice(currentSharePrice);
			return new BrokerShare(share, currentValuation4Sale(currentSharePrice, share.getNumber()), provisionValue);
		}
		return null;
	}

	public Double currentValuation4Sale(Double sharePrice, Integer number) {
		Double currentValuation = sharePrice * number;
		provisionValue = (currentValuation * PROVISION);
		return currentValuation - provisionValue;
	}

	public BrokerShare buyShare(String shareName, Double money) {
		if (money != null) {
			Double currentSharePrice = stock.getSharePrice(shareName);
			Integer shareNumber = countShareNumber(money, currentSharePrice);
			return new BrokerShare(new Share(currentSharePrice, shareNumber),
					currentValuation4Buy(currentSharePrice, shareNumber), provisionValue);
		}
		return null;
	}
	
	public Double currentValuation4Buy(Double sharePrice, Integer number) {
		Double currentValuation = sharePrice * number;
		provisionValue = (currentValuation * PROVISION);
		return currentValuation - provisionValue;
	}

	public Integer countShareNumber(Double money, Double price) {
		int shareNumber = (int) (money / price);
		money = money - ((shareNumber * price) + ((shareNumber * price) * PROVISION));
		return shareNumber;
	}

	public boolean isProfit(String shareName, Double sharePrice) {
		double profit = (stock.getSharePrice(shareName) - sharePrice) / sharePrice;
		if (profit > PROVISION) {
			return true;
		}
		return false;
	}
}
