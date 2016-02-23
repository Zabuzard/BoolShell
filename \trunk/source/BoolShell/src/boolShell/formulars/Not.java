package boolShell.formulars;

import boolShell.Formular;

/**
 * Logical NOT {@linkplain Formular}. Contains 1 child-formular
 * 
 * @author 1632700
 * 
 */
public class Not extends Formular {

	/**
	 * Create a NOT {@linkplain Formular} with 1 argument as its child-formular
	 * 
	 * @param arg
	 *            child-{@linkplain Formular} of NOT as e.g. {@linkplain Value},
	 *            {@linkplain Variable} or {@linkplain Reference}
	 */
	public Not(String arg) {
		this(validate(arg));
	}

	/**
	 * Create a NOT {@linkplain Formular} with 1 argument as its child-formular
	 * 
	 * @param form
	 *            child-{@linkplain Formular} of NOT as {@linkplain Formular}
	 */
	public Not(Formular form) {
		setChilds(new Formular[1]);
		getChilds()[0] = form;
		getChilds()[0].setParent(this);
	}

	@Override
	public String toString() {
		return "(not " + getChilds()[0] + ")";
	}

}
