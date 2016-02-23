package boolShell;

import java.util.Scanner;

/**
 * Reads and parses the input so the commands can be executed without problems
 * 
 * @author 1632700
 * 
 */
public class CommandReaderParser implements Commons {

	private Scanner sc = new Scanner(IN);

	/**
	 * Reads the input and solves it for all known commands. Uses command
	 * constants which are declared in {@linkplain Commons}
	 * 
	 * @return Array which contains all command information where the command is
	 *         saved in first and arguments in rest. If an error occured, error
	 *         message will be stored in second
	 */
	public String[] read() {
		// Read all the input
		String input = sc.nextLine();
		// Split input at each space
		String[] arg = input.split(" ");

		// Indicates which command is used
		int cmd;

		// Detect used command, if not known error indicator is saved
		switch (arg[0]) {
		case "not":
			cmd = NOT;
			break;
		case "and":
			cmd = AND;
			break;
		case "or":
			cmd = OR;
			break;
		case "show":
			cmd = SHOW;
			break;
		case "list":
			cmd = LIST;
			break;
		case "simplify":
			cmd = SIMPLIFY;
			break;
		case "subst":
			cmd = SUBST;
			break;
		case "quit":
			cmd = QUIT;
			break;
		default:
			cmd = ERROR;
			break;
		}

		// Check used command with allowed argument length
		// If argument length is not right, an specific error message is saved
		// For ensureCapacity() see below
		if (cmd == NOT && arg.length != 2) {
			arg = ensureCapacity(arg);
			arg[1] = ERROR_CMD[cmd];
			cmd = ERROR;
		} else if (cmd == AND && arg.length != 3) {
			arg = ensureCapacity(arg);
			arg[1] = ERROR_CMD[cmd];
			cmd = ERROR;
		} else if (cmd == OR && arg.length != 3) {
			arg = ensureCapacity(arg);
			arg[1] = ERROR_CMD[cmd];
			cmd = ERROR;
		} else if (cmd == SHOW && arg.length != 2) {
			arg = ensureCapacity(arg);
			arg[1] = ERROR_CMD[cmd];
			cmd = ERROR;
		} else if (cmd == LIST && arg.length != 1) {
			arg[1] = ERROR_CMD[cmd];
			cmd = ERROR;
		} else if (cmd == SIMPLIFY && arg.length != 2) {
			arg = ensureCapacity(arg);
			arg[1] = ERROR_CMD[cmd];
			cmd = ERROR;
		} else if (cmd == SUBST && arg.length != 4) {
			arg = ensureCapacity(arg);
			arg[1] = ERROR_CMD[cmd];
			cmd = ERROR;
		} else if (cmd == QUIT && arg.length != 1) {
			arg[1] = ERROR_CMD[cmd];
			cmd = ERROR;
		} else if (cmd == ERROR) {
			arg = ensureCapacity(arg);
			arg[1] = ERROR_CMD[cmd];
		}

		// Save the used command or error in arg[0]
		// arg is now clean of errors and can be used for parsing
		arg[0] = Integer.toString(cmd);

		return arg;
	}

	/**
	 * Parses the user input which was already read by
	 * {@linkplain CommandReaderParser}. Executes the command specific methods
	 * which are located at {@linkplain Commands}
	 * 
	 * @param formBuff
	 *            {@linkplain FormularBuffer} where all {@linkplain Formular}
	 *            should be saved
	 * @param arg
	 *            Argument array which was already read by
	 *            {@linkplain CommandReaderParser}
	 */
	public void parse(FormularBuffer formBuff, String[] arg) {

		// Execute the command specific methods
		switch (Integer.valueOf(arg[0])) {
		case NOT:
			Commands.not(formBuff, arg[1]);
			break;
		case AND:
			Commands.and(formBuff, arg[1], arg[2]);
			break;
		case OR:
			Commands.or(formBuff, arg[1], arg[2]);
			break;
		case SHOW:
			Commands.show(arg[1]);
			break;
		case LIST:
			Commands.list(formBuff);
			break;
		case SIMPLIFY:
			Commands.simplify(formBuff, arg[1]);
			break;
		case SUBST:
			Commands.subst(formBuff, arg[1], arg[2], arg[3]);
			break;
		case QUIT:
			Commands.quit();
			break;
		case ERROR:
			Commands.error(arg[1]);
			break;
		default:
			Commands.error(ERROR_CMD[ERROR]);
			break;
		}
	}

	/**
	 * Sometimes the static array length must be incremented to prevent
	 * NullPointer e.g. input "not", arg has length 1 but "not" needs 2
	 * arguments but the error must be saved in arg[1] so arg must have at least
	 * length 2. This method ensures that the array has enough capacity for the
	 * error message
	 * 
	 * @param arg
	 *            Argument array whos capacity should be incremented
	 * @return Argument array with length 2
	 */
	private String[] ensureCapacity(String[] arg) {
		if (arg.length == 1) {
			String temp = arg[0];
			arg = new String[2];
			arg[0] = temp;
			arg[1] = "";
		}
		return arg;
	}
}
