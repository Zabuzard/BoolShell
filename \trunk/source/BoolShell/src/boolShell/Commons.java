package boolShell;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Contains common constants like error-messages, command-indicators and the
 * default I/O-Streams
 * 
 * @author 1632700
 * 
 */
public interface Commons {

	/**
	 * Shortcut for printing
	 */
	PrintStream OUT = System.out;
	/**
	 * Shortcut for input
	 */
	InputStream IN = System.in;

	/**
	 * Prompt text which should be displayed in front of every output
	 */
	String PROMPT = "bool> ";
	/**
	 * Error text which should be displayed in front of every error-message
	 */
	String ERRORTEXT = "Error! ";

	/**
	 * Errors which occurred due command reading, index indicates commands (see
	 * below)
	 */
	String[] ERROR_CMD = { "Wrong usage: not <atom>",
			"Wrong usage: and <atom1> <atom2>",
			"Wrong usage: or <atom1> <atom2>", "Wrong usage: show <ref>",
			"Wrong usage: list", "Wrong usage: simplify <ref>",
			"Wrong usage: subst <ref> <var> <atom>", "Wrong usage: quit",
			"Unknown command!" };

	/**
	 * Miscellaneous errors which occurred due Formular ranges etc.
	 */
	String[] ERROR_MISC = { "Unknown argument!",
			"Vars only with positive numbers within INT-Range!",
			"Vars only positive within INT-Range!", "Refs only with numbers!",
			"Referred formular does not exist!", "There are no saved refs!" };

	/**
	 * Command-indicators and error-indicator
	 */
	int NOT = 0, AND = 1, OR = 2, SHOW = 3, LIST = 4, SIMPLIFY = 5, SUBST = 6,
			QUIT = 7, ERROR = 8;

	/**
	 * Returns string representation of object.
	 * 
	 * @return the string representation of object.
	 */
	@Override
	String toString();
}
