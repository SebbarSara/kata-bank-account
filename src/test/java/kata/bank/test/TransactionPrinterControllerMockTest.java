package kata.bank.test;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import kata.bank.controller.AccountController;
import kata.bank.model.Account;
import kata.bank.model.Customer;
import kata.bank.model.Transaction;
import kata.bank.service.TransactionPrinterService;
import kata.bank.service.TransactionService;

@RunWith(MockitoJUnitRunner.class)

public class TransactionPrinterControllerMockTest {

	@InjectMocks
	TransactionPrinterService transactionPrinterService;

	@Mock
	AccountController accountController;

	@InjectMocks
	TransactionService transactionService;

	Account accountOne;

	List<Transaction> transactions;
	SimpleDateFormat parseFormat;
	Date firstDate;
	Date secondDate;

	@Before
	public void init() throws ParseException {
		parseFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

		firstDate = parseFormat.parse("09/02/2019 12:04:30");
		secondDate = parseFormat.parse("10/02/2019 10:12:00");

		accountOne = new Account(1,new Customer("sara", "sebbar"), 1000);

		transactionService.createTransaction(accountOne, 100, firstDate);
		transactionService.createTransaction(accountOne, -300, firstDate);
		transactionService.createTransaction(accountOne, -300, secondDate);

	}

	@Test
	public void printTransactionsTest() {
		List<Transaction> transactions = transactionService.getAllTransactions();
		StringBuilder printer = transactionPrinterService.print(transactions);
		System.out.println(transactions);
		System.out.println(transactionPrinterService.print(transactions).toString());
		assertTrue(printer.toString().contains("<br>10/02/2019 10:12:00 || withdrawal || 300.0     || 700.0    || 1"));
		assertTrue(printer.toString().contains(" <br>09/02/2019 12:04:30 || withdrawal || 300.0     || 700.0    || 1"));
		assertTrue(printer.toString().contains(" <br>09/02/2019 12:04:30 || deposit    || 100.0     || 1100.0    || 1"));

	}

}
