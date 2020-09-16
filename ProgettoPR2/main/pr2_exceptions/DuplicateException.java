package pr2_exceptions;

/* Elemento già presente in bacheca */

public class DuplicateException extends Exception {
	public DuplicateException (String s) {
		super(s);
	}

}
