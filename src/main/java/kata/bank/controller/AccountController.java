package kata.bank.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kata.bank.exception.NotEnoughFundsException;
import kata.bank.exception.TooMuchFundsException;
import kata.bank.model.Account;
import kata.bank.model.Customer;
import kata.bank.model.Transaction;
import kata.bank.service.AccountService;
import kata.bank.service.TransactionPrinterService;
import kata.bank.service.TransactionService;

@RestController
@RequestMapping(value = "kata/account")
public class AccountController {

	@Autowired
	AccountService accountService;

	@Autowired
	TransactionService transactionService;

	@Autowired
	TransactionPrinterService transactionPrinterService;

	SimpleDateFormat parseFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	private final AtomicInteger counter = new AtomicInteger();

	/**
	 * @param lastName  of customer
	 * @param firstName of customer
	 * @param balance   for init
	 * @return created account
	 */
	@RequestMapping("/accountCreate")
	public Account create(@RequestParam(value = "lastName") String lastName,
			@RequestParam(value = "firstName") String firstName, @RequestParam(value = "balance") double balance) {
		Customer customer = new Customer(lastName, firstName);
		Account account = new Account(counter.incrementAndGet(), customer, balance);
		accountService.save(account);
		return account;
	}

	/**
	 * @return All accounts created in AccountService
	 */
	@RequestMapping("/list")
	public List<Account> getAccountsList() {
		return accountService.getAccountsList();
	}

	/**
	 * @param id: account_id
	 * @param amount: amount of transaction
	 * @param date: date of transaction , if null the date of today will used
	 * @return the created transaction
	 * @throws TooMuchFundsException : if the amount > 2000
	 * @throws ParseException        : if error in parsing date
	 */
	@RequestMapping("/deposit")
	public Transaction deposit(int id, int amount, String date) throws TooMuchFundsException, ParseException {
		if (amount > 2000) {
			throw new TooMuchFundsException();
		}
		Date parsedDate = this.parseDate(date);
		Account account = accountService.getAccountsById(id);
		Transaction transaction = transactionService.createTransaction(account, amount, parsedDate);
		account.setCurrentBalance(account.getCurrentBalance() + amount);
		return transaction;
	}

	/**
	 * @param id: account_id
	 * @param amount: amount of transaction
	 * @param date: date of transaction , if null the date of today will used
	 * @return the created transaction
	 * @throws TooMuchFundsException : if the amount > account.getCurrentBalance
	 * @throws ParseException        : if error in parsing date
	 */
	@RequestMapping("/withdrawal")
	public Transaction withdrawal(int id, int amount, String date) throws NotEnoughFundsException, ParseException {
		Account account = accountService.getAccountsById(id);
		if (account.getCurrentBalance() < amount) {
			throw new NotEnoughFundsException();
		}

		Date parsedDate = this.parseDate(date);

		Transaction transaction = transactionService.createTransaction(account, -amount, parsedDate);

		account.setCurrentBalance(account.getCurrentBalance() - amount);
		return transaction;
	}

	/**
	 * @param date: string that will be parsed in Date
	 * @return : parsed Date 
	 * @throws ParseException : handle parse Exception
	 */
	private Date parseDate(String date) throws ParseException {
		Date parsedDate = new Date();
		if (date != null && date.isEmpty()) {
			parsedDate = parseFormat.parse(date);

		}
		return parsedDate;
	}

}
