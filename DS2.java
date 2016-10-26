/**
 * <p> Title: CSC 230 Project 1: "Comparing Algorithms" </p>
 * <p> Description: The Purpose of this program is to test the runtime of two algorithms by measuring primitive operations.</p>
 * <p> Due Monday October 24, 11:59 pm </p>
 * 
 * @author Steven Turner (N00836867@students.ncc.edu)
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * <p>Title: ArrayListClass</p>
 *
 * <p>Description: Defines those properties and behaviors that are common to all lists.
 * The classes are designed using a generic type (T). Users of this
 * class (and its subclasses) should note that although the lists can store an "item"
 * of any type, they are required to override the equals method, defined
 * in the Object class, in their "item" class.</p>
 *
 * <p>Copyright: Copyright (c) 2016</p>
 *
 * @author F. Graham
 * @version 0.1
 */

abstract class LinkedListClass<T extends Comparable<T>> {
	/**
	 * Inner class Node has 2 references, forward and back
	 * Accessors and mutators are necessary to provide access 
	 * to classes other than LinkedListClass
	 * @author F. Graham
	 *
	 * @param <E>
	 */
	class Node<E extends Comparable<E>> {
		private Node<E> prev;
		private E data;
		private Node<E> next;
		
		public Node(){
			data = null;
			prev = next = null;
		}
		public Node(E d){
			data = d;
			prev = next = null;
		}
		public Node(Node<E> p, E d, Node<E> n){
			prev = p;
			data = d;
			next = n;
		}
		public Node<E> getPrev() {
			return prev;
		}
		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}
		public E getData() {
			return data;
		}
		public void setData(E data) {
			this.data = data;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		}
	}
	
	protected Node<T> head; // the head of the linked list of items
	protected int numItems; // the number of items in the list
	
	/**
     * default constructor - creates an empty list; 
     * the number of items in the list is initialized to 0
     */
	public LinkedListClass(){
		head = new Node<T>();  // dummy node
	}	

    /**
     * empty method - determines whether or not the list is empty
     * @return true if the list is empty; false otherwise
     */
	public boolean isEmpty(){
		return numItems == 0;
	}
	
    /**
     * listSize method - returns the number of items in the list
     * @return the number of items in the list
     */
	public int listSize(){
		return numItems;
	}
	
    /**
     * makeEmpty method - makes the list empty and sets numItems to 0
     */
	public void makeEmpty(){
		head = new Node<T>();  // dummy node
		numItems = 0;
		System.gc();
	}
    /**
     * toString method - returns the state of the object as a String
     * @return a String containing the items in the list
     */
	public String toString(){
		String str = (isEmpty() ? "The list is empty... " : "The list contains\n");
		Node<T> trav = head.next;
		while(trav != null){
			str += trav.data + (trav.getNext() == null? " " : " -> ");
			trav = trav.next;
		}
		return str;
	}
    /**
     * insert method - inserts an item in the list
     * @param <i>item</i> is a reference to the item to be inserted
     */
	public abstract void insert(T item);
	
    /**
     * search method - determines whether or not <i>item</i> is in the list
     * @param <i>item</i> is a reference to an item whose key-field has been initialized
     * @return an integer which represents the location in the list where <i>item</i>
     * was found; if <i>item</i> is not in the list, the method returns -1
     */
	public abstract int search(T item);

    /**
     * remove method - removes an item from the list
     * @param <i>item</i> is a reference to an item whose key-field has been initialized.
     * If the item is found it is removed and returned; otherwise the list remains unchanged
     */
	public abstract T remove(T item);

	
	}





public class DS2<T extends Comparable<T>> extends LinkedListClass<T> implements Comparable<DS2<T>>, CSC230Project1<T>{
	private long totalOps = 0;
	@Override
	public void insert(T item) {
		if(item != null){
			if(numItems == 0){
				head.setNext(new Node<T>(head, item, null));
				totalOps+= 3; //Two operations for operations in if statements. One for function call.
			}else {
				Node<T> trav = head;
				totalOps++; //Assigning variable
				while(trav.getNext() != null && item.compareTo(trav.getNext().getData()) > 0){
					trav = trav.getNext();
					totalOps+= 9; //For while loop: Three operations for trav.getNext() and one for getData(). One for assigning variable. One for comparison to null. Two for compareTo and greater than 0. One for && 
				}
				Node<T> newNode = (new Node<T>(trav, item, trav.getNext()));
				totalOps++; // One for assigning variable
				trav.setNext(newNode);
				totalOps++; //One for method call
				if(newNode.getNext() != null)
					newNode.getNext().setPrev(newNode);
					totalOps+= 4; // Two for if statement. Two for method call 
			}
			numItems++;
			totalOps++; // One for incrementing size
		}
	}

	@Override
	public int search(T item) {
		Node<T> trav = head;
		int count = 0;
		while(trav.getNext() != null && item.compareTo(trav.getNext().getData()) > 0 ){
			count++;
			trav = trav.getNext();	
		}
		
		if(count == numItems){
		return -1;
		}
		
		return count;
	}

	@SuppressWarnings("unchecked")
	public T remove() {
		Node<T> trav = head;
		totalOps++; //One for assigning variable
		Node<T> fin = new Node<T>();
		totalOps++;
		//int searched = search(item); //UNUSEDCODE
		//if(searched < 0){//UNUSEDCODE
			//return (T) "Item isn't in list";//UNUSEDCODE
		//}
		
		//else{
			while(trav.getNext() != null
					//&& item.compareTo(trav.getNext().getData()) > 0 UNUSEDCODE
					){
				trav = trav.getNext();
				totalOps+= 4; // Two for method call to getNext(). One for comparison to null and one for variable assignment
		}	
			T item = trav.getData();
			totalOps+=2; //One for variable assignment one for method call
			
			//while(trav.getNext() != null&& trav.getNext().getNext() != null&& trav.getNext().getNext().getNext() != null){
			//trav.setNext(trav.getNext().getNext());
			//trav = trav.getNext();
			//}
			
			//while(trav.getNext() != null){
				//	trav = trav.getNext();
				//}
			//if(item == trav.getData()){
			fin = new Node<T>(trav.getPrev(),(T) "null", null);
			totalOps+= 2; //One for variable assignment and one for method call
			trav.getPrev().setNext(fin);
			totalOps+=3; // two for method call. One for return statement
			//}
			
		return item;
			}
					
	
		

	@Override
	public int compareTo(DS2 o) {
		// TODO Auto-generated method stub
		if(this.numItems > o.numItems){
			return 1;
		}
		else if(this.numItems == o.numItems){
			return 0;
		}
		else{
		return -1;
		}
	}

	@Override
	public long totalOperations() {
		// TODO Auto-generated method stub
		return totalOps;
	}

	@Override
	public void resetTotalOperations() {
		// TODO Auto-generated method stub
		totalOps = 0;
	}

	@Override
	public T remove(T item) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
