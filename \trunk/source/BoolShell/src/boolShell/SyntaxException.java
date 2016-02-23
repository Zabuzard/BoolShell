package boolShell;

/**
 * BoolShells Exceptions Prints error-messages with a default entering message.
 * Error-messages are declared at {@linkplain Commons}
 * 
 * @author 1632700
 * 
 */
public class SyntaxException extends RuntimeException {

	private static final long serialVersionUID = 8120861516649367133L;

	/**
	 * Creates a Syntax Exception with default entering message
	 */
	public SyntaxException() {
		super(Commons.ERRORTEXT + "Unknown Error");
	}

	/**
	 * Creates a Syntax Exception with custom entering message
	 * 
	 * @param message
	 *            the entering message
	 */
	public SyntaxException(String message) {
		super(Commons.ERRORTEXT + message);
	}
}
