package pr2_exceptions;

/* Friend non ha accesso al dato */

public class UnAuthorizedLikeException extends Exception {
	public UnAuthorizedLikeException (String s) {
		super(s);
	}
}
