package boolShell.formulars;

import boolShell.Formular;

/**
 * Direct boolean value, true or false. Used as element for
 * {@linkplain Formular}
 * 
 * @author 1632700
 * 
 */
public class Value extends Formular {

	/**
	 * Value of this object
	 */
	private String value;

	/**
	 * Create a value as true or false
	 * 
	 * @param arg
	 *            String value of boolean value
	 */
	public Value(String arg) {
		if (arg.equals("true")) {
			value = "true";
		} else {
			value = "false";
		}
	}

	/**
	 * Get the value of the {@linkplain Value} object
	 * 
	 * @return value of the {@linkplain Value} object
	 */
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
}
