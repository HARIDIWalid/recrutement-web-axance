package fr.d2factory.libraryapp.exception;

/**
 * <b>Class HasLateBooksException</b> <br>
 * <br>
 * This exception is thrown when a member who owns late books tries to borrow another book
 * 
 * @author Walid Haridi (Sopra HR Software)
 */
public class HasLateBooksException extends RuntimeException {

	public HasLateBooksException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public HasLateBooksException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
}
