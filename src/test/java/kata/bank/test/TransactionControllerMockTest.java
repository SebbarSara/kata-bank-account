package kata.bank.test;

import static org.junit.Assert.assertEquals;

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
import kata.bank.service.TransactionService;

@RunWith(MockitoJUnitRunner.class)

public class TransactionControllerMockTest {

	
	
	@InjectMocks
	TransactionService transactionService;

	@Mock
	AccountController accountController;

	Account accountOne;
	Account accountTwo;
	Account accountThree;
	List<Transaction> transactions;
	SimpleDateFormat parseFormat;
	Date firstDate;
	Date secondDate;
	Date thirdDate;

	@Before
	public void init() throws ParseException {
		
		parseFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		firstDate = parseFormat.parse("09/02/2019 12:12:00");
		secondDate = parseFormat.parse("10/02/2019 10:10:00");
		thirdDate = parseFormat.parse("11/03/2019 09:26:05");
		
		accountOne = new Account(1,new Customer("sara", "sebbar"), 1000);
		accountTwo = new Account(2,new Customer("servane", "george"), 1000);
		accountThree = new Account(3,new Customer("Sercan", "Dacosta"), 1000);

		transactionService.createTransaction(accountOne, 100, firstDate);
		transactionService.createTransaction(accountOne, -300, firstDate);
		transactionService.createTransaction(accountOne, -300, secondDate);
		transactionService.createTransaction(accountTwo, -300, thirdDate);
		transactionService.createTransaction(accountTwo, -55, firstDate);
		transactionService.createTransaction(accountThree, -300, secondDate);

	}

	@Test
	public void getAllTransactionsTest(){
		assertEquals(6, transactionService.getAllTransactions().size());
	}

	@Test
	public void getClientTransactionsTest(){
		assertEquals(3, transactionService.findTransactionByAccount(accountOne).size());
		assertEquals(2, transactionService.findTransactionByAccount(accountTwo).size());
		assertEquals(1, transactionService.findTransactionByAccount(accountThree).size());
	}
	
	@Test
	public void getClientTransactionsByDateTest(){
		assertEquals(2, transactionService.findTransactionByAccountAndDate(accountOne,"09/02/2019 12:12:00").size());
		assertEquals(1, transactionService.findTransactionByAccountAndDate(accountTwo, "11/03/2019 09:26:05").size());
		assertEquals(1, transactionService.findTransactionByAccountAndDate(accountThree,"10/02/2019 10:10:00").size());
	}

}
