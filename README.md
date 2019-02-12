run  : "mvn spring-boot:run" to compile and run the application

Test AccountController and TransactionController :
	
	1-Create an account: 
http://localhost:8080/kata/account/accountCreate?firstName=Sara&lastName=Sebbar&balance=1000

	2-Show accounts list: 
http://localhost:8080/kata/account/list

	3-Deposit 200 in the account with id=1: 	 
http://localhost:8080/kata/account/deposit?id=1&amount=200

	4-withdrawal 400 from the account with id=1: 	 
http://localhost:8080/kata/account/deposit?id=1&amount=400

	5-print all transactions (all accounts and all dates)
http://localhost:8080/kata/transaction/printAllTransactions
	 	
	6-print all transactions for the specific account with id =1
http://localhost:8080/kata/transaction/printAccountTransactions?id=1

	7-print all transactions for the specific account with id =1 and date= 		date of transaction 
http://localhost:8080/kata/transaction/printTransactionsByDate?id=1&date=11/02/2019%2004:58:39