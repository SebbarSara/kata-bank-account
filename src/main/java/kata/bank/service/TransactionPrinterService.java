package kata.bank.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kata.bank.model.Transaction;

@Service
public class TransactionPrinterService {
	public static final String STATEMENT_HEADER = "**date of operation**||**type**||**amount**||**balance after transaction||**Account id** \n";

	/**
	 * @param transactions : all transactions to print
	 * @return : StringBuilder with STATEMENT_HEADER in first line and as many lines
	 *         as transactions size with all information see TransactionLine
	 */
	public StringBuilder print(List<Transaction> transactions) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(STATEMENT_HEADER);

		transactions.stream().map((Transaction transaction) -> {
			return transactionLine(transaction);
		}).collect(Collectors.toCollection(LinkedList::new)).descendingIterator()
				.forEachRemaining(t -> stringBuilder.append(t));

		return stringBuilder;

	}

	/**
     * @param transaction : to print
     * @return StringBuilder that contains transaction infos like :
     *  09/02/2019 12:04:30 || deposit    || 100.0     || 1100.0
     */
    private StringBuilder transactionLine(Transaction transaction) {
    	StringBuilder stringBuilder = new StringBuilder();
    	
        
        return  stringBuilder.append(("\n <br>"+transaction.getDate()
                + " || "+ transaction.getTypeTransaction()
                + " || " + transaction.getAmount())
        		+ "     || " + transaction.getBalance()
        		+ "    || " + transaction.getAccount().getId());
    }
}
