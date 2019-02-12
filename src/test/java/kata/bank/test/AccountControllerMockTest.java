package kata.bank.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import kata.bank.controller.AccountController;
import kata.bank.exception.NotEnoughFundsException;
import kata.bank.exception.TooMuchFundsException;
import kata.bank.model.Account;
import kata.bank.model.Customer;
import kata.bank.service.AccountService;
import kata.bank.service.TransactionService;

@RunWith(MockitoJUnitRunner.class)

public class AccountControllerMockTest {

	@Mock
	AccountService accountService;

	@Mock
	TransactionService TransactionService;
	
	@InjectMocks
	AccountController accountController;

	Customer clientOne;
	Customer clientTwo;
	Customer clientThree;
	Account accountOne;
	Account accountTwo;
	Account accountThree;
	List<Account> accountsList;
	SimpleDateFormat parseFormat;
	String date = "10/02/2019 10:23:00";

	@Before
	public void init() throws ParseException {
		
//		parseFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//		date = parseFormat.parse("10/02/2019 10:23:00");
		
		clientOne = new Customer("sara", "sebbar");
		clientTwo = new Customer("sara", "sebbar");
		clientThree = new Customer("sara", "sebbar");
		
		 accountOne  = new Account(1,clientOne, 1000);

		accountsList = new ArrayList<Account>();
		accountsList.add(accountOne);
		accountsList.add(new Account(2,clientTwo, 1000));
		accountsList.add(new Account(3,clientThree, 1000));
	}

	@Test
	public void getAllAcountsTest() {

		

		when(accountService.getAccountsList()).thenReturn(accountsList);

//		verify(accountController).create(accountOne);

		assertEquals(3, accountController.getAccountsList().size());

	}

	@Test
	public void DepositTest() throws TooMuchFundsException, ParseException {

		when(accountService.getAccountsById(1)).thenReturn(accountsList.get(0));
		accountsList.get(0).setCurrentBalance(1000);
		accountController.deposit(accountsList.get(0).getId(), 205, date);
		assertEquals(1205, accountsList.get(0).getCurrentBalance(), 0.0000);
	}

	@Test(expected = TooMuchFundsException.class)
	public void TooMuchDepositTest() throws TooMuchFundsException, ParseException {
		accountsList.get(0).setCurrentBalance(1000);
		accountController.deposit(accountsList.get(0).getId(), 5000, date);
	}

	@Test
	public void WithDrawalTest() throws NotEnoughFundsException, ParseException {

		when(accountService.getAccountsById(1)).thenReturn(accountsList.get(0));
		accountsList.get(0).setCurrentBalance(1000);

		accountController.withdrawal(accountsList.get(0).getId(), 205, date);
		accountController.withdrawal(accountsList.get(0).getId(), 300, date);
		assertEquals(495, accountsList.get(0).getCurrentBalance(), 0.0000);

	}

	@Test(expected = NotEnoughFundsException.class)
	public void NotEnoughWithDrawalTest() throws NotEnoughFundsException, ParseException {
		when(accountService.getAccountsById(1)).thenReturn(accountsList.get(0));
		accountsList.get(0).setCurrentBalance(1000);
		accountController.withdrawal(accountsList.get(0).getId(), 5000, date);		
	}

}
