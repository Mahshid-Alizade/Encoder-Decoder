
public class Node {
	
	Object data;
	Node next, prev;
	Node left, right;
	int index;

	//constructor of LinkedList
	public Node() {
		data = null;
		next = null;
		prev = null;
	}
	
	//constructor of LinkedList
	public Node(Object data , Node next) {
		this.data = data;
		this.next = this.prev = next;
	}
	
	//constructor of LinkedList
	public Node(Object data ) {
		this.data = data;
		this.next = this.prev = null;
	}
	
	public String toString() {
		return data + "";
	}
	

}
