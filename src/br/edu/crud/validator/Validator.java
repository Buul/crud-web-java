package br.edu.crud.validator;

import java.util.Map;

import br.edu.crud.exception.ValidationException;

public interface Validator {

	public boolean validar(Map<String, Object> valores) throws ValidationException;
}
