package APIRestSpring;

public class Stadistics {
	double sum;
	double avg;
	double max;
	double min;
	long count;

	public Stadistics() {
	}
	
	public void newTransaction(Transaction t) {
		sum = t.getAmount() + sum;
		count = count + 1;
		if(t.getAmount() > max) {
			max = t.getAmount();
		}
		if(t.getAmount() < min) {
			min = t.getAmount();
		}
	}
	
	public void newTransactionEvent(Transaction t, SqlMock sql) {
		try {
			long time = (t.getTimestamp() + 60000) - System.currentTimeMillis(); 
			Thread.sleep(time);
			sum = sum - t.getAmount();
			count = count - 1;
			if(max == t.getAmount()) {
				max = sql.getMaxLast60();
			}
			if(min == t.getAmount()) {
				min = sql.getMinLast60();
			}
		} catch (InterruptedException e) {
			// This is Ok
		}
	}
}