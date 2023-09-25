import java.util.ArrayList;

public class MyStack<T> implements StackInterface<T> {
	private int maxSize;
	private T[] stack;
	private int count;
	
	public MyStack(int size) {
		maxSize = size;
        T[] temp = (T[]) new Object[maxSize];
        stack = temp;
        count = 0;
	}
	
	public MyStack() {
		this(10);
	}
	
	
	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@Override
	public boolean isFull() {
		return count == maxSize;
	}

	@Override
	public T pop() throws StackUnderflowException {
		T element;
		if (isEmpty()) {
			throw new StackUnderflowException();
		}
	
		element = stack[count -1];
		count--;
		return element;
	}

	@Override
	public T top() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException();
		}
		
		return stack[count-1];
		
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public boolean push(T e) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		}	
		stack[count] = e;
		count++;
		return true;
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < size(); i++) {
			str += stack[i];
		}
		return str;
	}
	
	@Override
	public String toString(String delimiter) {
		String str = "";
		int size = size();
		for (int i = 0; i < size; i++) {			
			str += stack[i];
			if (i != size-1) {
				str+= delimiter;
			}		
		}
		return str;
	}

	@Override
	public void fill(ArrayList<T> list) {
		ArrayList<T> copy = list;
		
        for (int i = 0; i < copy.size(); i++) {
            if (isFull()) {
                throw new QueueOverflowException();
            }
            push(copy.get(i));
        }
		
	}

}
