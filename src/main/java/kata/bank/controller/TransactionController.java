package kata.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kata.bank.model.Account;
import kata.bank.model.Transaction;
import kata.bank.service.AccountService;
import kata.bank.service.TransactionPrinterService;
import kata.bank.service.TransactionService;

@RestController
@RequestMapping(value = "kata/transaction")
public class TransactionController {
	
	
	@Autowired
	AccountService accountService;

	@Autowired
	TransactionService transactionService;

	@Autowired
	TransactionPrinterService transactionPrinterService;
	
	
	/**
	 * @return stringBuilder that contains all Transactions of all accounts
	 */
	@RequestMapping("/printAllTransactions")
	public StringBuilder printAllTransaction() {
		List<Transaction> transactions = transactionService.getAllTransactions();
		StringBuilder printer = transactionPrinterService.print(transactions);
		return printer;
	}
	/**
	 * @param id : account id
	 * @return stringBuilder that contains all Transactions done by account with id in parameter
	 */
	@RequestMapping("/printAccountTransactions")
	public StringBuilder printAccountTransaction(int id) {
		Account account = accountService.getAccountsById(id);
		List<Transaction> transactions = transactionService.findTransactionByAccount(account);
		StringBuilder printer = transactionPrinterService.print(transactions);
		return printer;
	}

	/**
	 * @param id : account id
	 * @param date : transaction date
	 * @return stringBuilder that contains all Transactions done at the date in parameter by account with id in parameter
	 */
	@RequestMapping("/printTransactionsByDate")
	public StringBuilder printAccountTransactionByDate(int id, String date) {
		Account account = accountService.getAccountsById(id);
		List<Transaction> transactions = transactionService.findTransactionByAccountAndDate(account, date);
		StringBuilder printer = transactionPrinterService.print(transactions);
		return printer;
	}

}
