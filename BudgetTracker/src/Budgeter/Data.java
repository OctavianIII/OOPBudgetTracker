package Budgeter;

public class Data {
	private static Data instance = null;
	private DataNode head;
	
	private Data() {
		head = new DataNode(0);
	}
	
	public static Data getInstance() {
		if(instance==null) {
			instance = new Data();
		}
		return instance;
	}

	public DataNode getHead() {
		return head;
	}

	public void setHead(DataNode head) {
		this.head = head;
	}
}
