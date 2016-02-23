package boolShell.rules;

import boolShell.Formular;
import boolShell.Recursion;
import boolShell.formulars.And;
import boolShell.formulars.Not;
import boolShell.formulars.Value;

/**
 * Boolean rules for simplifying {@linkplain And} formulars
 * 
 * @author 1632700
 * 
 */
public final class RulesAnd {

	private RulesAnd() {
		
	}

	/**
	 * Simplifies an {@linkplain And} formular by using known rules
	 * 
	 * @param form
	 *            {@linkplain And} formular to simplify
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

		if (form instanceof And && form.getChilds()[0] instanceof Value) {
			form = valueAndForm(form, left);
		}

		if (form instanceof And && form.getChilds()[1] instanceof Value) {
			form = formAndValue(form, left);
		}

		if (form instanceof And && form.getChilds()[0] instanceof Not) {
			form = notFormAndForm(form, left);
		}

		if (form instanceof And && form.getChilds()[1] instanceof Not) {
			form = formAndNotForm(form, left);
		}

		if (form instanceof And) {
			form = formAndForm(form, left);
		}

		return form;

	}

	private static Formular valueAndForm(Formular form, boolean left) {
		int index;
		if (((Value) form.getChilds()[0]).getValue().equals("true")) {
			index = 1;
		} else {
			index = 0;
		}

		if (left) {
			if (form.getParent() != null) {
				form.getParent().getChilds()[0] = form.getChilds()[index];
			}
		} else {
			if (form.getParent() != null) {
				form.getParent().getChilds()[1] = form.getChilds()[index];
			}
		}
		return form.getChilds()[index];
	}

	private static Formular formAndValue(Formular form, boolean left) {
		int index;
		if (((Value) form.getChilds()[1]).getValue().equals("true")) {
			index = 0;
		} else {
			index = 1;
		}

		if (left) {
			if (form.getParent() != null) {
				form.getParent().getChilds()[0] = form.getChilds()[index];
			}
		} else {
			if (form.getParent() != null) {
				form.getParent().getChilds()[1] = form.getChilds()[index];
			}
		}
		return form.getChilds()[index];
	}

	private static Formular formAndNotForm(Formular form, boolean left) {
		if (Recursion.equalForms(form.getChilds()[0], form.getChilds()[1].getChilds()[0])) {
			Value value = new Value("false");

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
		} else {
			return form;
		}

	}

	private static Formular notFormAndForm(Formular form, boolean left) {
		if (Recursion.equalForms(form.getChilds()[0].getChilds()[0], form.getChilds()[1])) {
			Value value = new Value("false");

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
		} else {
			return form;
		}

	}

	private static Formular formAndForm(Formular form, boolean left) {
		if (Recursion.equalForms(form.getChilds()[0], form.getChilds()[1])) {
			if (left) {
				if (form.getParent() != null) {
					form.getParent().getChilds()[0] = form.getChilds()[0];
				}
			} else {
				if (form.getParent() != null) {
					form.getParent().getChilds()[1] = form.getChilds()[0];
				}
			}
			return form.getChilds()[0];
		} else {
			return form;
		}
	}
}
