package boolShell;

import java.util.ArrayList;
import java.util.List;

/**
 * Buffer of all saved {@linkplain Formular}. {@linkplain Formular} can be added
 * and removed. Can print the last saved {@linkplain Formular} which often
 * representates the last command of client
 * 
 * @author 1632700
 * 
 */
public class FormularBuffer {

	private List<Formular> list = new ArrayList<Formular>();

	/**
	 * Adds a {@linkplain Formular} to the buffer
	 * 
	 * @param form
	 *            {@linkplain Formular} which should be added
	 */
	public void add(Formular form) {
		list.add(form);
	}

	/**
	 * Removes a {@linkplain Formular} from the buffer
	 * 
	 * @param index
	 *            Index from {@linkplain Formular} which should be removed
	 */
	public void remove(int index) {
		list.remove(index);
	}

	/**
	 * Returns the {@linkplain Formular} which is represented by the index
	 * 
	 * @param index
	 *            Index of the {@linkplain Formular} which should be returned
	 * @return {@linkplain Formular} which is represented by the index
	 */
	public Formular get(int index) {
		return list.get(index);
	}

	/**
	 * Returns the size of the buffer as amount of saved {@linkplain Formular}
	 * 
	 * @return Size of buffer
	 */
	public int size() {
		return list.size();
	}

	/**
	 * Prints the last saved {@linkplain Formular} which often representates the
	 * last command of client
	 */
	public void printLast() {
		int index = size() - 1;
		Commons.OUT.println("#" + index + " = " + get(index));
	}
}
