package boolShell.formulars;

import boolShell.Formular;

/**
 * Logical OR {@linkplain Formular}. Contains 2 child-formular
 * 
 * @author 1632700
 * 
 */
public class Or extends Formular {

	/**
	 * Create an OR {@linkplain Formular} with 2 arguments as its
	 * child-formulars
	 * 
	 * @param arg
	 *            child-{@linkplain Formular}1 of OR as e.g. {@linkplain Value},
	 *            {@linkplain Variable} or {@linkplain Reference}
	 * @param arg2
	 *            child-{@linkplain Formular}2 of OR as e.g. {@linkplain Value},
	 *            {@linkplain Variable} or {@linkplain Reference}
	 */
	public Or(String arg, String arg2) {
		this(validate(arg), validate(arg2));
	}

	/**
	 * Create an OR {@linkplain Formular} with 2 arguments as its
	 * child-formulars
	 * 
	 * @param form1
	 *            child-{@linkplain Formular}1 of OR as {@linkplain Formular}
	 * @param form2
	 *            child-{@linkplain Formular}2 of OR as {@linkplain Formular}
	 */
	public Or(Formular form1, Formular form2) {
		setChilds(new Formular[2]);
		getChilds()[0] = form1;
		getChilds()[1] = form2;
		getChilds()[0].setParent(this);
		getChilds()[1].setParent(this);
	}

	@Override
	public String toString() {
		return "(" + getChilds()[0] + " or " + getChilds()[1] + ")";
	}

}
