package neldam.clent;

public class UserData {

	private String firstName;
	private String lastName;

	private Wallet wallet;

	public UserData(String firstName, String lastName, Wallet wallet) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.wallet = wallet;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
