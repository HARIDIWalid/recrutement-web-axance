package fr.d2factory.libraryapp.exception;

/**
 * <b>Class ParamException</b> <br>
 * <br>
 * This exception is thrown when parameter has exception
 * 
 * @author Walid Haridi (Sopra HR Software)
 */
public class ParamException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ParamException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ParamException(String arg0) {
		super(arg0);
	}
	
}
