package boolShell.formulars;

import boolShell.BoolShell;
import boolShell.Commons;
import boolShell.Formular;
import boolShell.FormularBuffer;
import boolShell.SyntaxException;

/**
 * Represents an in the {@linkplain FormularBuffer} saved {@linkplain Formular}
 * by its index
 * 
 * @author 1632700
 * 
 */
public class Reference extends Formular {

	private int index;
	private FormularBuffer formBuff;

	/**
	 * Create a reference to an in the {@linkplain FormularBuffer} saved
	 * {@linkplain Formular}
	 * 
	 * @param arg
	 *            argument of the reference, contains the index of the
	 *            referenced {@linkplain Formular}
	 */
	public Reference(String arg) {
		int tempNumber = -1;
		try {
			// Try to parse the given number as index
			tempNumber = Integer.parseInt(arg.substring(1));
		} catch (NumberFormatException e) {
			// Thrown if number can not be read as valid Integer
			throw new SyntaxException(Commons.ERROR_MISC[3]);
		}
		// Set the almost valid index
		formBuff = BoolShell.getFormBuff();

		setIndex(tempNumber);
	}

	/**
	 * Returns the index of the {@linkplain Formular} in the
	 * {@linkplain FormularBuffer} which is representated by the reference
	 * 
	 * @return index of the {@linkplain Formular} in the
	 *         {@linkplain FormularBuffer} which is representated by the
	 *         reference
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets the index of the reference Only positive Integer values which can
	 * point on existing {@linkplain Formular} in the
	 * {@linkplain FormularBuffer} are valid
	 * 
	 * @param index
	 *            index of the reference
	 */
	public void setIndex(int index) {
		// Only an positive index which is lower than buffer size, so it can
		// point to an existing form, is valid
		if (index < 0 || index > formBuff.size() - 1) {
			throw new SyntaxException(Commons.ERROR_MISC[4]);
		}
		this.index = index;
	}

	/**
	 * Gets the {@linkplain Formular} which is representated by the reference
	 * 
	 * @return {@linkplain Formular} which is representated by the reference
	 */
	public Formular getForm() {
		return formBuff.get(index);
	}

	@Override
	public String toString() {
		return getForm().toString();
	}
}
