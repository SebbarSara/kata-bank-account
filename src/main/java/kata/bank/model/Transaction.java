package kata.bank.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

	private int id;
	private int amount;
	private Date date;
	private double balance;
	private String typeTransaction;

	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

	Account account;

	public Transaction(int amount, double balance, Date date, Account account) {
		super();
		this.amount = Math.abs(amount);
		this.date = date;
		this.account = account;
		this.balance = balance;
		if (amount > 0) {
			this.typeTransaction = "deposit   ";
		} else {
			this.typeTransaction = "withdrawal";
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	//get date in fotmat : dd/mm/yyyy hh:mm:ss
	public String getDate() {
		return dateFormat.format(this.date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getTypeTransaction() {
		return typeTransaction;
	}

	public void setTypeTransaction(String typeTransaction) {
		this.typeTransaction = typeTransaction;
	}

	public String toString() {
		return "date: " + this.date + " amount :" + this.amount;
	}

}
