package boolShell;

/**
 * BoolShell Shell programm for usage with boolean formulars Supports AND, OR,
 * NOT, variables, referencing old input and more
 * 
 * @author 1632700
 * 
 */
public final class BoolShell implements Commons {

	private static boolean loop = true;

	/**
	 * Contains buffer for all formulas.
	 */
	private static FormularBuffer formBuff;
	/**
	 * Parser for user input.
	 */
	private static CommandReaderParser cmdRdPr;

	// Utility classes should not have a public or default constructor.
	private BoolShell() {

	}

	/**
	 * Starting BoolShell No argument support
	 * 
	 * @param args
	 *            arguments
	 */
	public static void main(String[] args) {
		// try {
		formBuff = new FormularBuffer();
		cmdRdPr = new CommandReaderParser();

		while (loop) {
			OUT.print(PROMPT);

			try {
				// Read user input
				String[] arg = cmdRdPr.read();
				// Parse user input
				cmdRdPr.parse(formBuff, arg);
				// Catch exceptions of BoolShell
			} catch (SyntaxException e) {
				OUT.println(e.getMessage());
				// System.out.println("Error! TEST");
			}
		}
		// Catch any other exceptions
		// } catch (Exception e) {
		// OUT.println(ERRORTEXT + "Unknown error, please contact developer!");
		// // Enable for debugging
		// // e.printStackTrace();
		// }
	}

	/**
	 * Quits BookShell and terminates the shell
	 */
	public static void quit() {
		loop = false;
	}

	/**
	 * Returns the formular buffer.
	 * 
	 * @return the formular buffer.
	 */
	public static FormularBuffer getFormBuff() {
		// TODO Auto-generated method stub
		return formBuff;
	}
}
