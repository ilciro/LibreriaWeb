package exceptions;

public class LibroNonTrovatoException extends Exception{

	private static final long serialVersionUID = 1L;



	public LibroNonTrovatoException (String message) {
		super(message);
	}
}
