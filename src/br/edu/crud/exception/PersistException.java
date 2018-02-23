package br.edu.crud.exception;

public class PersistException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public PersistException(String erro) {
		super(erro);
	}

	public PersistException(Exception e) {
		super(e);
	}
	
	public PersistException(String erro, Exception e) {
		super(erro, e);
	}
		
}
