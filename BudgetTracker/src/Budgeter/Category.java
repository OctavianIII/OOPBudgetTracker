package Budgeter;

public class Category extends DataNode {
	private String description;
	
	public Category(double g, String t, String d) {
		super(g, t);
		description = d;
	}
	
	public Category(int id, String t, double g, String d) {
		super(g, t);
		description = d;
		this.id = id;
	}
	
	public void update(String t, double g, String d) {
		title = t;
		goal = g;
		description = d;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Category," + id + "," + title + "," + goal +  "," + description;
	}
	
	public Object[] header() {
		Object[] h = {"", "", "Title", "Date", "Amount"};
		return h;
	}
}
