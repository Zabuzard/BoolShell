package boolShell.formulars;

import boolShell.Formular;

/**
 * Logical AND {@linkplain Formular}. Contains 2 child-formular
 * 
 * @author 1632700
 * 
 */
public class And extends Formular {

	/**
	 * Create an AND {@linkplain Formular} with 2 arguments as its
	 * child-formulars
	 * 
	 * @param arg
	 *            child-{@linkplain Formular}1 of AND as e.g. {@linkplain Value}
	 *            , {@linkplain Variable} or {@linkplain Reference}
	 * @param arg2
	 *            child-{@linkplain Formular}2 of AND as e.g. {@linkplain Value}
	 *            , {@linkplain Variable} or {@linkplain Reference}
	 */
	public And(String arg, String arg2) {
		this(validate(arg), validate(arg2));
	}

	/**
	 * Create an AND {@linkplain Formular} with 2 arguments as its
	 * child-formulars
	 * 
	 * @param form1
	 *            child-{@linkplain Formular}1 of AND as {@linkplain Formular}
	 * @param form2
	 *            child-{@linkplain Formular}2 of AND as {@linkplain Formular}
	 */
	public And(Formular form1, Formular form2) {
		setChilds(new Formular[2]);
		getChilds()[0] = form1;
		getChilds()[1] = form2;
		getChilds()[0].setParent(this);
		getChilds()[1].setParent(this);
	}

	@Override
	public String toString() {
		return "(" + getChilds()[0] + " and " + getChilds()[1] + ")";
	}

}
