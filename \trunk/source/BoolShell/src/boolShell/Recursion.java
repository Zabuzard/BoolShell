package boolShell;

import boolShell.formulars.And;
import boolShell.formulars.Not;
import boolShell.formulars.Or;
import boolShell.formulars.Reference;
import boolShell.formulars.Value;
import boolShell.formulars.Variable;
import boolShell.rules.RulesAnd;
import boolShell.rules.RulesNot;
import boolShell.rules.RulesOr;

/**
 * Contains all recursive methods Used by {@linkplain Commands}. Can substitute
 * a {@linkplain Variable} in a {@linkplain Formular} by another
 * {@linkplain Formular}, simplify a {@linkplain Formular} by using boolean
 * rules, create a copy of a {@linkplain Formular}
 * 
 * @author 1632700
 * 
 */
public final class Recursion {

	private Recursion() {
	}

	/**
	 * Substitutes the {@linkplain Variable} check in a {@linkplain Formular}
	 * form1 by another {@linkplain Formular} form2 by searching it recursive in
	 * a tree-structure
	 * 
	 * @param form1
	 *            {@linkplain Formular} in which substitution will happen
	 * @param check
	 *            {@linkplain Variable} to substitute
	 * @param form2
	 *            {@linkplain Formular} which will override the check-
	 *            {@linkplain Variable}
	 * @param left
	 *            Current location in the tree-structur, true for child[0] and
	 *            false for child[1]
	 * @return The substituted {@linkplain Formular}
	 */
	public static Formular subst(Formular form1, Variable check,
			Formular form2, boolean left) {

		// Solving all references to forms
		if (form1 instanceof Reference) {
			form1 = ((Reference) form1).getForm();
		}

		// Check for variable and value, they are the deepest elements
		// If variable check for substitution
		if (form1 instanceof Variable) {
			// Check if variable must be substituted
			if (((Variable) form1).getNumber() == check.getNumber()) {

				// Substitute by overriding parents child (self) with form2
				if (left) {
					form1.getParent().getChilds()[0] = form2;
				} else {
					form1.getParent().getChilds()[1] = form2;
				}
			}
			return form1;

			// If value, no substitution, return
		} else if (form1 instanceof Value) {
			return form1;

			// If formular has 2 childs, create new substitution first with the
			// right child
		} else if (form1.getChilds().length == 2) {
			subst(form1.getChilds()[1], check, form2, false);
		}

		// Formular has now at least 1 child, create new substitution now with
		// the left child
		subst(form1.getChilds()[0], check, form2, true);
		return form1;

	}

	/**
	 * Simplifies a given {@linkplain Formular} using boolean rules
	 * 
	 * @param form
	 *            {@linkplain Formular} which should be simplified
	 * @param left
	 *            Current location in the tree-structur, true for child[0] and
	 *            false for child[1]
	 * @return Simplified {@linkplain Formular}
	 */
	public static Formular simplify(Formular form, boolean left) {

		// Solving all references to forms
		if (form instanceof Reference) {
			form = ((Reference) form).getForm();
		}

		// Check for variable and value, they are the deepest elements
		// Return because they are simplified already
		if (form instanceof Variable || form instanceof Value) {
			return form;
			// If formular has 2 childs, simplify first the right child
		} else if (form.getChilds().length == 2) {
			simplify(form.getChilds()[1], false);
		}
		// Formular has now at least 1 child, simplify now the left child
		simplify(form.getChilds()[0], true);

		// Now begin simplifying the form-object
		// Code above will ensure that deepest form will be simplified first
		// Simplify with the correct rules
		if (form instanceof And) {
			form = RulesAnd.checkRules(form, left);
		} else if (form instanceof Not) {
			form = RulesNot.checkRules(form, left);
		} else if (form instanceof Or) {
			form = RulesOr.checkRules(form, left);
		}

		return form;

	}

	/**
	 * Creates a new structurally equal copy of a {@linkplain Formular}
	 * 
	 * @param form
	 *            {@linkplain Formular} which should be copied
	 * @return Copy of the {@linkplain Formular}
	 */
	public static Formular copyForm(Formular form) {

		// Solving all references to forms
		if (form instanceof Reference) {
			form = ((Reference) form).getForm();
		}

		// Check for the formular type and create a new formular for each with
		// the same attributes
		// Check for variable and value, they are the deepest elements
		if (form instanceof Variable) {
			form = new Variable(("v" + ((Variable) form).getNumber()));
			return form;

		} else if (form instanceof Value) {
			form = new Value(((Value) form).getValue());
			return form;

			// Start new copyForm for each element of these types
		} else if (form instanceof And) {
			form = new And(copyForm(form.getChilds()[0]),
					copyForm(form.getChilds()[1]));

		} else if (form instanceof Or) {
			form = new Or(copyForm(form.getChilds()[0]),
					copyForm(form.getChilds()[1]));

		} else if (form instanceof Not) {
			form = new Not(copyForm(form.getChilds()[0]));
		}

		return form;
	}

	/**
	 * Compares two {@linkplain Formular} with each other and returnes if they
	 * are equal
	 * 
	 * @param form1
	 *            First {@linkplain Formular} to compare with
	 * @param form2
	 *            Second {@linkplain Formular} to compare with
	 * @return true for equal, false for not equal
	 */
	public static boolean equalForms(Formular form1, Formular form2) {
		// Check for Nullpointer and no Forms
		if (form1 == null || form2 == null || !(form1 instanceof Formular)
				|| !(form2 instanceof Formular)) {
			return false;
		}

		// Now check each form specific
		// Check if both forms are AND
		// First compare the right childs, just if they are equal compare the
		// left childs
		if (form1 instanceof And && form2 instanceof And) {
			if (equalForms(form1.getChilds()[1], form2.getChilds()[1])) {
				return equalForms(form1.getChilds()[0], form2.getChilds()[0]);
			} else {
				return false;
			}

			// Check if both forms are NOT
			// Compare their child
		} else if (form1 instanceof Not && form2 instanceof Not) {
			return equalForms(form1.getChilds()[0], form2.getChilds()[0]);

			// Check if both forms are OR
			// First compare the right childs, just if they are equal compare
			// the left childs
		} else if (form1 instanceof Or && form2 instanceof Or) {
			if (equalForms(form1.getChilds()[1], form2.getChilds()[1])) {
				return equalForms(form1.getChilds()[0], form2.getChilds()[0]);
			} else {
				return false;
			}

			// Check if both forms are VALUE
			// Check if their values are equal
		} else if (form1 instanceof Value && form2 instanceof Value) {
			return ((Value) form1).getValue()
					.equals(((Value) form2).getValue());

			// Check if both forms are VARIABLE
			// Check if their numbers are equal
		} else if (form1 instanceof Variable && form2 instanceof Variable) {
			return ((Variable) form1).getNumber() == ((Variable) form2)
					.getNumber();

			// No known Formular
		} else {
			return false;
		}
	}
}
