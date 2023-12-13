package Budgeter;

public class Main {
	public static Data d;
	public static DataParser parser;
	public static MainGUI mGUI;
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		d = Data.getInstance();
		
//		Range r1 = new Range("October", 400, "1 Oct", "31 Oct");
//		Range r2 = new Range("Novemenber", 600, "1 Nov", "30 Nov");
//		Range r3 = new Range("December", 700, "1 Dec", "31 Dec");
//		
//		d.getHead().add(r1);
//		d.getHead().add(r2);
//		d.getHead().add(r3);
//		
//		
//		Category c1 = new Category(200, "Food", "Purchases for eating");
//		Category c2 = new Category(240, "Food", "Purchases for eating");
//		Category c3 = new Category(220, "Food", "Purchases for eating");
//		Category c4 = new Category(300, "Subway", "Transportation via the subway");
//		Category c5 = new Category(140, "Subway", "Transportation via the subway");
//		Category c6 = new Category(290, "Entertainment", "Entermainment such as movies and nights out");
//		
//		r1.add(c1);
//		r1.add(c4);
//		r2.add(c2);
//		r2.add(c5);
//		r2.add(c6);
//		r3.add(c3);
//		
//		Transaction t1 = new Transaction("Pizza", "3 Oct", 4.5);
//		Transaction t2 = new Transaction("Pasta", "7 Oct", 8);
//		Transaction t3 = new Transaction("Q Train", "12 Oct", 5);
//		Transaction t4 = new Transaction("1 Train", "29 Oct", 23);
//		Transaction t5 = new Transaction("Sushi", "3 Nov", 4);
//		Transaction t6 = new Transaction("BBQ", "6 Nov", 22);
//		Transaction t7 = new Transaction("3 Train", "14 Nov", 53);
//		Transaction t8 = new Transaction("6 Train", "17 Nov", 7);
//		Transaction t9 = new Transaction("Movies", "20 Nov", 12);
//		Transaction t10 = new Transaction("Video Game", "30 Nov", 49);
//		Transaction t11 = new Transaction("Groceries", "1 Dec", 123);
//		Transaction t12 = new Transaction("Snacks", "19 Dec", 1);
//		
//		c1.add(t1);
//		c1.add(t2);
//		c2.add(t5);
//		c2.add(t6);
//		c3.add(t11);
//		c3.add(t12);
//		c4.add(t3);
//		c4.add(t4);
//		c5.add(t7);
//		c5.add(t8);
//		c6.add(t9);
//		c6.add(t10);
		
		
		mGUI = new MainGUI();
		mGUI.show();
		
	}

}
