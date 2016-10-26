
public interface CSC230Project1<T extends Comparable<T>>{
	public void insert(T t);
	public T remove();
	public long totalOperations();
	public void resetTotalOperations();
}

