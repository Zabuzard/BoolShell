package boolShell.formulars;

import boolShell.Commons;
import boolShell.Formular;
import boolShell.SyntaxException;

/**
 * Variable for {@linkplain Formular} Used as placeholder for the client
 * 
 * @author 1632700
 * 
 */
public class Variable extends Formular {

	private int number;

	/**
	 * Create a variable using the argument which contains the number of the
	 * variable
	 * 
	 * @param arg
	 *            argument which contains the number of the variable
	 */
	public Variable(String arg) {
		int tempNumber = -1;
		try {
			// Try to parse the given number
			tempNumber = Integer.parseInt(arg.substring(1));
		} catch (NumberFormatException e) {
			// Thrown if number can not be read as valid Integer
			throw new SyntaxException(Commons.ERROR_MISC[1]);
		}
		// Set the almost valid number
		setNumber(tempNumber);
	}

	/**
	 * Get the number of the variable
	 * 
	 * @return number of the variable
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Sets the number of the variable. Only positive Integer values are valid
	 * 
	 * @param number
	 *            number of the variable
	 */
	public void setNumber(int number) {
		// Only positive Integer values are valid
		if (number < 0 || number >= Integer.MAX_VALUE) {
			throw new SyntaxException(Commons.ERROR_MISC[2]);
		}
		this.number = number;
	}

	@Override
	public String toString() {
		return "v" + getNumber();
	}
}
