package Budgeter;

public class Transaction extends DataNode{
	private String date;
	private double amount;
	
	public Transaction(String name, String date, double amount) {
		super(0, name);
		this.date = date;
		this.amount = amount;
	}
	
	public Transaction(int id, String name, double amount, String date) {
		super(0, name);
		this.date = date;
		this.amount = amount;
		this.id = id;
	}
	
	public void update(String t, double a, String d) {
		title = t;
		amount = a;
		date = d;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction," + id + "," + title + "," + date +  "," + amount;
	}
}
