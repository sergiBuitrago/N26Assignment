package APIRestSpring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SqlMock {
	// This class is supposed to Mock a dataBase connection, each function on this class 
	// is a query which will be excecuted in O(1);
	private List<Transaction> TransactionTable;
	
	SqlMock(){
		TransactionTable = new ArrayList<Transaction>();
	}
	
	public void add(Transaction t) {
	//This function Mocks a insert into the table.
		TransactionTable.add(t);
		Collections.sort(TransactionTable);
	}
	
	public List<Transaction> get(long min, long max) {
	//This function mocks the following select:
	//SELECT * FROM Transactions WHERE Timestamp > $min AND Timestamps < $max
		Transaction t = TransactionTable.get(0);
		List<Transaction> result = new ArrayList<Transaction>();
		for(int i = 0; t.getTimestamp() <= min; i++) {
			t = TransactionTable.get(i);
			if (t.getTimestamp()<= max & t.getTimestamp() >= min) {
				result.add(t);
			}
		}
		
		return result;
		
	}
	
	public Transaction getLast() {
	//This function mocks the following select:
	//SELECT MAX(Timestamp), Amount FROM Transactions 
		return TransactionTable.get(0);
		
	}

	public double getMaxLast60() {
	//This function mocks the following select:
	//SELECT MAX(Amount) FROM Transactions WHERE timestamp > $mintime
		double max = 0;
		int i = 0;
		long minTime = System.currentTimeMillis() - 60000;
		while (TransactionTable.get(i).getTimestamp() > minTime) {
			if (TransactionTable.get(i).getAmount() > max) {
				max = TransactionTable.get(0).getAmount();
			}
			i++;
		}
		return max;
		
	}
	
	public double getMinLast60() {
	//This function mocks the following select:
	//SELECT MIN(Amount) FROM Transactions WHERE timestamp > $mintime
		double min = Double.MAX_VALUE;
		int i = 0;
		long minTime = System.currentTimeMillis() - 60000;
		while (TransactionTable.get(i).getTimestamp() > minTime) {
			if (TransactionTable.get(i).getAmount() < min) {
				min = TransactionTable.get(0).getAmount();
			}
			i++;
		}
		return min;
	}
	
	
	
}
