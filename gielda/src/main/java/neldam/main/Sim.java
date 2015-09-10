package neldam.main;

import java.util.Date;
import java.util.List;

import neldam.clent.Client;
import neldam.time.Time;

public class Sim {
	
	private Time time = Time.getTime();
	
	public void startSimulation(List<Client> clientList){
		for(Date day : time.getDays()){
			for(Client client : clientList){
				client.doSomething();
			}	
			time.incrementDate();
		}
		
		time.decrementDate();
		System.out.println("\n\n\nKoniec sysmulacji, wyniki koncowe: \n");
		for(Client client : clientList){
			client.saleAll();
			client.showInfo();
		}
	}
}
