package neldam.clent;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import neldam.share.Share;

public class Wallet {
	private Map<String, Double> moneyMap = new HashMap<String, Double>();
	private Map<String, Share> shareMap = new HashMap<String, Share>();

	public Map<String, Share> getShareMap() {
		return shareMap;
	}

	public void setShareMap(Map<String, Share> shareMap) {
		this.shareMap = shareMap;
	}

	public Wallet(String currency, Double value) {
		this.moneyMap.put(currency, value);
	}

	public void putMoney(String currency, Double value) {
		if (this.moneyMap.containsKey(currency)) {
			this.moneyMap.put(currency, addMoney(this.moneyMap.get(currency), value));
			return;
		}
		this.moneyMap.put(currency, value);
	}

	private Double addMoney(Double oldMoney, Double newMoney) {
		return newMoney + oldMoney;
	}

	public Double getMoney(String currency, Double value) {
		if (this.moneyMap.containsKey(currency)) {
			double moneyInWallet = this.moneyMap.get(currency);
			if (moneyInWallet >= value) {
				this.moneyMap.put(currency, moneyInWallet - value);
				return value;
			}
			return null;
		}
		return null;
	}

	public Double getMoneyInfo(String currency) {
		return this.moneyMap.get(currency);
	}

	public void putShare(String shareName, Share share) {
		if (this.shareMap.containsKey(shareName)) {
			this.shareMap.put(shareName, countNewShare(share, this.shareMap.get(shareName)));
			return;
		}
		this.shareMap.put(shareName, share);
	}

	private Share countNewShare(Share buyShare, Share oldShare) {
		return new Share(countAveragePrice(buyShare, oldShare), countNewShareNumber(buyShare, oldShare));
	}

	private Double countAveragePrice(Share buyShare, Share oldShare) {
		return ((buyShare.getNumber() * buyShare.getPrice()) + (oldShare.getNumber() * oldShare.getPrice()))
				/ (buyShare.getNumber() + oldShare.getNumber());
	}

	private Integer countNewShareNumber(Share buyShare, Share oldShare) {
		return buyShare.getNumber() + oldShare.getNumber();
	}

	public Share getShare(String shareName, Integer shareNumber) {
		if (this.shareMap.containsKey(shareName)) {
			return this.shareMap.get(shareName).getShare(shareNumber);
		}
		return null;
	}

	public Share getShare(String shareName) {
		if (this.shareMap.containsKey(shareName)) {
			Share share = this.shareMap.get(shareName).getShare();
			this.shareMap.remove(shareName);
			return share;
		}
		return null;
	}

	public Share getShareInfo(String shareName) {
		return this.shareMap.get(shareName);
	}

	public String getWalletInfo() {
		String info = "";
		for (Entry<String, Share> entry : this.shareMap.entrySet()) {
			Share share = (Share) entry.getValue();
			info += entry.getKey() + ": " + share.getShareInfo();
		}
		return info;
	}

}
