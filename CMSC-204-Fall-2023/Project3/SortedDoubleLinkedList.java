/*
 * Rose LeFlore
 * Project 3
 * Professor Thai
 * Due: 10/9
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{
	
	private java.util.Comparator<T> comparator;
	
	public SortedDoubleLinkedList(java.util.Comparator<T> compareableObject) {
		super();
		this.comparator  = compareableObject;
	}
	
	//inserts element in correct position in the sorted list
	public void add(T data) {
		Node<T> newNode = new Node<>(data);
		
		//if list is empty, just add the node
		if (head == null) {
			head = newNode;
			tail = newNode;
		}
		//if new node is less than head node, replace as new head
		else if(comparator.compare(data, (T) head.data) <= 0){
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		//if new node is greater than tail, replace as new tail
		else if(comparator.compare(data, (T) tail.data) >= 0) {
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		}
		//otherwise, goes in middle in specified position
		else {
			Node<T> current = head;
			//traverse through the list until larger element is found
			while(current.next != null && comparator.compare(data, current.data) > 0) {
				current = current.next;
			}
			//insert node before current node
			newNode.prev = current.prev;
			newNode.next = current;
			current.prev.next = newNode;
			current.prev = newNode;
		}		
		size++;
		
	}
	
	//invalid for sorted list, throws error
	@Override
	public void addToEnd(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	//invalid for sorted list, throws error
	@Override
	public void addToFront(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	//Call super class iterator
	@Override
	public java.util.ListIterator<T> iterator(){
		return super.iterator();
	}
	
	//remove element, calls super method
	@Override
	public BasicDoubleLinkedList.Node remove(T data, java.util.Comparator<T> comparator){
		return super.remove(data, comparator);
	}
	
	
	
}
