package br.edu.crud.validator;

import java.util.Map;

import br.edu.crud.exception.ValidationException;
import br.edu.crud.util.Constants;

public class LoginValidator implements Validator {

	@Override
	public boolean validar(Map<String, Object> valores) throws ValidationException {
		String msgErro = "";
		for (String key : valores.keySet()) {
			String campo = (String) valores.get(key);
			if (campo == null)
				msgErro += Constants.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", key).concat("<br/>");
			else {
				switch (campo) {
				case "":
					msgErro += Constants.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", key).concat("<br/>");
					break;
				default:
					break;
				}
			}
		}

		if (!"".equals(msgErro))
			throw new ValidationException(msgErro);

		return true;
	}
}
