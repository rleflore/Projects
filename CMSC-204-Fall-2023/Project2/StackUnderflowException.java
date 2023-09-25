import javax.management.RuntimeErrorException;

public class StackUnderflowException extends RuntimeException {
	
	public StackUnderflowException() {
		super("This should have caused an StackUnderflowException");
	}

}
