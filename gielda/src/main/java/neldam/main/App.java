package neldam.main;

import java.util.ArrayList;
import java.util.List;

import neldam.clent.Client;
import neldam.clent.UserData;
import neldam.clent.Wallet;
import neldam.strategy.impl.MarketTiming_2;
import neldam.strategy.impl.MarketTiming;
import neldam.strategy.impl.RandomStrategy;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		Sim sim = new Sim();
		sim.startSimulation(createClientList());
	}

	private static List<Client> createClientList() {
		List<Client> clientList = new ArrayList<Client>();
		clientList.add(new Client(clientList.size(), new UserData("Jan", "Kowalski", new Wallet("PLN", 1000.0)),
				new MarketTiming_2()));
		clientList.add(new Client(clientList.size(), new UserData("Borys", "Niewiem", new Wallet("PLN", 1000.0)),
				new MarketTiming()));
		clientList.add(new Client(clientList.size(), new UserData("Mateusz", "Hasme", new Wallet("PLN", 1000.0)),
				new RandomStrategy()));
		return clientList;
	}
}
