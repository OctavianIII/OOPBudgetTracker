package Budgeter;

public class DataNode {
	private static int count = 0;
	protected int id;
	protected double goal;
	protected String title;
	public DataNode subData;
	public DataNode next;
	
	
	public DataNode(double g, String t) {
		goal = g;
		title = t;
		DataNode.count++;
		id=DataNode.count;
	}
	
	public DataNode(double g) {
		goal = g;
		title = "";
		DataNode.count++;
		id=DataNode.count;
	}
	
	public DataNode() {
		goal = 0;
		title = "";
		DataNode.count++;
		id=DataNode.count;
	}

	public double getGoal() {
		return goal;
	}

	public void setGoal(double goal) {
		this.goal = goal;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public static void setCount(int count) {
		DataNode.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	//updates the node with new data
	public void update(String t, double g) {
		title = t;
		goal = g;
	}
	
	//adds a child to the node
	public boolean add(DataNode d) {
		DataNode h = subData;
		if(h == null) {
			subData = d;
			return true;
		}
		DataNode cur = h;
		while(cur.next!=null) {
			cur=cur.next;
		}
		cur.next=d;
		return true;
	}
	
	//removes a child from the node
	public boolean remove(DataNode d) {
		DataNode h = subData;
		if(h==null) {
			return false;
		}
		DataNode cur = h;
		if(cur.equals(d)) {
			subData = subData.next;
			return true;
		}
		cur = cur.next;
		DataNode prev = h;
		while(cur!=null) {
			if(cur.equals(d)) {
				prev.next=cur.next;
				return true;
			}
		}
		return false;
	}
	
	//gets a child with a matching ID to the node
	public DataNode get(int i) {
		DataNode cur = this.subData;
		while(cur!=null) {
			if(cur.getId()==i) {
				return cur;
			}
			cur=cur.next;
		}
		return null;
	}
	
	//returns an array containing the data types stored within the node's children
	public Object[] header() {
		Object[] h = {"", "", "Title", "Budget", "Start Date", "End Date", "Total Spent"};
		return h;
	}
}
