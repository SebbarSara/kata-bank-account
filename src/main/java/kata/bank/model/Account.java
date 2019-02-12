package kata.bank.model;

public class Account {

	
	private int id;
	private Customer client;
	private double currentBalance;

	public Account(int id,Customer client, double balance) {
		super();
		this.id=id;
		this.client = client;
		this.currentBalance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getClient() {
		return client;
	}

	public void setClient(Customer client) {
		this.client = client;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double balance) {
		this.currentBalance = balance;
	}

}
