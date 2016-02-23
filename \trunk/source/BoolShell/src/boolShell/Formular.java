package boolShell;

import boolShell.formulars.Reference;
import boolShell.formulars.Value;
import boolShell.formulars.Variable;

/**
 * Abstract class for all Formulars. Supports general validation-method which
 * creates the right Formular out of the command e.g. {@linkplain Variable},
 * {@linkplain Value} or {@linkplain Reference}
 * 
 * @author 1632700
 * 
 */
public abstract class Formular {

	/**
	 * Contains all child-formulars of every formular e.g. {@linkplain And} has
	 * 2 child-formulars
	 */
	private Formular[] childs;

	/**
	 * Contains the parent-formular of every formular e.g. {@linkplain And} is
	 * the parent of its 2 child-formulars
	 */
	private Formular parent;

	@Override
	abstract public String toString();

	/**
	 * Validates the command argument as {@linkplain Variable},
	 * {@linkplain Value} or {@linkplain Reference}
	 * 
	 * @param arg
	 *            command argument as {@linkplain Variable}, {@linkplain Value}
	 *            or {@linkplain Reference}
	 * @return The right formular which represents the command argument
	 */
	public static Formular validate(String arg) {
		Formular form;
		if (arg.charAt(0) == 'v') {
			form = new Variable(arg);
		} else if (arg.equals("true") || arg.equals("false")) {
			form = new Value(arg);
		} else if (arg.charAt(0) == '#') {
			form = new Reference(arg);
		}
		// No known Formular, like variable, value or reference
		else {
			throw new SyntaxException(Commons.ERROR_MISC[0]);
		}

		return form;
	}

	/**
	 * Returns the childs.
	 * 
	 * @return the childs.
	 */
	public Formular[] getChilds() {
		return childs;
	}

	/**
	 * Sets the childs to given childs.
	 * 
	 * @param childs
	 *            the childs to set.
	 */
	public void setChilds(Formular[] childs) {
		this.childs = childs;
	}

	/**
	 * Returns the parent.
	 * 
	 * @return the parent.
	 */
	public Formular getParent() {
		return parent;
	}

	/**
	 * Sets the parent to given parent.
	 * 
	 * @param parent
	 *            the parent to set.
	 */
	public void setParent(Formular parent) {
		this.parent = parent;
	}

}
