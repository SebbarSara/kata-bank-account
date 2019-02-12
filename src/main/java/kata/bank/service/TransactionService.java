package kata.bank.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kata.bank.model.Account;
import kata.bank.model.Transaction;

@Service
public class TransactionService {

	private Map<Integer, Transaction> DB = new HashMap<>();

	public void save(Transaction transaction) {
		transaction.setId(DB.keySet().size() + 1);
		DB.put(transaction.getId(), transaction);
	};

	public Transaction createTransaction(Account account, int amount, Date date) {

		double balanceAfterTransaction = Double.sum(account.getCurrentBalance(), amount);

		Transaction transaction = new Transaction(amount, balanceAfterTransaction, date, account);
		transaction.setId(DB.keySet().size() + 1);
		DB.put(transaction.getId(), transaction);
		return transaction;
	}

	public List<Transaction> findTransactionByAccount(Account account) {

		List<Transaction> transactions = new ArrayList<Transaction>();
		for (Transaction transaction : DB.values()) {
			if (transaction.getAccount() == account) {
				transactions.add(transaction);
			}
		}

		return transactions;
	}

	public List<Transaction> findTransactionByAccountAndDate(Account account, String date) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		for (Transaction transaction : this.findTransactionByAccount(account)) {
			if (transaction.getAccount().equals(account) && transaction.getDate().equals(date)) {
				transactions.add(transaction);
			}
		}

		return transactions;
	}

	public List<Transaction> getAllTransactions() {
		List<Transaction> list = new ArrayList<>();
		if (list.isEmpty()) {
			list.addAll(DB.values());
		}
		return list;

	}

}
