
@SuppressWarnings("serial")
public class QueueUnderflowException extends RuntimeException {
	
	public QueueUnderflowException() {
		super("This should have caused an QueueUnderflowException");
	}
}
