package pr2_exceptions;

/* Il dato non � presente nella bacheca */

public class NoDataFoundException extends Exception {
	public NoDataFoundException (String s) {
		super(s);
	}

}
