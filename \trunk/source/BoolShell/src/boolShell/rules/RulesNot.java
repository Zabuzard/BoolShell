package boolShell.rules;

import boolShell.Formular;
import boolShell.formulars.Not;
import boolShell.formulars.Value;

/**
 * Boolean rules for simplifying {@linkplain Not} formulars
 * 
 * @author 1632700
 * 
 */
public final class RulesNot {

	//Utility classes should not have a public or default constructor. 

	private RulesNot() {
		
	}

	/**
	 * Simplifies an {@linkplain Not} formular by using known rules
	 * 
	 * @param form
	 *            {@linkplain Not} formular to simplify
	 * @param left
	 *            Current location in the tree-structur, true for child[0] and
	 *            false for child[1]
	 * @return The simplified {@linkplain Formular}
	 */
	public static Formular checkRules(Formular form, boolean left) {

		// Check which rules must be used
		// If a rule does not change something the next rule must be used
		// Form indicates if rule was used
		// Because if a rule has changed something, it will change the form

		if (form instanceof Not && form.getChilds()[0] instanceof Value) {
			form = notValue(form, left);
		}

		if (form instanceof Not && form.getChilds()[0] instanceof Not) {
			form = notNot(form, left);
		}

		return form;

	}

	private static Formular notValue(Formular form, boolean left) {
		Value value;
		if (((Value) form.getChilds()[0]).getValue().equals("true")) {
			value = new Value("false");
		} else {
			value = new Value("true");
		}

		if (left) {
			if (form.getParent() != null) {
				form.getParent().getChilds()[0] = value;
			}
		} else {
			if (form.getParent() != null) {
				form.getParent().getChilds()[1] = value;
			}
		}

		return value;
	}

	private static Formular notNot(Formular form, boolean left) {

		if (left) {
			if (form.getParent() != null) {
				form.getParent().getChilds()[0] = form.getChilds()[0].getChilds()[0];
			}
		} else {
			if (form.getParent() != null) {
				form.getParent().getChilds()[1] = form.getChilds()[0].getChilds()[0];
			}
		}

		return form.getChilds()[0].getChilds()[0];
	}
}
