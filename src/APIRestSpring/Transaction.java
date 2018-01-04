package APIRestSpring;

public class Transaction implements Comparable{
	private long timestamp;
	private double amount;
	
	public Transaction(long time, double amo){
		this.timestamp = time;
		this.amount = amo;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	

	@Override
	public int compareTo(Object o) {
		long compareTimestamp=((Transaction)o).getTimestamp();
		return (int) (compareTimestamp - this.timestamp);
	}
}
