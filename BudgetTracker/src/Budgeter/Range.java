package Budgeter;

public class Range extends DataNode {
	private String start;
	private String end;
	
	public Range(String t, double g, String s, String e) {
		super(g,t);
		start = s;
		end = e;
	}
	
	public Range(int id, String t, double g, String s, String e) {
		super(g, t);
		start = s;
		end = e;
		this.id = id;
	}
	
	public void update(String t, double g, String s, String e) {
		title = t;
		goal = g;
		start = s;
		end = e;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "Range," + id + "," + title + "," + goal +  "," + start + "," + end;
	}
	
	public Object[] header() {
		Object[] h = {"", "", "Title", "Budget", "Description", "Total Spent"};
		return h;
	}
}
