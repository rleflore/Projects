
@SuppressWarnings("serial")
public class QueueOverflowException extends RuntimeException {

	public QueueOverflowException(){
		System.out.println("This should have caused an QueueOverflowException");
	}
}
