package pr2_exceptions;

/* Permessi insufficienti */

public class InvalidPwdException extends Exception {
	public InvalidPwdException (String s) {
		super(s);
	}

}
