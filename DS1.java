/*
 * <p> Title: CSC 230 Project 1: "Comparing Algorithms" </p>
 * <p> Description: The Purpose of this program is to test the runtime of two algorithms by measuring primitive operations. Code for data structure
 * 					is from the Data Structures and Algorithms textbook.</p>
 * <p> Due Monday October 24, 11:59 pm </p>
 * 
 * @author Steven Turner (N00836867@students.ncc.edu)
 * 
 * 
 * 
 */



public class DS1<T extends Comparable<T>> implements CSC230Project1<T> {
 // instance variables
 private T[] data;
 private int f = 0;
 private int sz = 0;
 private static final int CAPACITY = 100;
 private long totalOps = 0;
 // constructors
 public DS1( ) {this(CAPACITY);}
// generic array used for storage // index of the front element
// current number of elements
 @SuppressWarnings("unchecked")
public DS1(int capacity) {
 data = (T[]) new Comparable[capacity];
 }

 // methods
 /* Returns the number of elements in the queue. */
 public int size() { return sz; }

 /*Tests whether the queue is empty. 
  * ∗/
  */
 public boolean isEmpty() { return (sz == 0); }
@Override
 /* Inserts an element at the rear of the queue. 
 */
 public void insert(T t) throws IllegalStateException {
 if (sz == data.length){ 
	 totalOps++;
	 throw new IllegalStateException("Queue is full");//One for the comparison of sz to data.length 
 }
  
 int avail = (f + sz) % data.length;
 totalOps+= 4; //Two for arithmetic operations and one for declaring a variable
 // use modular arithmetic
 data[avail] = t;
 totalOps+=2; //Two for indexing array and declaring a variable
 sz++;
 totalOps++; //One for arithmetic operations
 }
 /* Returns, but does not remove, the first element of the queue (null if empty). */
 public T first( ) {
 if (isEmpty()) return null;
 return data[f];
 }
@Override
 /* Removes and returns the first element of the queue (null if empty). */
 public T remove(){
 if (isEmpty()){
	 totalOps+=2;
	 return null; 	
  // One for returning null One for calling isEmpty
 }
  T answer = data[f];
  totalOps += 2; // Two for indexing an array and declaring a variable
 data[f] =  null;
 totalOps++; // One for declaring a variable
 f = (f + 1) % data.length;
 totalOps+=5; //Three for arithmetic operations, One for declaring a variable and one for returning a value
 sz--;
 return answer;
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


}
