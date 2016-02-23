package boolShell;

import boolShell.formulars.And;
import boolShell.formulars.Not;
import boolShell.formulars.Or;
import boolShell.formulars.Reference;
import boolShell.formulars.Variable;

/**
 * Contains all commands Central interface of {@linkplain CommandReaderParser}
 * and {@linkplain Formular}.
 * 
 * @author 1632700
 * 
 */
public final class Commands implements Commons {

	private Commands() {
	}

	/**
	 * NOT-command. Adds a logical NOT to other {@linkplain Formular}. Registers
	 * the resulting {@linkplain Formular} in the {@linkplain FormularBuffer}
	 * 
	 * @param formBuff
	 *            {@linkplain FormularBuffer} to add the {@linkplain Formular}
	 * @param arg
	 *            Command argument to use with NOT, e.g. {@linkplain Value},
	 *            {@linkplain Variable} or {@linkplain Reference}
	 */
	public static void not(FormularBuffer formBuff, String arg) {
		formBuff.add(new Not(arg));
		formBuff.printLast();
	}

	/**
	 * AND-command. Adds a logical AND to other {@linkplain Formular}. Registers
	 * the resulting {@linkplain Formular} in the {@linkplain FormularBuffer}
	 * 
	 * @param formBuff
	 *            {@linkplain FormularBuffer} to add the {@linkplain Formular}
	 * @param arg
	 *            Command argument1 to use with AND, e.g. {@linkplain Value},
	 *            {@linkplain Variable} or {@linkplain Reference}
	 * @param arg2
	 *            Command argument2 to use with AND, e.g. {@linkplain Value},
	 *            {@linkplain Variable} or {@linkplain Reference}
	 */
	public static void and(FormularBuffer formBuff, String arg, String arg2) {
		formBuff.add(new And(arg, arg2));
		formBuff.printLast();
	}

	/**
	 * OR-command. Adds a logical OR to other {@linkplain Formular}. Registers the
	 * resulting {@linkplain Formular} in the {@linkplain FormularBuffer}
	 * 
	 * @param formBuff
	 *            {@linkplain FormularBuffer} to add the {@linkplain Formular}
	 * @param arg
	 *            Command argument1 to use with OR, e.g. {@linkplain Value},
	 *            {@linkplain Variable} or {@linkplain Reference}
	 * @param arg2
	 *            Command argument2 to use with OR, e.g. {@linkplain Value},
	 *            {@linkplain Variable} or {@linkplain Reference}
	 */
	public static void or(FormularBuffer formBuff, String arg, String arg2) {
		formBuff.add(new Or(arg, arg2));
		formBuff.printLast();
	}

	/**
	 * SHOW-command. Prints the {@linkplain Formular} represented by the given
	 * {@linkplain Reference}
	 * 
	 * @param arg
	 *            Command argument as {@linkplain Reference} which representates
	 *            a {@linkplain Formular} in the {@linkplain FormularBuffer}
	 */
	public static void show(String arg) {
		Formular form = Formular.validate(arg);
		// Only create a reference if command argument was typed correctly
		if (form instanceof Reference) {
			Reference ref = (Reference) form;
			OUT.println("#" + ref.getIndex() + " = " + ref);
		} else {
			// Command argument was typed wrong
			throw new SyntaxException(ERROR_CMD[SHOW]);
		}
	}

	/**
	 * LIST-command. Prints all in the {@linkplain FormularBuffer} saved
	 * {@linkplain Formular}
	 * 
	 * @param formBuff
	 *            {@linkplain FormularBuffer} where the {@linkplain Formular} is
	 *            saved
	 */
	public static void list(FormularBuffer formBuff) {
		// Buffer is empty, no formular is saved yet
		if (formBuff.size() == 0) {
			throw new SyntaxException(ERROR_MISC[5]);
		}
		// Print all saved formular
		for (int i = 0; i < formBuff.size(); i++) {
			OUT.println("#" + i + " = " + formBuff.get(i));
		}
	}

	/**
	 * SIMPLIFY-command. Simplifies the logical boolean {@linkplain Formular} and
	 * saves the new {@linkplain Formular} in the {@linkplain FormularBuffer}
	 * 
	 * @param formBuff
	 *            {@linkplain FormularBuffer} where the simplified
	 *            {@linkplain Formular} should be saved
	 * @param arg
	 *            Command argument as {@linkplain Reference} which representates
	 *            the to simplifying {@linkplain Formular} in the
	 *            {@linkplain FormularBuffer}
	 */
	public static void simplify(FormularBuffer formBuff, String arg) {
		Reference ref;

		Formular form = Formular.validate(arg);
		// Only create a reference if command argument was typed correctly
		if (form instanceof Reference) {
			ref = (Reference) form;
		} else {
			// Command argument was typed wrong
			throw new SyntaxException(ERROR_CMD[SIMPLIFY]);
		}

		// Simplify the given form
		// Create a copy of ref before simplifying
		// Without old forms will be effected and infinity loops will occure
		form = Recursion.simplify(Recursion.copyForm(ref), true);

		// Add the simplified form to the buffer and print it
		formBuff.add(form);
		formBuff.printLast();
	}

	/**
	 * SUBST-command. Substitutes a {@linkplain Variable} in a
	 * {@linkplain Reference} with a given other {@linkplain Formular} and saves
	 * the new {@linkplain Formular} in the {@linkplain FormularBuffer}
	 * 
	 * @param formBuff
	 *            {@linkplain FormularBuffer} where the substituted
	 *            {@linkplain Formular} should be saved
	 * @param arg
	 *            Command argument1 as {@linkplain Reference} where the
	 *            substitution will be perfomed
	 * @param arg2
	 *            Command argument2 as {@linkplain Variable} which should be
	 *            substituted
	 * @param arg3
	 *            Command argument3 as substitution {@linkplain Formular}, e.g.
	 *            {@linkplain Value}, {@linkplain Variable} or
	 *            {@linkplain Reference}
	 */
	public static void subst(FormularBuffer formBuff, String arg, String arg2,
			String arg3) {
		Reference ref;
		Variable var;

		Formular form = Formular.validate(arg);
		// Only create a reference if command argument was typed correctly
		if (form instanceof Reference) {
			ref = (Reference) form;
		} else {
			// Command argument was typed wrong
			throw new SyntaxException(ERROR_CMD[SUBST]);
		}

		form = Formular.validate(arg2);
		// Only create a variable if command argument was typed correctly
		if (form instanceof Variable) {
			var = (Variable) form;
		} else {
			// Command argument was typed wrong
			throw new SyntaxException(ERROR_CMD[SUBST]);
		}

		form = Formular.validate(arg3);

		// Substitute the given var in a ref by the form
		// Create a copy of ref before substituting
		// Without old forms will be effected and infinity loops will occure
		form = Recursion.subst(Recursion.copyForm(ref), var, form, true);

		// Add the substituted form to the buffer and print it
		formBuff.add(form);
		formBuff.printLast();
	}

	/**
	 * QUIT-command. Quits the BoolShell by executing its method
	 */
	public static void quit() {
		BoolShell.quit();
	}

	/**
	 * ERROR-command. Not usable by client. Throws a exception which occurred at
	 * wrong command inputs by client
	 * 
	 * @param arg
	 *            Errortext which should be thrown as message
	 */
	public static void error(String arg) {
		throw new SyntaxException(arg);
	}
}
