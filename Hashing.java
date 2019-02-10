package utd.ds.assign6;

import java.util.ArrayList;
import java.util.List;

public class Hashing {

	static int addedCount = 0;
	static int collision = 0;
	public static void main(String[] args) {

		Node[] NodeArray = new Node[31];

		List<Node> input = new ArrayList<Node>();
		input.add(new Node("Cat"));
		input.add(new Node("Kite"));
		input.add(new Node("Visit"));
		input.add(new Node("Teacher"));
		input.add(new Node("Swimming"));
		input.add(new Node("Playing"));
		input.add(new Node("On Call"));
		input.add(new Node("Class1"));
		input.add(new Node("Street 6"));
		input.add(new Node("Road 10"));
		input.add(new Node("Coit Rd"));
		input.add(new Node("Kulkarni"));
		input.add(new Node("Cricket"));
		input.add(new Node("Kedar7"));
		input.add(new Node("Pranav15"));
		input.add(new Node("J House"));
		input.add(new Node("Turnpike"));
		input.add(new Node("Laptop"));
		input.add(new Node("phone"));
		input.add(new Node("down"));

		for(Node node : input) {
			if(addedCount+1 <= (Math.ceil(NodeArray.length/2))) {
				hasing(NodeArray, node);	
			}else {

				System.out.println("No of collisions till rehashing :"+collision);
				System.out.println();
				collision = 0;
				int newSize = 2 * NodeArray.length;

				while(!isPrime(newSize)) {
					newSize++;
				}
				List<Node> rehashlist = new ArrayList<>();

				for(Node n : NodeArray) {
					if(n != null && n.status) {
						rehashlist.add(n);
					}
				}

				NodeArray = new Node[newSize];
				addedCount = 0;
				for(Node rehashnode : rehashlist) {
					hasing(NodeArray, rehashnode);
				}

				hasing(NodeArray, node);				
			}
		}

		System.out.println("No of collisions during and after rehashing : "+collision+", size:"+NodeArray.length);
		System.out.println();
		int k = 0;
		for(Node n : NodeArray) {
			if(n != null)
				System.out.println(n.key+", at index: "+k);
			k++;
		}
	}

	public static void hasing(Node[] NodeArray, Node node) {
		int ascSum = 0;
		int modVal = 0;
		for(char c : node.key.toCharArray()) {
			ascSum += c;
		}

		modVal = ascSum % NodeArray.length; // Identifying index position to add
		if(NodeArray[modVal] == null) {			
			NodeArray[modVal] = node;
			addedCount = addedCount +1;
		}else {
			collision++;
			System.out.println(node.key+", collision @ :"+modVal+", key present :"+NodeArray[modVal].key);
			int i = 1;
			int newIndex =  (int) (modVal + Math.pow(i, 2)); //Quadratic probing 
			while(NodeArray[newIndex] != null) {			
				i++;
				collision++;
				System.out.println(node.key+", collision @ :"+newIndex+", key present :"+NodeArray[newIndex].key);
				newIndex = (int) (modVal + Math.pow(i, 2));

				if(newIndex >= NodeArray.length) {
					newIndex = newIndex % NodeArray.length;
				}
			}
			NodeArray[newIndex] = node;
			addedCount = addedCount +1;
		}
	}

	public static boolean isPrime(int no) {
		boolean flag = true;
		for(int i = 2; i < no/2; i++) {
			if(no % i == 0) {
				flag = false;
				break;
			}
		}		
		return flag;
	}
}
class Node{
	String key;
	boolean status = false;

	public Node(String key) {
		this.key = key;
		this.status = true;
	}
}

