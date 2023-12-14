package Budgeter;

public class DataParser {
	
	public static boolean parse(String s) {
		DataNode h = new DataNode();
		String[] arr = s.split("\n", 0);
		DataNode curR = null;
		DataNode curC = null; 
		DataNode curT = null;
		int count =0;
		//object creation based on line specifiers from object's toString method
		for(int i = 0; i < arr.length; i++) {
			String[] line = arr[i].split(",", 0);
			if(line[0].equals("Range")) {
				if(curR==null) {
					h.subData = new Range(Integer.parseInt(line[1]), line[2], Double.parseDouble(line[3]), line[4], line[5]);	
					curR = h.subData;
				} else {
					curR.next = new Range(Integer.parseInt(line[1]), line[2], Double.parseDouble(line[3]), line[4], line[5]);	
					curR=curR.next;
					curC=null;
					curT=null;
				}
				count++;
			}
			if(line[0].equals("Category")) {
				if(curC==null) {
					curR.subData = new Category(Integer.parseInt(line[1]), line[2], Double.parseDouble(line[3]), line[4]);
					curC = curR.subData;
				} else {
					curC.next = new Category(Integer.parseInt(line[1]), line[2], Double.parseDouble(line[3]), line[4]);
					curC=curC.next;
					curT=null;
				}
				count++;
			}
			if(line[0].equals("Transaction")) {
				if(curT==null) {
					curC.subData = new Transaction(Integer.parseInt(line[1]), line[2], Double.parseDouble(line[4]), line[3]);
					curT=curC.subData;
				} else {
					curT.next = new Transaction(Integer.parseInt(line[1]), line[2], Double.parseDouble(line[4]), line[3]);
					curT=curT.next;
				}
				count++;
			}
		}
		DataNode.setCount(count);
		Main.d.setHead(h);
		return true;
	}
	
	public static String parse(Data d) {
		String s = "";
		DataNode ranges = d.getHead().subData;
		while(ranges!=null) {
			DataNode cats = ranges.subData;
			s+=ranges.toString() + "\n";
			while(cats!=null) {
				DataNode t = cats.subData;
				s+=cats.toString() + "\n";
				while(t!=null) {
					s+=t.toString() + "\n";
					t=t.next;
				}
				cats=cats.next;
			}
			ranges=ranges.next;
			
		}
		return s;
	}
	
	//stores the data within all the children of a node in a 2d array
	public static String[][] parse(DataNode d){
		DataNode cur = d.subData;
		int size = 0;
		while(cur!=null) {
			cur = cur.next;
			size++;
		}
		String arr[][] = new String[size][];
		cur = d.subData;
		for(int i = 0; i < size; i++) {
			String tmp[] = cur.toString().split(",");
			arr[i] = new String[tmp.length+1];
			System.arraycopy(tmp, 0, arr[i], 0, tmp.length);
			arr[i][tmp.length] = String.format("%.2f", getSum(cur));
			cur=cur.next;
		}
		return arr;
	}
	
	//gets the sum of money spent in all the children of a given node
	public static double getSum(DataNode d) {
		if(d instanceof Transaction || d.subData==null) {
			return 0;
		}
		double amount = 0;
		DataNode cur = d.subData;
		while(cur!=null) {
			DataNode sub = cur;
			while(!(sub instanceof Transaction) && sub != null) {
				sub=sub.subData;
			}
			while(sub!=null) {
				amount+=((Transaction) sub).getAmount();
				sub=sub.next;
			}
			if((cur instanceof Transaction)) {
				break;
			}
			cur=cur.next;
		}
		System.out.println("\n\n\n");
		return amount;
	}
}
