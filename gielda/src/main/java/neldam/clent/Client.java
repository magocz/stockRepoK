package neldam.clent;

import java.util.Iterator;
import java.util.List;

import neldam.broker.Broker;
import neldam.share.BrokerShare;
import neldam.share.Share;
import neldam.strategy.Strategy;

public class Client {

	private Broker broker = new Broker();

	private Integer id;
	private UserData userData;

	private Strategy strategy;
	
	private java.text.DecimalFormat decimalFormat;

	public Client(Integer id, UserData userData, Strategy strategy) {
		this.id = id;
		this.userData = userData;
		this.strategy = strategy;
		createFormater();
	}

	public void doSomething() {
		sale(strategy.think2sale(userData.getWallet().getShareMap()));
		buy(strategy.think2buy());
	}

	// kupujemy za 333zl
	private void buy(List<String> share2buy) {
		Double money = 333.0;
		BrokerShare share = null;
		for (String shareName : share2buy) {
			if ((share = broker.buyShare(shareName, userData.getWallet().getMoney("PLN", money))) != null) {
				this.userData.getWallet().putShare(shareName, share.getShare());
				userData.getWallet().putMoney("PLN", money - share.getCost());
				showBuyInfo(shareName, share);
			}
		}
	}

	public void showBuyInfo(String shareName, BrokerShare brokerShare) {
		System.out.println("Kupiono akcje: " + shareName + " w ilosci :" + brokerShare.getShare().getNumber()
				+ " i w cenie: " + brokerShare.getShare().getPrice() + ", calkowity koszt: " + decimalFormat.format(brokerShare.getCost())
				+ " w tym: " + decimalFormat.format(brokerShare.getProvision()) + " prowozji");
	}

	// sprzedajemy
	private void sale(List<String> share2sale) {
		for (String shareName : share2sale) {
			Share share = userData.getWallet().getShare(shareName);
			BrokerShare brokerShare = broker.sellShare_BrokerShare(shareName, share);
			userData.getWallet().putMoney("PLN", brokerShare.getCost());
			showSaleInfo(shareName, share, brokerShare);
		}
	}

	public void showSaleInfo(String shareName, Share saleShare, BrokerShare brokerShare) {
		System.out.println("Sprzedano akcje: " + shareName + " w ilosci :" + saleShare.getNumber() + " i w cenie: "
				+ brokerShare.getShare().getPrice() + " / stara cena: " + decimalFormat.format(saleShare.getPrice()) + " z zyskiem: "
				+ decimalFormat.format((brokerShare.getProfit() - saleShare.getValuation())) + ", prowizja: " + decimalFormat.format(brokerShare.getProvision()));
	}

	public void saleAll() {
		Iterator<String> it = this.userData.getWallet().getShareMap().keySet().iterator();
		Share share;
		while (it.hasNext()) {
			String shareName = it.next();
			share = userData.getWallet().getShare(shareName);
			BrokerShare brokerShare = broker.sellShare_BrokerShare(shareName, share);
			if (brokerShare != null) {
				userData.getWallet().putMoney("PLN", brokerShare.getCost());
				showSaleInfo(shareName, share, brokerShare);
				it = this.userData.getWallet().getShareMap().keySet().iterator();
			}
		}
	}

	public void showInfo() {
		String info = "\n\n" + this.id + ": " + this.userData.getFirstName() + " " + this.userData.getLastName()
				+ "\n Money: " + decimalFormat.format(this.userData.getWallet().getMoneyInfo("PLN")) + "\n"
				+ this.userData.getWallet().getWalletInfo();
		;
		System.out.println(info);
	}

	private void createFormater() {
		decimalFormat = new java.text.DecimalFormat();
		decimalFormat.setMaximumFractionDigits(2);
		decimalFormat.setMinimumFractionDigits(2);
	}
}
