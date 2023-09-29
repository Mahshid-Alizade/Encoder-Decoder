
public class MaxHeap {

	public int[] heap;
	private int size;
	private int maxsize;
	private int root;

	public MaxHeap(int maxsize) {
		this.maxsize = maxsize;
		this.size = 0;
		heap = new int[this.maxsize + 1];
		heap[0] = 999999;
	}

	public int getSize() {
//		System.out.println(size);
		return size;
	}

	// return the pos if parent
	private int parent(int pos) {
		return pos / 2;
	}

	// return the pos of leftChild
	private int leftChild(int pos) {
		return (2 * pos);
	}

	// return the pos of rightchild
	private int rightChild(int pos) {
		return (2 * pos) + 1;
	}

	// is the node is leaf return true else return false
	private boolean isLeaf(int pos) {// TODO >= size/2
		if (pos > (size / 2) && pos <= size) {
			return true;
		}
		return false;
	}

	// swap two given nodes
	private void swap(int p1, int p2) {
		int tmp;
		tmp = heap[p1];
		heap[p1] = heap[p2];
		heap[p2] = tmp;
	}

	// insert an element to tree
	public void insert(int element) {

		size += 1;
		heap[size] = element;

		// Traverse up
		int current = size;
		while (heap[current] > heap[parent(current)]) {
			swap(current, parent(current));
			current = parent(current);
		}
	}

	// find pos of the wrong node
	private void maxHeapify(int pos) {
		if (isLeaf(pos))
			return;

		if (heap[pos] < heap[leftChild(pos)] || heap[pos] < heap[rightChild(pos)]) {

			if (heap[leftChild(pos)] > heap[rightChild(pos)]) {
				swap(pos, leftChild(pos));
				maxHeapify(leftChild(pos));
			} else {
				swap(pos, rightChild(pos));
				maxHeapify(rightChild(pos));
			}
		}
	}

	// remove the root
	public int deleteRoot() {
		int prevRoot = heap[1];
		heap[1] = heap[size];
		heap[size] = 0;
		size--;
		maxHeapify(1);
		return prevRoot;
	}
	
	//get root
	int getRoot(){
		return heap[1];
	}
	
	//CONTAINS
	boolean contains(int x) {
		for(int i = 1 ; i < heap.length ; i ++) {
			if (heap[i] == x)
				return true;
		}
		return false;
	}
	

	//PRINT HEAP
	public void print() {
		for (int i = 1; i <= (size / 2); i++) {
			System.out.println(" PARENT : " + heap[i] + " LEFT CHILD : " + heap[2 * i] + " RIGHT CHILD :"
					+ heap[2 * i + 1]);

		}
	}
	
	//CLEAR
	void clear() {
		size = 0;
	}

}
