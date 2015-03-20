package ar.edu.design.examples.microprocesador.exceptions;

public class SystemException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SystemException(Throwable e) {
		super(e);
	}
	
	public SystemException(String msg, Throwable e) {
		super(msg, e);
	}
	
}
