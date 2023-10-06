import java.util.ArrayList;

/*
 * Rose LeFlore
 * Project 3
 * Due: 10/9
 * Professor Thai
 */

public class BasicDoubleLinkedList<T> extends java.lang.Object{

	Node head;
	Node tail;
	int size;
	
	
	public BasicDoubleLinkedList() {
		size = 0;
		head = null;
		tail = null;
	}
	
	//Adds a new node to the end of the linked list
	public void addToEnd(T data) {
		Node<T> node = new Node<>(data);
		//no entries
		if (tail == null) {
			tail = node;
			head = node;
		}
		//if there are entries
		else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}	
		//decreasing size
		size++;
		
	}
	
	//adds a node to the front of the linked list
	@SuppressWarnings("unchecked")
	public void addToFront(T data) {
		Node<T> node = new Node<>(data);
		
		//no entries
		if (head == null) {
			head = node;
			tail = node;
		}
		//if there are entires
		else {
			node.next = head;
			head.prev = node;
			head = node;
		}
		//increasing size
		size++;
	}
	
	//Returns but does not remove the data of the last node
	public T getLast() {
		return (T) tail.data;
	}
	
	//Returns but does not remove the data of the fist node
	public T getFirst() {
		return (T) head.data;
	}
	
	//Returns number of nodes in the list
	public int getSize() {
		return size;
	}
	
	//Returns object of the DoubleLInkedListIterator
	public java.util.ListIterator<T> iterator(){
		DoubleLinkedListIterator<T> iterator = new DoubleLinkedListIterator<>();
		return iterator;		
	}
	
	//Removes the first instance of the targetData from list
	public BasicDoubleLinkedList.Node remove(T targetData, java.util.Comparator<T> comparator){
		
		Node current = head;
		
		//Traversing the list until the end or if it's found
		while(current != null) {
			//if target is found
			if (comparator.compare(targetData, (T) current.data) == 0) {
				//if the target is the head
				if (current.prev == null) {
					//make the next node the new head
					head = current.next;
					//if list has more than one node, clear previous head
					if (head != null) {
						head.prev = null;
					}
					//list is empty
					else {
						tail = null;
					}
				}
				
				//If target is the tail
				else if(current.next == null) {
					//make previous node the new tail
					tail = current.prev;
					//clear previous tail
					tail.next = null;
				}
				
				//target is found in between head and tail
				else {
					Node previous = current.prev;
					Node next = current.next;
					
					//connect nodes together without the current node
					previous.next = next;
					next.prev = previous;
				}
				//decreasing size
				size--;
				
				//completley remove node
				current.next = null;
				current.prev = null;
				
				//return the removed node
				return current;
			}
			//next iteration
			current = current.next;
		}
		
		//if target wasn't found
		return null;
	}

	
	//Removes and returns frist element from list
	public T retrieveFirstElement() {
		//if no entries, returns null
		if (head == null) {
			return null;
		}
		//if there is one entry, returns the head
		else if (head == tail){
			//setting both head and tail to null
			head = null;
			tail = null;
			return (T) head.data;
		}
		
		//if there are multiple entries
		Node node = head;
		//setting the head to the next node on the list
		head = head.next;
		head.prev = null;
		node.next = null;
		//decreasing size
		size--;
		//returning first element
		return (T) node.data;
	}
	
	
	//Removes and returns last element from the list
	public T retrieveLastElement() {
		//if no entries
		if (tail == null) {
			return null;
		}
		//if there is one entry, return tail
		else if (tail == head){
			//setting head and tail to null
			head = null;
			tail = null;
			return (T) tail.data;
		}
		
		//if there are multiple entries
		Node<T> node = tail;
		//setting the new tail to the previous tail
		tail = tail.prev;
		tail.next = null;
		//reference to previous tail to null
		node.prev = null;
		//removing size
		size--;
		//returns last element
		return node.data;
		
	}
	
	//Returns an arrayList of all the items in the double linked list
	public java.util.ArrayList<T> toArrayList(){
		ArrayList<T> arr = new ArrayList<>();
		Node<T> current = head;
		while(current != null) {
			arr.add(current.data);
			//next iteration
			current = current.next;			
		}
		return arr;	
		
	}	
	
	//Inner class Node
	protected class Node<T>{
		public T data;
		public Node<T> prev, next;
		
		public Node(T data) {
			this.data = data;
			prev = null;
			next = null;
		}
	}
		
	
	
	// Iterator class
	protected class DoubleLinkedListIterator<T> implements java.util.ListIterator<T>{
		
		private Node<T> current;
		private int index;
		
		public DoubleLinkedListIterator() {
			current = head;
			index = 0;
		}

		@Override
		public boolean hasNext() {
			return (current != null);
		}

		@Override
		public T next() {
			//if no next element
			if(!hasNext()) {
				throw new  java.util.NoSuchElementException();
			}
			//data from current node
		    T data = current.data;
		    current = current.next;
		    index++;
		    return data;
		}

		@Override
		public boolean hasPrevious() {
			//if index is greater than 0, there is a previous node
			return index > 0;		
		}

		@Override
		public T previous() {
	        if (!hasPrevious()) {
	        	throw new  java.util.NoSuchElementException();
	        }
	        //if reached end of the list (current will be null)
	        //go to the tail
	        if (current == null) {
	            current = tail;
	        } 
	        // move to previous node otherwise
	        else {
	            current = current.prev;
	        }

	        index--;
	        return current.data;
		}

		@Override
		public int nextIndex() throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
			
		}

		@Override
		public void set(T e) throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
			
		}

		@Override
		public void add(T e) throws UnsupportedOperationException{
			throw new UnsupportedOperationException();			
		}
		
	}

}
