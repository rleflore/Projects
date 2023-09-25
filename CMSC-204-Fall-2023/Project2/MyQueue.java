import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T>  {
    private int maxSize;
    private int count;
    private T[] queue;
    

    public MyQueue() {
        maxSize = 10;
        T[] temp = (T[]) new Object[maxSize];
        queue = temp;
        count = 0;
    }
    
    public MyQueue(int size) {
       maxSize = size;
        T[] temp = (T[]) new Object[maxSize];
        queue = temp;
        count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {        
        return count == maxSize;
    }

    public T dequeue() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }
        T item = queue[0];
        System.arraycopy(queue, 1, queue, 0, count-1);
        count--;
        return item;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean enqueue(T e) throws QueueOverflowException {    
        if (isFull()) {
            throw new QueueOverflowException();
        }
        queue[count] = e;
        count++;
        return true;
    }
    
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < count; i++) {
            str += queue[i];
        }
        return str;
    }

    @Override
    public String toString(String delimiter) {
        String str = "";
        for (int i = 0; i < count; i++) {
            str += queue[i];
            if (i != count - 1) {
                str += delimiter;
            }
        }
        return str;
    }

    @Override
    public void fill(ArrayList<T> list) {
    	
    	
        for (int i = 0; i < list.size(); i++) {
            if (isFull()) {
                throw new QueueOverflowException();
            }
            enqueue(list.get(i));
        }
    }

}
